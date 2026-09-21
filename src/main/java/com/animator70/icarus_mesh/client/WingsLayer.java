package com.animator70.icarus_mesh.client;

// 我的类
import com.animator70.icarus_mesh.capability.WingsCapability;
import com.animator70.icarus_mesh.init.WingsRegistry;
import com.animator70.icarus_mesh.wing.WingDefinition;
import com.animator70.icarus_mesh.wing.WingType;
import com.animator70.icarus_mesh.client.models.*;

// MJ 类
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

// Minecraft 类
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.LivingEntity;

/**
 * 翅膀渲染层
 * WingsLayer
 * 
 * @param <T> 实体类型
 * @param <M> 实体模型类型
 */
public class WingsLayer<T extends LivingEntity, M extends EntityModel<T>> extends RenderLayer<T, M> {
    // 翅膀整体离玩家背后的偏移量（单位：格）。原版为 0.125，翅膀根部会嵌入身体，这里增大到 0.3
    private static final double WING_OFFSET_Z = 0.3D;

    // 6 套翅膀
    private final FeatheredWingsModel<T> featheredWings;
    private final LeatherWingsModel<T> leatherWings;
    private final LightWingsModel<T> lightWings;
    private final FlandresWingsModel<T> flandresWings;
    private final DiscordsWingsModel<T> discordsWings;
    private final ZanzasWingsModel<T> zanzasWings;

    public WingsLayer(RenderLayerParent<T, M> context, EntityModelSet loader) {
        super(context);

        this.featheredWings = new FeatheredWingsModel<>(loader.bakeLayer(IcarusMeshModels.FEATHERED));
        this.leatherWings = new LeatherWingsModel<>(loader.bakeLayer(IcarusMeshModels.LEATHER));
        this.lightWings = new LightWingsModel<>(loader.bakeLayer(IcarusMeshModels.LIGHT));
        this.flandresWings = new FlandresWingsModel<>(loader.bakeLayer(IcarusMeshModels.FLANDRES));
        this.discordsWings = new DiscordsWingsModel<>(loader.bakeLayer(IcarusMeshModels.DISCORDS));
        this.zanzasWings = new ZanzasWingsModel<>(loader.bakeLayer(IcarusMeshModels.ZANZAS));
    }

    /**
     * 每帧渲染：从实体 Capability 读取翅膀 id → 查注册表得到模型/贴图/颜色 → 渲染两层贴图
     * 翅膀通过主色、副色对贴图像素染色（白色底图 × 颜色 = 目标色）
     */
    @Override
    public void render(
            PoseStack matrices,
            MultiBufferSource vertexConsumers,
            int light,
            T entity,
            float limbAngle,
            float limbDistance,
            float tickDelta,
            float animationProgress,
            float headYaw,
            float headPitch) {
        // code...
        WingsCapability.get(entity).ifPresent(cap -> {
            // 如果没有翅膀，直接返回
            if (!cap.hasWings()) {
                return;
            }

            // 通过翅膀 id 查注册表得到模型/贴图/颜色
            WingDefinition definition = WingsRegistry.get(cap.getWingId());

            // 如果注册表中没有该翅膀，直接返回
            if (definition == null) {
                return;
            }

            // 根据翅膀类型选择对应的 3D 模型
            WingEntityModel<T> wingModel = getModel(definition.type());

            // 如果没有对应的 3D 模型，直接返回
            if (wingModel == null) {
                return;
            }

            // 取主/副颜色的 RGB（用于给翅膀贴图染色）
            float[] primary = definition.primaryColor().getTextureDiffuseColors();
            float[] secondary = definition.secondaryColor().getTextureDiffuseColors();

            // 保存当前矩阵，用于恢复
            matrices.pushPose();
            // 把翅膀整体向玩家背后偏移，避免嵌入身体
            matrices.translate(0.0D, 0.0D, WING_OFFSET_Z);

            // 应用该翅膀类型的缩放（龙翼偏大、光翼偏小）
            float scale = definition.type().getScale();

            matrices.scale(scale, scale, scale);

            // 复制父模型姿态（如潜行/年轻等），并计算本帧扇动动画
            this.getParentModel().copyPropertiesTo(wingModel);

            // 设置翅膀动画
            wingModel.setupAnim(entity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);

            // 先渲染副色层，再渲染主色层（与原版 Icarus 一致）
            VertexConsumer buffer2 = vertexConsumers
                    .getBuffer(RenderType.entityTranslucent(definition.type().getTextureLayer2()));

            wingModel.renderToBuffer(matrices, buffer2, light, OverlayTexture.NO_OVERLAY, secondary[0], secondary[1],
                    secondary[2], 1.0F);

            VertexConsumer buffer1 = vertexConsumers
                    .getBuffer(RenderType.entityTranslucent(definition.type().getTextureLayer1()));

            wingModel.renderToBuffer(matrices, buffer1, light, OverlayTexture.NO_OVERLAY, primary[0], primary[1],
                    primary[2], 1.0F);

            matrices.popPose();
        });
    }

    /**
     * 根据翅膀类型选择对应的 3D 模型（机械羽翼复用羽翼模型、机械皮革复用皮革模型）。
     */
    private WingEntityModel<T> getModel(WingType type) {
        return switch (type) {
            case FEATHERED, MECHANICAL_FEATHERED -> featheredWings;
            case DRAGON, MECHANICAL_LEATHER -> leatherWings;
            case LIGHT -> lightWings;
            case FLANDRES -> flandresWings;
            case DISCORDS -> discordsWings;
            case ZANZAS -> zanzasWings;
        };
    }
}
