package net.tombvali.topatomod.item.custom;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.tombvali.topatomod.networking.packets.ForgeNetworkHandler;
import net.tombvali.topatomod.networking.packets.YeetPacket;

public class Yeeter extends Item {
    public Yeeter(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack item, Player player, LivingEntity entity, InteractionHand hand) {
        if (player.getPassengers().isEmpty()) {
            entity.startRiding(player);
        } else {
            float f7 = player.getYRot();
            float f = player.getXRot();
            float f1 = -Mth.sin(f7 * ((float) Math.PI / 180F)) * Mth.cos(f * ((float) Math.PI / 180F));
            float f2 = -Mth.sin(f * ((float) Math.PI / 180F));
            float f3 = Mth.cos(f7 * ((float) Math.PI / 180F)) * Mth.cos(f * ((float) Math.PI / 180F));
            float f4 = Mth.sqrt(f1 * f1 + f2 * f2 + f3 * f3);
            float f5 = 6;
            f1 *= f5 / f4;
            f2 *= f5 / f4;
            f3 *= f5 / f4;
            float finalF = f1;
            float finalF1 = f2;
            float finalF2 = f3;
            player.getPassengers().forEach(rider -> {
                if (rider instanceof ServerPlayer ridePlayer) {
                    if (!ridePlayer.level.isClientSide) {
                        ridePlayer.stopRiding();
                        ForgeNetworkHandler.sendToPlayer(ridePlayer, new YeetPacket(finalF, finalF1 + 1, finalF2));
                    }
                } else {
                    yeet(player);
                }
            });
        }
        return InteractionResult.SUCCESS;
    }

    public void yeet(Player player) {
        player.getPassengers().forEach((rider -> {
            rider.stopRiding();
            pushEntity(player, rider, 10, SoundEvents.ENDER_DRAGON_FLAP, 1f);
        }));
    }

    public static void pushEntity(Entity thrower, Entity entity, int distance, SoundEvent sound, float volume) {
        float f7 = thrower.getYRot();
        float f = thrower.getXRot();
        float f1 = -Mth.sin(f7 * ((float) Math.PI / 180F)) * Mth.cos(f * ((float) Math.PI / 180F));
        float f2 = -Mth.sin(f * ((float) Math.PI / 180F));
        float f3 = Mth.cos(f7 * ((float) Math.PI / 180F)) * Mth.cos(f * ((float) Math.PI / 180F));
        float f4 = Mth.sqrt(f1 * f1 + f2 * f2 + f3 * f3);
        float f5 = distance * ((1.0F + (float) thrower.level.random.nextInt(4)) / 4.0F);
        f1 *= f5 / f4;
        f2 *= f5 / f4;
        f3 *= f5 / f4;
        thrower.level.playSound(null, thrower.blockPosition(), sound, SoundSource.PLAYERS, volume, 1);
        entity.push(f1, f2 + 1, f3);
    }
}