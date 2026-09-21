package com.animator70.icarus_mesh.client;

// 我的类
import com.animator70.icarus_mesh.IcarusMesh;

// Minecraft 类
import net.minecraft.client.model.geom.ModelLayerLocation;

/**
 * 翅膀模型
 * 模型层的 ModelLayerLocation 注册
 * IcarusMeshModels
 */
public class IcarusMeshModels {
    public static final ModelLayerLocation FEATHERED = new ModelLayerLocation(IcarusMesh.id("feathered"), "main");
    public static final ModelLayerLocation LEATHER = new ModelLayerLocation(IcarusMesh.id("leather"), "main");
    public static final ModelLayerLocation LIGHT = new ModelLayerLocation(IcarusMesh.id("light"), "main");
    public static final ModelLayerLocation FLANDRES = new ModelLayerLocation(IcarusMesh.id("flandres"), "main");
    public static final ModelLayerLocation DISCORDS = new ModelLayerLocation(IcarusMesh.id("discords"), "main");
    public static final ModelLayerLocation ZANZAS = new ModelLayerLocation(IcarusMesh.id("zanzas"), "main");
}
