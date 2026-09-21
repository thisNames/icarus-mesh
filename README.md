# Icarus Mesh

一个小型 Minecraft Forge 1.20.1 模组：通过指令在玩家背后显示各种翅膀，飞行时翅膀会扇动。

- **零外部依赖**：只依赖 Forge，不需要 Curios / Caelus 等前置模组
- **纯视觉**：翅膀只是装饰，不提供飞行能力、不消耗饥饿、不影响速度
- **83 种翅膀**：6 套模型 × 多种颜色变体

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
| `/wings set <翅膀id>` | 给自己显示指定翅膀 |
| `/wings set <翅膀id> <玩家>` | 给指定玩家显示翅膀 |
| `/wings clear [玩家]` | 清除翅膀（不写玩家则清除自己） |
| `/wings list` | 列出全部可用翅膀 |

> 输入 `/wings set ` 后按 `Tab` 键可自动补全翅膀 id。
>
> 翅膀显示在玩家背后（第三人称视角或看其他玩家可见），玩家**鞘翅滑翔 / 创造飞行 / 飞行药水 / 龙戒指**飞行时翅膀自动扇动。

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

## 翅膀缩放值

| 翅膀类型 | 缩放值 | 相对大小 |
|---|---|---|
| 羽翼 / 机械羽翼 | `1.0` | 默认 |
| 龙翼 / 机械皮革翼 | `1.15` | 偏大 15% |
| 光翼 | `0.85` | 偏小 15% |
| 芙兰 / Discord / Zanza | `1.0` | 默认 |

> 缩放锚点在翅膀根部。建议范围 `0.5 ~ 2.0`（日常 `0.7 ~ 1.5` 最自然），超过 `3.0` 会明显异常，超过 `5.0` 基本飞出视野。

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

- 翅膀为**临时装饰**，玩家死亡或重新进入世界后消失（设计如此，便于后续做成 buff 效果）。
- 翅膀缩放、位置等参数在源码 `WingType.java` 和 `WingEntityModel.java` 中，可按需调整。
