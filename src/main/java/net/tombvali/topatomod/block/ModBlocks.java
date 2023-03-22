package net.tombvali.topatomod.block;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.tombvali.topatomod.block.custom.LivingBlock;
import net.tombvali.topatomod.block.custom.PhoenixiteOreBlock;
import net.tombvali.topatomod.block.custom.ResonatingShrineBlock;
import net.tombvali.topatomod.block.custom.TomatoCropBlock;
import net.tombvali.topatomod.item.ModCreativeModeTab;
import net.tombvali.topatomod.item.ModItems;
import net.tombvali.topatomod.TopatoMod;

import java.util.function.Supplier;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, TopatoMod.MODID);


    public static final  RegistryObject<Block> TOPATO_BLOCK = registerBlock("topato_block",
            ()-> new LivingBlock(BlockBehaviour.Properties.of(Material.CLAY)), ModCreativeModeTab.TOPATOMOD_TAB);

    public static final  RegistryObject<Block> RESONATING_SHRINE_BLOCK = registerBlock("resonating_shrine_block",
            ()-> new ResonatingShrineBlock(BlockBehaviour.Properties.of(Material.HEAVY_METAL)), ModCreativeModeTab.TOPATOMOD_TAB);


    public static final  RegistryObject<Block> TOMATO_CROP_BLOCK = BLOCKS.register("tomato_crop_block",
            ()-> new TomatoCropBlock(BlockBehaviour.Properties.copy(Blocks.WHEAT )));



    public static final  RegistryObject<Block> CELESTIUM_ORE = registerBlock("celestium_ore",
            ()-> new Block(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops()), ModCreativeModeTab.TOPATOMOD_TAB);
    public static final  RegistryObject<Block> DEEPSLATE_CELESTIUM_ORE = registerBlock("deepslate_celestium_ore",
            ()-> new Block(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops()), ModCreativeModeTab.TOPATOMOD_TAB);

    public static final  RegistryObject<Block> PHOENIXITE_ORE = registerBlock("phoenixite_ore",
            ()-> new PhoenixiteOreBlock(BlockBehaviour.Properties.of(Material.STONE).strength(4f).requiresCorrectToolForDrops().lightLevel(state -> state.getValue(PhoenixiteOreBlock.LIT) ? 5 : 0)), ModCreativeModeTab.TOPATOMOD_TAB);
    public static final  RegistryObject<Block> DEEPSLATE_PHOENIXITE_ORE = registerBlock("deepslate_phoenixite_ore",
            ()-> new PhoenixiteOreBlock(BlockBehaviour.Properties.of(Material.STONE).strength(4f).requiresCorrectToolForDrops().lightLevel(state -> state.getValue(PhoenixiteOreBlock.LIT) ? 5 : 0)), ModCreativeModeTab.TOPATOMOD_TAB);

    public static final  RegistryObject<Block> STARDUST_ORE = registerBlock("stardust_ore",
            ()-> new Block(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops()), ModCreativeModeTab.TOPATOMOD_TAB);
    public static final  RegistryObject<Block> DEEPSLATE_STARDUST_ORE = registerBlock("deepslate_stardust_ore",
            ()-> new Block(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops()), ModCreativeModeTab.TOPATOMOD_TAB);

    public static final  RegistryObject<Block> VOIDSTONE_ORE = registerBlock("voidstone_ore",
            ()-> new Block(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops()), ModCreativeModeTab.TOPATOMOD_TAB);
    public static final  RegistryObject<Block> DEEPSLATE_VOIDSTONE_ORE = registerBlock("deepslate_voidstone_ore",
            ()-> new Block(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops()), ModCreativeModeTab.TOPATOMOD_TAB);


    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab){

        RegistryObject<T> toReturn = BLOCKS.register(name, block);

        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }


    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab){


        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }


}
