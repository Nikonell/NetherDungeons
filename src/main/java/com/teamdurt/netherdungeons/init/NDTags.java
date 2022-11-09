package com.teamdurt.netherdungeons.init;

import com.teamdurt.netherdungeons.NetherDungeons;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.levelgen.structure.Structure;

public class NDTags {
    public static class Structures {

        public static final TagKey<Structure> NO_INTERSECTING_FEATURES = tag("no_intersecting_features");

        private static TagKey<Structure> tag(String name) {
            return TagKey.create(Registry.STRUCTURE_REGISTRY, new ResourceLocation(NetherDungeons.MOD_ID, name));
        }

    }
}
