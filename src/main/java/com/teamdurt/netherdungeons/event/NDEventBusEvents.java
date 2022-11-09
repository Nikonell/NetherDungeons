package com.teamdurt.netherdungeons.event;

import com.teamdurt.netherdungeons.NetherDungeons;
import com.teamdurt.netherdungeons.init.NDEntityTypes;
import com.teamdurt.netherdungeons.client.entity.custom.BabyGhastEntity;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = NetherDungeons.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class NDEventBusEvents {
    @SubscribeEvent
    public static void EntityAttributeEvent(EntityAttributeCreationEvent event) {
        event.put(NDEntityTypes.BABY_GHAST.get(), BabyGhastEntity.setAttributes());
    }

    @SubscribeEvent
    public static void init(FMLCommonSetupEvent event) {
        event.enqueueWork(BabyGhastEntity::init);
    }

}
