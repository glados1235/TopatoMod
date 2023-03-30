package net.tombvali.topatomod.world;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.tombvali.topatomod.TopatoMod;
import net.tombvali.topatomod.world.feature.ModPlacedFeatures;

import java.awt.*;
import java.security.PublicKey;
import java.util.function.Supplier;

public class ModBiomes {

    public static final DeferredRegister<Biome> BIOMES =
            DeferredRegister.create(ForgeRegistries.BIOMES, TopatoMod.MODID);


    public static Biome testBiome(){
        MobSpawnSettings.Builder spawnSettings = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder genSettings = new BiomeGenerationSettings.Builder();
        genSettings.addFeature(50, Holder.direct(ModPlacedFeatures.PHOENIXITE_ORE_PLACED.get()));
        return new Biome.BiomeBuilder()
                .precipitation(Biome.Precipitation.NONE)
                .temperature(0.7f)
                .downfall(0)
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(0xF100E5FF)
                        .fogColor(0xF100E5FF).build()).mobSpawnSettings(spawnSettings.build()).generationSettings(genSettings.build()).build();

    }



    public static <B extends Biome>ResourceKey<Biome> createBiome(String id, Supplier<? extends B> biome) {
        BIOMES.register(id, biome);
        return ResourceKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(TopatoMod.MODID, id));
    }
}
