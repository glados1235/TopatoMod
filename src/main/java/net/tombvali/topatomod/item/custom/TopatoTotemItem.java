package net.tombvali.topatomod.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.entity.EntityTypeTest;
import net.minecraft.world.phys.AABB;
import net.tombvali.topatomod.item.ModItems;

import javax.swing.*;
import java.util.List;

public class TopatoTotemItem extends Item {

    public TopatoTotemItem(Properties properties) {
        super(properties);
    }


    @Override
    public void inventoryTick(ItemStack itemStack, Level level, Entity entity, int i, boolean b) {
        super.inventoryTick(itemStack, level, entity, i, b);


        if (entity instanceof Player player) {
            if (player.isHurt()) {
                List<Monster> nearbyMobs = level.getEntitiesOfClass(Monster.class, new AABB(player.blockPosition()).inflate(5));
                for (Monster mob : nearbyMobs) {

                    mob.hurt(DamageSource.GENERIC, 5);
                    player.getMainHandItem().setDamageValue(player.getMainHandItem().getDamageValue() + 1);


                }
            }

            if (player.getOffhandItem().getItem() == ModItems.TOPATO_TOTEM.get()) {
                if (player.getHealth() <= 2f) {
                    player.heal(100);
                    level.playSound(null, player.blockPosition(), SoundEvents.TOTEM_USE, SoundSource.PLAYERS, 1, 1);
                    itemStack.shrink(1);
                }
            }

        }
    }
}
