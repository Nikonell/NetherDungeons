package com.teamdurt.netherdungeons.client.entity.model;

import com.teamdurt.netherdungeons.NetherDungeons;
import com.teamdurt.netherdungeons.client.entity.custom.BabyGhastEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class BabyGhastModel extends AnimatedGeoModel<BabyGhastEntity> {
    @Override
    public ResourceLocation getModelResource(BabyGhastEntity object) {
        return new ResourceLocation(NetherDungeons.MOD_ID, "geo/baby_ghast.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(BabyGhastEntity object) {
        return new ResourceLocation(NetherDungeons.MOD_ID, "textures/entity/baby_ghast/baby_ghast.png");
    }

    @Override
    public ResourceLocation getAnimationResource(BabyGhastEntity animatable) {
        return new ResourceLocation(NetherDungeons.MOD_ID, "animations/baby_ghast.animation.json");
    }
}
