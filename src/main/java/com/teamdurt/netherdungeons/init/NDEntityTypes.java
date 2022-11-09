package com.teamdurt.netherdungeons.init;

import com.teamdurt.netherdungeons.NetherDungeons;
import com.teamdurt.netherdungeons.client.entity.custom.BabyGhastEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class NDEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, NetherDungeons.MOD_ID);

    public static final RegistryObject<EntityType<BabyGhastEntity>> BABY_GHAST =
            ENTITY_TYPES.register("baby_ghast",
                    () -> EntityType.Builder.of(BabyGhastEntity::new, MobCategory.AMBIENT)
                            .sized(0.375f, 0.375f)
                            .build(new ResourceLocation(NetherDungeons.MOD_ID, "baby_ghast").toString()));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    };
}
