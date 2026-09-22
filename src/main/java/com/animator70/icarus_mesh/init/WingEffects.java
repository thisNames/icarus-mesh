package com.animator70.icarus_mesh.init;

// 我的类
import com.animator70.icarus_mesh.IcarusMesh;
import com.animator70.icarus_mesh.effect.WingEffect;
import com.animator70.icarus_mesh.wing.WingDefinition;

// Minecraft 类
import net.minecraft.world.effect.MobEffect;

// Forge 类
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

// Java 类
import java.util.HashMap;
import java.util.Map;

/**
 * 翅膀效果注册表：为每个翅膀注册一个对应的正面 MobEffect。
 * 玩家拥有效果即获得翅膀（由 CommonEvents 监听效果变化联动入队/出队）。
 * WingEffects
 */
public class WingEffects {
    // 效果注册器
    public static final DeferredRegister<MobEffect> EFFECTS = DeferredRegister
            .create(ForgeRegistries.MOB_EFFECTS, IcarusMesh.MODID);

    // 翅膀 id → 效果（注册项）
    private static final Map<String, RegistryObject<MobEffect>> EFFECT_BY_WING = new HashMap<>();

    /**
     * 注册全部翅膀效果（需在 WingsRegistry.init() 之后调用）。
     */
    public static void init(IEventBus modBus) {
        for (WingDefinition definition : WingsRegistry.all()) {
            String wingId = definition.id();
            int color = definition.primaryColor().getFireworkColor();

            EFFECT_BY_WING.put(wingId, EFFECTS.register(wingId, () -> new WingEffect(wingId, color)));
        }

        EFFECTS.register(modBus);
    }

    /**
     * 根据翅膀 id 获取对应的效果（可能为 null，表示未注册）。
     */
    public static MobEffect getEffect(String wingId) {
        RegistryObject<MobEffect> registryObject = EFFECT_BY_WING.get(wingId);
        return registryObject == null ? null : registryObject.get();
    }
}
