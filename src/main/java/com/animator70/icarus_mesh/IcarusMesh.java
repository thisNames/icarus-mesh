package com.animator70.icarus_mesh;

// 我的类
import com.animator70.icarus_mesh.config.WingsConfig;
import com.animator70.icarus_mesh.init.WingEffects;
import com.animator70.icarus_mesh.init.WingsRegistry;
import com.animator70.icarus_mesh.network.IcarusMeshNetworking;

// Minecraft 类
import net.minecraft.resources.ResourceLocation;

// Forge 类
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
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
    @SuppressWarnings("removal")
    public IcarusMesh(FMLJavaModLoadingContext context) {
        // 初始化翅膀注册表、效果注册表与网络通道
        WingsRegistry.init();
        WingEffects.init(context.getModEventBus());
        IcarusMeshNetworking.init();
        // 注册渲染配置（COMMON 类型，全局 config/ 目录，非每存档；值由登录时手动同步给客户端）
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, WingsConfig.SPEC);
    }

    // 资源路径构造器
    @SuppressWarnings("removal")
    public static ResourceLocation id(String path) {
        // 将路径拼接到模组 ID 前缀
        return new ResourceLocation(MODID, path);
    }
}
