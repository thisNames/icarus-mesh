# Icarus Mesh

一个小型 Minecraft Forge 1.20.1 模组：通过指令或效果（MobEffect）在玩家背后显示各种翅膀，飞行时翅膀会扇动。

- **零外部依赖**：只依赖 Forge，不需要 Curios / Caelus 等前置模组
- **纯视觉**：翅膀只是装饰，不提供飞行能力、不消耗饥饿、不影响速度
- **83 种翅膀**：6 套模型 × 多种颜色变体
- **两种获取方式**：指令直接设置，或通过正面效果（Buff）自动获得

---

## 安装

模组需要**客户端和服务端同时安装**（双端）。

1. 运行 `gradlew.bat build` 构建
2. 将 `build/libs/icarus_mesh-1.0.0-1.20.1.jar` 放入 Minecraft 的 `mods/` 目录
3. 需要 Minecraft Forge **1.20.1**（版本 `47.x`）

---

## 指令

进入开启了作弊的世界，按 `T` 输入：

| 指令 | 作用 |
|---|---|
| `/wings set <翅膀id> [玩家]` | 给自己（或指定玩家）**入队**一个翅膀（先到先得，重复无效） |
| `/wings remove <翅膀id> [玩家]` | 按 id **出队**一个翅膀（对应 buff 到期 / 卸下装备） |
| `/wings clear [玩家]` | 清空翅膀队列（不写玩家则清空自己） |
| `/wings get [玩家]` | 查看当前显示的翅膀（队首）+ 完整队列 |
| `/wings list` | 列出全部可用翅膀 |

> 输入 `/wings set ` 后按 `Tab` 键可自动补全翅膀 id。
>
> 翅膀显示在玩家背后（第三人称视角或看其他玩家可见），玩家**鞘翅滑翔 / 创造飞行 / 飞行药水 / 龙戒指**飞行时翅膀自动扇动。

### 队列语义（先到先得）

翅膀采用「队列」结构，同一时刻只显示**队首**的那个翅膀：

1. **先入队者优先**：`/wings set A` 再 `/wings set B` → 显示 A（队首），B 排在后面
2. **重复无效**：再 `/wings set A` 会提示「已在队列中」，不会重复入队
3. **队首出队轮换**：`/wings remove A` 移除队首后，自动轮到 B 显示
4. **清空**：`/wings clear` 清空整个队列，翅膀全部消失

> 这套队列结构是为未来扩展准备的：以后做成药水 buff / 饰品 / 物品时，直接用「入队 / 出队」即可，同一套逻辑兼容多种来源。

---

## 翅膀效果（MobEffect）

每个翅膀都对应一个**正面效果（MobEffect）**，玩家拥有该效果即可自动获得对应翅膀，效果消失则翅膀自动移除。

- 效果 id 与翅膀 id 相同，命名空间为 `icarus_mesh`，共 **83 个**。
- 效果为**正面效果**（`BENEFICIAL`），状态栏显示中文名（如「白色羽翼」）和图标，粒子颜色 = 翅膀主色。

### 获取方式

```
/effect give @s icarus_mesh:white_feathered_wings 30 0
```

- **拥有效果** → 翅膀自动入队显示
- **效果到期 / 喝牛奶 / `/effect clear` / 死亡** → 翅膀自动出队消失

### 实现特性

- **事件驱动**：只在「获得 / 到期 / 移除」的瞬间触发入队/出队，效果持续期间每 tick 零开销。
- **复用队列**：效果和指令共用同一套翅膀队列，先入队者优先显示。

> 本模组只提供「效果」本身；药水、药水箭、食物等「施加效果的方式」留给其他模组、数据包或玩家自行添加。

---

## 翅膀结构

本模组有 3 个层级：**模型（形状）→ 种类（贴图）→ 条目（颜色变体）**。

| 层级 | 数量 | 说明 |
|---|---|---|
| 模型（3D 形状） | 6 套 | 羽翼、皮革、光翼、芙兰、Discord、Zanza |
| 种类（贴图） | 8 种 | 上面 6 种 + 机械羽翼、机械皮革翼（复用模型、换贴图） |
| 条目（可 `/wings set`） | 83 个 | 5 种可染色类型 × 16 色 + 3 独特 |

**8 个种类 → 6 套模型** 的映射：

| 种类（WingType） | 使用的模型 |
|---|---|
| FEATHERED（羽翼） | FeatheredWingsModel |
| MECHANICAL_FEATHERED（机械羽翼） | FeatheredWingsModel（同一套） |
| DRAGON（龙翼） | LeatherWingsModel |
| MECHANICAL_LEATHER（机械皮革翼） | LeatherWingsModel（同一套） |
| LIGHT（光翼） | LightWingsModel |
| FLANDRES（芙兰） | FlandresWingsModel |
| DISCORDS（Discord） | DiscordsWingsModel |
| ZANZAS（Zanza） | ZanzasWingsModel |

> 其中「机械羽翼 / 机械皮革翼」只是给「羽翼 / 龙翼」换了个金属质感的贴图，形状完全一样。

---

## 配置文件

翅膀的缩放、位置、间距等外观参数通过**全局配置文件**调整，无需改代码。

- **位置**：`config/icarus_mesh-common.toml`（全局一份，所有存档共享，非每存档）
- **类型**：COMMON（服务端权威，登录时自动同步给所有客户端）
- **生效**：改文件保存即**实时生效**（Forge 自动检测文件变化并重载，无需 `/reload` 或重进游戏）

### 配置结构

```toml
[general]
    globalScale = 1.0     # 全局缩放乘数（乘到每个翅膀上）
    offsetX = 0.0         # 全局左右偏移（格）
    offsetY = 0.0         # 全局上下偏移（格）
    offsetZ = 0.3         # 全局前后偏移（格）

[feathered]               # 羽翼
    scale = 1.0           # 缩放
    headDistance = 0.0    # 待机/飞行翅膀根高度
    crouchHeadDistance = -3.0  # 蹲下翅膀根高度
    wingSpacing = 3.0     # 两半翅膀间距

[dragon]                  # 龙翼（其余 6 个类型结构相同）
    ...
```

共 8 个类型分组：`feathered` / `dragon` / `mechanical_feathered` / `mechanical_leather` / `light` / `flandres` / `discords` / `zanzas`。

### 参数说明

| 分组 | 参数 | 默认 | 含义 |
|---|---|---|---|
| `[general]` | `globalScale` | `1.0` | 全局缩放乘数，乘到每个翅膀的 `scale` 上 |
| `[general]` | `offsetX` | `0.0` | 左右偏移（正数向右） |
| `[general]` | `offsetY` | `0.0` | 上下偏移（正数向上） |
| `[general]` | `offsetZ` | `0.3` | 前后偏移（正数离背部更远） |
| 每个类型 | `scale` | 见下表 | 该翅膀缩放（× 全局 `globalScale`） |
| 每个类型 | `headDistance` | `0.0` | 待机/飞行时翅膀根高度（越大约靠近头） |
| 每个类型 | `crouchHeadDistance` | `-3.0` | 蹲下时翅膀根高度 |
| 每个类型 | `wingSpacing` | `3.0` | 左右两半翅膀间距（越大两翼离得越远） |

### 各类型默认缩放

| 类型 | 默认 `scale` |
|---|---|
| feathered | `1.0` |
| dragon | `1.15` |
| mechanical_feathered | `1.0` |
| mechanical_leather | `1.15` |
| light | `0.85` |
| flandres | `1.0` |
| discords | `1.0` |
| zanzas | `1.0` |

> 缩放锚点在翅膀根部。建议范围 `0.5 ~ 2.0`（日常 `0.7 ~ 1.5` 最自然），超过 `3.0` 会明显异常。

### 全服同步机制

- **服务端 config 是权威值**：玩家登录时，服务端把配置快照发给客户端
- **客户端本地 config 不被修改**：同步值只存在内存里，不会覆盖客户端本地文件
- **实时更新**：改服务端 config 保存后，Forge 自动重载，已在线玩家立即看到新外观

## 颜色对照

| 英文（id 前缀） | 中文 | 颜色 |
|---|---|---|
| white | 白色 | `#F9FFFE` |
| orange | 橙色 | `#F9801D` |
| magenta | 品红 | `#C74EBD` |
| light_blue | 淡蓝 | `#3AB3DA` |
| yellow | 黄色 | `#FED83D` |
| lime | 黄绿 | `#80C71F` |
| pink | 粉红 | `#F38BAA` |
| gray | 灰色 | `#474F52` |
| light_gray | 淡灰 | `#9D9D97` |
| cyan | 青色 | `#169C9C` |
| purple | 紫色 | `#8932B8` |
| blue | 蓝色 | `#3C44AA` |
| brown | 棕色 | `#835432` |
| green | 绿色 | `#5E7C16` |
| red | 红色 | `#B02E26` |
| black | 黑色 | `#1D1D21` |

---

## 翅膀列表（83 种）

### 1. 羽翼（Feathered，16 色）

| 颜色 | 翅膀 id |
|---|---|
| 白色 | `white_feathered_wings` |
| 橙色 | `orange_feathered_wings` |
| 品红 | `magenta_feathered_wings` |
| 淡蓝 | `light_blue_feathered_wings` |
| 黄色 | `yellow_feathered_wings` |
| 黄绿 | `lime_feathered_wings` |
| 粉红 | `pink_feathered_wings` |
| 灰色 | `gray_feathered_wings` |
| 淡灰 | `light_gray_feathered_wings` |
| 青色 | `cyan_feathered_wings` |
| 紫色 | `purple_feathered_wings` |
| 蓝色 | `blue_feathered_wings` |
| 棕色 | `brown_feathered_wings` |
| 绿色 | `green_feathered_wings` |
| 红色 | `red_feathered_wings` |
| 黑色 | `black_feathered_wings` |

### 2. 龙翼（Dragon / 皮革，16 色）

| 颜色 | 翅膀 id |
|---|---|
| 白色 | `white_dragon_wings` |
| 橙色 | `orange_dragon_wings` |
| 品红 | `magenta_dragon_wings` |
| 淡蓝 | `light_blue_dragon_wings` |
| 黄色 | `yellow_dragon_wings` |
| 黄绿 | `lime_dragon_wings` |
| 粉红 | `pink_dragon_wings` |
| 灰色 | `gray_dragon_wings` |
| 淡灰 | `light_gray_dragon_wings` |
| 青色 | `cyan_dragon_wings` |
| 紫色 | `purple_dragon_wings` |
| 蓝色 | `blue_dragon_wings` |
| 棕色 | `brown_dragon_wings` |
| 绿色 | `green_dragon_wings` |
| 红色 | `red_dragon_wings` |
| 黑色 | `black_dragon_wings` |

### 3. 机械羽翼（Mechanical Feathered，16 色）

| 颜色 | 翅膀 id |
|---|---|
| 白色 | `white_mechanical_feathered_wings` |
| 橙色 | `orange_mechanical_feathered_wings` |
| 品红 | `magenta_mechanical_feathered_wings` |
| 淡蓝 | `light_blue_mechanical_feathered_wings` |
| 黄色 | `yellow_mechanical_feathered_wings` |
| 黄绿 | `lime_mechanical_feathered_wings` |
| 粉红 | `pink_mechanical_feathered_wings` |
| 灰色 | `gray_mechanical_feathered_wings` |
| 淡灰 | `light_gray_mechanical_feathered_wings` |
| 青色 | `cyan_mechanical_feathered_wings` |
| 紫色 | `purple_mechanical_feathered_wings` |
| 蓝色 | `blue_mechanical_feathered_wings` |
| 棕色 | `brown_mechanical_feathered_wings` |
| 绿色 | `green_mechanical_feathered_wings` |
| 红色 | `red_mechanical_feathered_wings` |
| 黑色 | `black_mechanical_feathered_wings` |

### 4. 机械皮革翼（Mechanical Leather，16 色）

| 颜色 | 翅膀 id |
|---|---|
| 白色 | `white_mechanical_leather_wings` |
| 橙色 | `orange_mechanical_leather_wings` |
| 品红 | `magenta_mechanical_leather_wings` |
| 淡蓝 | `light_blue_mechanical_leather_wings` |
| 黄色 | `yellow_mechanical_leather_wings` |
| 黄绿 | `lime_mechanical_leather_wings` |
| 粉红 | `pink_mechanical_leather_wings` |
| 灰色 | `gray_mechanical_leather_wings` |
| 淡灰 | `light_gray_mechanical_leather_wings` |
| 青色 | `cyan_mechanical_leather_wings` |
| 紫色 | `purple_mechanical_leather_wings` |
| 蓝色 | `blue_mechanical_leather_wings` |
| 棕色 | `brown_mechanical_leather_wings` |
| 绿色 | `green_mechanical_leather_wings` |
| 红色 | `red_mechanical_leather_wings` |
| 黑色 | `black_mechanical_leather_wings` |

### 5. 光翼（Light，16 色）

| 颜色 | 翅膀 id |
|---|---|
| 白色 | `white_light_wings` |
| 橙色 | `orange_light_wings` |
| 品红 | `magenta_light_wings` |
| 淡蓝 | `light_blue_light_wings` |
| 黄色 | `yellow_light_wings` |
| 黄绿 | `lime_light_wings` |
| 粉红 | `pink_light_wings` |
| 灰色 | `gray_light_wings` |
| 淡灰 | `light_gray_light_wings` |
| 青色 | `cyan_light_wings` |
| 紫色 | `purple_light_wings` |
| 蓝色 | `blue_light_wings` |
| 棕色 | `brown_light_wings` |
| 绿色 | `green_light_wings` |
| 红色 | `red_light_wings` |
| 黑色 | `black_light_wings` |

### 6. 独特翅膀（Unique，3 种）

| 名称 | 翅膀 id | 说明 |
|---|---|---|
| 芙兰之翼 | `flandres_wings` | 参考《东方 Project》芙兰朵露，黑色枯枝挂七彩水晶 |
| Discord 之翼 | `discords_wings` | 参考《小马宝莉》Discord，不对称混沌风格 |
| Zanza 之翼 | `zanzas_wings` | 参考《异度神剑》Zanza，几何圆环造型 |

---

## 使用示例

```
/wings set white_feathered_wings        # 给自己白色羽翼
/wings set red_dragon_wings             # 给自己红色龙翼
/wings set flandres_wings               # 给自己芙兰之翼
/wings set blue_light_wings Steve       # 给 Steve 蓝色光翼
/wings clear                            # 清除自己的翅膀
```

---

## 附注

- 翅膀队列会**持久化**：退出游戏重新进入后，队列顺序会完整恢复（配合 buff 等效果使用时状态不丢失）。
- 玩家**死亡重生**后翅膀消失（死亡时队列清空，不跨死亡保留）。
- 玩家**切换维度**后翅膀会重新同步渲染（避免维度切换后翅膀不显示）。
- 翅膀缩放、位置、间距等参数通过配置文件 `config/icarus_mesh-common.toml` 调整（详见「配置文件」章节），无需改代码。
