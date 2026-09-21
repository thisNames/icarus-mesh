// Made with Model Converter by Globox_Z

// 我的类
package com.animator70.icarus_mesh.client.models;

// Google 类
import com.google.common.collect.ImmutableList;

// Minecraft 类
import net.minecraft.client.model.AgeableListModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import net.minecraft.util.Mth;

/**
 * 翅膀模型
 * WingEntityModel
 * 
 * @param <T> LivingEntity
 */
public class WingEntityModel<T extends LivingEntity> extends AgeableListModel<T> {
    public final ModelPart rightWing;
    public final ModelPart leftWing;

    // 翅膀状态
    public State state = State.IDLE;

    public WingEntityModel(ModelPart root) {
        this.rightWing = root.getChild("rightWing");
        this.leftWing = root.getChild("leftWing");
    }

    /**
     * 创建翅膀模型的骨架：两个空的左右翅膀根节点（pivot）
     * 具体的翅膀几何由各子模型（如 FeatheredWingsModel）挂载在这两个节点下
     */
    public static MeshDefinition getModelData() {
        MeshDefinition modelData = new MeshDefinition();
        PartDefinition modelPartData = modelData.getRoot();

        // 左右翅膀的根节点（旋转锚点），几何体由子模型在下方挂载
        modelPartData.addOrReplaceChild("rightWing", CubeListBuilder.create(), PartPose.offset(0.0F, 5.0F, 0.0F));
        modelPartData.addOrReplaceChild("leftWing", CubeListBuilder.create(), PartPose.offset(0.0F, 5.0F, 0.0F));

        return modelData;
    }

    /**
     * 每帧计算并应用翅膀姿态，是整个翅膀动画的核心。
     * 
     * 根据实体状态（飞行 / 潜行 / 待机）算出目标旋转角，再用正弦波叠加出「扇动」效果。
     * 变量含义：a/b 为扇动频率/幅度；k/l/n 为绕 X/Z/Y 轴的旋转角（弧度）；m 为根部高度偏移。
     */
    @Override
    public void setupAnim(
            T entity,
            float limbAngle,
            float limbDistance,
            float animationProgress,
            float headYaw,
            float headPitch) {
        // code...
        state = State.IDLE;

        // 默认（待机）参数：几乎不扇动，翅膀自然下垂
        float a = 0.125F; // 扇动频率
        float b = 0.1F; // 扇动幅度
        float k = 0.4F; // 绕 X 轴旋转（上下扇）
        float l = -0.5F; // 绕 Z 轴旋转（前后摆）
        float m = 2.0F; // 翅膀根部高度（数值越小翅膀越往上、越靠近头部）
        float n = 0.0F; // 绕 Y 轴旋转（水平摆）

        // 如果实体是飞行状态，则根据下落速度把翅膀逐渐收拢，并根据飞行速度快速、大幅扇动
        if (entity.isFallFlying() || (entity instanceof Player player && player.getAbilities().flying)) {
            // 飞行状态
            state = State.FLYING;

            // 翅膀展开程度：1=完全展开，0=完全收拢
            float o = 1.0F;
            Vec3 vec3d = entity.getDeltaMovement();

            // 向下俯冲时，根据下落速度把翅膀逐渐收拢
            if (vec3d.y < 0.0D) {
                Vec3 vec3d2 = vec3d.normalize();
                o = 1.0F - (float) Math.pow(-vec3d2.y, 1.5D);
            }

            // 用展开程度 o 混合出飞行时的翅膀角度（越收拢越接近滑翔姿态）
            k = o * 0.35F + (1.0F - o) * k;
            l = o * -1.6F + (0.3F - o) * l;

            if (entity.zza > 0) {
                // 飞行移动：快速、大幅扇动
                a = 0.4F;
                b = 1.0F;
            } else {
                // 飞行悬停：慢速、小幅扇动（仍持续扇动）
                a = 0.2F;
                b = 0.5F;
            }
        } else if (entity.isCrouching()) {
            // 潜行状态
            state = State.CROUCHING;

            k = 0.7F;
            m = 3.0F;
            n = 0.09F;
        }

        // 正弦波叠加：让翅膀绕 X 轴周期性上下摆动，即「扇动」效果
        k += Mth.sin(entity.tickCount * a) * b;

        // 设定翅膀根部的锚点位置：x 由 getWingAnchorX() 决定（各子模型对齐各自翅膀根），右翼稍后镜像到 -x
        this.leftWing.x = getWingAnchorX();
        this.leftWing.y = m;

        // 如果实体是玩家，则把 elytraRot 字段写入玩家实体，并做 0.1 平滑插值
        if (entity instanceof AbstractClientPlayer) {
            AbstractClientPlayer player = (AbstractClientPlayer) entity;
            // 写入玩家自己的 elytraRot 字段并做 0.1 平滑插值：让翅膀与鞘翅动画一致、过渡不生硬

            player.elytraRotX = (player.elytraRotX + (k - player.elytraRotX) * 0.1F);
            player.elytraRotY = (player.elytraRotY + (n - player.elytraRotY) * 0.1F);
            player.elytraRotZ = (player.elytraRotZ + (l - player.elytraRotZ) * 0.1F);

            this.leftWing.xRot = player.elytraRotX;
            this.leftWing.yRot = player.elytraRotY;
            this.leftWing.zRot = player.elytraRotZ;
        } else {
            this.leftWing.xRot = k;
            this.leftWing.zRot = l;
            this.leftWing.yRot = n;
        }

        // 右翼是左翼的镜像：x 位置、绕 Y/Z 轴旋转取反（绕 X 轴的上下扇动保持同向）
        this.rightWing.x = -this.leftWing.x;

        this.rightWing.yRot = -this.leftWing.yRot;
        this.rightWing.y = this.leftWing.y;

        this.rightWing.xRot = this.leftWing.xRot;
        this.rightWing.zRot = -this.leftWing.zRot;
    }

    /**
     * 翅膀旋转锚点的 X 位置（单位：模型像素）。
     * 默认 1 对应大部分翅膀（羽翼/龙翼/芙兰/Discord/Zanza）的翅膀根；
     * 光翼（LightWingsModel）的翅膀根在 -1，由它覆盖此方法。
     * 把锚点对齐翅膀根后，扇动时翅膀根就不会跟着位移。
     */
    protected float getWingAnchorX() {
        return 3.0F;
    }

    @Override
    protected Iterable<ModelPart> headParts() {
        return ImmutableList.of();
    }

    @Override
    protected Iterable<ModelPart> bodyParts() {
        return ImmutableList.of(this.rightWing, this.leftWing);
    }

    public enum State {
        IDLE, CROUCHING, FLYING
    }
}
