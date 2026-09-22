package com.animator70.icarus_mesh.wing;

// 我的类
import com.animator70.icarus_mesh.IcarusMesh;

// Minecraft 类
import net.minecraft.resources.ResourceLocation;

/**
 * 翅膀类型枚举（贴图路径）
 * 「贴图 + 颜色」的维度（8 个），模型是「形状」的维度（6 套），「机械」系列只是给同一个形状换了个金属质感的贴图。
 * 缩放、头部距离、间距等渲染参数不再硬编码在这里，统一由 config/icarus_mesh-client.toml 按类型配置。
 * WingType
 */
public enum WingType {
    // 1 羽翼 FeatheredWingsModel
    FEATHERED("feathered"),
    // 2 龙翼 LeatherWingsModel
    DRAGON("dragon"),
    // 3 机械羽翼 FeatheredWingsModel（同一套）
    MECHANICAL_FEATHERED("mechanical_feathered"),
    // 4 机械皮革翼 LeatherWingsModel（同一套）
    MECHANICAL_LEATHER("mechanical_leather"),
    // 5 光翼 LightWingsModel
    LIGHT("light"),
    // 6 芙兰 FlandresWingsModel
    FLANDRES("flandres"),
    // 7 Discord DiscordsWingsModel
    DISCORDS("discords"),
    // 8 Zanza ZanzasWingsModel
    ZANZAS("zanzas");

    // 翅膀纹理路径
    private final ResourceLocation textureLayer1;
    private final ResourceLocation textureLayer2;

    /**
     * 翅膀类型构造器
     * 
     * @param name 翅膀类型名称
     */
    WingType(String name) {
        this.textureLayer1 = IcarusMesh.id("textures/entity/" + name + "_wings.png");
        this.textureLayer2 = IcarusMesh.id("textures/entity/" + name + "_wings_2.png");
    }

    /**
     * 获取翅膀纹理路径
     * 
     * @return
     */
    public ResourceLocation getTextureLayer1() {
        return this.textureLayer1;
    }

    /**
     * 获取翅膀纹理路径
     * 
     * @return
     */
    public ResourceLocation getTextureLayer2() {
        return this.textureLayer2;
    }
}
