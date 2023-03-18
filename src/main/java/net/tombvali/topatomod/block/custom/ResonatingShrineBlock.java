package net.tombvali.topatomod.block.custom;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.scores.criteria.ObjectiveCriteria;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.stream.Stream;


public class ResonatingShrineBlock extends Block {
    protected static final VoxelShape SHAPE = Shapes.join(Block.box(0, 0, 0, 16, 2, 16), Block.box(2, 2, 2, 14, 4, 14), BooleanOp.OR);

    public ResonatingShrineBlock(Properties properties) {
        super(properties);

    }

    @Override
    public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext) {
        return SHAPE;
    }

    @Override
    public void stepOn(Level level, BlockPos blockPos, BlockState blockState, Entity entity) {
        super.stepOn(level, blockPos, blockState, entity);
        if (entity instanceof Player player) {

            player.addEffect(new MobEffectInstance(MobEffects.HEAL, 60 * 20, 1));


            for (ItemStack armor : player.getArmorSlots()) {
                if (!armor.isEmpty()) { // Check if the armor slot is not empty
                    armor.setDamageValue(0); // Repair the armor
                }
            }
        }
    }
}
