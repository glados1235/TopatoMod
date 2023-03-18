package net.tombvali.topatomod.entities.nonliving;

import com.mojang.math.Vector3d;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;
import net.tombvali.topatomod.TopatoMod;
import org.jline.utils.Log;

public class Resonance extends Entity {

    public int age;
    public int value;
    public Player followingPlayer;

    public Resonance(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public void onAddedToWorld() {
        super.onAddedToWorld();
        followingPlayer = level.getNearestPlayer(this.position().x,this.position().y,this.position().z, 10, false);
    }

    @Override
    public void tick() {
        super.tick();
        if(followingPlayer != null){
            // Calculate the direction to the player
            Vec3 direction = followingPlayer.position().subtract(position());
            double distance = direction.length();
            direction = direction.normalize();

            // Log the distance and direction to the player
            Log.info("Distance to player: " + distance);
            Log.info("Direction to player: " + direction);

            // Move towards the player with a set speed
            double speed = 100; // Change this value to adjust the speed
            Vec3 movement = direction.scale(speed * speed * 0.1);
            setDeltaMovement(getDeltaMovement().add(movement));

            // Log the current velocity and position of the entity
            LOGGER.info("Current velocity: " + getDeltaMovement());
            Log.info("Current position: " + position());
        }
    }

    @Override
    protected void defineSynchedData() {

    }

    @Override
    protected void readAdditionalSaveData(CompoundTag p_20052_) {

    }

    @Override
    protected void addAdditionalSaveData(CompoundTag p_20139_) {

    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
