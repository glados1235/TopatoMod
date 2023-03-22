package net.tombvali.topatomod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.AABB;
import static net.tombvali.topatomod.TopatoMod.LOGGER;
import java.util.List;
import java.util.Random;

public class LivingBlock extends Block {

    public static final BooleanProperty IS_DOING_ACTION = BooleanProperty.create("is_doing_action");

    public static Player playerTarget;

    public LivingBlock(Properties properties) {
        super(properties);

    }

    public boolean isRandomlyTicking(BlockState value) {
        return true;
    }

    @Override
    public void randomTick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
        super.randomTick(blockState, serverLevel, blockPos, randomSource);
        LOGGER.info("Tick!");
        AABB searchArea = new AABB(blockPos).inflate(16.0);
        List<Player> nearbyPlayers = serverLevel.getNearbyPlayers(TargetingConditions.DEFAULT, null, searchArea);
        if (!nearbyPlayers.isEmpty()) {
            Player randomPlayer = nearbyPlayers.get(new Random().nextInt(nearbyPlayers.size()));

            if(randomPlayer != null){
                randomPlayer.hurt(DamageSource.GENERIC, 2);
                randomPlayer = null;
            }
        }


    }


}
