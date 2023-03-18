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


public class TomatoGrenadeItem extends Item {
    public TomatoGrenadeItem(Properties properties) {
        super(properties);
    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {

        if (player.getMainHandItem().is(ModItems.TOMATO_GRENADE.get())) {
            TomatoGrenade tomatoGrenade = new TomatoGrenade(level, player);
            tomatoGrenade.moveTo(player.position().add(0, 1, 0));
            tomatoGrenade.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 0.8F, 1.0F);
            level.addFreshEntity(tomatoGrenade);
            player.getCooldowns().addCooldown(player.getMainHandItem().getItem(), 50);
        }
        if (!player.getAbilities().instabuild) {
            player.getMainHandItem().shrink(1);
        }
        level.playSound(null, player.blockPosition(), SoundEvents.SNOWBALL_THROW, SoundSource.PLAYERS, 1, 1);
        return InteractionResultHolder.success(player.getItemInHand(hand));
    }


    @Override
    public void appendHoverText(ItemStack itemStack, @Nullable Level level, List<Component> components, TooltipFlag tooltipFlag) {

        if(Screen.hasShiftDown()){
            components.add(Component.literal("Its a bomb that is made from tomatoes! how amazing").withStyle(ChatFormatting.BLUE));
        }
        else {
            components.add(Component.literal("This seems wrong but so right! Hold SHIFT for more info!").withStyle(ChatFormatting.AQUA));
        }
        super.appendHoverText(itemStack, level, components, tooltipFlag);
    }
}

