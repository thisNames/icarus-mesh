package com.animator70.icarus_mesh.event;

// 我的类
import com.animator70.icarus_mesh.IcarusMesh;
import com.animator70.icarus_mesh.config.WingsConfig;
import com.animator70.icarus_mesh.config.WingsRenderConfig;
import com.animator70.icarus_mesh.network.IcarusMeshNetworking;
import com.animator70.icarus_mesh.network.SyncWingsConfigPacket;

// Minecraft 类
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;

// Forge 类
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.server.ServerLifecycleHooks;

/**
 * 配置相关事件（MOD bus，两端注册）。
 * ConfigEvents
 */
@Mod.EventBusSubscriber(modid = IcarusMesh.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ConfigEvents {
    /**
     * 配置重载（如 /reload）后，把新的渲染参数同步给所有在线玩家。
     * 只在服务端（含单机 integrated server）执行；纯客户端 getCurrentServer() 为 null 时跳过。
     */
    @SubscribeEvent
    public static void onConfigReloading(ModConfigEvent.Reloading event) {
        ModConfig config = event.getConfig();

        if (config.getSpec() != WingsConfig.SPEC) {
            return;
        }

        MinecraftServer server = ServerLifecycleHooks.getCurrentServer();
        if (server == null || !server.isRunning()) {
            return;
        }

        WingsRenderConfig snapshot = WingsRenderConfig.snapshotFromConfig();
        SyncWingsConfigPacket packet = new SyncWingsConfigPacket(snapshot.toArray());

        for (ServerPlayer player : server.getPlayerList().getPlayers()) {
            IcarusMeshNetworking.sendConfigToPlayer(packet, player);
        }
    }
}
