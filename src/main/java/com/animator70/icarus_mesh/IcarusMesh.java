package com.animator70.icarus_mesh;

// 我的类
import com.animator70.icarus_mesh.init.WingsRegistry;
import com.animator70.icarus_mesh.network.IcarusMeshNetworking;

// Minecraft 类
import net.minecraft.resources.ResourceLocation;

// Forge 类
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

/**
 * 模组入口
 * IcarusMesh
 */
@Mod(IcarusMesh.MODID)
public class IcarusMesh {
    // 模组 ID
    public static final String MODID = "icarus_mesh";

    // 构造器
    public IcarusMesh(FMLJavaModLoadingContext context) {
        // 初始化翅膀注册表与网络通道
        WingsRegistry.init();
        IcarusMeshNetworking.init();
    }

    // 资源路径构造器
    public static ResourceLocation id(String path) {
        // 将路径拼接到模组 ID 前缀
        return new ResourceLocation(MODID, path);
    }
}
