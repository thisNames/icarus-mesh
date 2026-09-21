package com.animator70.icarus_mesh.wing;

// 我的类
import com.animator70.icarus_mesh.IcarusMesh;

// Minecraft 类
import net.minecraft.resources.ResourceLocation;

/**
 * 翅膀类型
 * WingType
 */
public enum WingType {
    // 翅膀类型枚举（第二个参数为渲染缩放：龙翼偏大、光翼偏小，1.0 为默认大小）
    FEATHERED("feathered", 1.0F),
    DRAGON("dragon", 1.25F),
    MECHANICAL_FEATHERED("mechanical_feathered", 1.0F),
    MECHANICAL_LEATHER("mechanical_leather", 1.15F),
    LIGHT("light", 0.85F),
    FLANDRES("flandres", 1.0F),
    DISCORDS("discords", 1.0F),
    ZANZAS("zanzas", 1.0F);

    // 翅膀纹理路径
    private final ResourceLocation textureLayer1;
    private final ResourceLocation textureLayer2;

    // 翅膀渲染缩放（1.0 = 默认大小）
    private final float scale;

    /**
     * 翅膀类型构造器
     * 
     * @param name  翅膀类型名称
     * @param scale 翅膀渲染缩放（1.0 = 默认大小）
     */
    WingType(String name, float scale) {
        this.textureLayer1 = IcarusMesh.id("textures/entity/" + name + "_wings.png");
        this.textureLayer2 = IcarusMesh.id("textures/entity/" + name + "_wings_2.png");

        this.scale = scale;
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

    /**
     * 获取翅膀渲染缩放
     * 
     * @return 缩放值（1.0 = 默认大小）
     */
    public float getScale() {
        return this.scale;
    }
}
