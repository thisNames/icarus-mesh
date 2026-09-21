package com.animator70.icarus_mesh.client;

// 我的类
import com.animator70.icarus_mesh.capability.WingsCapability;
import com.animator70.icarus_mesh.config.WingsRenderConfig;
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
 * 渲染层（读 Capability → 选模型 → 渲染）
 * WingsLayer
 * 
 * @param <T> 实体类型
 * @param <M> 实体模型类型
 */
public class WingsLayer<T extends LivingEntity, M extends EntityModel<T>> extends RenderLayer<T, M> {
    // 6 套翅膀
    private final FeatheredWingsModel<T> featheredWings;
    private final LeatherWingsModel<T> leatherWings;
    private final LightWingsModel<T> lightWings;
    private final FlandresWingsModel<T> flandresWings;
    private final DiscordsWingsModel<T> discordsWings;
    private final ZanzasWingsModel<T> zanzasWings;

    public WingsLayer(RenderLayerParent<T, M> context, EntityModelSet loader) {
        super(context);

        // 6 套翅膀模型
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
            WingDefinition definition = WingsRegistry.get(cap.getCurrentWingId());

            // 如果注册表中没有该翅膀，直接返回
            if (definition == null) {
                return;
            }

            WingType type = definition.type();

            // 根据翅膀类型选择对应的 3D 模型
            WingEntityModel<T> wingModel = getModel(type);

            // 如果没有对应的 3D 模型，直接返回
            if (wingModel == null) {
                return;
            }

            // 客户端渲染参数（服务端同步的快照，实现全服统一）
            WingsRenderConfig cfg = ClientWingsConfig.get();

            // 取主/副颜色的 RGB（用于给翅膀贴图染色）
            float[] primary = definition.primaryColor().getTextureDiffuseColors();
            float[] secondary = definition.secondaryColor().getTextureDiffuseColors();

            // 保存当前矩阵，用于恢复
            matrices.pushPose();
            // 应用配置里的位置偏移（X 左右 / Y 上下 / Z 前后，单位：格）
            matrices.translate(cfg.offsetX(), cfg.offsetY(), cfg.offsetZ());

            // 复制父模型姿态（如潜行/年轻等），并计算本帧扇动动画
            this.getParentModel().copyPropertiesTo(wingModel);

            // 注入该类型的渲染参数（头部距离/蹲下距离/间距），再计算扇动动画
            wingModel.setRenderParams(
                    (float) cfg.headDistance(type),
                    (float) cfg.crouchHeadDistance(type),
                    (float) cfg.wingSpacing(type));

            // 设置翅膀动画（此时翅膀根 pivot 已就位，leftWing.y 即翅膀根高度）
            wingModel.setupAnim(entity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);

            // 绕翅膀根高度缩放：先把原点移到翅膀根高度，缩放，再移回。
            // 这样缩放锚点在翅膀根部（x 取左右翼对称中线），极端缩放（3 倍等）时翅膀根高度不漂移
            // 缩放 = 该类型缩放 × 配置文件里的全局缩放乘数
            double scale = cfg.scale(type) * cfg.globalScale();
            float anchorY = wingModel.leftWing.y;

            matrices.translate(0.0D, anchorY, 0.0D);
            matrices.scale((float) scale, (float) scale, (float) scale);
            matrices.translate(0.0D, -anchorY, 0.0D);

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
