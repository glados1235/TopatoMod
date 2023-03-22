package net.tombvali.topatomod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.AABB;
import net.tombvali.topatomod.block.ModBlocks;

import static net.tombvali.topatomod.TopatoMod.LOGGER;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

public class LivingBlock extends Block {

    public static final BooleanProperty IS_CORE = BooleanProperty.create("is_core");
    public static final IntegerProperty TICK_COUNT = IntegerProperty.create("tick_count", 0, 320);

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(TICK_COUNT, IS_CORE);
    }

    public LivingBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(IS_CORE, Boolean.valueOf(true)));
    }

    public boolean isRandomlyTicking(BlockState value) {
        return true;
    }

    @Override
    public void randomTick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
        super.randomTick(blockState, serverLevel, blockPos, randomSource);

        AABB searchArea = new AABB(blockPos).inflate(5.0);
        List<Player> nearbyPlayers = serverLevel.getNearbyPlayers(TargetingConditions.DEFAULT, null, searchArea);
        if (!nearbyPlayers.isEmpty()) {
            Player randomPlayer = nearbyPlayers.get(new Random().nextInt(nearbyPlayers.size()));

            if(randomPlayer != null){
                randomPlayer.hurt(DamageSource.GENERIC, 2);
                randomPlayer = null;
                nearbyPlayers.clear();
            }
        }
        else {
            // create a list of non-air neighboring blocks
            if(blockState.getValue(IS_CORE) == true){

                List<BlockPos> nonAirNeighbors = new ArrayList<BlockPos>();
                for (Direction dir : Direction.values()) {
                    BlockPos neighborPos = blockPos.offset(dir.getNormal());
                    if (serverLevel.getBlockState(neighborPos) != Blocks.AIR.defaultBlockState()) {
                        nonAirNeighbors.add(neighborPos);
                    }
                }
                if (!nonAirNeighbors.isEmpty()) {
                    LOGGER.info("Tick!");
                    // randomly select a non-air neighboring block and change its state
                    BlockPos randomNonAirBlock = nonAirNeighbors.get(new Random().nextInt(nonAirNeighbors.size()));
                    serverLevel.setBlock(randomNonAirBlock, ModBlocks.TOPATO_BLOCK.get().defaultBlockState().setValue(this.IS_CORE, false), 3);
                }
            }

        }
    }
}
