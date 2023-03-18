package net.tombvali.topatomod.item.custom;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.tombvali.topatomod.item.ModItems;

import static net.tombvali.topatomod.TopatoMod.LOGGER;
public class ResonanceChamberItem extends Item {

    public static final float resonanceCapacity = 100f;


    public ResonanceChamberItem(Properties properties) {
        super(properties);

    }
    public ResonanceChamberItem(){

        CompoundTag tag = new CompoundTag();
        tag.putFloat("CustomData", 0);
        ItemStack stack = new ItemStack(this);
        stack.setTag(tag);
    }


    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int i, boolean b) {
        super.inventoryTick(stack, level, entity, i, b);
        CompoundTag tag = stack.getTag();
        if (tag != null && tag.contains("currentResonance")) {
            int currentResonance = tag.getInt("currentResonance");
            LOGGER.info("currentResonance | " + currentResonance);
        }
    }
}
