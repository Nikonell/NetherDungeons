package com.teamdurt.netherdungeons.client.entity.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.teamdurt.netherdungeons.NetherDungeons;
import com.teamdurt.netherdungeons.client.entity.custom.BabyGhastEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;


public class BabyGhastModel<T extends BabyGhastEntity> extends EntityModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(NetherDungeons.MOD_ID, "baby_ghast"), "main");
    private final ModelPart right_legs;
    private final ModelPart back_legs2;
    private final ModelPart left_legs;
    private final ModelPart front_legs2;
    private final ModelPart body;

    public BabyGhastModel(ModelPart root) {
        this.right_legs = root.getChild("right_legs");
        this.back_legs2 = root.getChild("back_legs2");
        this.left_legs = root.getChild("left_legs");
        this.front_legs2 = root.getChild("front_legs2");
        this.body = root.getChild("body");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition right_legs = partdefinition.addOrReplaceChild("right_legs", CubeListBuilder.create().texOffs(6, 6).addBox(0.0F, 0.0F, -3.0F, 0.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 22.0F, 0.0F));

        PartDefinition back_legs2 = partdefinition.addOrReplaceChild("back_legs2", CubeListBuilder.create().texOffs(6, 12).addBox(-3.0F, 0.0F, 0.0F, 6.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 22.0F, 3.0F));

        PartDefinition left_legs = partdefinition.addOrReplaceChild("left_legs", CubeListBuilder.create().texOffs(6, 6).addBox(0.0F, 0.0F, -3.0F, 0.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 22.0F, 0.0F));

        PartDefinition front_legs2 = partdefinition.addOrReplaceChild("front_legs2", CubeListBuilder.create().texOffs(6, 12).addBox(-3.0F, 0.0F, 0.0F, 6.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 22.0F, -3.0F));

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -3.0F, -3.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 19.0F, 0.0F));

        PartDefinition heart = body.addOrReplaceChild("heart", CubeListBuilder.create().texOffs(24, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 32, 32);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float heartScale = 1 + (Mth.cos(ageInTicks * 0.07F + 10) / 5.2F);
        float bodySwing = 20 + Mth.cos(ageInTicks * 0.07F + 10.2F) / 2.2F;

        body.getChild("heart").xScale = heartScale;
        body.getChild("heart").yScale = heartScale;
        body.getChild("heart").zScale = heartScale;

        front_legs2.xRot = 0.9F - Mth.cos(ageInTicks * 0.07F + 10) / 4;
        back_legs2.xRot = 0.9F + Mth.cos(ageInTicks * 0.07F + 9.8F) / 4;

        left_legs.zRot = -0.6F - Mth.cos(ageInTicks * 0.07F + 10.4F) / 2.2F;
        right_legs.zRot = 0.6F + Mth.cos(ageInTicks * 0.07F + 10.2F) / 2.2F;



        if (entity.getDeltaMovement().x == 0 && entity.getDeltaMovement().z == 0) {
            front_legs2.xRot = -0.6F - Mth.cos(ageInTicks * 0.07F + 10) / 2.2F;
            back_legs2.xRot = 0.6F + Mth.cos(ageInTicks * 0.07F + 9.8F) / 2.2F;

            body.y = bodySwing;
            front_legs2.y = 3 + bodySwing;
            back_legs2.y = 3 + bodySwing;
            left_legs.y = 3 + bodySwing;
            right_legs.y = 3 + bodySwing;
        }
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        right_legs.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        back_legs2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        left_legs.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        front_legs2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
