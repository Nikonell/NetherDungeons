package com.teamdurt.netherdungeons.init;

import com.teamdurt.netherdungeons.NetherDungeons;
import com.teamdurt.netherdungeons.block.ArtichokeBlock;
import com.teamdurt.netherdungeons.block.MagmacubeLanternBlock;
import com.teamdurt.netherdungeons.block.WarpedNetherWartBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class NDBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, NetherDungeons.MOD_ID);

    public static final RegistryObject<Block> MAGMA_NETHER_BRICKS = registerBlock("magma_nether_bricks",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(2f, 6f).sound(SoundType.NETHER_BRICKS)
                    .lightLevel(BlockState -> 3).emissiveRendering(new BlockBehaviour.StatePredicate() {
                        @Override
                        public boolean test(BlockState p_61036_, BlockGetter p_61037_, BlockPos p_61038_) {
                            return true;
                        }
                    })), CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> MAGMA_NETHER_BRICK_STAIRS = registerBlock("magma_nether_brick_stairs",
            () -> new StairBlock(() -> NDBlocks.MAGMA_NETHER_BRICKS.get().defaultBlockState(),
                    BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(2f, 6f).sound(SoundType.NETHER_BRICKS)
                    .lightLevel(BlockState -> 3).emissiveRendering(new BlockBehaviour.StatePredicate() {
                        @Override
                        public boolean test(BlockState p_61036_, BlockGetter p_61037_, BlockPos p_61038_) {
                            return true;
                        }
                    })), CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> MAGMA_NETHER_BRICK_SLAB = registerBlock("magma_nether_brick_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(2f, 6f).sound(SoundType.NETHER_BRICKS)
                    .lightLevel(BlockState -> 3).emissiveRendering(new BlockBehaviour.StatePredicate() {
                        @Override
                        public boolean test(BlockState p_61036_, BlockGetter p_61037_, BlockPos p_61038_) {
                            return true;
                        }
                    })), CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> MAGMA_NETHER_BRICK_FENCE = registerBlock("magma_nether_brick_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(2f, 6f).sound(SoundType.NETHER_BRICKS)
                    .lightLevel(BlockState -> 3).emissiveRendering(new BlockBehaviour.StatePredicate() {
                        @Override
                        public boolean test(BlockState p_61036_, BlockGetter p_61037_, BlockPos p_61038_) {
                            return true;
                        }
                    })), CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> MAGMA_NETHER_BRICK_WALL = registerBlock("magma_nether_brick_wall",
            () -> new WallBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(2f, 6f).sound(SoundType.NETHER_BRICKS)
                    .lightLevel(BlockState -> 3).emissiveRendering(new BlockBehaviour.StatePredicate() {
                        @Override
                        public boolean test(BlockState p_61036_, BlockGetter p_61037_, BlockPos p_61038_) {
                            return true;
                        }
                    })), CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> CHISELED_MAGMA_NETHER_BRICKS = registerBlock("chiseled_magma_nether_bricks",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(2f, 6f).sound(SoundType.NETHER_BRICKS)
                    .lightLevel(BlockState -> 3).emissiveRendering(new BlockBehaviour.StatePredicate() {
                        @Override
                        public boolean test(BlockState p_61036_, BlockGetter p_61037_, BlockPos p_61038_) {
                            return true;
                        }
                    })), CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> OMINOUS_QUARTZ = registerBlock("ominous_quartz",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(0.9f, 0.9f)),
            CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> POLISHED_OMINOUS_QUARTZ = registerBlock("polished_ominous_quartz",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(0.9f, 0.9f)),
            CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> OMINOUS_QUARTZ_BRICKS = registerBlock("ominous_quartz_bricks",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(0.9f, 0.9f).sound(SoundType.NETHER_BRICKS)),
            CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> OMINOUS_QUARTZ_PLATE = registerBlock("ominous_quartz_plate",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(0.9f, 0.9f)),
            CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> CHISELED_OMINOUS_QUARTZ_BRICKS = registerBlock("chiseled_ominous_quartz_bricks",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(0.9f, 0.9f).sound(SoundType.NETHER_BRICKS)),
            CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> OMINOUS_QUARTZ_STAIRS = registerBlock("ominous_quartz_stairs",
            () -> new StairBlock(() -> NDBlocks.OMINOUS_QUARTZ.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE)
                    .requiresCorrectToolForDrops().strength(0.9f, 0.9f)), CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> OMINOUS_QUARTZ_SLAB = registerBlock("ominous_quartz_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(0.9f, 0.9f)),
            CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> POLISHED_OMINOUS_QUARTZ_STAIRS = registerBlock("polished_ominous_quartz_stairs",
            () -> new StairBlock(() -> NDBlocks.POLISHED_OMINOUS_QUARTZ.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE)
                    .requiresCorrectToolForDrops().strength(0.9f, 0.9f)), CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> POLISHED_OMINOUS_QUARTZ_SLAB = registerBlock("polished_ominous_quartz_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(0.9f, 0.9f)),
            CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> OMINOUS_QUARTZ_BRICKS_STAIRS = registerBlock("ominous_quartz_brick_stairs",
            () -> new StairBlock(() -> NDBlocks.OMINOUS_QUARTZ.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE)
                    .requiresCorrectToolForDrops().strength(0.9f, 0.9f).sound(SoundType.NETHER_BRICKS)), CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> OMINOUS_QUARTZ_BRICKS_SLAB = registerBlock("ominous_quartz_brick_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(0.9f, 0.9f).sound(SoundType.NETHER_BRICKS)),
            CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> OMINOUS_QUARTZ_PLATE_STAIRS = registerBlock("ominous_quartz_plate_stairs",
            () -> new StairBlock(() -> NDBlocks.OMINOUS_QUARTZ.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE)
                    .requiresCorrectToolForDrops().strength(0.9f, 0.9f)), CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> OMINOUS_QUARTZ_PLATE_SLAB = registerBlock("ominous_quartz_plate_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(0.9f, 0.9f)),
            CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> WARPED_NETHER_BRICKS = registerBlock("warped_nether_bricks",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(0.8f, 0.8f).sound(SoundType.NETHER_BRICKS)),
            CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> WARPED_NETHER_BRICKS_STAIRS = registerBlock("warped_nether_brick_stairs",
            () -> new StairBlock(() -> NDBlocks.WARPED_NETHER_BRICKS.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE)
                    .requiresCorrectToolForDrops().strength(0.8f, 0.8f).sound(SoundType.NETHER_BRICKS)), CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> WARPED_NETHER_BRICKS_SLAB = registerBlock("warped_nether_brick_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(0.8f, 0.8f).sound(SoundType.NETHER_BRICKS)),
            CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> WARPED_NETHER_BRICKS_WALL = registerBlock("warped_nether_brick_wall",
            () -> new WallBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(0.8f, 0.8f).sound(SoundType.NETHER_BRICKS)),
            CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> WARPED_NETHER_BRICK_FENCE = registerBlock("warped_nether_brick_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(0.8f, 0.8f).sound(SoundType.NETHER_BRICKS)),
            CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> RED_NETHER_BRICK_FENCE = registerBlock("red_nether_brick_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(0.8f, 0.8f).sound(SoundType.NETHER_BRICKS)),
            CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> TEMPERED_GOLD_BLOCK = registerBlock("tempered_gold_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.GOLD_BLOCK).requiresCorrectToolForDrops().strength(5f, 6f)),
            CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> WARPED_NETHER_WART = BLOCKS.register("warped_nether_wart",
            () -> new WarpedNetherWartBlock(BlockBehaviour.Properties.copy(Blocks.NETHER_WART)));

    public static final RegistryObject<Block> MAGMACUBE_LANTERN = BLOCKS.register("magmacube_lantern",
            () -> new MagmacubeLanternBlock(BlockBehaviour.Properties.copy(Blocks.LANTERN), false));

    public static final RegistryObject<Block> MAGMACUBE_LANTERN_EMPTY = BLOCKS.register("magmacube_lantern_empty",
            () -> new MagmacubeLanternBlock(BlockBehaviour.Properties.copy(Blocks.LANTERN).lightLevel(BlockState -> 0), true));

    public static final RegistryObject<Block> GOLD_CHAIN = registerBlock("gold_chain",
            () -> new ChainBlock(BlockBehaviour.Properties.copy(Blocks.CHAIN)), CreativeModeTab.TAB_DECORATIONS);

    public static final RegistryObject<Block> ARTICHOKE = BLOCKS.register("artichoke",
            () -> new ArtichokeBlock(BlockBehaviour.Properties.copy(Blocks.SWEET_BERRY_BUSH)));

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block,
                                                                            CreativeModeTab tab) {
        return NDItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
