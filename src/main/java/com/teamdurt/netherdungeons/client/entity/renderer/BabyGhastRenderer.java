package com.teamdurt.netherdungeons.client.entity.renderer;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import com.teamdurt.netherdungeons.NetherDungeons;
import com.teamdurt.netherdungeons.client.entity.custom.BabyGhastEntity;
import com.teamdurt.netherdungeons.client.entity.model.BabyGhastModel;

import javax.annotation.Nullable;

public class BabyGhastRenderer extends MobRenderer<BabyGhastEntity, BabyGhastModel<BabyGhastEntity>> {

    protected static final ResourceLocation TEXTURE = new ResourceLocation(NetherDungeons.MOD_ID, "textures/entity/baby_ghast/baby_ghast.png");

    public BabyGhastRenderer(EntityRendererProvider.Context context) {
        super(context, new BabyGhastModel<>(context.bakeLayer(BabyGhastModel.LAYER_LOCATION)), 0.2F);
    }

    @Override
    public ResourceLocation getTextureLocation(BabyGhastEntity entity) {
        return TEXTURE;
    }

    @Override
    protected RenderType getRenderType(BabyGhastEntity babyGhastEntity, boolean b, boolean b1, boolean b2) {
        return RenderType.entityTranslucent(TEXTURE);
    }
}
