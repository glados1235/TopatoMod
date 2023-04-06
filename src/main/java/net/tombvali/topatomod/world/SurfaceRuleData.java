package net.tombvali.topatomod.world;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.placement.CaveSurface;
import net.tombvali.topatomod.TopatoMod;
import net.tombvali.topatomod.block.ModBlocks;

public class SurfaceRuleData {
    private static final SurfaceRules.RuleSource DIRT = makeStateRule(Blocks.DIRT);
    private static final SurfaceRules.RuleSource GRASS_BLOCK = makeStateRule(Blocks.GRASS_BLOCK);
    private static final SurfaceRules.RuleSource RED_TERRACOTTA = makeStateRule(ModBlocks.DEEPSLATE_CELESTIUM_ORE.get());
    private static final SurfaceRules.RuleSource GLOWSTONE = makeStateRule(Blocks.GLOWSTONE);


    public static SurfaceRules.RuleSource TEST_BIOME = SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.TEST_BIOME), SurfaceRules.sequence(

            //SurfaceRules.ifTrue(SurfaceRules.ON_FLOOR, SurfaceRules.state(ModBlocks.DEEPSLATE_CELESTIUM_ORE.get().defaultBlockState())),

            SurfaceRules.ifTrue
                    (SurfaceRules.UNDER_CEILING, SurfaceRules.sequence
                            (SurfaceRules.ifTrue
                                    (SurfaceRules.stoneDepthCheck
                                            (40, false, CaveSurface.FLOOR), SurfaceRules.state
                                            (Blocks.DIRT.defaultBlockState())), SurfaceRules.state(Blocks.STONE.defaultBlockState())))

    ));


    public static SurfaceRules.RuleSource makeRules() {
        //SurfaceRules.ConditionSource isAtOrAboveWaterLevel = SurfaceRules.waterBlockCheck(-1, 0);
        //SurfaceRules.RuleSource grassSurface = SurfaceRules.sequence(SurfaceRules.ifTrue(isAtOrAboveWaterLevel, GRASS_BLOCK), DIRT);

        return SurfaceRules.sequence(

                //SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.TEST_BIOME), TEST_BIOME)
                //SurfaceRules.ifTrue(SurfaceRules.isBiome(ModBiomes.THE_HALLOW), TEST_BIOME)
        );
    }

    private static SurfaceRules.RuleSource makeStateRule(Block block) {
        return SurfaceRules.state(block.defaultBlockState());
    }
}