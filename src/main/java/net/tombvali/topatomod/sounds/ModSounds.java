package net.tombvali.topatomod.sounds;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.tombvali.topatomod.TopatoMod;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, TopatoMod.MODID);

    public static final RegistryObject<SoundEvent> LOST_ONE_AMBIENT =
            registerSoundEvent("lost_one_ambient");
    public static final RegistryObject<SoundEvent> LOST_ONE_DEATH =
            registerSoundEvent("lost_one_death");




    private static RegistryObject<SoundEvent> registerSoundEvent(String name){
        return SOUND_EVENTS.register(name, () -> new SoundEvent(new ResourceLocation(TopatoMod.MODID, name)));

    }
}
