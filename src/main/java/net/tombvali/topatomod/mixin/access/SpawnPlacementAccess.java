package net.tombvali.topatomod.mixin.access;

import io.netty.channel.unix.Errors;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(SpawnPlacements.class)

public interface SpawnPlacementAccess {
    @Invoker("register")
    static <T extends Mob> void mod_register(EntityType<T> entityType, SpawnPlacements.Type decoratorType, Heightmap.Types heightMapType, SpawnPlacements.SpawnPredicate<T> decoratorPredicate) {
        throw new Error("FUCK, MY MIXIN");
    }

}
