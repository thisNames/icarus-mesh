package com.animator70.icarus_mesh.config;

// 我的类
import com.animator70.icarus_mesh.wing.WingType;

/**
 * 翅膀渲染参数快照（扁平 double 数组）。
 * 服务端从 WingsConfig 构建后通过网络同步给客户端，客户端用它渲染，实现全服统一。
 * 索引布局：
 * [0..3] = globalScale, offsetX, offsetY, offsetZ
 * [4..] = 每个 WingType（按 ordinal 顺序）4 个值：scale, headDistance,
 * crouchHeadDistance, wingSpacing
 */
public final class WingsRenderConfig {
    private static final int GLOBAL_COUNT = 4;
    private static final int PER_TYPE_COUNT = 4;

    private final double[] values;

    public WingsRenderConfig(double[] values) {
        this.values = values;
    }

    public double globalScale() {
        return values[0];
    }

    public double offsetX() {
        return values[1];
    }

    public double offsetY() {
        return values[2];
    }

    public double offsetZ() {
        return values[3];
    }

    public double scale(WingType type) {
        return values[typeIndex(type)];
    }

    public double headDistance(WingType type) {
        return values[typeIndex(type) + 1];
    }

    public double crouchHeadDistance(WingType type) {
        return values[typeIndex(type) + 2];
    }

    public double wingSpacing(WingType type) {
        return values[typeIndex(type) + 3];
    }

    private static int typeIndex(WingType type) {
        return GLOBAL_COUNT + type.ordinal() * PER_TYPE_COUNT;
    }

    public double[] toArray() {
        return values.clone();
    }

    /**
     * 从 WingsConfig（COMMON 配置）构建当前快照。
     */
    public static WingsRenderConfig snapshotFromConfig() {
        double[] values = new double[GLOBAL_COUNT + WingType.values().length * PER_TYPE_COUNT];

        values[0] = WingsConfig.GLOBAL_SCALE.get();
        values[1] = WingsConfig.OFFSET_X.get();
        values[2] = WingsConfig.OFFSET_Y.get();
        values[3] = WingsConfig.OFFSET_Z.get();

        for (WingType type : WingType.values()) {
            WingsConfig.PerType perType = WingsConfig.forType(type);
            int base = typeIndex(type);

            values[base] = perType.scale.get();
            values[base + 1] = perType.headDistance.get();
            values[base + 2] = perType.crouchHeadDistance.get();
            values[base + 3] = perType.wingSpacing.get();
        }

        return new WingsRenderConfig(values);
    }
}
