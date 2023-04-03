package net.tombvali.topatomod.entities.mobs;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.tombvali.topatomod.sounds.ModSounds;
import software.bernie.geckolib3.core.AnimationState;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

import java.util.Random;

public class LostOneEntity extends Monster implements IAnimatable {
    AnimationFactory factory = GeckoLibUtil.createFactory(this);

    public LostOneEntity(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
    }

    public static AttributeSupplier setAttribute() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 30)
                .add(Attributes.ATTACK_DAMAGE, 5)
                .add(Attributes.MOVEMENT_SPEED, 0.32F)
                .add(Attributes.FOLLOW_RANGE, 10)
                .add(Attributes.ATTACK_SPEED, 1.3f).build();
    }

    @Override
    protected void tickDeath() {
        super.tickDeath();
        level.addParticle(ParticleTypes.SMOKE, this.getX(), this.getY(), this.getZ(), 0, 1, 0);
    }


    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.2, false));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 1.0d));
        this.goalSelector.addGoal(4, new RandomStrollGoal(this, 1));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(3, new NoCreeperGoal<>(this, LivingEntity.class, true));
    }

    private <P extends IAnimatable> PlayState predicate(AnimationEvent<P> event) {
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.lost_one.walk", true));
        } else {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.lost_one.idle", true));
        }
        return PlayState.CONTINUE;
    }

    private PlayState attackPredicate(AnimationEvent event) {

        int rand = 1 + random.nextInt(2);
        if (this.swinging && event.getController().getAnimationState().equals(AnimationState.Stopped)) {
            event.getController().markNeedsReload();
            if (rand == 1) {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.lost_one.attackM1", false));
                this.swinging = false;
            } else {
                event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.lost_one.attackM2", false));
                this.swinging = false;
            }
        }

        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller", 0, this::predicate));
        data.addAnimationController(new AnimationController(this, "attackController", 0, this::attackPredicate));
    }


    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    protected void playStepSound(BlockPos pos, BlockState blockState) {
        this.playSound(SoundEvents.ANVIL_STEP, 0.15F, 1.0f);
    }

    protected SoundEvent getAmbientSound() {
        return ModSounds.LOST_ONE_AMBIENT.get();
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.PLAYER_HURT;
    }

    protected SoundEvent getDeathSound() {
        return ModSounds.LOST_ONE_DEATH.get();
    }

    protected float getSoundVolume() {
        return 1f;
    }

    public static class NoCreeperGoal<T extends LivingEntity> extends NearestAttackableTargetGoal<T> {
        public NoCreeperGoal(Mob p_26060_, Class p_26061_, boolean p_26062_) {
            super(p_26060_, p_26061_, p_26062_);
        }

        @Override
        public void start() {
            if (target instanceof Creeper){
                return;
            }
            super.start();
        }
    }
    public static boolean checkSpawnRules(EntityType<LostOneEntity> type, LevelAccessor world, MobSpawnType spawnType, BlockPos pos, RandomSource rand){
        return checkMobSpawnRules(type, world, spawnType, pos, rand);
    }

}

