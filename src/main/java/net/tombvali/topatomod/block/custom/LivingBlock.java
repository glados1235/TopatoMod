package net.tombvali.topatomod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.AABB;
import net.tombvali.topatomod.block.ModBlocks;

import static net.tombvali.topatomod.TopatoMod.LOGGER;

import java.util.ArrayList;
import java.util.List;

import java.util.Random;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class LivingBlock extends Block {

    public static final BooleanProperty IS_CORE = BooleanProperty.create("is_core");
    Random random = new Random();
    public int maxBlockCount;
    public float maxRange;
    public static final IntegerProperty BLOCK_COUNT = IntegerProperty.create("block_count", 1, 20);

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(BLOCK_COUNT, IS_CORE);
    }

    public LivingBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(IS_CORE, true));
        this.registerDefaultState(this.defaultBlockState().setValue(BLOCK_COUNT, 1));
    }

    @Override
    public void onPlace(BlockState blockState, Level level, BlockPos blockPos, BlockState blockState1, boolean b) {
        super.onPlace(blockState, level, blockPos, blockState1, b);
        maxRange = 0;
        maxBlockCount = 0;
        maxRange = 1 + random.nextFloat(2.5f);
        maxBlockCount = 1 + random.nextInt(20);
    }

    public boolean isRandomlyTicking(BlockState value) {
        return true;
    }

    @Override
    public void randomTick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource randomSource) {
        super.randomTick(blockState, serverLevel, blockPos, randomSource);
        if (blockState.getValue(IS_CORE) && blockState.getValue(BLOCK_COUNT) <= maxBlockCount - 1) {
            AABB aabb = new AABB(blockPos).inflate(maxRange);
            List<BlockPos> nonAirNeighbors = BlockPos.betweenClosedStream(aabb).filter(pos -> !serverLevel.getBlockState(pos).isAir()).map(BlockPos::immutable).toList();
            if (!nonAirNeighbors.isEmpty()) {
                BlockPos randomNonAirBlock = nonAirNeighbors.get(new Random().nextInt(nonAirNeighbors.size()));
                if (!randomNonAirBlock.equals(blockPos) && serverLevel.getBlockState(randomNonAirBlock).getBlock() != blockState.getBlock()) {
                    blockState = blockState.setValue(BLOCK_COUNT, blockState.getValue(BLOCK_COUNT) + 1);
                    serverLevel.setBlockAndUpdate(blockPos, blockState);
                    serverLevel.setBlock(randomNonAirBlock, ModBlocks.DEEPSLATE_PHOENIXITE_ORE.get().defaultBlockState().setValue(IS_CORE, false), 3);
                    LOGGER.info(blockState.getValue(BLOCK_COUNT));
                    LOGGER.info("Deep Tick!");
                }
            }
        }
    }
}
