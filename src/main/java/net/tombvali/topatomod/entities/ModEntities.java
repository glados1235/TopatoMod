package net.tombvali.topatomod.entities;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import net.minecraftforge.registries.RegistryObject;
import net.tombvali.topatomod.TopatoMod;
import net.tombvali.topatomod.entities.mobs.LostOneEntity;
import net.tombvali.topatomod.entities.nonliving.Resonance;
import net.tombvali.topatomod.entities.projectiles.TomatoGrenade;

public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPE =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, TopatoMod.MODID);


    public static final RegistryObject<EntityType<TomatoGrenade>> TOMATO_GRENADE = ENTITY_TYPE.register("tomato_grenade", ()->
            EntityType.Builder.<TomatoGrenade>of(TomatoGrenade::new, MobCategory.MISC).sized(0.16F, 0.16F).clientTrackingRange(4).updateInterval(10).build("tomato_grenade"));

    public static final RegistryObject<EntityType<Resonance>> RESONANCE = ENTITY_TYPE.register("resonance", ()->
            EntityType.Builder.<Resonance>of(Resonance::new, MobCategory.MISC).sized(0.16F, 0.16F).clientTrackingRange(4).updateInterval(10).build("resonance"));

    public static final RegistryObject<EntityType<LostOneEntity>> LOST_ONE = ENTITY_TYPE.register("lost_one", ()->
            EntityType.Builder.<LostOneEntity>of(LostOneEntity::new, MobCategory.MONSTER).sized(1F, 2F).clientTrackingRange(4).updateInterval(10).build("lost_one"));
}
