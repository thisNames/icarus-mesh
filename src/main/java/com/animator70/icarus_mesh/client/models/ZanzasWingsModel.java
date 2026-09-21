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
 * ZanzasWingsModel
 * 
 * @param <T> LivingEntity
 */
public class ZanzasWingsModel<T extends LivingEntity> extends WingEntityModel<T> {

    public ZanzasWingsModel(ModelPart root) {
        super(root);
    }

    @SuppressWarnings("null")
    public static LayerDefinition getLayerDefinition() {
        MeshDefinition modelData = getModelData();
        PartDefinition modelPartData = modelData.getRoot();

        PartDefinition modelPartData1 = modelPartData
                .getChild("leftWing")
                .addOrReplaceChild(
                        "lWing01",
                        CubeListBuilder.create()
                                .texOffs(0, 18)
                                .addBox(0.0F, -5.0F, 0.25F, 14.0F, 15.0F, 0.0F),
                        PartPose.offsetAndRotation(-6.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.436332F));

        PartDefinition modelPartData2 = modelPartData1.addOrReplaceChild(
                "lWing02",
                CubeListBuilder.create()
                        .texOffs(29, 17)
                        .addBox(0.0F, -6.0F, 0.25F, 17.0F, 17.0F, 0.0F),
                PartPose.offsetAndRotation(14.0F, 0.0F, 0.0F, 0.0F, -0.2182F, 0.0F));

        PartDefinition modelPartData3 = modelPartData2.addOrReplaceChild(
                "lWingCircle",
                CubeListBuilder.create(),
                PartPose.offsetAndRotation(2.0F, 6.0F, 1.0F, 0.0F, 0.0F, 0.0436F));

        modelPartData3.addOrReplaceChild(
                "cube_r1",
                CubeListBuilder.create()
                        .texOffs(0, 34)
                        .addBox(-7.5F, -8.5F, 0.25F, 13.0F, 13.0F, 0.0F),
                PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -2.3562F));

        PartDefinition modelPartData4 = modelPartData
                .getChild("rightWing")
                .addOrReplaceChild(
                        "rWing01",
                        CubeListBuilder.create()
                                .texOffs(0, 18)
                                .addBox(-14.0F, -5.0F, 0.25F, 14.0F, 15.0F, 0.0F, true),
                        PartPose.offsetAndRotation(6.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.436332F));

        PartDefinition modelPartData5 = modelPartData4.addOrReplaceChild(
                "rWing02",
                CubeListBuilder.create()
                        .texOffs(29, 17)
                        .addBox(-17.0F, -6.0F, 0.25F, 17.0F, 17.0F, 0.0F, true),
                PartPose.offsetAndRotation(-14.0F, 0.0F, 0.0F, 0.0F, 0.2182F, 0.0F));

        PartDefinition modelPartData6 = modelPartData5.addOrReplaceChild(
                "rWingCircle",
                CubeListBuilder.create(),
                PartPose.offsetAndRotation(-2.0F, 6.0F, 1.0F, 0.0F, 0.0F, -0.0436F));

        modelPartData6.addOrReplaceChild(
                "cube_r2",
                CubeListBuilder.create()
                        .texOffs(0, 34)
                        .addBox(-5.5F, -8.5F, 0.25F, 13.0F, 13.0F, 0.0F, true),
                PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 2.3562F));

        return LayerDefinition.create(modelData, 64, 64);
    }
}
