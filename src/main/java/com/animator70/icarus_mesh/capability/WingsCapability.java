package com.animator70.icarus_mesh.capability;

// 我的类
import com.animator70.icarus_mesh.IcarusMesh;

// Minecraft 类
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

// Forge 类
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;

// JetBrains 注解
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * 存储玩家当前穿戴的翅膀 id（空字符串表示未穿戴）
 */
public class WingsCapability {
    // 能力
    public static final Capability<WingsCapability> CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {
    });
    // 翅膀 ID
    private String wingId = "";

    public String getWingId() {
        return this.wingId;
    }

    public void setWingId(String wingId) {
        this.wingId = wingId == null ? "" : wingId;
    }

    public boolean hasWings() {
        return !this.wingId.isEmpty();
    }

    public static LazyOptional<WingsCapability> get(Entity entity) {
        return entity.getCapability(CAPABILITY);
    }

    /**
     * 给每个 Player 实体动态附加翅膀 Capability（Forge 能力系统的接入点）
     */
    public static void attach(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {
            event.addCapability(new ResourceLocation(IcarusMesh.MODID, "wings"), new Provider());
        }
    }

    /**
     * 能力提供者：把 {@link WingsCapability} 暴露给 Forge，并提供 NBT 序列化（持久化）
     * LazyOptional 是惰性持有能力的容器，避免空实体反复创建实例
     */
    public static class Provider implements ICapabilitySerializable<CompoundTag> {
        private final WingsCapability instance = new WingsCapability();
        private final LazyOptional<WingsCapability> lazy = LazyOptional.of(() -> this.instance);

        @Override
        public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
            return CAPABILITY.orEmpty(cap, this.lazy);
        }

        @Override
        public CompoundTag serializeNBT() {
            CompoundTag tag = new CompoundTag();

            tag.putString("wingId", this.instance.wingId);
            return tag;
        }

        @Override
        public void deserializeNBT(CompoundTag nbt) {
            this.instance.wingId = nbt.getString("wingId");
        }
    }
}
