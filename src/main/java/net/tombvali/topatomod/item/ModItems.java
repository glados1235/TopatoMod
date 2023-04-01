package net.tombvali.topatomod.item;

import net.minecraft.world.food.FoodProperties;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.tombvali.topatomod.TopatoMod;
import net.tombvali.topatomod.block.ModBlocks;
import net.tombvali.topatomod.entities.ModEntities;
import net.tombvali.topatomod.item.custom.*;

public class ModItems {


    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, TopatoMod.MODID);


    public static final RegistryObject<Item> TOMATO = ITEMS.register("tomato",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.TOPATOMOD_TAB).food(new FoodProperties.Builder().nutrition(2).saturationMod(3).build())));

    public static final RegistryObject<Item> TOMATO_SEEDS = ITEMS.register("tomato_seeds",
            () -> new ItemNameBlockItem(ModBlocks.TOMATO_CROP_BLOCK.get(),
                    new Item.Properties().tab(ModCreativeModeTab.TOPATOMOD_TAB)));

    public static final RegistryObject<Item> RESONATING_DISK = ITEMS.register("resonating_disk",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.TOPATOMOD_TAB)));

    public static final RegistryObject<Item> RESONANCE_CHAMBER = ITEMS.register("resonance_chamber",
            () -> new ResonanceChamberItem(new Item.Properties().tab(ModCreativeModeTab.TOPATOMOD_TAB)));

    public static final RegistryObject<Item> TOPATO_TOTEM = ITEMS.register("topato_totem",
            () -> new TopatoTotemItem(new Item.Properties().tab(ModCreativeModeTab.TOPATOMOD_TAB).durability(50)));


    public static final RegistryObject<Item> YEETER = ITEMS.register("yeeter",
            () -> new Yeeter(new Item.Properties().tab(ModCreativeModeTab.TOPATOMOD_TAB)));


    public static final RegistryObject<Item> TOMATO_GRENADE = ITEMS.register("tomato_grenade",
            () -> new TomatoGrenadeItem(new Item.Properties().stacksTo(16).tab(ModCreativeModeTab.TOPATOMOD_TAB)));
    public static final RegistryObject<Item> TOMATO_GRENADE_LAUNCHER = ITEMS.register("tomato_grenade_launcher",
            () -> new TomatoGrenadeLauncherItem(new Item.Properties().tab(ModCreativeModeTab.TOPATOMOD_TAB).durability(150)));


    public static final RegistryObject<Item> CELESTIUM_INGOT = ITEMS.register("celestium_ingot",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.TOPATOMOD_TAB)));

    public static final RegistryObject<Item> PHOENIXITE_INGOT = ITEMS.register("phoenixite_ingot",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.TOPATOMOD_TAB)));

    public static final RegistryObject<Item> STARDUST_INGOT = ITEMS.register("stardust_ingot",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.TOPATOMOD_TAB)));

    public static final RegistryObject<Item> VOIDSTONE_INGOT = ITEMS.register("voidstone_ingot",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.TOPATOMOD_TAB)));


    public static final RegistryObject<Item> RAW_CELESTIUM = ITEMS.register("raw_celestium",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.TOPATOMOD_TAB)));

    public static final RegistryObject<Item> RAW_PHOENIXITE = ITEMS.register("raw_phoenixite",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.TOPATOMOD_TAB)));

    public static final RegistryObject<Item> RAW_STARDUST = ITEMS.register("raw_stardust",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.TOPATOMOD_TAB)));

    public static final RegistryObject<Item> RAW_VOIDSTONE = ITEMS.register("raw_voidstone",
            () -> new Item(new Item.Properties().tab(ModCreativeModeTab.TOPATOMOD_TAB)));



    public static final RegistryObject<Item> LOST_ONE_SPAWN_EGG = ITEMS.register("lost_one_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.LOST_ONE, 375313, 679627,
            new Item.Properties().tab(ModCreativeModeTab.TOPATOMOD_TAB)));







}
