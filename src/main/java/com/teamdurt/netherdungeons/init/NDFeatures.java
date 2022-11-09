package com.teamdurt.netherdungeons.init;

import com.teamdurt.netherdungeons.NetherDungeons;
import com.teamdurt.netherdungeons.world.gen.feature.ArtichokeFeature;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.common.Mod;

import net.minecraft.world.level.levelgen.feature.Feature;

@Mod.EventBusSubscriber
public class NDFeatures {
    public static final DeferredRegister<Feature<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.FEATURES, NetherDungeons.MOD_ID);
    public static final RegistryObject<Feature<?>> ARTICHOKE = REGISTRY.register("artichoke", ArtichokeFeature::feature);
}


