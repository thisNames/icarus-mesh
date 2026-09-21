// Made with Model Converter by Globox_Z
// Model by cybercat5555

// 我的类
package com.animator70.icarus_mesh.client.models;

// Minecraft 类
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.LivingEntity;

/**
 * 翅膀模型
 * FlandresWingsModel
 * 
 * @param <T> LivingEntity
 */
public class FlandresWingsModel<T extends LivingEntity> extends WingEntityModel<T> {
    private final ModelPart leftWing03;
    private final ModelPart rightWing03;

    public FlandresWingsModel(ModelPart root) {
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
                                .texOffs(11, 0)
                                .addBox(-1.0F, -1.0F, -1.0F, 2.0F, 3.0F, 6.0F),
                        PartPose.offsetAndRotation(-6.0F, 0.0F, 0.0F, 0.0F, 1.5708F, 0.436332F));

        PartDefinition modelPartData2 = modelPartData1.addOrReplaceChild(
                "leftWing02",
                CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-1.0F, -1.0F, -1.0F, 1.0F, 2.0F, 9.0F),
                PartPose.offsetAndRotation(0.0F, 0.5F, 5.0F, 0.2618F, 0.3054F, 0.0F));

        PartDefinition modelPartData3 = modelPartData2.addOrReplaceChild(
                "leftWing03",
                CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-0.6F, -1.8F, 0.0F, 1.0F, 2.0F, 9.0F),
                PartPose.offsetAndRotation(-0.5F, 0.5F, 8.0F, 0.7854F, 0.0F, 0.0F));

        PartDefinition modelPartData4 = modelPartData3.addOrReplaceChild(
                "leftWing04",
                CubeListBuilder.create()
                        .texOffs(0, 21)
                        .addBox(-0.7F, 0.0F, -0.6F, 1.0F, 11.0F, 1.0F),
                PartPose.offsetAndRotation(0.0F, -1.0F, 9.0F, 0.6981F, 0.0F, 0.0F));

        PartDefinition modelPartData5 = modelPartData4.addOrReplaceChild(
                "leftWing05",
                CubeListBuilder.create()
                        .texOffs(0, 21)
                        .addBox(-0.8F, -0.3F, -0.7F, 1.0F, 11.0F, 1.0F),
                PartPose.offsetAndRotation(0.0F, 10.8F, 0.0F, -0.3054F, 0.0F, 0.0F));

        PartDefinition modelPartData6 = modelPartData5.addOrReplaceChild(
                "lGem7",
                CubeListBuilder.create(),
                PartPose.offsetAndRotation(0.0308F, 2.2372F, 0.0333F, -0.9599F, 0.0F, 0.0F));

        modelPartData6.addOrReplaceChild(
                "sideB_r1",
                CubeListBuilder.create()
                        .texOffs(36, 30)
                        .addBox(-0.89F, -0.7F, -0.5F, 2.0F, 2.0F, 5.0F),
                PartPose.offsetAndRotation(-0.5F, 6.0F, 2.0F, -1.7453F, 0.0F, 0.0F));

        modelPartData6.addOrReplaceChild(
                "sideF_r1",
                CubeListBuilder.create()
                        .texOffs(36, 30)
                        .addBox(-0.88F, -1.6F, -0.5F, 2.0F, 2.0F, 5.0F),
                PartPose.offsetAndRotation(-0.5F, 6.0F, -1.0F, -1.3963F, 0.0F, 0.0F));

        modelPartData6.addOrReplaceChild(
                "largeTop_r1",
                CubeListBuilder.create()
                        .texOffs(40, 37)
                        .addBox(-0.9F, -0.5F, -0.3F, 2.0F, 3.0F, 3.0F),
                PartPose.offsetAndRotation(-0.5F, 4.0F, 0.5F, -0.7854F, 0.0F, 0.0F));

        modelPartData6.addOrReplaceChild(
                "point_r1",
                CubeListBuilder.create()
                        .texOffs(40, 43)
                        .addBox(-1.0F, -0.7F, -0.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.1F)),
                PartPose.offsetAndRotation(-0.5F, 10.0F, 0.5F, -0.7854F, 0.0F, 0.0F));

        modelPartData6.addOrReplaceChild(
                "top_r1",
                CubeListBuilder.create()
                        .texOffs(40, 43)
                        .addBox(-1.0F, -0.5F, -1.0F, 2.0F, 2.0F, 2.0F),
                PartPose.offsetAndRotation(-0.5F, 2.0F, 1.0F, -0.7854F, 0.0F, 0.0F));

        PartDefinition modelPartData7 = modelPartData5.addOrReplaceChild(
                "lGem8",
                CubeListBuilder.create(),
                PartPose.offsetAndRotation(0.4383F, 8.6075F, 0.5953F, -0.9599F, 0.0F, 0.0F));

        modelPartData7.addOrReplaceChild(
                "sideB_r2", CubeListBuilder.create()
                        .texOffs(0, 11)
                        .addBox(-0.89F, -0.7F, -0.5F, 2.0F, 2.0F, 5.0F),
                PartPose.offsetAndRotation(-0.5F, 6.0F, 2.0F, -1.7453F, 0.0F, 0.0F));

        modelPartData7.addOrReplaceChild(
                "sideF_r2",
                CubeListBuilder.create()
                        .texOffs(0, 11)
                        .addBox(-0.88F, -1.6F, -0.5F, 2.0F, 2.0F, 5.0F),
                PartPose.offsetAndRotation(-0.5F, 6.0F, -1.0F, -1.3963F, 0.0F, 0.0F));

        modelPartData7.addOrReplaceChild(
                "largeTop_r2",
                CubeListBuilder.create()
                        .texOffs(4, 18)
                        .addBox(-0.9F, -0.5F, -0.3F, 2.0F, 3.0F, 3.0F),
                PartPose.offsetAndRotation(-0.5F, 4.0F, 0.5F, -0.7854F, 0.0F, 0.0F));

        modelPartData7.addOrReplaceChild(
                "point_r2",
                CubeListBuilder.create()
                        .texOffs(4, 24)
                        .addBox(-1.0F, -0.7F, -0.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.1F)),
                PartPose.offsetAndRotation(-0.5F, 10.0F, 0.5F, -0.7854F, 0.0F, 0.0F));

        modelPartData7.addOrReplaceChild(
                "top_r2",
                CubeListBuilder.create()
                        .texOffs(4, 24)
                        .addBox(-1.0F, -0.5F, -1.0F, 2.0F, 2.0F, 2.0F),
                PartPose.offsetAndRotation(-0.5F, 2.0F, 1.0F, -0.7854F, 0.0F, 0.0F));

        PartDefinition modelPartData8 = modelPartData4.addOrReplaceChild(
                "lGem5",
                CubeListBuilder.create(),
                PartPose.offsetAndRotation(-0.142F, 0.7935F, 0.1272F, -1.0472F, 0.0F, 0.0F));

        modelPartData8.addOrReplaceChild(
                "sideB_r3",
                CubeListBuilder.create()
                        .texOffs(4, 30)
                        .addBox(-0.89F, -0.7F, -0.5F, 2.0F, 2.0F, 5.0F),
                PartPose.offsetAndRotation(-0.5F, 6.0F, 2.0F, -1.7453F, 0.0F, 0.0F));

        modelPartData8.addOrReplaceChild(
                "sideF_r3",
                CubeListBuilder.create()
                        .texOffs(4, 30)
                        .addBox(-0.88F, -1.6F, -0.5F, 2.0F, 2.0F, 5.0F),
                PartPose.offsetAndRotation(-0.5F, 6.0F, -1.0F, -1.3963F, 0.0F, 0.0F));

        modelPartData8.addOrReplaceChild(
                "largeTop_r3",
                CubeListBuilder.create()
                        .texOffs(8, 37)
                        .addBox(-0.9F, -0.5F, -0.3F, 2.0F, 3.0F, 3.0F),
                PartPose.offsetAndRotation(-0.5F, 4.0F, 0.5F, -0.7854F, 0.0F, 0.0F));

        modelPartData8.addOrReplaceChild(
                "point_r3",
                CubeListBuilder.create()
                        .texOffs(8, 43)
                        .addBox(-1.0F, -0.7F, -0.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.1F)),
                PartPose.offsetAndRotation(-0.5F, 10.0F, 0.5F, -0.7854F, 0.0F, 0.0F));

        modelPartData8.addOrReplaceChild(
                "top_r3",
                CubeListBuilder.create()
                        .texOffs(8, 43)
                        .addBox(-1.0F, -0.5F, -1.0F, 2.0F, 2.0F, 2.0F),
                PartPose.offsetAndRotation(-0.5F, 2.0F, 1.0F, -0.7854F, 0.0F, 0.0F));

        PartDefinition modelPartData9 = modelPartData4.addOrReplaceChild(
                "lGem6",
                CubeListBuilder.create(),
                PartPose.offsetAndRotation(0.3039F, 5.7996F, 0.357F, -1.0472F, 0.0F, 0.0F));

        modelPartData9.addOrReplaceChild(
                "sideB_r4",
                CubeListBuilder.create()
                        .texOffs(20, 30)
                        .addBox(-0.89F, -0.7F, -0.5F, 2.0F, 2.0F, 5.0F),
                PartPose.offsetAndRotation(-0.5F, 6.0F, 2.0F, -1.7453F, 0.0F, 0.0F));

        modelPartData9.addOrReplaceChild(
                "sideF_r4",
                CubeListBuilder.create()
                        .texOffs(20, 30)
                        .addBox(-0.88F, -1.6F, -0.5F, 2.0F, 2.0F, 5.0F),
                PartPose.offsetAndRotation(-0.5F, 6.0F, -1.0F, -1.3963F, 0.0F, 0.0F));

        modelPartData9.addOrReplaceChild(
                "largeTop_r4",
                CubeListBuilder.create()
                        .texOffs(24, 37)
                        .addBox(-0.9F, -0.5F, -0.3F, 2.0F, 3.0F, 3.0F),
                PartPose.offsetAndRotation(-0.5F, 4.0F, 0.5F, -0.7854F, 0.0F, 0.0F));

        modelPartData9.addOrReplaceChild(
                "point_r4",
                CubeListBuilder.create()
                        .texOffs(24, 43)
                        .addBox(-1.0F, -0.7F, -0.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.1F)),
                PartPose.offsetAndRotation(-0.5F, 10.0F, 0.5F, -0.7854F, 0.0F, 0.0F));

        modelPartData9.addOrReplaceChild(
                "top_r4",
                CubeListBuilder.create()
                        .texOffs(24, 43)
                        .addBox(-1.0F, -0.5F, -1.0F, 2.0F, 2.0F, 2.0F),
                PartPose.offsetAndRotation(-0.5F, 2.0F, 1.0F, -0.7854F, 0.0F, 0.0F));

        PartDefinition modelPartData10 = modelPartData3.addOrReplaceChild(
                "lGem4",
                CubeListBuilder.create(),
                PartPose.offsetAndRotation(-0.4345F, -0.814F, 3.3462F, -0.1309F, 0.0F, 0.0F));

        modelPartData10.addOrReplaceChild(
                "sideB_r5",
                CubeListBuilder.create()
                        .texOffs(45, 11)
                        .addBox(-0.89F, -0.7F, -0.5F, 2.0F, 2.0F, 5.0F),
                PartPose.offsetAndRotation(-0.5F, 6.0F, 2.0F, -1.7453F, 0.0F, 0.0F));

        modelPartData10.addOrReplaceChild(
                "sideF_r5",
                CubeListBuilder.create()
                        .texOffs(45, 11)
                        .addBox(-0.88F, -1.6F, -0.5F, 2.0F, 2.0F, 5.0F),
                PartPose.offsetAndRotation(-0.5F, 6.0F, -1.0F, -1.3963F, 0.0F, 0.0F));

        modelPartData10.addOrReplaceChild(
                "largeTop_r5",
                CubeListBuilder.create().texOffs(49, 18)
                        .addBox(-0.9F, -0.5F, -0.3F, 2.0F, 3.0F, 3.0F),
                PartPose.offsetAndRotation(-0.5F, 4.0F, 0.5F, -0.7854F, 0.0F, 0.0F));

        modelPartData10.addOrReplaceChild(
                "point_r5",
                CubeListBuilder.create()
                        .texOffs(49, 24)
                        .addBox(-1.0F, -0.7F, -0.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.1F)),
                PartPose.offsetAndRotation(-0.5F, 10.0F, 0.5F, -0.7854F, 0.0F, 0.0F));

        modelPartData10.addOrReplaceChild(
                "top_r5",
                CubeListBuilder.create()
                        .texOffs(49, 24)
                        .addBox(-1.0F, -0.5F, -1.0F, 2.0F, 2.0F, 2.0F),
                PartPose.offsetAndRotation(-0.5F, 2.0F, 1.0F, -0.7854F, 0.0F, 0.0F));

        PartDefinition modelPartData11 = modelPartData2.addOrReplaceChild(
                "lGem01", CubeListBuilder.create(),
                PartPose.offset(0.6635F, 0.2041F, -1.1535F));

        modelPartData11.addOrReplaceChild(
                "sideB_r6",
                CubeListBuilder.create()

                        .texOffs(0, 11)
                        .addBox(-0.89F, -0.7F, -0.5F, 2.0F, 2.0F, 5.0F),
                PartPose.offsetAndRotation(-0.5F, 6.0F, 2.0F, -1.7453F, 0.0F, 0.0F));

        modelPartData11.addOrReplaceChild(
                "sideF_r6",
                CubeListBuilder.create()
                        .texOffs(0, 11)
                        .addBox(-0.88F, -1.6F, -0.5F, 2.0F, 2.0F, 5.0F),
                PartPose.offsetAndRotation(-0.5F, 6.0F, -1.0F, -1.3963F, 0.0F, 0.0F));

        modelPartData11.addOrReplaceChild(
                "largeTop_r6",
                CubeListBuilder.create()
                        .texOffs(4, 18)
                        .addBox(-0.9F, -0.5F, -0.3F, 2.0F, 3.0F, 3.0F),
                PartPose.offsetAndRotation(-0.5F, 4.0F, 0.5F, -0.7854F, 0.0F, 0.0F));

        modelPartData11.addOrReplaceChild(
                "point_r6",
                CubeListBuilder.create()
                        .texOffs(4, 24)
                        .addBox(-1.0F, -0.7F, -0.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.1F)),
                PartPose.offsetAndRotation(-0.5F, 10.0F, 0.5F, -0.7854F, 0.0F, 0.0F));

        modelPartData11.addOrReplaceChild(
                "top_r6",
                CubeListBuilder.create()
                        .texOffs(4, 24)
                        .addBox(-1.0F, -0.5F, -1.0F, 2.0F, 2.0F, 2.0F),
                PartPose.offsetAndRotation(-0.5F, 2.0F, 1.0F, -0.7854F, 0.0F, 0.0F));

        PartDefinition modelPartData12 = modelPartData2.addOrReplaceChild(
                "lGem2",
                CubeListBuilder.create(),
                PartPose.offsetAndRotation(0.267F, -0.1566F, 4.027F, 0.0873F, 0.0F, 0.0F));

        modelPartData12.addOrReplaceChild(
                "sideB_r7",
                CubeListBuilder.create()
                        .texOffs(15, 11)
                        .addBox(-0.89F, -0.7F, -0.5F, 2.0F, 2.0F, 5.0F),
                PartPose.offsetAndRotation(-0.5F, 6.0F, 2.0F, -1.7453F, 0.0F, 0.0F));

        modelPartData12.addOrReplaceChild(
                "sideF_r7",
                CubeListBuilder.create()
                        .texOffs(15, 11)
                        .addBox(-0.88F, -1.6F, -0.5F, 2.0F, 2.0F, 5.0F),
                PartPose.offsetAndRotation(-0.5F, 6.0F, -1.0F, -1.3963F, 0.0F, 0.0F));

        modelPartData12.addOrReplaceChild(
                "largeTop_r7",
                CubeListBuilder.create()
                        .texOffs(19, 18)
                        .addBox(-0.9F, -0.5F, -0.3F, 2.0F, 3.0F, 3.0F),
                PartPose.offsetAndRotation(-0.5F, 4.0F, 0.5F, -0.7854F, 0.0F, 0.0F));

        modelPartData12.addOrReplaceChild(
                "point_r7",
                CubeListBuilder.create()
                        .texOffs(19, 24)
                        .addBox(-1.0F, -0.7F, -0.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.1F)),
                PartPose.offsetAndRotation(-0.5F, 10.0F, 0.5F, -0.7854F, 0.0F, 0.0F));

        modelPartData12.addOrReplaceChild(
                "top_r7",
                CubeListBuilder.create()
                        .texOffs(19, 24)
                        .addBox(-1.0F, -0.5F, -1.0F, 2.0F, 2.0F, 2.0F),
                PartPose.offsetAndRotation(-0.5F, 2.0F, 1.0F, -0.7854F, 0.0F, 0.0F));

        PartDefinition modelPartData13 = modelPartData2.addOrReplaceChild(
                "lGem3",
                CubeListBuilder.create(),
                PartPose.offsetAndRotation(0.1582F, -0.1549F, 7.1073F, 0.48F, 0.0F, 0.0F));
        modelPartData13.addOrReplaceChild(
                "sideB_r8",
                CubeListBuilder.create()
                        .texOffs(30, 11)
                        .addBox(-0.89F, -0.7F, -0.5F, 2.0F, 2.0F, 5.0F),
                PartPose.offsetAndRotation(-0.5F, 6.0F, 2.0F, -1.7453F, 0.0F, 0.0F));

        modelPartData13.addOrReplaceChild(
                "sideF_r8",
                CubeListBuilder.create()
                        .texOffs(30, 11)
                        .addBox(-0.88F, -1.6F, -0.5F, 2.0F, 2.0F, 5.0F),
                PartPose.offsetAndRotation(-0.5F, 6.0F, -1.0F, -1.3963F, 0.0F, 0.0F));

        modelPartData13.addOrReplaceChild(
                "largeTop_r8",
                CubeListBuilder.create()
                        .texOffs(34, 18)
                        .addBox(-0.9F, -0.5F, -0.3F, 2.0F, 3.0F, 3.0F),
                PartPose.offsetAndRotation(-0.5F, 4.0F, 0.5F, -0.7854F, 0.0F, 0.0F));

        modelPartData13.addOrReplaceChild(
                "point_r8",
                CubeListBuilder.create()
                        .texOffs(34, 24)
                        .addBox(-1.0F, -0.7F, -0.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.1F)),
                PartPose.offsetAndRotation(-0.5F, 10.0F, 0.5F, -0.7854F, 0.0F, 0.0F));

        modelPartData13.addOrReplaceChild(
                "top_r8",
                CubeListBuilder.create()
                        .texOffs(34, 24)
                        .addBox(-1.0F, -0.5F, -1.0F, 2.0F, 2.0F, 2.0F),
                PartPose.offsetAndRotation(-0.5F, 2.0F, 1.0F, -0.7854F, 0.0F, 0.0F));

        PartDefinition modelPartData14 = modelPartData
                .getChild("rightWing")
                .addOrReplaceChild(
                        "rightWing01",
                        CubeListBuilder.create()
                                .texOffs(11, 0)
                                .addBox(-1.0F, -1.0F, -1.0F, 2.0F, 3.0F, 6.0F, true),
                        PartPose.offsetAndRotation(6.0F, 0.0F, 0.0F, 0.0F, -1.5708F, -0.436332F));

        PartDefinition modelPartData15 = modelPartData14.addOrReplaceChild(
                "rightWing02",
                CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(0.0F, -1.0F, -1.0F, 1.0F, 2.0F, 9.0F, true),
                PartPose.offsetAndRotation(0.0F, 0.5F, 5.0F, 0.2618F, -0.3054F, 0.0F));

        PartDefinition modelPartData16 = modelPartData15.addOrReplaceChild(
                "rightWing03",
                CubeListBuilder.create()
                        .texOffs(0, 0)
                        .addBox(-0.4F, -1.8F, 0.0F, 1.0F, 2.0F, 9.0F, true),
                PartPose.offsetAndRotation(0.5F, 0.5F, 8.0F, 0.7854F, 0.0F, 0.0F));

        PartDefinition modelPartData17 = modelPartData16.addOrReplaceChild(
                "rightWing04",
                CubeListBuilder.create()
                        .texOffs(0, 21)
                        .addBox(-0.3F, 0.0F, -0.6F, 1.0F, 11.0F, 1.0F, true),
                PartPose.offsetAndRotation(0.0F, -1.0F, 9.0F, 0.6981F, 0.0F, 0.0F));

        PartDefinition modelPartData18 = modelPartData17.addOrReplaceChild(
                "rightWing05",
                CubeListBuilder.create()
                        .texOffs(0, 21)
                        .addBox(-0.2F, -0.3F, -0.7F, 1.0F, 11.0F, 1.0F, true),
                PartPose.offsetAndRotation(0.0F, 10.8F, 0.0F, -0.3054F, 0.0F, 0.0F));

        PartDefinition modelPartData19 = modelPartData18.addOrReplaceChild(
                "rGem07",
                CubeListBuilder.create(),
                PartPose.offsetAndRotation(-0.0308F, 2.2372F, 0.0333F, -0.9599F, 0.0F, 0.0F));

        modelPartData19.addOrReplaceChild(
                "sideB_r9",
                CubeListBuilder.create()
                        .texOffs(36, 30)
                        .addBox(-1.11F, -0.7F, -0.5F, 2.0F, 2.0F, 5.0F, true),
                PartPose.offsetAndRotation(0.5F, 6.0F, 2.0F, -1.7453F, 0.0F, 0.0F));

        modelPartData19.addOrReplaceChild(
                "sideF_r9",
                CubeListBuilder.create()
                        .texOffs(36, 30)
                        .addBox(-1.12F, -1.6F, -0.5F, 2.0F, 2.0F, 5.0F, true),
                PartPose.offsetAndRotation(0.5F, 6.0F, -1.0F, -1.3963F, 0.0F, 0.0F));
        modelPartData19.addOrReplaceChild(
                "largeTop_r9",
                CubeListBuilder.create()
                        .texOffs(40, 37)
                        .addBox(-1.1F, -0.5F, -0.3F, 2.0F, 3.0F, 3.0F, true),
                PartPose.offsetAndRotation(0.5F, 4.0F, 0.5F, -0.7854F, 0.0F, 0.0F));

        modelPartData19.addOrReplaceChild(
                "point_r9",
                CubeListBuilder.create()
                        .texOffs(40, 43)
                        .addBox(-1.0F, -0.7F, -0.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.1F)).mirror(),
                PartPose.offsetAndRotation(0.5F, 10.0F, 0.5F, -0.7854F, 0.0F, 0.0F));

        modelPartData19.addOrReplaceChild(
                "top_r9",
                CubeListBuilder.create()
                        .texOffs(40, 43)
                        .addBox(-1.0F, -0.5F, -1.0F, 2.0F, 2.0F, 2.0F, true),
                PartPose.offsetAndRotation(0.5F, 2.0F, 1.0F, -0.7854F, 0.0F, 0.0F));

        PartDefinition modelPartData20 = modelPartData18.addOrReplaceChild(
                "rGem08",
                CubeListBuilder.create(),
                PartPose.offsetAndRotation(-0.4383F, 8.6075F, 0.5953F, -0.9599F, 0.0F, 0.0F));

        modelPartData20.addOrReplaceChild(
                "sideB_r10",
                CubeListBuilder.create().texOffs(0, 11)
                        .addBox(-1.11F, -0.7F, -0.5F, 2.0F, 2.0F, 5.0F, true),
                PartPose.offsetAndRotation(0.5F, 6.0F, 2.0F, -1.7453F, 0.0F, 0.0F));

        modelPartData20.addOrReplaceChild(
                "sideF_r10",
                CubeListBuilder.create()
                        .texOffs(0, 11)
                        .addBox(-1.12F, -1.6F, -0.5F, 2.0F, 2.0F, 5.0F, true),
                PartPose.offsetAndRotation(0.5F, 6.0F, -1.0F, -1.3963F, 0.0F, 0.0F));

        modelPartData20.addOrReplaceChild(
                "largeTop_r10",
                CubeListBuilder.create()
                        .texOffs(4, 18)
                        .addBox(-1.1F, -0.5F, -0.3F, 2.0F, 3.0F, 3.0F, true),
                PartPose.offsetAndRotation(0.5F, 4.0F, 0.5F, -0.7854F, 0.0F, 0.0F));

        modelPartData20.addOrReplaceChild(
                "point_r10",
                CubeListBuilder.create()
                        .texOffs(4, 24)
                        .addBox(-1.0F, -0.7F, -0.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.1F)).mirror(),
                PartPose.offsetAndRotation(0.5F, 10.0F, 0.5F, -0.7854F, 0.0F, 0.0F));

        modelPartData20.addOrReplaceChild(
                "top_r10",
                CubeListBuilder.create()
                        .texOffs(4, 24)
                        .addBox(-1.0F, -0.5F, -1.0F, 2.0F, 2.0F, 2.0F, true),
                PartPose.offsetAndRotation(0.5F, 2.0F, 1.0F, -0.7854F, 0.0F, 0.0F));

        PartDefinition modelPartData21 = modelPartData17.addOrReplaceChild(
                "rGem05",
                CubeListBuilder.create(),
                PartPose.offsetAndRotation(0.142F, 0.7935F, 0.1272F, -1.0472F, 0.0F, 0.0F));

        modelPartData21.addOrReplaceChild(
                "sideB_r11",
                CubeListBuilder.create()
                        .texOffs(4, 30)
                        .addBox(-1.11F, -0.7F, -0.5F, 2.0F, 2.0F, 5.0F, true),
                PartPose.offsetAndRotation(0.5F, 6.0F, 2.0F, -1.7453F, 0.0F, 0.0F));

        modelPartData21.addOrReplaceChild(
                "sideF_r11",
                CubeListBuilder.create()
                        .texOffs(4, 30)
                        .addBox(-1.12F, -1.6F, -0.5F, 2.0F, 2.0F, 5.0F, true),
                PartPose.offsetAndRotation(0.5F, 6.0F, -1.0F, -1.3963F, 0.0F, 0.0F));

        modelPartData21.addOrReplaceChild(
                "largeTop_r11",
                CubeListBuilder.create().texOffs(8, 37)
                        .addBox(-1.1F, -0.5F, -0.3F, 2.0F, 3.0F, 3.0F, true),
                PartPose.offsetAndRotation(0.5F, 4.0F, 0.5F, -0.7854F, 0.0F, 0.0F));

        modelPartData21.addOrReplaceChild(
                "point_r11",
                CubeListBuilder.create()
                        .texOffs(8, 43)
                        .addBox(-1.0F, -0.7F, -0.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.1F)).mirror(),
                PartPose.offsetAndRotation(0.5F, 10.0F, 0.5F, -0.7854F, 0.0F, 0.0F));

        modelPartData21.addOrReplaceChild(
                "top_r11",
                CubeListBuilder.create()
                        .texOffs(8, 43)
                        .addBox(-1.0F, -0.5F, -1.0F, 2.0F, 2.0F, 2.0F, true),
                PartPose.offsetAndRotation(0.5F, 2.0F, 1.0F, -0.7854F, 0.0F, 0.0F));

        PartDefinition modelPartData22 = modelPartData17.addOrReplaceChild(
                "rGem06",
                CubeListBuilder.create(),
                PartPose.offsetAndRotation(-0.3039F, 5.7996F, 0.357F, -1.0472F, 0.0F, 0.0F));

        modelPartData22.addOrReplaceChild(
                "sideB_r12",
                CubeListBuilder.create()
                        .texOffs(20, 30)
                        .addBox(-1.11F, -0.7F, -0.5F, 2.0F, 2.0F, 5.0F, true),
                PartPose.offsetAndRotation(0.5F, 6.0F, 2.0F, -1.7453F, 0.0F, 0.0F));

        modelPartData22.addOrReplaceChild(
                "sideF_r12",
                CubeListBuilder.create()
                        .texOffs(20, 30)
                        .addBox(-1.12F, -1.6F, -0.5F, 2.0F, 2.0F, 5.0F, true),
                PartPose.offsetAndRotation(0.5F, 6.0F, -1.0F, -1.3963F, 0.0F, 0.0F));

        modelPartData22.addOrReplaceChild(
                "largeTop_r12",
                CubeListBuilder.create()
                        .texOffs(24, 37)
                        .addBox(-1.1F, -0.5F, -0.3F, 2.0F, 3.0F, 3.0F, true),
                PartPose.offsetAndRotation(0.5F, 4.0F, 0.5F, -0.7854F, 0.0F, 0.0F));

        modelPartData22.addOrReplaceChild(
                "point_r12",
                CubeListBuilder.create()
                        .texOffs(24, 43)
                        .addBox(-1.0F, -0.7F, -0.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.1F)).mirror(),
                PartPose.offsetAndRotation(0.5F, 10.0F, 0.5F, -0.7854F, 0.0F, 0.0F));

        modelPartData22.addOrReplaceChild(
                "top_r12",
                CubeListBuilder.create()
                        .texOffs(24, 43)
                        .addBox(-1.0F, -0.5F, -1.0F, 2.0F, 2.0F, 2.0F, true),
                PartPose.offsetAndRotation(0.5F, 2.0F, 1.0F, -0.7854F, 0.0F, 0.0F));

        PartDefinition modelPartData23 = modelPartData16.addOrReplaceChild(
                "rGem04",
                CubeListBuilder.create(),
                PartPose.offsetAndRotation(0.4345F, -0.814F, 3.3462F, -0.1309F, 0.0F, 0.0F));

        modelPartData23.addOrReplaceChild(
                "sideB_r13",
                CubeListBuilder.create()
                        .texOffs(45, 11)
                        .addBox(-1.11F, -0.7F, -0.5F, 2.0F, 2.0F, 5.0F, true),
                PartPose.offsetAndRotation(0.5F, 6.0F, 2.0F, -1.7453F, 0.0F, 0.0F));

        modelPartData23.addOrReplaceChild(
                "sideF_r13",
                CubeListBuilder.create()
                        .texOffs(45, 11)
                        .addBox(-1.12F, -1.6F, -0.5F, 2.0F, 2.0F, 5.0F, true),
                PartPose.offsetAndRotation(0.5F, 6.0F, -1.0F, -1.3963F, 0.0F, 0.0F));

        modelPartData23.addOrReplaceChild(
                "largeTop_r13",
                CubeListBuilder.create()
                        .texOffs(49, 18)
                        .addBox(-1.1F, -0.5F, -0.3F, 2.0F, 3.0F, 3.0F, true),
                PartPose.offsetAndRotation(0.5F, 4.0F, 0.5F, -0.7854F, 0.0F, 0.0F));

        modelPartData23.addOrReplaceChild(
                "point_r13",
                CubeListBuilder.create()
                        .texOffs(49, 24)
                        .addBox(-1.0F, -0.7F, -0.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.1F)).mirror(),
                PartPose.offsetAndRotation(0.5F, 10.0F, 0.5F, -0.7854F, 0.0F, 0.0F));

        modelPartData23.addOrReplaceChild(
                "top_r13",
                CubeListBuilder.create()
                        .texOffs(49, 24)
                        .addBox(-1.0F, -0.5F, -1.0F, 2.0F, 2.0F, 2.0F, true),
                PartPose.offsetAndRotation(0.5F, 2.0F, 1.0F, -0.7854F, 0.0F, 0.0F));

        PartDefinition modelPartData24 = modelPartData15.addOrReplaceChild(
                "rGem01",
                CubeListBuilder.create(),
                PartPose.offset(-0.6635F, 0.2041F, -1.1535F));

        modelPartData24.addOrReplaceChild(
                "sideB_r14",
                CubeListBuilder.create()
                        .texOffs(0, 11)
                        .addBox(-1.11F, -0.7F, -0.5F, 2.0F, 2.0F, 5.0F, true),
                PartPose.offsetAndRotation(0.5F, 6.0F, 2.0F, -1.7453F, 0.0F, 0.0F));

        modelPartData24.addOrReplaceChild(
                "sideF_r14",
                CubeListBuilder.create()
                        .texOffs(0, 11)
                        .addBox(-1.12F, -1.6F, -0.5F, 2.0F, 2.0F, 5.0F, true),
                PartPose.offsetAndRotation(0.5F, 6.0F, -1.0F, -1.3963F, 0.0F, 0.0F));

        modelPartData24.addOrReplaceChild(
                "largeTop_r14",
                CubeListBuilder.create()
                        .texOffs(4, 18)
                        .addBox(-1.1F, -0.5F, -0.3F, 2.0F, 3.0F, 3.0F, true),
                PartPose.offsetAndRotation(0.5F, 4.0F, 0.5F, -0.7854F, 0.0F, 0.0F));

        modelPartData24.addOrReplaceChild(
                "point_r14",
                CubeListBuilder.create()
                        .texOffs(4, 24)
                        .addBox(-1.0F, -0.7F, -0.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.1F)).mirror(),
                PartPose.offsetAndRotation(0.5F, 10.0F, 0.5F, -0.7854F, 0.0F, 0.0F));

        modelPartData24.addOrReplaceChild(
                "top_r14",
                CubeListBuilder.create()
                        .texOffs(4, 24)
                        .addBox(-1.0F, -0.5F, -1.0F, 2.0F, 2.0F, 2.0F, true),
                PartPose.offsetAndRotation(0.5F, 2.0F, 1.0F, -0.7854F, 0.0F, 0.0F));

        PartDefinition modelPartData25 = modelPartData15.addOrReplaceChild(
                "rGem02",
                CubeListBuilder.create(),
                PartPose.offsetAndRotation(-0.267F, -0.1566F, 4.027F, 0.0873F, 0.0F, 0.0F));

        modelPartData25.addOrReplaceChild(
                "sideB_r15",
                CubeListBuilder.create()
                        .texOffs(15, 11)
                        .addBox(-1.11F, -0.7F, -0.5F, 2.0F, 2.0F, 5.0F, true),
                PartPose.offsetAndRotation(0.5F, 6.0F, 2.0F, -1.7453F, 0.0F, 0.0F));

        modelPartData25.addOrReplaceChild(
                "sideF_r15",
                CubeListBuilder.create()
                        .texOffs(15, 11)
                        .addBox(-1.12F, -1.6F, -0.5F, 2.0F, 2.0F, 5.0F, true),
                PartPose.offsetAndRotation(0.5F, 6.0F, -1.0F, -1.3963F, 0.0F, 0.0F));

        modelPartData25.addOrReplaceChild(
                "largeTop_r15",
                CubeListBuilder.create()
                        .texOffs(19, 18)
                        .addBox(-1.1F, -0.5F, -0.3F, 2.0F, 3.0F, 3.0F, true),
                PartPose.offsetAndRotation(0.5F, 4.0F, 0.5F, -0.7854F, 0.0F, 0.0F));

        modelPartData25.addOrReplaceChild(
                "point_r15",
                CubeListBuilder.create()
                        .texOffs(19, 24)
                        .addBox(-1.0F, -0.7F, -0.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.1F)).mirror(),
                PartPose.offsetAndRotation(0.5F, 10.0F, 0.5F, -0.7854F, 0.0F, 0.0F));

        modelPartData25.addOrReplaceChild(
                "top_r15",
                CubeListBuilder.create()
                        .texOffs(19, 24)
                        .addBox(-1.0F, -0.5F, -1.0F, 2.0F, 2.0F, 2.0F, true),
                PartPose.offsetAndRotation(0.5F, 2.0F, 1.0F, -0.7854F, 0.0F, 0.0F));

        PartDefinition modelPartData26 = modelPartData15.addOrReplaceChild(
                "rGem03",
                CubeListBuilder.create(),
                PartPose.offsetAndRotation(-0.1582F, -0.1549F, 7.1073F, 0.48F, 0.0F, 0.0F));

        modelPartData26.addOrReplaceChild(
                "sideB_r16",
                CubeListBuilder.create()
                        .texOffs(30, 11)
                        .addBox(-1.11F, -0.7F, -0.5F, 2.0F, 2.0F, 5.0F, true),
                PartPose.offsetAndRotation(0.5F, 6.0F, 2.0F, -1.7453F, 0.0F, 0.0F));

        modelPartData26.addOrReplaceChild(
                "sideF_r16",
                CubeListBuilder.create()
                        .texOffs(30, 11)
                        .addBox(-1.12F, -1.6F, -0.5F, 2.0F, 2.0F, 5.0F, true),
                PartPose.offsetAndRotation(0.5F, 6.0F, -1.0F, -1.3963F, 0.0F, 0.0F));

        modelPartData26.addOrReplaceChild(
                "largeTop_r16",
                CubeListBuilder.create()
                        .texOffs(34, 18)
                        .addBox(-1.1F, -0.5F, -0.3F, 2.0F, 3.0F, 3.0F, true),
                PartPose.offsetAndRotation(0.5F, 4.0F, 0.5F, -0.7854F, 0.0F, 0.0F));

        modelPartData26.addOrReplaceChild(
                "point_r16",
                CubeListBuilder.create()
                        .texOffs(34, 24)
                        .addBox(-1.0F, -0.7F, -0.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(-0.1F)).mirror(),
                PartPose.offsetAndRotation(0.5F, 10.0F, 0.5F, -0.7854F, 0.0F, 0.0F));

        modelPartData26.addOrReplaceChild(
                "top_r16",
                CubeListBuilder.create()
                        .texOffs(34, 24)
                        .addBox(-1.0F, -0.5F, -1.0F, 2.0F, 2.0F, 2.0F, true),
                PartPose.offsetAndRotation(0.5F, 2.0F, 1.0F, -0.7854F, 0.0F, 0.0F));

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
            leftWing03.xRot = (float) Math.toRadians(-50);
        }
        if (state == State.FLYING) {
            leftWing03.xRot = (float) Math.toRadians(45);
        }

        rightWing03.xRot = leftWing03.xRot;
    }
}
