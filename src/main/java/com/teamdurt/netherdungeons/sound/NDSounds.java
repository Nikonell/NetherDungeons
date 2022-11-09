package com.teamdurt.netherdungeons.sound;

import com.teamdurt.netherdungeons.NetherDungeons;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class NDSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, NetherDungeons.MOD_ID);

    public static final RegistryObject<SoundEvent> BABY_GHAST_DEATH = registerSoundEvent("baby_ghast_death");

    public static final RegistryObject<SoundEvent> BABY_GHAST_AMBIENT = registerSoundEvent("baby_ghast_ambient");

    public static final RegistryObject<SoundEvent> BABY_GHAST_HURT = registerSoundEvent("baby_ghast_hurt");

    public static final RegistryObject<SoundEvent> VAMPIRISM = registerSoundEvent("vampirism");

    private static RegistryObject<SoundEvent> registerSoundEvent(String name) {
        return SOUND_EVENTS.register(name, () -> new SoundEvent(new ResourceLocation(NetherDungeons.MOD_ID, name)));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
