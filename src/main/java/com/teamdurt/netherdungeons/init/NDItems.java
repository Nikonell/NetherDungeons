package com.teamdurt.netherdungeons.init;

import com.teamdurt.netherdungeons.NetherDungeons;
import com.teamdurt.netherdungeons.item.WitherSkeletonSword;
import net.minecraft.world.item.*;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class NDItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, NetherDungeons.MOD_ID);

    public static final RegistryObject<Item> WARPED_NETHER_WART = ITEMS.register("warped_nether_wart",
            () -> new BlockItem(NDBlocks.WARPED_NETHER_WART.get(), new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> TEMPERED_GOLD_INGOT = ITEMS.register("tempered_gold_ingot",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> TEMPERED_GOLD_NUGGET = ITEMS.register("tempered_gold_nugget",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> TEMPERED_GOLD_SWORD = ITEMS.register("tempered_gold_sword",
            () -> new SwordItem(NDTiers.TEMPERED_GOLD, 6, -2.4f, new Item.Properties().tab(CreativeModeTab.TAB_COMBAT)));

    public static final RegistryObject<Item> TEMPERED_GOLD_PICKAXE = ITEMS.register("tempered_gold_pickaxe",
            () -> new PickaxeItem(NDTiers.TEMPERED_GOLD, 4, -2.8f, new Item.Properties().tab(CreativeModeTab.TAB_TOOLS)));

    public static final RegistryObject<Item> TEMPERED_GOLD_AXE = ITEMS.register("tempered_gold_axe",
            () -> new AxeItem(NDTiers.TEMPERED_GOLD, 8, -3f, new Item.Properties().tab(CreativeModeTab.TAB_TOOLS)));

    public static final RegistryObject<Item> TEMPERED_GOLD_SHOVEL = ITEMS.register("tempered_gold_shovel",
            () -> new ShovelItem(NDTiers.TEMPERED_GOLD, 4.5f, -3f, new Item.Properties().tab(CreativeModeTab.TAB_TOOLS)));

    public static final RegistryObject<Item> TEMPERED_GOLD_HOE = ITEMS.register("tempered_gold_hoe",
            () -> new HoeItem(NDTiers.TEMPERED_GOLD, 0, 0f, new Item.Properties().tab(CreativeModeTab.TAB_TOOLS)));

    public static final RegistryObject<Item> WITHER_SKELETON_SWORD = ITEMS.register("wither_skeleton_sword",
            () -> new WitherSkeletonSword(NDTiers.WITHER_SKELETON_SWORD_TIER, 6, -2.4f, new Item.Properties().tab(CreativeModeTab.TAB_COMBAT)));

    public static final RegistryObject<Item> CURSED_BOW = ITEMS.register("cursed_bow",
            () -> new BowItem(new Item.Properties().tab(CreativeModeTab.TAB_COMBAT).stacksTo(1)));

    public static final RegistryObject<Item> BABY_GHAST_SPAWN_EGG = ITEMS.register("baby_ghast_spawn_egg",
            () -> new ForgeSpawnEggItem(NDEntityTypes.BABY_GHAST, 0xFFF3F1ED, 0xFFE7BEBE,
                    new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static final RegistryObject<Item> ARTICHOKE_FRUIT = ITEMS.register("artichoke_fruit",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_FOOD).food(NDFoods.ARTICHOKE)));

    public static final RegistryObject<Item> MAGMACUBE_LANTERN = ITEMS.register("magmacube_lantern",
            () -> new BlockItem(NDBlocks.MAGMACUBE_LANTERN.get(),
                    new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS).stacksTo(16)));

    public static final RegistryObject<Item> MAGMACUBE_LANTERN_EMPTY = ITEMS.register("magmacube_lantern_empty",
            () -> new BlockItem(NDBlocks.MAGMACUBE_LANTERN_EMPTY.get(),
                    new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS).stacksTo(16)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
