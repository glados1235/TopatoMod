package net.tombvali.topatomod.painting;

import net.minecraft.world.entity.decoration.PaintingVariant;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.tombvali.topatomod.TopatoMod;

public class ModPaintings {
    public static final DeferredRegister<PaintingVariant> PAINTING_VARIANTS =
            DeferredRegister.create(ForgeRegistries.PAINTING_VARIANTS, TopatoMod.MODID);


    public static final RegistryObject<PaintingVariant> YEEHAW_TOPATO = PAINTING_VARIANTS.register("yeehaw_topato",
            () -> new PaintingVariant(48, 48));
    public static final RegistryObject<PaintingVariant> TOPATO_PLAGUE_DOCTOR = PAINTING_VARIANTS.register("topato_plague_doctor",
            () -> new PaintingVariant(32, 32));
    public static final RegistryObject<PaintingVariant> TOPATO_VORTEX = PAINTING_VARIANTS.register("topato_vortex",
            () -> new PaintingVariant(32, 32));
    public static final RegistryObject<PaintingVariant> TOPATO_PERFECTION = PAINTING_VARIANTS.register("topato_perfection",
            () -> new PaintingVariant(48, 48));

}
