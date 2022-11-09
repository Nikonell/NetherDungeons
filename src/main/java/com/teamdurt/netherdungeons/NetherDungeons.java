package com.teamdurt.netherdungeons;

import com.teamdurt.netherdungeons.client.entity.custom.BabyGhastEntity;
import com.teamdurt.netherdungeons.init.*;
import com.teamdurt.netherdungeons.effect.NDEffects;
import com.teamdurt.netherdungeons.client.entity.renderer.BabyGhastRenderer;
import com.teamdurt.netherdungeons.sound.NDSounds;
import com.teamdurt.netherdungeons.util.ModItemProperties;
import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import software.bernie.example.GeckoLibMod;
import software.bernie.geckolib3.GeckoLib;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(NetherDungeons.MOD_ID)
public class NetherDungeons {
    public static final String MOD_ID = "netherdungeons";
    private static final Logger LOGGER = LogUtils.getLogger();

    public NetherDungeons() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        NDItems.register(modEventBus);
        NDBlocks.register(modEventBus);
        NDEntityTypes.register(modEventBus);
        NDSounds.register(modEventBus);
        NDEffects.register(modEventBus);
        NDFeatures.REGISTRY.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        GeckoLibMod.DISABLE_IN_DEV = true;
        GeckoLib.initialize();

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            SpawnPlacements.register(NDEntityTypes.BABY_GHAST.get(),
                    SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                    BabyGhastEntity::checkMobSpawnRules);
        });
    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModeEvents {
        @SubscribeEvent
        public static void OnClientSetup(FMLClientSetupEvent event) {
            EntityRenderers.register(NDEntityTypes.BABY_GHAST.get(), BabyGhastRenderer::new);

            ModItemProperties.addCustomItemProperties();
        }
    }
}
