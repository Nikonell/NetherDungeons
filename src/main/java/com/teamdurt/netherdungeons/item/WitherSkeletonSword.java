package com.teamdurt.netherdungeons.item;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public class WitherSkeletonSword extends SwordItem {
    public WitherSkeletonSword(Tier tier, int i, float v, Properties properties) {
        super(tier, i, v, properties);
    }

    @Override
    public boolean hurtEnemy(ItemStack itemStack, LivingEntity entity, LivingEntity source) {
        boolean hasWitherEffect = entity.hasEffect(MobEffects.WITHER);
        if (hasWitherEffect) {
            entity.hurt(DamageSource.WITHER, 3f);
        }
        return super.hurtEnemy(itemStack, entity, source);
    }
}

