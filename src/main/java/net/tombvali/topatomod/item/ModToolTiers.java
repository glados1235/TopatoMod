package net.tombvali.topatomod.item;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;
import net.tombvali.topatomod.TopatoMod;
import net.tombvali.topatomod.util.ModTags;

import java.awt.*;
import java.util.List;

public class ModToolTiers {
    public static Tier STARDUST;



    static {
        STARDUST = TierSortingRegistry.registerTier(
                new ForgeTier(2, 300, 8.5f, 2f, 24, ModTags.Blocks.NEEDS_STARDUST_TOOL, () -> Ingredient.of(ModItems.STARDUST_INGOT.get())),
                new ResourceLocation(TopatoMod.MODID, "stardust"), List.of(Tiers.STONE), List.of());

    }



}
