package com.animator70.icarus_mesh.network;

// 我的类
import com.animator70.icarus_mesh.client.ClientWingsConfig;
import com.animator70.icarus_mesh.config.WingsRenderConfig;

// Minecraft 类
import net.minecraft.network.FriendlyByteBuf;

// Forge 类
import net.minecraftforge.network.NetworkEvent;

// Java 类
import java.util.function.Supplier;

/**
 * 渲染配置同步包（服务端 → 客户端）。
 * 服务端在玩家登录时把全局 COMMON 配置的渲染参数快照发给客户端，客户端缓存后用于渲染，实现全服统一。
 */
public class SyncWingsConfigPacket {
    private final double[] values;

    public SyncWingsConfigPacket(double[] values) {
        this.values = values;
    }

    public static void encode(SyncWingsConfigPacket msg, FriendlyByteBuf buf) {
        buf.writeVarInt(msg.values.length);

        for (double value : msg.values) {
            buf.writeDouble(value);
        }
    }

    public static SyncWingsConfigPacket decode(FriendlyByteBuf buf) {
        int length = buf.readVarInt();
        double[] values = new double[length];

        for (int i = 0; i < length; i++) {
            values[i] = buf.readDouble();
        }

        return new SyncWingsConfigPacket(values);
    }

    public static void handle(SyncWingsConfigPacket msg, Supplier<NetworkEvent.Context> ctx) {
        NetworkEvent.Context context = ctx.get();

        context.enqueueWork(() -> {
            if (context.getDirection().getReceptionSide().isClient()) {
                ClientWingsConfig.apply(new WingsRenderConfig(msg.values));
            }
        });

        context.setPacketHandled(true);
    }
}
