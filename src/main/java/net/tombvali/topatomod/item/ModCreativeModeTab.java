package net.tombvali.topatomod.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTab {
    public static final CreativeModeTab TOPATOMOD_TAB = new CreativeModeTab("topatomod_tab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.TOMATO.get());
        }
    };

}
