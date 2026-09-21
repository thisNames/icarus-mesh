// Made with Model Converter by Globox_Z
// Model by cybercat5555

// // 我的类
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
 * LeatherWingsModel
 * 
 * @param <T> LivingEntity
 */
public class LeatherWingsModel<T extends LivingEntity> extends WingEntityModel<T> {
    private final ModelPart leftWing03;
    private final ModelPart rightWing03;

    public LeatherWingsModel(ModelPart root) {
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
                                .texOffs(21, 0)
                                .addBox(-1.0F, -1.0F, -1.0F, 2.0F, 4.0F, 5.0F),
                        PartPose.offsetAndRotation(-6.0F, 0.0F, 0.0F, 0.0F, 1.5708F, 0.436332F));

        PartDefinition modelPartData2 = modelPartData1.addOrReplaceChild(
                "leftWing02",
                CubeListBuilder.create()
                        .texOffs(0, 47)
                        .addBox(-0.5F, 0.0F, -1.5F, 1.0F, 2.0F, 8.0F),
                PartPose.offsetAndRotation(-0.5F, 0.0F, 3.5F, 0.6109F, 0.0F, 0.0F));

        PartDefinition modelPartData3 = modelPartData2.addOrReplaceChild(
                "leftWing03",
                CubeListBuilder.create()
                        .texOffs(39, 0)
                        .addBox(-0.5F, -1.1F, -0.5F, 1.0F, 2.0F, 8.0F),
                PartPose.offsetAndRotation(0.0F, 1.0F, 6.5F, -0.5672F, 0.3054F, 0.0F));

        PartDefinition modelPartData4 = modelPartData3.addOrReplaceChild(
                "leftWing04",
                CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-0.6F, -0.8F, -4.0F, 1.0F, 1.0F, 19.0F),
                PartPose.offsetAndRotation(0.0F, 0.0F, 7.5F, -0.7418F, 0.0F, 0.0F));

        modelPartData4.addOrReplaceChild(
                "leftWing04Leather",
                CubeListBuilder.create()
                        .texOffs(0, 28)
                        .addBox(0.0F, 0.0F, 0.0F, 0.0F, 5.0F, 14.0F),
                PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition modelPartData5 = modelPartData3.addOrReplaceChild(
                "leftWingStrut01",
                CubeListBuilder.create()
                        .texOffs(21, 0)
                        .addBox(-0.6F, -0.5F, -0.5F, 1.0F, 1.0F, 16.0F),
                PartPose.offsetAndRotation(0.0F, 1.0F, 7.0F, -1.0036F, 0.0F, 0.0F));

        modelPartData5.addOrReplaceChild(
                "leftWingStrut01Leather",
                CubeListBuilder.create()
                        .texOffs(0, 22)
                        .addBox(-0.05F, 0.0F, 0.0F, 0.0F, 5.0F, 14.0F),
                PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition modelPartData6 = modelPartData3.addOrReplaceChild(
                "leftWingStrut02",
                CubeListBuilder.create()
                        .texOffs(21, 0)
                        .addBox(-0.6F, -0.5F, -0.5F, 1.0F, 1.0F, 16.0F),
                PartPose.offsetAndRotation(0.0F, 1.0F, 6.0F, -1.309F, 0.0F, 0.0F));

        modelPartData6.addOrReplaceChild(
                "leftWingStrut02Leather",
                CubeListBuilder.create()
                        .texOffs(0, 4)
                        .addBox(-0.1F, 0.0F, -1.0F, 0.0F, 6.0F, 16.0F),
                PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition modelPartData7 = modelPartData3.addOrReplaceChild(
                "leftWingStrut03",
                CubeListBuilder.create()
                        .texOffs(21, 0)
                        .addBox(-0.6F, -0.5F, -0.5F, 1.0F, 1.0F, 16.0F),
                PartPose.offsetAndRotation(0.0F, 1.0F, 5.0F, -1.6581F, 0.0F, 0.0F));

        modelPartData7.addOrReplaceChild(
                "leftWingStrut03Leather",
                CubeListBuilder.create()
                        .texOffs(0, 12)
                        .addBox(-0.15F, 0.0F, 0.0F, 0.0F, 6.0F, 15.0F),
                PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition modelPartData8 = modelPartData2.addOrReplaceChild(
                "leftStrutLowest",
                CubeListBuilder.create()
                        .texOffs(12, 0)
                        .addBox(-1.1F, 0.0F, -0.5F, 1.0F, 12.0F, 1.0F),
                PartPose.offsetAndRotation(0.5F, 1.0F, 2.0F, -0.3491F, 0.0F, 0.0F));

        modelPartData8.addOrReplaceChild(
                "leftStrutLowestLeather01",
                CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(0.0F, -1.0F, 0.0F, 0.0F, 12.0F, 6.0F),
                PartPose.offset(-0.5F, 0.0F, 0.0F));

        modelPartData8.addOrReplaceChild(
                "leftStrutLowestLeather02",
                CubeListBuilder.create()
                        .texOffs(28, 28)
                        .addBox(0.0F, -1.0F, -9.0F, 0.0F, 12.0F, 9.0F),
                PartPose.offsetAndRotation(-0.5F, 0.0F, 0.0F, 0.0F, -0.2618F, 0.0F));

        PartDefinition modelPartData9 = modelPartData
                .getChild("rightWing")
                .addOrReplaceChild(
                        "rightWing01",
                        CubeListBuilder.create()
                                .texOffs(21, 0)
                                .addBox(-1.0F, -1.0F, -1.0F, 2.0F, 4.0F, 5.0F, true),
                        PartPose.offsetAndRotation(6.0F, 0.0F, 0.0F, 0.0F, -1.5708F, -0.436332F));

        PartDefinition modelPartData10 = modelPartData9.addOrReplaceChild(
                "rightWing02",
                CubeListBuilder.create()
                        .texOffs(0, 47)
                        .addBox(-0.5F, 0.0F, -1.5F, 1.0F, 2.0F, 8.0F, true),
                PartPose.offsetAndRotation(0.5F, 0.0F, 3.5F, 0.6109F, 0.0F, 0.0F));

        PartDefinition modelPartData11 = modelPartData10.addOrReplaceChild(
                "rightWing03",
                CubeListBuilder.create()
                        .texOffs(39, 0)
                        .addBox(-0.5F, -1.1F, -0.5F, 1.0F, 2.0F, 8.0F, true),
                PartPose.offsetAndRotation(0.0F, 1.0F, 6.5F, -0.5672F, -0.3054F, 0.0F));

        PartDefinition modelPartData12 = modelPartData11.addOrReplaceChild(
                "rightWing04",
                CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-0.4F, -0.8F, -4.0F, 1.0F, 1.0F, 19.0F, true),
                PartPose.offsetAndRotation(0.0F, 0.0F, 7.5F, -0.7418F, 0.0F, 0.0F));

        modelPartData12.addOrReplaceChild(
                "rightWing04Leather",
                CubeListBuilder.create()
                        .texOffs(0, 28)
                        .addBox(0.0F, 0.0F, 0.0F, 0.0F, 5.0F, 14.0F, true),
                PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition modelPartData13 = modelPartData11.addOrReplaceChild(
                "rightWingStrut01",
                CubeListBuilder.create()
                        .texOffs(21, 0)
                        .addBox(-0.4F, -0.5F, -0.5F, 1.0F, 1.0F, 16.0F, true),
                PartPose.offsetAndRotation(0.0F, 1.0F, 7.0F, -1.0036F, 0.0F, 0.0F));

        modelPartData13.addOrReplaceChild(
                "rightWingStrut01Leather",
                CubeListBuilder.create()
                        .texOffs(0, 22)
                        .addBox(0.05F, 0.0F, 0.0F, 0.0F, 5.0F, 14.0F, true),
                PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition modelPartData14 = modelPartData11.addOrReplaceChild(
                "rightWingStrut02",
                CubeListBuilder.create()
                        .texOffs(21, 0)
                        .addBox(-0.4F, -0.5F, -0.5F, 1.0F, 1.0F, 16.0F, true),
                PartPose.offsetAndRotation(0.0F, 1.0F, 6.0F, -1.309F, 0.0F, 0.0F));

        modelPartData14.addOrReplaceChild(
                "rightWingStrut02Leather",
                CubeListBuilder.create()
                        .texOffs(0, 4)
                        .addBox(0.1F, 0.0F, -1.0F, 0.0F, 6.0F, 16.0F, true),
                PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition modelPartData15 = modelPartData11.addOrReplaceChild(
                "rightWingStrut03",
                CubeListBuilder.create()
                        .texOffs(21, 0)
                        .addBox(-0.4F, -0.5F, -0.5F, 1.0F, 1.0F, 16.0F, true),
                PartPose.offsetAndRotation(0.0F, 1.0F, 5.0F, -1.6581F, 0.0F, 0.0F));

        modelPartData15.addOrReplaceChild(
                "rightWingStrut03Leather",
                CubeListBuilder.create()
                        .texOffs(0, 12)
                        .addBox(0.15F, 0.0F, 0.0F, 0.0F, 6.0F, 15.0F, true),
                PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition modelPartData16 = modelPartData10.addOrReplaceChild(
                "rightStrutLowest",
                CubeListBuilder.create()
                        .texOffs(12, 0)
                        .addBox(0.1F, 0.0F, -0.5F, 1.0F, 12.0F, 1.0F, true),
                PartPose.offsetAndRotation(-0.5F, 1.0F, 2.0F, -0.3491F, 0.0F, 0.0F));

        modelPartData16.addOrReplaceChild(
                "rightStrutLowestLeather01",
                CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(0.0F, -1.0F, 0.0F, 0.0F, 12.0F, 6.0F, true),
                PartPose.offset(0.5F, 0.0F, 0.0F));

        modelPartData16.addOrReplaceChild(
                "rightStrutLowestLeather02",
                CubeListBuilder.create()
                        .texOffs(28, 28)
                        .addBox(0.0F, -1.0F, -9.0F, 0.0F, 12.0F, 9.0F, true),
                PartPose.offsetAndRotation(0.5F, 0.0F, 0.0F, 0.0F, 0.2618F, 0.0F));

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
