package com.animator70.icarus_mesh.wing;

// Minecraft 类
import net.minecraft.world.item.DyeColor;

/**
 * 一套翅膀的定义：唯一 id（用于指令）、模型类型、主/次颜色。
 */
public record WingDefinition(String id, WingType type, DyeColor primaryColor, DyeColor secondaryColor) {
}
