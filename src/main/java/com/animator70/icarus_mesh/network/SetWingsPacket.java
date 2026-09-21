package com.animator70.icarus_mesh.network;

// 我的类
import com.animator70.icarus_mesh.capability.WingsCapability;

// Minecraft 类
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;

// Forge 类
import net.minecraftforge.network.NetworkEvent;

// Java 类
import java.util.function.Supplier;

/**
 * 设置翅膀的网络包
 * SetWingsPacket
 */
public class SetWingsPacket {
    // 实体 id
    private final int entityId;
    // 翅膀 id
    private final String wingId;

    /**
     * 设置翅膀的网络包
     * 
     * @param entityId 实体 id
     * @param wingId   翅膀 id
     */
    public SetWingsPacket(int entityId, String wingId) {
        this.entityId = entityId;
        this.wingId = wingId;
    }

    /**
     * 编码/解码
     * 
     * @param msg 消息
     * @param buf 缓冲区
     */
    public static void encode(SetWingsPacket msg, FriendlyByteBuf buf) {
        buf.writeInt(msg.entityId);
        buf.writeUtf(msg.wingId);
    }

    /**
     * 解码
     * 
     * @param buf 缓冲区
     * @return
     */
    public static SetWingsPacket decode(FriendlyByteBuf buf) {
        return new SetWingsPacket(buf.readInt(), buf.readUtf());
    }

    /**
     * 在客户端接收：按实体 id 找到实体，把翅膀 id 写入其 Capability（供渲染层读取）
     * enqueueWork 会把任务切回主线程执行；多次判空避免实体未加载时崩溃
     */
    public static void handle(SetWingsPacket msg, Supplier<NetworkEvent.Context> ctx) {
        NetworkEvent.Context context = ctx.get();

        context.enqueueWork(() -> {
            if (context.getDirection().getReceptionSide().isClient()) {
                Minecraft mc = Minecraft.getInstance();

                if (mc.level != null) {
                    Entity entity = mc.level.getEntity(msg.entityId);

                    if (entity != null) {
                        entity.getCapability(WingsCapability.CAPABILITY).ifPresent(cap -> cap.setWingId(msg.wingId));
                    }
                }
            }
        });

        context.setPacketHandled(true);
    }
}
