package com.teamdurt.netherdungeons.item;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;

public class CursedBow extends BowItem {
    public CursedBow(Properties properties) {
        super(properties);
    }

    @Override
    public void releaseUsing(ItemStack itemstack, Level world, LivingEntity entityLiving, int timeLeft) {
        if (!world.isClientSide() && entityLiving instanceof ServerPlayer entity) {
            double x = entity.getX();
            double y = entity.getY();
            double z = entity.getZ();
            if (true) {
                ItemStack stack = ProjectileWeaponItem.getHeldProjectile(entity, e -> e.getItem() == Items.ARROW);
                if (stack == ItemStack.EMPTY) {
                    for (int i = 0; i < entity.getInventory().items.size(); i++) {
                        ItemStack teststack = entity.getInventory().items.get(i);
                        if (teststack != null && teststack.getItem() == Items.ARROW) {
                            stack = teststack;
                            break;
                        }
                    }
                }
                if (entity.getAbilities().instabuild || stack != ItemStack.EMPTY) {
                    if (new ItemStack(Items.ARROW).isDamageableItem()) {
                        if (stack.hurt(1, world.getRandom(), entity)) {
                            stack.shrink(1);
                            stack.setDamageValue(0);
                            if (stack.isEmpty())
                                entity.getInventory().removeItem(stack);
                        }
                    } else {
                        stack.shrink(1);
                        if (stack.isEmpty())
                            entity.getInventory().removeItem(stack);
                    }
                }
            }
        }
    }
}

