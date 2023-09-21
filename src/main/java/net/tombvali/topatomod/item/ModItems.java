package net.tombvali.topatomod.item;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.food.FoodProperties;

import net.minecraft.world.item.*;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.tombvali.topatomod.TopatoMod;
import net.tombvali.topatomod.block.ModBlocks;
import net.tombvali.topatomod.item.entities.ModEntities;
import net.tombvali.topatomod.item.custom.*;
import net.tombvali.topatomod.item.custom.armor.ModArmorMaterials;
import net.tombvali.topatomod.item.custom.armor.StardustArmorItem;

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


    //ARMORS


    public static final RegistryObject<Item> STARDUST_HELMET = ITEMS.register("stardust_helmet",
            () -> new StardustArmorItem(ModArmorMaterials.STARDUST, EquipmentSlot.HEAD, new Item.Properties().tab(ModCreativeModeTab.TOPATOMOD_TAB)));
    public static final RegistryObject<Item> STARDUST_CHESTPLATE = ITEMS.register("stardust_chestplate",
            () -> new StardustArmorItem(ModArmorMaterials.STARDUST, EquipmentSlot.CHEST, new Item.Properties().tab(ModCreativeModeTab.TOPATOMOD_TAB)));
    public static final RegistryObject<Item> STARDUST_LEGGINGS = ITEMS.register("stardust_leggings",
            () -> new StardustArmorItem(ModArmorMaterials.STARDUST, EquipmentSlot.LEGS, new Item.Properties().tab(ModCreativeModeTab.TOPATOMOD_TAB)));
    public static final RegistryObject<Item> STARDUST_BOOTS = ITEMS.register("stardust_boots",
            () -> new StardustArmorItem(ModArmorMaterials.STARDUST, EquipmentSlot.FEET, new Item.Properties().tab(ModCreativeModeTab.TOPATOMOD_TAB)));

    //TOOLS

    public static final RegistryObject<Item> STARDUST_SWORD = ITEMS.register("stardust_sword",
            () -> new SwordItem(ModToolTiers.STARDUST, 5, 4, new Item.Properties().tab(ModCreativeModeTab.TOPATOMOD_TAB).stacksTo(1)));

    public static final RegistryObject<Item> STARDUST_PICKAXE = ITEMS.register("stardust_pickaxe",
            () -> new PickaxeItem(ModToolTiers.STARDUST, 2, 2, new Item.Properties().tab(ModCreativeModeTab.TOPATOMOD_TAB).stacksTo(1)));

    public static final RegistryObject<Item> STARDUST_SHOVEL = ITEMS.register("stardust_shovel",
            () -> new ShovelItem(ModToolTiers.STARDUST, 1, 2, new Item.Properties().tab(ModCreativeModeTab.TOPATOMOD_TAB).stacksTo(1)));

    public static final RegistryObject<Item> STARDUST_AXE = ITEMS.register("stardust_axe",
            () -> new AxeItem(ModToolTiers.STARDUST, 4, 3, new Item.Properties().tab(ModCreativeModeTab.TOPATOMOD_TAB).stacksTo(1)));
}
