package net.tombvali.topatomod.item.custom;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.tombvali.topatomod.entities.projectiles.TomatoGrenade;
import net.tombvali.topatomod.item.ModItems;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TomatoGrenadeLauncherItem extends Item {


    public TomatoGrenadeLauncherItem(Properties properties) {
        super(properties);
    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {

        int slot = player.getInventory().findSlotMatchingItem(ModItems.TOMATO_GRENADE.get().getDefaultInstance());

        if (player.getMainHandItem().is(ModItems.TOMATO_GRENADE_LAUNCHER.get()) && slot != -1) {
            ItemStack stack = player.getInventory().getItem(slot);
            TomatoGrenade tomatoGrenade = new TomatoGrenade(level, player);
            tomatoGrenade.moveTo(player.position().add(0, 1, 0));
            tomatoGrenade.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.8F, 1.0F);
            level.addFreshEntity(tomatoGrenade);
            player.getCooldowns().addCooldown(player.getMainHandItem().getItem(), 15);
            level.playSound(null, player.blockPosition(), SoundEvents.SNOWBALL_THROW, SoundSource.PLAYERS, 1, 1);

            player.getMainHandItem().setDamageValue(player.getMainHandItem().getDamageValue() + 1);
            if (player.getMainHandItem().getDamageValue() >= player.getMainHandItem().getMaxDamage())
                player.getMainHandItem().setCount(0);

            if (!player.getAbilities().instabuild && stack.is(ModItems.TOMATO_GRENADE.get())) {
                stack.shrink(1);
            }
        }
        return InteractionResultHolder.success(player.getItemInHand(hand));
    }

    @Override
    public boolean isValidRepairItem(ItemStack p_41402_, ItemStack p_41403_) {
        return super.isValidRepairItem(p_41402_, p_41403_);
    }

    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> components, TooltipFlag tooltipFlag) {

        if(Screen.hasShiftDown()){
            components.add(Component.literal("Faster speeds over just throwing, and way more power behind it!").withStyle(ChatFormatting.BLUE));
        }
        else {
            components.add(Component.literal("Seems like it fits tomatoes? Hold SHIFT for more info!").withStyle(ChatFormatting.AQUA));
        }
        super.appendHoverText(itemStack, level, components, tooltipFlag);
    }
}
