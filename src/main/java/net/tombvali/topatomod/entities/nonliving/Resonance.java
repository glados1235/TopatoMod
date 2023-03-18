package net.tombvali.topatomod.entities.nonliving;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

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
            this.moveTo(followingPlayer.position());
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
        return null;
    }
}
