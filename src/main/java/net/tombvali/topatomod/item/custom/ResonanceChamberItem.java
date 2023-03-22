package net.tombvali.topatomod.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static net.tombvali.topatomod.TopatoMod.LOGGER;

public class ResonanceChamberItem extends Item {

    public static final float resonanceCapacity = 100f;


    public ResonanceChamberItem(Properties properties) {
        super(properties);

    }


    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int i, boolean b) {
        super.inventoryTick(stack, level, entity, i, b);
        CompoundTag tag = stack.getTag();
        if (tag != null && tag.contains("currentResonance")) {
            float currentResonance = tag.getFloat("currentResonance");
            //LOGGER.info("currentResonance | " + currentResonance);
        }
        else {stack.getOrCreateTag().putFloat("currentResonance",  0);}
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> components, TooltipFlag tooltipFlag) {

        CompoundTag tag = itemStack.getTag();
        if (tag != null && itemStack.getTag().contains("currentResonance") && itemStack.getTag().getFloat("currentResonance") != 0) {
            components.add(Component.literal(String.valueOf(itemStack.getTag().getFloat("currentResonance"))).withStyle(ChatFormatting.BLUE));
        }
        else {components.add(Component.literal("EMPTY").withStyle(ChatFormatting.BLUE));}

        super.appendHoverText(itemStack, level, components, tooltipFlag);
    }
}
