package com.teamdurt.netherdungeons.event;

import com.teamdurt.netherdungeons.NetherDungeons;
import com.teamdurt.netherdungeons.init.NDBlocks;
import com.teamdurt.netherdungeons.effect.NDEffects;
import com.teamdurt.netherdungeons.init.NDItems;
import com.teamdurt.netherdungeons.sound.NDSounds;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.MagmaCube;
import net.minecraft.world.entity.monster.WitherSkeleton;
import net.minecraft.world.entity.monster.piglin.PiglinBrute;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = NetherDungeons.MOD_ID)
public class NDEventsHandler {
    @SubscribeEvent
    public static void giveAxeToPiglinBrute(EntityJoinLevelEvent event) {
        if (!event.getEntity().level.isClientSide() && event.getEntity() instanceof PiglinBrute entity) {
            float givingChance = 50f;
            if (Math.random() * 100 <= givingChance) {
                entity.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(NDItems.TEMPERED_GOLD_AXE.get()));
            }
        }
    }

    @SubscribeEvent
    public static void giveSwordToWitherSkeleton(EntityJoinLevelEvent event) {
        if (!event.getEntity().level.isClientSide() && event.getEntity() instanceof WitherSkeleton entity) {
            float givingChance = 50f;
            if (Math.random() * 100 <= givingChance) {
                entity.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(NDItems.WITHER_SKELETON_SWORD.get()));
            }
        }
    }

    @SubscribeEvent
    public static void catchMagmaCube(PlayerInteractEvent.EntityInteract event) {
        Player player = event.getEntity();
        if (!player.level.isClientSide() && event.getTarget() instanceof MagmaCube entity && entity.getSize() <= 1) {
            InteractionHand hand;
            if (player.getMainHandItem().is(NDBlocks.MAGMACUBE_LANTERN_EMPTY.get().asItem())) {
                hand = InteractionHand.MAIN_HAND;
                player.swing(InteractionHand.MAIN_HAND, true);
            } else if (player.getOffhandItem().is(NDBlocks.MAGMACUBE_LANTERN_EMPTY.get().asItem())) {
                hand = InteractionHand.OFF_HAND;
                player.swing(InteractionHand.OFF_HAND, true);
            } else {
                return;
            }
            int empty_lanterns_count = player.getItemInHand(hand).getCount();
            ItemStack lantern_stack = new ItemStack(NDBlocks.MAGMACUBE_LANTERN.get().asItem());
            if (player.isCreative()) {
                player.addItem(lantern_stack);
            } else if (empty_lanterns_count > 1) {
                player.getItemInHand(hand).setCount(empty_lanterns_count - 1);
                player.addItem(lantern_stack);
            } else {
                player.setItemInHand(hand, lantern_stack);
            }
            entity.remove(Entity.RemovalReason.DISCARDED);
        }
    }

    @SubscribeEvent
    public static void applyCursedBowEffect(LivingAttackEvent event) {
        if (event.getEntity().level.isClientSide() || event.getSource().getEntity() == null || event.getSource().getDirectEntity() == null || !event.getSource().getEntity().getHandSlots().iterator().hasNext() || !event.getSource().getDirectEntity().getType().equals(EntityType.ARROW)) {
            return;
        }
        boolean isKeepBow = event.getSource().getEntity().getHandSlots().iterator().next().is(NDItems.CURSED_BOW.get());
        if (isKeepBow) {
            event.getEntity().addEffect(new MobEffectInstance(MobEffects.WITHER, 100));
        }
    }

    @SubscribeEvent
    public static void applyVampirismEffect(LivingDeathEvent event) {
        if (event.getEntity().level.isClientSide() || event.getSource().getEntity() == null) {
            return;
        }
        LivingEntity killer = (LivingEntity) event.getSource().getEntity();
        LivingEntity entity = event.getEntity();
        if (killer.hasEffect(NDEffects.VAMPIRISM.get())) {
            int effectAmplifier = killer.getEffect(NDEffects.VAMPIRISM.get()).getAmplifier();
            int healPercentage = (effectAmplifier + 1) * 5 + 5;
            float healthToHeal = entity.getMaxHealth() * healPercentage / 100;
            killer.heal(healthToHeal);
            killer.level.playSound((Player)null, killer.blockPosition(), NDSounds.VAMPIRISM.get(), SoundSource.PLAYERS,
                    1.0f, 1.0f);
        }
    }
}
