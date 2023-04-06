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
import net.minecraftforge.registries.RegistryObject;
import net.tombvali.topatomod.TopatoMod;
import net.tombvali.topatomod.world.feature.ModPlacedFeatures;

import java.awt.*;
import java.security.PublicKey;
import java.util.function.Supplier;

public class ModBiomes {



    public static final ResourceKey<Biome> TEST_BIOME = register("prism_meadow");
    //public static final ResourceKey<Biome> THE_HALLOW = register("the_hallow");

    private static ResourceKey<Biome> register(String name) {
        return ResourceKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(TopatoMod.MODID, name));
    }
}
