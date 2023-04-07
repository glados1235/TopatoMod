package net.tombvali.topatomod.util;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.tombvali.topatomod.TopatoMod;

import java.util.stream.Stream;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> NEEDS_STARDUST_TOOL
                = tag("needs_stardust_tool");

        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(TopatoMod.MODID, name));
        }

        private static TagKey<Block> forgeTag(String name) {
            return BlockTags.create(new ResourceLocation("forge", name));
        }
    }

}
