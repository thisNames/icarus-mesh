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
    // 翅膀类型枚举
    FEATHERED("feathered"),
    DRAGON("dragon"),
    MECHANICAL_FEATHERED("mechanical_feathered"),
    MECHANICAL_LEATHER("mechanical_leather"),
    LIGHT("light"),
    FLANDRES("flandres"),
    DISCORDS("discords"),
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
