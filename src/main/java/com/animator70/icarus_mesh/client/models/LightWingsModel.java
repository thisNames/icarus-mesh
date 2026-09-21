// Made with Model Converter by Globox_Z
// Model by cybercat5555

// 我的类
package com.animator70.icarus_mesh.client.models;

// Minecraft 类
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.world.entity.LivingEntity;

/**
 * 翅膀模型
 * LightWingsModel
 * 
 * @param <T> LivingEntity
 */
public class LightWingsModel<T extends LivingEntity> extends WingEntityModel<T> {

    public LightWingsModel(net.minecraft.client.model.geom.ModelPart root) {
        super(root);
    }

    @SuppressWarnings("null")
    public static LayerDefinition getLayerDefinition() {
        MeshDefinition modelData = getModelData();
        PartDefinition modelPartData = modelData.getRoot();

        PartDefinition modelPartData1 = modelPartData
                .getChild("rightWing")
                .addOrReplaceChild(
                        "rWingMain",
                        CubeListBuilder.create(),
                        PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.836332F));

        modelPartData1.addOrReplaceChild(
                "rWing01",
                CubeListBuilder.create()
                        .texOffs(26, 18)
                        .addBox(-1.0F, -4.0F, 0.0F, 2.0F, 13.0F, 0.0F, true),
                PartPose.offsetAndRotation(-3.0F, -1.0F, 1.0F, 0.0F, 0.0F, 0.3054F));

        modelPartData1.addOrReplaceChild(
                "rWing02",
                CubeListBuilder.create()
                        .texOffs(25, 12)
                        .addBox(-1.5F, -4.0F, 0.0F, 3.0F, 19.0F, 0.0F, true),
                PartPose.offsetAndRotation(-6.0F, -3.0F, 1.0F, 0.0F, 0.0F, 0.48F));

        modelPartData1.addOrReplaceChild(
                "rWing03",
                CubeListBuilder.create()
                        .texOffs(25, 8)
                        .addBox(-1.5F, -4.0F, 0.0F, 3.0F, 23.0F, 0.0F, true),
                PartPose.offsetAndRotation(-9.5F, -5.0F, 1.0F, 0.0F, 0.0F, 0.6981F));

        modelPartData1.addOrReplaceChild(
                "rWing04",
                CubeListBuilder.create()
                        .texOffs(19, 16)
                        .addBox(-1.0F, -4.0F, 0.0F, 2.0F, 15.0F, 0.0F, true),
                PartPose.offsetAndRotation(-12.0F, -7.75F, 1.0F, 0.0F, 0.0F, 0.9599F));

        modelPartData1.addOrReplaceChild(
                "rWing05",
                CubeListBuilder.create()
                        .texOffs(12, 21)
                        .addBox(-0.5F, -2.0F, 0.0F, 2.0F, 10.0F, 0.0F, true),
                PartPose.offsetAndRotation(-12.25F, -11.75F, 1.0F, 0.0F, 0.0F, 1.0908F));

        PartDefinition modelPartData2 = modelPartData
                .getChild("leftWing")
                .addOrReplaceChild(
                        "lWingMain",
                        CubeListBuilder.create(),
                        PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.836332F));

        modelPartData2.addOrReplaceChild(
                "lWing01",
                CubeListBuilder.create()
                        .texOffs(26, 18)
                        .addBox(-1.0F, -4.0F, 0.0F, 2.0F, 13.0F, 0.0F),
                PartPose.offsetAndRotation(3.0F, -1.0F, 1.0F, 0.0F, 0.0F, -0.3054F));

        modelPartData2.addOrReplaceChild(
                "lWing02",
                CubeListBuilder.create()
                        .texOffs(25, 12)
                        .addBox(-1.5F, -4.0F, 0.0F, 3.0F, 19.0F, 0.0F),
                PartPose.offsetAndRotation(6.0F, -3.0F, 1.0F, 0.0F, 0.0F, -0.48F));

        modelPartData2.addOrReplaceChild(
                "lWing03",
                CubeListBuilder.create()
                        .texOffs(25, 8)
                        .addBox(-1.5F, -4.0F, 0.0F, 3.0F, 23.0F, 0.0F),
                PartPose.offsetAndRotation(9.5F, -5.0F, 1.0F, 0.0F, 0.0F, -0.6981F));

        modelPartData2.addOrReplaceChild(
                "lWing04",
                CubeListBuilder.create()
                        .texOffs(19, 16)
                        .addBox(-1.0F, -4.0F, 0.0F, 2.0F, 15.0F, 0.0F),
                PartPose.offsetAndRotation(12.0F, -7.75F, 1.0F, 0.0F, 0.0F, -0.9599F));

        modelPartData2.addOrReplaceChild(
                "lWing05",
                CubeListBuilder.create()
                        .texOffs(12, 21)
                        .addBox(-1.5F, -2.0F, 0.0F, 2.0F, 10.0F, 0.0F),
                PartPose.offsetAndRotation(12.25F, -11.75F, 1.0F, 0.0F, 0.0F, -1.0908F));

        return LayerDefinition.create(modelData, 32, 32);
    }
}
