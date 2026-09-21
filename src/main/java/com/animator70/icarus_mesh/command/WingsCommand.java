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
    public static void register(RegisterCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("wings")
                .requires(source -> source.hasPermission(2))
                .then(Commands.literal("set")

                        .then(Commands.argument("wing", StringArgumentType.word())
                                .suggests((ctx, builder) -> SharedSuggestionProvider
                                        .suggest(WingsRegistry.all().stream().map(WingDefinition::id), builder))
                                .executes(
                                        ctx -> setWing(ctx.getSource(), ctx.getSource().getPlayerOrException(),
                                                StringArgumentType.getString(ctx, "wing")))

                                .then(Commands.argument("target", EntityArgument.player())
                                        .executes(
                                                ctx -> setWing(ctx.getSource(), EntityArgument.getPlayer(ctx, "target"),
                                                        StringArgumentType.getString(ctx, "wing"))))))

                .then(Commands.literal("clear")
                        .executes(ctx -> clearWing(ctx.getSource(), ctx.getSource().getPlayerOrException()))
                        .then(Commands.argument("target", EntityArgument.player())
                                .executes(
                                        ctx -> clearWing(ctx.getSource(),
                                                EntityArgument.getPlayer(ctx, "target")))))

                .then(Commands.literal("list")
                        .executes(ctx -> listWings(ctx.getSource()))));
    }

    private static int setWing(CommandSourceStack source, ServerPlayer target, String wingId) {
        if (!WingsRegistry.contains(wingId)) {
            source.sendFailure(Component.literal("未知的翅膀: " + wingId));
            return 0;
        }

        WingsCapability.get(target).ifPresent(cap -> cap.setWingId(wingId));
        IcarusMeshNetworking.sendToTrackingAndSelf(new SetWingsPacket(target.getId(), wingId), target);

        source.sendSuccess(
                () -> Component.literal("已将翅膀 " + wingId + " 显示在 " + target.getName().getString() + " 身后"), true);

        return 1;
    }

    private static int clearWing(CommandSourceStack source, ServerPlayer target) {
        WingsCapability.get(target).ifPresent(cap -> cap.setWingId(""));
        IcarusMeshNetworking.sendToTrackingAndSelf(new SetWingsPacket(target.getId(), ""), target);

        source.sendSuccess(() -> Component.literal("已清除 " + target.getName().getString() + " 的翅膀"), true);

        return 1;
    }

    private static int listWings(CommandSourceStack source) {
        String all = String.join(", ", WingsRegistry.all().stream().map(WingDefinition::id).toList());

        source.sendSuccess(() -> Component.literal("可用翅膀: " + all), false);

        return 1;
    }
}
