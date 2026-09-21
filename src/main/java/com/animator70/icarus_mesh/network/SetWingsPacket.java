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
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * 同步翅膀队列的网络包（服务端 → 客户端）。
 * 客户端收到后，把实体的翅膀队列整体替换为该队列。
 */
public class SetWingsPacket {
    // 实体 id
    private final int entityId;
    // 翅膀队列（队首在前）
    private final List<String> wingQueue;

    /**
     * @param entityId  实体 id
     * @param wingQueue 翅膀队列（队首在前）
     */
    public SetWingsPacket(int entityId, List<String> wingQueue) {
        this.entityId = entityId;
        this.wingQueue = wingQueue;
    }

    public static void encode(SetWingsPacket msg, FriendlyByteBuf buf) {
        buf.writeInt(msg.entityId);
        buf.writeCollection(msg.wingQueue, FriendlyByteBuf::writeUtf);
    }

    public static SetWingsPacket decode(FriendlyByteBuf buf) {
        int entityId = buf.readInt();
        List<String> queue = buf.readCollection(ArrayList::new, FriendlyByteBuf::readUtf);

        return new SetWingsPacket(entityId, queue);
    }

    /**
     * 在客户端接收：按实体 id 找到实体，把翅膀队列写入其 Capability（供渲染层读取）。
     * enqueueWork 会把任务切回主线程执行；多次判空避免实体未加载时崩溃。
     */
    public static void handle(SetWingsPacket msg, Supplier<NetworkEvent.Context> ctx) {
        NetworkEvent.Context context = ctx.get();

        context.enqueueWork(() -> {
            if (context.getDirection().getReceptionSide().isClient()) {
                Minecraft mc = Minecraft.getInstance();

                if (mc.level != null) {
                    Entity entity = mc.level.getEntity(msg.entityId);

                    if (entity != null) {
                        entity.getCapability(WingsCapability.CAPABILITY)
                                .ifPresent(cap -> cap.setWingQueue(msg.wingQueue));
                    }
                }
            }
        });

        context.setPacketHandled(true);
    }
}
