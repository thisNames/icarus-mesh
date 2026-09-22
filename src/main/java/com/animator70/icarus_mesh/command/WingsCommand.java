package com.animator70.icarus_mesh.command;

// 我的类
import com.animator70.icarus_mesh.capability.WingsCapability;
import com.animator70.icarus_mesh.init.WingsRegistry;
import com.animator70.icarus_mesh.network.IcarusMeshNetworking;
import com.animator70.icarus_mesh.network.SetWingsPacket;
import com.animator70.icarus_mesh.wing.WingDefinition;

// M 类
import com.mojang.brigadier.arguments.StringArgumentType;

// Minecraft 类
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.SharedSuggestionProvider;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;

// Forge 类
import net.minecraftforge.event.RegisterCommandsEvent;

/**
 * 翅膀命令
 * WingsCommand
 */
public class WingsCommand {
    /**
     * 注册指令
     */
    public static void register(RegisterCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("wings")
                .requires(source -> source.hasPermission(2))

                // 入队
                .then(Commands.literal("set")
                        .then(Commands.argument("wing", StringArgumentType.word())
                                .suggests((ctx, builder) -> SharedSuggestionProvider
                                        .suggest(WingsRegistry.all().stream().map(WingDefinition::id), builder))
                                .executes(ctx -> setWing(
                                        ctx.getSource(),
                                        ctx.getSource().getPlayerOrException(),
                                        StringArgumentType.getString(ctx, "wing")))

                                .then(Commands.argument("target", EntityArgument.player())
                                        .executes(ctx -> setWing(
                                                ctx.getSource(),
                                                EntityArgument.getPlayer(ctx, "target"),
                                                StringArgumentType.getString(ctx, "wing"))))))

                // 清空队列
                .then(Commands.literal("clear")
                        .executes(ctx -> clearWing(
                                ctx.getSource(),
                                ctx.getSource().getPlayerOrException()))

                        .then(Commands.argument("target", EntityArgument.player())
                                .executes(ctx -> clearWing(
                                        ctx.getSource(),
                                        EntityArgument.getPlayer(ctx, "target")))))

                // 出列
                .then(Commands.literal("remove")
                        .then(Commands.argument("wing", StringArgumentType.word())
                                .suggests((ctx, builder) -> SharedSuggestionProvider
                                        .suggest(WingsRegistry.all().stream().map(WingDefinition::id), builder))
                                .executes(ctx -> removeWing(
                                        ctx.getSource(),
                                        ctx.getSource().getPlayerOrException(),
                                        StringArgumentType.getString(ctx, "wing")))

                                .then(Commands.argument("target", EntityArgument.player())
                                        .executes(ctx -> removeWing(
                                                ctx.getSource(),
                                                EntityArgument.getPlayer(ctx, "target"),
                                                StringArgumentType.getString(ctx, "wing"))))))

                // 获取首列
                .then(Commands.literal("get")
                        .executes(ctx -> getWing(
                                ctx.getSource(),
                                ctx.getSource().getPlayerOrException()))

                        .then(Commands.argument("target", EntityArgument.player())
                                .executes(ctx -> getWing(
                                        ctx.getSource(),
                                        EntityArgument.getPlayer(ctx, "target")))))

                // 列出所有可用翅膀
                .then(Commands.literal("list")
                        .executes(ctx -> listWings(ctx.getSource()))));
    }

    /**
     * 设置玩家的翅膀（指令调用）
     */
    private static int setWing(CommandSourceStack source, ServerPlayer target, String wingId) {
        if (!WingsRegistry.contains(wingId)) {
            source.sendFailure(Component.literal("未知的翅膀: " + wingId));
            return 0;
        }

        // 入队（重复无效），并同步完整队列 + 持久化
        WingsCapability.get(target).ifPresent(cap -> {
            boolean added = cap.addWing(wingId);

            WingsCapability.syncPersistentData(target);
            IcarusMeshNetworking.sendToTrackingAndSelf(new SetWingsPacket(target.getId(), cap.getWingQueue()), target);

            if (added) {
                source.sendSuccess(() -> Component.literal("已为 " + target.getName().getString() + " 添加翅膀 " + wingId),
                        true);
            } else {
                source.sendSuccess(() -> Component.literal("翅膀 " + wingId + " 已在队列中，无需重复添加"), true);
            }
        });

        return 1;
    }

    /**
     * 清空玩家的翅膀（指令调用）
     */
    private static int clearWing(CommandSourceStack source, ServerPlayer target) {
        // 清空整个队列，并同步 + 持久化
        WingsCapability.get(target).ifPresent(cap -> {
            cap.clearWings();
            WingsCapability.syncPersistentData(target);
            IcarusMeshNetworking.sendToTrackingAndSelf(new SetWingsPacket(target.getId(), cap.getWingQueue()), target);
        });

        source.sendSuccess(() -> Component.literal("已清除 " + target.getName().getString() + " 的所有翅膀"), true);

        return 1;
    }

    /**
     * 移除玩家的翅膀（指令调用）
     */
    private static int removeWing(CommandSourceStack source, ServerPlayer target, String wingId) {
        // 按 id 出队，并同步完整队列 + 持久化
        WingsCapability.get(target).ifPresent(cap -> {
            boolean removed = cap.removeWing(wingId);
            WingsCapability.syncPersistentData(target);
            IcarusMeshNetworking.sendToTrackingAndSelf(new SetWingsPacket(target.getId(), cap.getWingQueue()), target);

            if (removed) {
                source.sendSuccess(() -> Component.literal("已从 " +
                        target.getName().getString() +
                        " 的队列移除翅膀 " +
                        wingId), true);
            } else {
                source.sendSuccess(() -> Component.literal("翅膀 " +
                        wingId +
                        " 不在队列中"), true);
            }
        });

        return 1;
    }

    /**
     * 获取玩家当前的翅膀（指令调用）
     */
    private static int getWing(CommandSourceStack source, ServerPlayer target) {
        // 取队首（当前显示的翅膀），并展示完整队列
        WingsCapability.get(target).ifPresent(cap -> {
            String current = cap.getCurrentWingId();

            if (current.isEmpty()) {
                source.sendSuccess(
                        () -> Component.literal(target.getName().getString() + " 当前没有翅膀"), false);
            } else {
                source.sendSuccess(
                        () -> Component.literal(target.getName().getString() +
                                " 当前翅膀: "
                                + current
                                + "（队列: "
                                + String.join(" > ", cap.getWingQueue()) + "）"),
                        false);
            }
        });

        return 1;
    }

    /**
     * 显示所有可用翅膀（指令调用）
     */
    private static int listWings(CommandSourceStack source) {
        String all = String.join(", ", WingsRegistry.all().stream().map(WingDefinition::id).toList());

        source.sendSuccess(() -> Component.literal("可用翅膀: " + all), false);

        return 1;
    }
}
