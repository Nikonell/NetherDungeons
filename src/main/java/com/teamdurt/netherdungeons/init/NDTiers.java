package com.teamdurt.netherdungeons.init;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;

public class NDTiers {
    public static final ForgeTier TEMPERED_GOLD = new ForgeTier(4, 1561, 12f, 0f, 18,
            BlockTags.NEEDS_DIAMOND_TOOL, () -> Ingredient.of(NDItems.TEMPERED_GOLD_INGOT.get()));

    public static final ForgeTier WITHER_SKELETON_SWORD_TIER = new ForgeTier(2, 231, 8f, 0f, 12,
            BlockTags.NEEDS_IRON_TOOL, () -> null);
}
