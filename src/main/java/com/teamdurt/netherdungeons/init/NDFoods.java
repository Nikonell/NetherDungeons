package com.teamdurt.netherdungeons.init;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class NDFoods {
    public static final FoodProperties ARTICHOKE = (new FoodProperties.Builder()).nutrition(8).saturationMod(0.9375f)
            .effect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 60), 1.0f).alwaysEat().build();
}
