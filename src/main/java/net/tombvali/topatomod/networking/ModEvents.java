package net.tombvali.topatomod.networking;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.tombvali.topatomod.entities.ModEntities;
import net.tombvali.topatomod.entities.nonliving.Resonance;

import static net.tombvali.topatomod.TopatoMod.MODID;


@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModEvents {

    @SubscribeEvent
    public static void onLivingDeath(LivingDeathEvent event) {
        //Dead Guy
        LivingEntity entity = event.getEntity();

        Level level = entity.level;

        //Player B)
        Player player = level.getNearestPlayer(entity,
                //Distance to search
                30);

        //Summon the ORB
        Resonance res = ModEntities.RESONANCE.get().create(level);
        res.moveTo(entity.position());
        level.addFreshEntity(res);
    }

}
