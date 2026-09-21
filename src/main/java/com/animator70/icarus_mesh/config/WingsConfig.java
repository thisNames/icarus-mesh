package com.animator70.icarus_mesh.config;

// 我的类
import com.animator70.icarus_mesh.wing.WingType;

// Forge 类
import net.minecraftforge.common.ForgeConfigSpec;

// Java 类
import java.util.EnumMap;
import java.util.Map;

/**
 * 翅膀渲染配置（COMMON 配置，全局共享）
 * 全局偏移 + 按翅膀类型（8 种）单独的缩放/头部距离/蹲下距离/间距。
 * 由 Forge 配置系统生成到全局 config/icarus_mesh-common.toml（非每存档）。
 * 服务端读取后通过 SyncWingsConfigPacket 在玩家登录时同步给客户端，实现全服统一。
 * 渲染层读的是客户端缓存（同步后的快照），不是每帧读磁盘，开销为零。
 */
public class WingsConfig {
    public static final ForgeConfigSpec SPEC;

    // 全局参数
    public static final ForgeConfigSpec.DoubleValue GLOBAL_SCALE;
    public static final ForgeConfigSpec.DoubleValue OFFSET_X;
    public static final ForgeConfigSpec.DoubleValue OFFSET_Y;
    public static final ForgeConfigSpec.DoubleValue OFFSET_Z;

    // 每个翅膀类型的渲染参数
    private static final Map<WingType, PerType> PER_TYPE = new EnumMap<>(WingType.class);

    static {
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

        builder.comment("翅膀渲染微调（全局 COMMON 配置）。修改后重启服务器（或 /reload）生效，玩家重进后同步。");

        builder.push("general");
        GLOBAL_SCALE = builder
                .comment("全局缩放乘数，会乘到每个翅膀自己的缩放上。默认 1.0。")
                .defineInRange("globalScale", 1.0D, 0.1D, 5.0D);
        OFFSET_X = builder
                .comment("翅膀左右偏移（格）。正数向右。默认 0.0。")
                .defineInRange("offsetX", 0.0D, -2.0D, 2.0D);
        OFFSET_Y = builder
                .comment("翅膀上下偏移（格）。正数向上。默认 0.0。")
                .defineInRange("offsetY", 0.0D, -2.0D, 2.0D);
        OFFSET_Z = builder
                .comment("翅膀前后偏移（格）。正数离背部更远。默认 0.3。")
                .defineInRange("offsetZ", 0.3D, -1.0D, 2.0D);
        builder.pop();

        // 每个翅膀类型单独的参数
        for (WingType type : WingType.values()) {
            builder.push(type.name().toLowerCase());
            PER_TYPE.put(type, PerType.build(builder, type));
            builder.pop();
        }

        SPEC = builder.build();
    }

    public static PerType forType(WingType type) {
        return PER_TYPE.get(type);
    }

    private static double defaultScale(WingType type) {
        return switch (type) {
            case DRAGON, MECHANICAL_LEATHER -> 1.15D;
            case LIGHT -> 0.85D;
            default -> 1.0D;
        };
    }

    /**
     * 单个翅膀类型的渲染参数
     */
    public static final class PerType {
        public final ForgeConfigSpec.DoubleValue scale;
        public final ForgeConfigSpec.DoubleValue headDistance;
        public final ForgeConfigSpec.DoubleValue crouchHeadDistance;
        public final ForgeConfigSpec.DoubleValue wingSpacing;

        private PerType(ForgeConfigSpec.DoubleValue scale,
                ForgeConfigSpec.DoubleValue headDistance,
                ForgeConfigSpec.DoubleValue crouchHeadDistance,
                ForgeConfigSpec.DoubleValue wingSpacing) {
            this.scale = scale;
            this.headDistance = headDistance;
            this.crouchHeadDistance = crouchHeadDistance;
            this.wingSpacing = wingSpacing;
        }

        static PerType build(ForgeConfigSpec.Builder builder, WingType type) {
            ForgeConfigSpec.DoubleValue scale = builder
                    .comment("该翅膀的缩放。默认 " + defaultScale(type) + "。")
                    .defineInRange("scale", defaultScale(type), 0.1D, 5.0D);

            ForgeConfigSpec.DoubleValue headDistance = builder
                    .comment("待机/飞行时翅膀根高度（越大约靠近头部）。默认 0.0。")
                    .defineInRange("headDistance", 0.0D, -10.0D, 10.0D);

            ForgeConfigSpec.DoubleValue crouchHeadDistance = builder
                    .comment("潜行时翅膀根高度。默认 -3.0。")
                    .defineInRange("crouchHeadDistance", -3.0D, -10.0D, 10.0D);

            ForgeConfigSpec.DoubleValue wingSpacing = builder
                    .comment("左右两半翅膀的间距（越大两翼离得越远）。默认 3.0。")
                    .defineInRange("wingSpacing", 3.0D, -10.0D, 10.0D);

            return new PerType(scale, headDistance, crouchHeadDistance, wingSpacing);
        }
    }
}
