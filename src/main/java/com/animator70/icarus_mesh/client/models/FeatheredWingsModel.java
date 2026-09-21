// Made with Model Converter by Globox_Z
// Model by cybercat5555

// 我的类
package com.animator70.icarus_mesh.client.models;

// Minecraft 类
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.world.entity.LivingEntity;

/**
 * 翅膀模型
 * FeatheredWingsModel
 * 
 * @param <T> LivingEntity
 */
public class FeatheredWingsModel<T extends LivingEntity> extends WingEntityModel<T> {
    private final ModelPart leftWing03;
    private final ModelPart rightWing03;

    public FeatheredWingsModel(ModelPart root) {
        super(root);

        this.leftWing03 = root
                .getChild("leftWing")
                .getChild("leftWing01")
                .getChild("leftWing02")
                .getChild("leftWing03");

        this.rightWing03 = root
                .getChild("rightWing")
                .getChild("rightWing01")
                .getChild("rightWing02")
                .getChild("rightWing03");
    }

    @SuppressWarnings("null")
    public static LayerDefinition getLayerDefinition() {
        MeshDefinition modelData = getModelData();
        PartDefinition modelPartData = modelData.getRoot();

        PartDefinition modelPartData1 = modelPartData
                .getChild("leftWing")
                .addOrReplaceChild(
                        "leftWing01",
                        CubeListBuilder.create()
                                .texOffs(0, 0)
                                .addBox(-1.0F, -2.0F, -1.0F, 2.0F, 4.0F, 6.0F),
                        PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 1.5708F, 0.436332F));

        PartDefinition modelPartData2 = modelPartData1.addOrReplaceChild(
                "leftWing02",
                CubeListBuilder.create()
                        .texOffs(0, 47)
                        .addBox(-0.5F, -1.5F, 0.5F, 1.0F, 2.0F, 8.0F),
                PartPose.offsetAndRotation(-0.5F, 0.0F, 3.5F, 0.1309F, 0.3054F, 0.0F));

        PartDefinition modelPartData3 = modelPartData2.addOrReplaceChild(
                "leftWing03",
                CubeListBuilder.create()
                        .texOffs(39, 0)
                        .addBox(-0.5F, -0.1F, -0.5F, 1.0F, 2.0F, 8.0F),
                PartPose.offsetAndRotation(0.0F, -1.0F, 8.5F, -0.5672F, 0.3054F, 0.0F));

        PartDefinition modelPartData4 = modelPartData3.addOrReplaceChild(
                "leftWing04",
                CubeListBuilder.create()
                        .texOffs(33, 25)
                        .addBox(-0.7F, -0.2F, -0.5F, 1.0F, 14.0F, 1.0F, true),
                PartPose.offsetAndRotation(0.0F, 0.5F, 7.2F, 1.0908F, 0.0F, 0.0F));

        modelPartData4.addOrReplaceChild(
                "leftWing05",
                CubeListBuilder.create()
                        .texOffs(0, 13)
                        .addBox(0.4F, -4.0F, -12.3F, 0.0F, 20.0F, 13.0F, true),
                PartPose.offset(-0.5F, 4.8F, -0.2F));

        PartDefinition modelPartData5 = modelPartData4.addOrReplaceChild(
                "lFeathers02",
                CubeListBuilder.create(),
                PartPose.offsetAndRotation(0.0F, -3.5F, -3.2F, 0.0F, 0.0F, 0.0873F));

        modelPartData5.addOrReplaceChild(
                "Box_r1",
                CubeListBuilder.create()
                        .texOffs(26, 26)
                        .addBox(0.0F, -6.6F, -13.8F, 1.0F, 14.0F, 14.0F, true),
                PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.48F, 0.0F, 0.0F));

        PartDefinition modelPartData6 = modelPartData2.addOrReplaceChild(
                "lFeathers01",
                CubeListBuilder.create(),
                PartPose.offsetAndRotation(0.6F, 1.3F, 1.5F, -0.1745F, -0.0873F, 0.0F));

        modelPartData6.addOrReplaceChild(
                "Box_r2",
                CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-0.5F, -0.8F, -8.1F, 1.0F, 10.0F, 16.0F, true),
                PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.1745F, 0.0F, 0.0F));

        PartDefinition modelPartData7 = modelPartData
                .getChild("rightWing")
                .addOrReplaceChild(
                        "rightWing01",
                        CubeListBuilder.create()
                                .texOffs(0, 0)
                                .addBox(-1.0F, -2.0F, -1.0F, 2.0F, 4.0F, 6.0F, true),
                        PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, -0.436332F));

        PartDefinition modelPartData8 = modelPartData7.addOrReplaceChild(
                "rightWing02",
                CubeListBuilder.create()
                        .texOffs(0, 47)
                        .addBox(-0.5F, -1.5F, 0.5F, 1.0F, 2.0F, 8.0F, true),
                PartPose.offsetAndRotation(0.5F, 0.0F, 3.5F, 0.1309F, -0.3054F, 0.0F));

        PartDefinition modelPartData9 = modelPartData8.addOrReplaceChild(
                "rightWing03",
                CubeListBuilder.create()
                        .texOffs(39, 0)
                        .addBox(-0.5F, -0.1F, -0.5F, 1.0F, 2.0F, 8.0F, true),
                PartPose.offsetAndRotation(0.0F, -1.0F, 8.5F, -0.5672F, -0.3054F, 0.0F));

        PartDefinition modelPartData10 = modelPartData9.addOrReplaceChild(
                "rightWing04",
                CubeListBuilder.create()
                        .texOffs(33, 25)
                        .addBox(-0.3F, -0.2F, -0.5F, 1.0F, 14.0F, 1.0F),
                PartPose.offsetAndRotation(0.0F, 0.5F, 7.2F, 1.0908F, 0.0F, 0.0F));

        modelPartData10.addOrReplaceChild(
                "rightWing05",
                CubeListBuilder.create()
                        .texOffs(0, 13)
                        .addBox(-0.4F, -4.0F, -12.3F, 0.0F, 20.0F, 13.0F),
                PartPose.offset(0.5F, 4.8F, -0.2F));

        PartDefinition modelPartData11 = modelPartData10.addOrReplaceChild(
                "rFeathers02",
                CubeListBuilder.create(),
                PartPose.offsetAndRotation(0.0F, -3.5F, -3.2F, 0.0F, 0.0F, -0.0873F));

        modelPartData11.addOrReplaceChild(
                "Box_r3",
                CubeListBuilder.create()
                        .texOffs(26, 26)
                        .addBox(-1.0F, -6.6F, -13.8F, 1.0F, 14.0F, 14.0F),
                PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.48F, 0.0F, 0.0F));

        PartDefinition modelPartData12 = modelPartData8.addOrReplaceChild(
                "rFeathers01",
                CubeListBuilder.create(),
                PartPose.offsetAndRotation(-0.6F, 1.3F, 1.5F, -0.1745F, 0.0873F, 0.0F));

        modelPartData12.addOrReplaceChild(
                "Box_r4", CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-0.5F, -0.8F, -8.1F, 1.0F, 10.0F, 16.0F),
                PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.1745F, 0.0F, 0.0F));

        return LayerDefinition.create(modelData, 64, 64);
    }

    @Override
    public void setupAnim(
            T entity,
            float limbAngle,
            float limbDistance,
            float animationProgress,
            float headYaw,
            float headPitch) {
        // code...
        super.setupAnim(entity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);

        if (state == State.IDLE || state == State.CROUCHING) {
            leftWing03.xRot = (float) Math.toRadians(-60);
        }
        if (state == State.FLYING) {
            leftWing03.xRot = (float) Math.toRadians(-32.5);
        }

        rightWing03.xRot = leftWing03.xRot;
    }
}
