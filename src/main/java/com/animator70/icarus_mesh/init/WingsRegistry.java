package com.animator70.icarus_mesh.init;

// 我的类
import com.animator70.icarus_mesh.wing.WingDefinition;
import com.animator70.icarus_mesh.wing.WingType;

// Minecraft 类
import net.minecraft.world.item.DyeColor;

// Java 类
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 翅膀注册表
 * 83 个翅膀的注册表
 * WingsRegistry
 */
public class WingsRegistry {
    // 翅膀
    private static final Map<String, WingDefinition> WINGS = new LinkedHashMap<>();

    /**
     * 生成全部翅膀定义：16 种颜色 × 5 种可染色类型 = 80 个，再加 3 个独特翅膀，共 83 个
     */
    public static void init() {
        for (DyeColor color : DyeColor.values()) {
            String name = color.getName();

            register(new WingDefinition(name + "_feathered_wings", WingType.FEATHERED, color, color));
            register(new WingDefinition(name + "_dragon_wings", WingType.DRAGON, color, dragonSecondary(color)));

            register(new WingDefinition(name + "_mechanical_feathered_wings", WingType.MECHANICAL_FEATHERED,
                    DyeColor.WHITE, color));

            register(new WingDefinition(name + "_mechanical_leather_wings", WingType.MECHANICAL_LEATHER, DyeColor.WHITE,
                    color));

            register(new WingDefinition(name + "_light_wings", WingType.LIGHT, color, color));
        }

        register(new WingDefinition("flandres_wings", WingType.FLANDRES, DyeColor.WHITE, DyeColor.WHITE));
        register(new WingDefinition("discords_wings", WingType.DISCORDS, DyeColor.WHITE, DyeColor.WHITE));
        register(new WingDefinition("zanzas_wings", WingType.ZANZAS, DyeColor.WHITE, DyeColor.WHITE));
    }

    private static void register(WingDefinition definition) {
        WINGS.put(definition.id(), definition);
    }

    public static WingDefinition get(String id) {
        return WINGS.get(id);
    }

    public static boolean contains(String id) {
        return WINGS.containsKey(id);
    }

    public static Collection<WingDefinition> all() {
        return WINGS.values();
    }

    /**
     * 还原 Icarus 中龙翼每套颜色的副色映射
     */
    private static DyeColor dragonSecondary(DyeColor primary) {
        return switch (primary) {
            case MAGENTA -> DyeColor.PINK;
            case LIGHT_BLUE -> DyeColor.WHITE;
            case LIME -> DyeColor.PINK;
            case PINK -> DyeColor.WHITE;
            case GRAY -> DyeColor.LIGHT_GRAY;
            case LIGHT_GRAY -> DyeColor.WHITE;
            case CYAN -> DyeColor.LIGHT_BLUE;
            case PURPLE -> DyeColor.MAGENTA;
            case BLUE -> DyeColor.LIGHT_BLUE;
            case BROWN -> DyeColor.ORANGE;
            case GREEN -> DyeColor.LIME;
            case RED -> DyeColor.YELLOW;
            case BLACK -> DyeColor.PURPLE;
            default -> primary;
        };
    }
}
