package com.teamdurt.netherdungeons.client.entity.renderer;

import com.teamdurt.netherdungeons.NetherDungeons;
import com.teamdurt.netherdungeons.client.entity.custom.BabyGhastEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.teamdurt.netherdungeons.client.entity.model.BabyGhastModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class BabyGhastRenderer extends GeoEntityRenderer<BabyGhastEntity> {
    public BabyGhastRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new BabyGhastModel());
        this.shadowRadius = 0.2f;
    }

    @Override
    public ResourceLocation getTextureLocation(BabyGhastEntity instance) {
        return new ResourceLocation(NetherDungeons.MOD_ID, "textures/entity/baby_ghast/baby_ghast.png");
    }

    @Override
    public RenderType getRenderType(BabyGhastEntity animatable, float partialTicks, PoseStack stack, @Nullable MultiBufferSource renderTypeBuffer, @Nullable VertexConsumer vertexBuilder, int packedLightIn, ResourceLocation textureLocation) {
        stack.scale(1f, 1f, 1f);
        return RenderType.entityTranslucent(textureLocation);
    }
}
