package com.animator70.icarus_mesh.client;

// 我的类
import com.animator70.icarus_mesh.config.WingsRenderConfig;

/**
 * 客户端渲染配置缓存
 * 默认从本地 COMMON 配置构建；服务端通过 SyncWingsConfigPacket 同步后覆盖，实现全服统一
 */
public final class ClientWingsConfig {
    private static volatile WingsRenderConfig current = WingsRenderConfig.snapshotFromConfig();

    public static WingsRenderConfig get() {
        return current;
    }

    public static void apply(WingsRenderConfig config) {
        current = config;
    }

    private ClientWingsConfig() {
    }
}
