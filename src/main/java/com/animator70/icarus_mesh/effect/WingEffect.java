package com.animator70.icarus_mesh.effect;

// Minecraft 类
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

/**
 * 翅膀效果：持有对应的翅膀 id。
 * 玩家拥有此效果（正面效果）时自动获得对应翅膀；效果消失时翅膀自动移除。
 * WingEffect
 */
public class WingEffect extends MobEffect {
    // 对应的翅膀 id
    private final String wingId;

    /**
     * @param wingId 对应的翅膀 id
     * @param color  效果粒子颜色（用翅膀主色）
     */
    public WingEffect(String wingId, int color) {
        super(MobEffectCategory.BENEFICIAL, color);
        this.wingId = wingId;
    }

    /**
     * 获取对应的翅膀 id
     */
    public String getWingId() {
        return this.wingId;
    }
}
