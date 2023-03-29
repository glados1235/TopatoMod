package net.tombvali.topatomod.entities.nonliving;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;
import net.tombvali.topatomod.entities.projectiles.TomatoGrenade;
import net.tombvali.topatomod.item.ModItems;
import net.tombvali.topatomod.item.custom.ResonanceChamberItem;

import static net.tombvali.topatomod.TopatoMod.LOGGER;

public class Resonance extends Entity {

    private static final EntityDataAccessor<Integer> TIMER = SynchedEntityData.defineId(Resonance.class, EntityDataSerializers.INT);

    public float value = 0.4f + this.random.nextFloat() * (2.2f - 0.4f);
    private final double speed = 0.1;
    public Player followingPlayer;


    public Resonance(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public void onAddedToWorld() {
        super.onAddedToWorld();
        followingPlayer = level.getNearestPlayer(this.position().x, this.position().y, this.position().z, 12, false);

    }

    @Override
    public void tick() {
        super.tick(); // call superclass tick method to update entity state

        int slot = -1;
        this.setTimer(this.getTimer() + 1);
        if (followingPlayer != null) {
            for (int i = 0; i < followingPlayer.getInventory().getContainerSize(); ++i) {
                ItemStack itemstack1 = followingPlayer.getInventory().getItem(i);
                if (itemstack1.is(ModItems.RESONANCE_CHAMBER.get())) {
                    slot = i;
                    break;
                }
            }

            if (slot != -1) {
                ItemStack stack = followingPlayer.getInventory().getItem(slot);

                Vec3 vec3 = new Vec3(
                        this.followingPlayer.getX() - this.getX(),
                        this.followingPlayer.getY() - this.getY(),
                        this.followingPlayer.getZ() - this.getZ());


                double d0 = vec3.lengthSqr(); // calculate the squared length of the vector
                double d1 = 1.0D - Math.sqrt(d0) / 8.0D; // calculate a scaling factor based on distance
                vec3 = vec3.normalize().scale(d1 * d1 * 0.1D); // scale the vector by the factor
                vec3 = vec3.normalize().scale(speed);
                this.setDeltaMovement(this.getDeltaMovement().add(vec3)); // add the vector to the entity's motion
                this.move(MoverType.SELF, this.getDeltaMovement()); // move the entity
                if (getTimer() >= 150) {

                    if (stack != followingPlayer.getInventory().getItem(slot)) {
                        this.Kill(true, stack);
                    } else {
                        this.Kill(false, stack);
                    }

                }
                if (this.getBoundingBox().intersects(followingPlayer.getBoundingBox())) {
                    this.Kill(true, stack);
                }
            }
            else {
                this.Kill(false, null);
            }
        } else {
            Vec3 vec3 = new Vec3((this.random.nextDouble() - 0.5D) * 2.0D, 1.0D, (this.random.nextDouble() - 0.5D) * 2.0D);
            vec3 = vec3.normalize().scale(speed);
            this.setDeltaMovement(this.getDeltaMovement().add(vec3));
            this.move(MoverType.SELF, this.getDeltaMovement());

            if (getTimer() >= 150) {
                this.Kill(false, null);
            }
        }


        // add a particle effect at the entity's current position
        level.addParticle(ParticleTypes.GLOW_SQUID_INK, this.getX(), this.getY(), this.getZ(), 0, 0, 0);
    }


    public void Kill(Boolean shouldReward, ItemStack stack) {
        level.addParticle(ParticleTypes.EXPLOSION, this.getX(), this.getY(), this.getZ(), 0, 0, 0);
        if (shouldReward) {
            stack.getOrCreateTag().putFloat("currentResonance", stack.getTag().getFloat("currentResonance") + value);
            this.remove(RemovalReason.DISCARDED);
        } else {
            this.remove(RemovalReason.DISCARDED);
        }

    }

    public int getTimer() {
        return this.entityData.get(TIMER);
    }

    public void setTimer(int i) {
        this.entityData.set(TIMER, i);
    }

    @Override
    protected void defineSynchedData() {
        this.entityData.define(TIMER, 0);

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
