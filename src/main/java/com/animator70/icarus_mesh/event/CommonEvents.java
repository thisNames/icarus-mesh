package com.animator70.icarus_mesh.event;

// 我的类
import com.animator70.icarus_mesh.IcarusMesh;
import com.animator70.icarus_mesh.capability.WingsCapability;
import com.animator70.icarus_mesh.command.WingsCommand;
import com.animator70.icarus_mesh.network.IcarusMeshNetworking;
import com.animator70.icarus_mesh.network.SetWingsPacket;

// Minecraft 类
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

// Forge 类
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

/**
 * 事件
 * CommonEvents
 */
@Mod.EventBusSubscriber(modid = IcarusMesh.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CommonEvents {

    @SubscribeEvent
    public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event) {
        WingsCapability.attach(event);
    }

    @SubscribeEvent
    public static void onRegisterCommands(RegisterCommandsEvent event) {
        WingsCommand.register(event);
    }

    /**
     * 玩家登录时做双向同步：把该玩家翅膀广播给所有人，再把其他人的翅膀补发给该玩家
     * 数据包是「即时推送」，后进服的人收不到历史数据，所以登录时需要补发
     */
    @SubscribeEvent
    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
        ServerPlayer player = (ServerPlayer) event.getEntity();

        // 将新登录玩家自己的翅膀同步给所有追踪者（含自身）
        WingsCapability.get(player).ifPresent(cap -> {
            if (cap.hasWings()) {
                IcarusMeshNetworking.sendToAll(new SetWingsPacket(player.getId(), cap.getWingId()));
            }
        });

        // 将其他玩家的翅膀同步给新登录玩家
        for (ServerPlayer other : player.server.getPlayerList().getPlayers()) {
            if (other == player) {
                continue;
            }

            WingsCapability.get(other).ifPresent(cap -> {
                if (cap.hasWings()) {
                    IcarusMeshNetworking.sendToPlayer(new SetWingsPacket(other.getId(), cap.getWingId()), player);
                }
            });
        }
    }

    /**
     * 有实体进入玩家视距时，把目标玩家（若带翅膀）的翅膀补发给该玩家。
     */
    @SubscribeEvent
    public static void onStartTracking(PlayerEvent.StartTracking event) {
        Entity target = event.getTarget();

        if (target instanceof Player) {
            WingsCapability.get(target).ifPresent(cap -> {
                if (cap.hasWings()) {
                    IcarusMeshNetworking.sendToPlayer(new SetWingsPacket(target.getId(), cap.getWingId()),
                            (ServerPlayer) event.getEntity());
                }
            });
        }
    }
}
