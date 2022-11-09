package com.teamdurt.netherdungeons.world.gen.feature;

import com.teamdurt.netherdungeons.init.NDBlocks;
import net.minecraft.world.level.levelgen.placement.RarityFilter;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.RandomPatchFeature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.Level;
import net.minecraft.resources.ResourceKey;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.core.Holder;

import java.util.Set;
import java.util.List;

import static com.teamdurt.netherdungeons.block.ArtichokeBlock.AGE;
import static com.teamdurt.netherdungeons.block.ArtichokeBlock.MAX_AGE;

public class ArtichokeFeature extends RandomPatchFeature {
    public static ArtichokeFeature FEATURE = null;
    public static Holder<ConfiguredFeature<RandomPatchConfiguration, ?>> CONFIGURED_FEATURE = null;
    public static Holder<PlacedFeature> PLACED_FEATURE = null;

    public static Feature<?> feature() {
        FEATURE = new ArtichokeFeature();
        CONFIGURED_FEATURE = FeatureUtils.register("netherdungeons:artichoke", FEATURE, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK,
                new SimpleBlockConfiguration(BlockStateProvider.simple(NDBlocks.ARTICHOKE.get().defaultBlockState().setValue(AGE, MAX_AGE))), List.of(), 64));
        PLACED_FEATURE = PlacementUtils.register("netherdungeons:artichoke", CONFIGURED_FEATURE, List.of(CountPlacement.of(12),
                RarityFilter.onAverageOnceEvery(32), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome()));
        return FEATURE;
    }

    private final Set<ResourceKey<Level>> generate_dimensions = Set.of(Level.NETHER);

    public ArtichokeFeature() {
        super(RandomPatchConfiguration.CODEC);
    }

    public boolean place(FeaturePlaceContext<RandomPatchConfiguration> context) {
        WorldGenLevel world = context.level();
        if (!generate_dimensions.contains(world.getLevel().dimension()))
            return false;
        return super.place(context);
    }
}


