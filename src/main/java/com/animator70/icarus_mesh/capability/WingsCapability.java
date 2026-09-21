package com.animator70.icarus_mesh.capability;

// 我的类
import com.animator70.icarus_mesh.IcarusMesh;

// Minecraft 类
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;

// Forge 类
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;

// JetBrains 注解
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

// Java 类
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.List;

/**
 * 存储玩家当前穿戴的翅膀队列（先入队者优先显示，队首即当前翅膀）
 * 
 * 队列采用「先到先得、后来排后、队首出队后轮到下一个」的语义，
 * 可同时兼容药水 buff、饰品、物品等多种来源（同一翅膀 id 不重复入队）。
 */
public class WingsCapability {

    // 能力
    public static final Capability<WingsCapability> CAPABILITY = CapabilityManager.get(new CapabilityToken<>() {
    });

    // 翅膀队列：队首为当前显示的翅膀
    private final Deque<String> wingQueue = new ArrayDeque<>();

    /**
     * 获取当前显示的翅膀 id（队首），队列为空则返回空字符串。
     */
    public String getCurrentWingId() {
        String first = this.wingQueue.peekFirst();
        return first == null ? "" : first;
    }

    /**
     * 是否有翅膀（队列不为空）
     */
    public boolean hasWings() {
        return !this.wingQueue.isEmpty();
    }

    /**
     * 入队：把翅膀加入队尾。若该翅膀已在队列中，则跳过（重复无效）。
     *
     * @param wingId 翅膀 id
     * @return 是否真正入队（false 表示已存在，被跳过）
     */
    public boolean addWing(String wingId) {
        if (wingId == null || wingId.isEmpty() || this.wingQueue.contains(wingId)) {
            return false;
        }

        this.wingQueue.addLast(wingId);

        return true;
    }

    /**
     * 出队：按翅膀 id 移除。用于 buff 到期、物品卸下等场景。
     */
    public boolean removeWing(String wingId) {
        return this.wingQueue.remove(wingId);
    }

    /**
     * 清空队列
     */
    public void clearWings() {
        this.wingQueue.clear();
    }

    /**
     * 获取队列副本（队首在前），用于数据包同步 / NBT 序列化
     */
    public List<String> getWingQueue() {
        return new ArrayList<>(this.wingQueue);
    }

    /**
     * 设置整个队列（客户端接收同步时使用）
     */
    public void setWingQueue(Collection<String> queue) {
        this.wingQueue.clear();
        if (queue != null) {
            this.wingQueue.addAll(queue);
        }
    }

    /**
     * 获取实体的翅膀 Capability
     */
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
            ListTag list = new ListTag();

            for (String id : this.instance.wingQueue) {
                list.add(StringTag.valueOf(id));
            }

            tag.put("wingQueue", list);
            return tag;
        }

        @Override
        public void deserializeNBT(CompoundTag nbt) {
            this.instance.wingQueue.clear();

            for (Tag entry : nbt.getList("wingQueue", Tag.TAG_STRING)) {
                this.instance.wingQueue.addLast(entry.getAsString());
            }
        }
    }
}
