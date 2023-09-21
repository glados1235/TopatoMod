package net.tombvali.topatomod.item.entities.projectiles;


import net.minecraft.network.protocol.Packet;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import net.tombvali.topatomod.item.entities.ModEntities;
import net.tombvali.topatomod.item.ModItems;

public class TomatoGrenade extends ThrowableItemProjectile {


    private static final EntityDataAccessor<Integer> TIMER = SynchedEntityData.defineId(TomatoGrenade.class, EntityDataSerializers.INT);
    public TomatoGrenade(EntityType<? extends ThrowableItemProjectile> p_37442_, Level p_37443_) {
        super(p_37442_, p_37443_);
    }

    public TomatoGrenade(Level level, LivingEntity entity){
        super(ModEntities.TOMATO_GRENADE.get(), level);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.TOMATO_GRENADE.get();
    }

    public int getTimer() {
        return this.entityData.get(TIMER);
    }

     public void setTimer(int i) {
        this.entityData.set(TIMER, i);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(TIMER, 0);

    }

    @Override
    protected void onHitBlock(BlockHitResult hitResult) {
        super.onHitBlock(hitResult);
        this.setDeltaMovement(0,0,0);
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public void tick() {
        super.tick();
        this.setTimer(this.getTimer()+1);
        if(this.getTimer() >= 100){
            if(!this.level.isClientSide){
                this.level.explode(this, this.getX(), this.getY(0.0625D), this.getZ(), 6F, Explosion.BlockInteraction.NONE);
                this.remove(RemovalReason.DISCARDED);
            }
            this.setTimer(0);
        }
    }
}
