package net.tombvali.topatomod.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.tombvali.topatomod.item.ModItems;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static net.tombvali.topatomod.TopatoMod.LOGGER;
public class ResonanceChamberItem extends Item {

    public static final float resonanceCapacity = 100f;


    public ResonanceChamberItem(Properties properties) {
        super(properties);
//        CompoundTag tag = new CompoundTag();
//        tag.putFloat("CustomData", 0);
//        ItemStack stack = new ItemStack(this);
//        stack.setTag(tag);
    }


    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int i, boolean b) {
        super.inventoryTick(stack, level, entity, i, b);
        CompoundTag tag = stack.getTag();
        if (tag != null && tag.contains("currentResonance")) {
            float currentResonance = tag.getFloat("currentResonance");
            LOGGER.info("currentResonance | " + currentResonance);
        }
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> components, TooltipFlag tooltipFlag) {

        if(Screen.hasShiftDown()){
            if (itemStack.getTag().contains("currentResonance")) {
                components.add(Component.literal(String.valueOf(itemStack.getTag().getFloat("currentResonance"))).withStyle(ChatFormatting.BLUE));
            }
        }
        else {
            components.add(Component.literal("This seems wrong but so right! Hold SHIFT for more info!").withStyle(ChatFormatting.AQUA));
        }
        super.appendHoverText(itemStack, level, components, tooltipFlag);
    }
}
