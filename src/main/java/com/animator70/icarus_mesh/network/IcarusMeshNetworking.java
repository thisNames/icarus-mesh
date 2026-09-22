package com.animator70.icarus_mesh.network;

// 我的类
import com.animator70.icarus_mesh.IcarusMesh;

// Minecraft 类
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;

// Forge 类
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;

/**
 * 网络通道
 * IcarusMeshNetworking
 */
public class IcarusMeshNetworking {
    // 网络协议版本号
    private static final String PROTOCOL_VERSION = "1";
    // 网络包 ID
    private static int id = 0;

    /**
     * 网络通道：版本号「1」用于服务端/客户端握手校验，不一致则拒绝通信
     */
    @SuppressWarnings("removal")
    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(IcarusMesh.MODID, "main"),
            () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals);

    public static void init() {
        CHANNEL.registerMessage(id++, SetWingsPacket.class, SetWingsPacket::encode, SetWingsPacket::decode,
                SetWingsPacket::handle);

        CHANNEL.registerMessage(id++, SyncWingsConfigPacket.class, SyncWingsConfigPacket::encode,
                SyncWingsConfigPacket::decode, SyncWingsConfigPacket::handle);
    }

    /**
     * 广播给服务器所有玩家
     */
    public static void sendToAll(SetWingsPacket packet) {
        CHANNEL.send(PacketDistributor.ALL.noArg(), packet);
    }

    /**
     * 发送给指定玩家
     */
    public static void sendToPlayer(SetWingsPacket packet, ServerPlayer player) {
        CHANNEL.send(PacketDistributor.PLAYER.with(() -> player), packet);
    }

    /**
     * 发送渲染配置快照给指定玩家
     */
    public static void sendConfigToPlayer(SyncWingsConfigPacket packet, ServerPlayer player) {
        CHANNEL.send(PacketDistributor.PLAYER.with(() -> player), packet);
    }

    /**
     * 发送给追踪该实体的所有玩家 + 实体自己（用于设置翅膀时同步周围人）
     */
    public static void sendToTrackingAndSelf(SetWingsPacket packet, Entity entity) {
        CHANNEL.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> entity), packet);
    }
}
