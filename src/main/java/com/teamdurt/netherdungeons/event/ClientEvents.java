package com.teamdurt.netherdungeons.event;

import com.teamdurt.netherdungeons.NetherDungeons;
import com.teamdurt.netherdungeons.client.entity.model.BabyGhastModel;
import com.teamdurt.netherdungeons.client.entity.renderer.BabyGhastRenderer;
import com.teamdurt.netherdungeons.init.NDEntityTypes;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = NetherDungeons.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvents {

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(NDEntityTypes.BABY_GHAST.get(), BabyGhastRenderer::new);
    }

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(BabyGhastModel.LAYER_LOCATION, BabyGhastModel::createBodyLayer);
    }
}
