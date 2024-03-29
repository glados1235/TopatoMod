package net.tombvali.topatomod;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.tombvali.topatomod.client.renderer.LostOneRenderer;
import net.tombvali.topatomod.client.renderer.ResonanceRenderer;
import net.tombvali.topatomod.client.renderer.TomatoGrenadeRenderer;
import net.tombvali.topatomod.client.renderer.client.models.LostOneModel;
import net.tombvali.topatomod.client.renderer.client.models.ResonanceModel;
import net.tombvali.topatomod.client.renderer.client.models.TomatoGrenadeModel;
import net.tombvali.topatomod.block.ModBlocks;
import net.tombvali.topatomod.item.entities.ModEntities;
import net.tombvali.topatomod.item.entities.mobs.LostOneEntity;
import net.tombvali.topatomod.item.ModItems;
import net.tombvali.topatomod.mixin.access.SpawnPlacementAccess;
import net.tombvali.topatomod.networking.packets.ForgeNetworkHandler;
import net.tombvali.topatomod.painting.ModPaintings;
import net.tombvali.topatomod.sounds.ModSounds;

import net.tombvali.topatomod.world.SurfaceRuleData;
import net.tombvali.topatomod.world.feature.ModConfiguredFeatures;
import net.tombvali.topatomod.world.feature.ModPlacedFeatures;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.bernie.geckolib3.GeckoLib;
import terrablender.api.SurfaceRuleManager;


// The value here should match an entry in the META-INF/mods.toml file
@Mod(TopatoMod.MODID)
public class TopatoMod {
    public static ResourceKey<Level> SOUND_DIMENSION;
    public static final String MODID = "topatomod";


    public static final Logger LOGGER = LogManager.getLogger();

    public TopatoMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::commonSetup);
        ModItems.ITEMS.register(modEventBus);
        ModEntities.ENTITY_TYPE.register(modEventBus);
        ModBlocks.BLOCKS.register(modEventBus);
        ModSounds.SOUND_EVENTS.register(modEventBus);
        ModConfiguredFeatures.CONFIGURED_FEATURES.register(modEventBus);
        ModPlacedFeatures.PLACED_FEATURES.register(modEventBus);
        ModPaintings.PAINTING_VARIANTS.register(modEventBus);


        GeckoLib.initialize();
        modEventBus.addListener(this::registerEntityRenderers);
        modEventBus.addListener(this::bakeLayers);
        modEventBus.addListener(this::entityAttributeEvent);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() ->
                SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, MODID, SurfaceRuleData.makeRules()));
        ForgeNetworkHandler.init();

        SpawnPlacementAccess.mod_register(ModEntities.LOST_ONE.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, LostOneEntity::checkSpawnRules);
        SOUND_DIMENSION = ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(MODID, "sound_dimension"));
    }

    public void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers renderer) {
        renderer.registerEntityRenderer(ModEntities.TOMATO_GRENADE.get(), TomatoGrenadeRenderer::new);
        renderer.registerEntityRenderer(ModEntities.RESONANCE.get(), ResonanceRenderer::new);
        renderer.registerEntityRenderer(ModEntities.LOST_ONE.get(), LostOneRenderer::new);
    }

    public void bakeLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(TomatoGrenadeModel.LAYER_LOCATION, TomatoGrenadeModel::createBodyLayer);
        event.registerLayerDefinition(ResonanceModel.LAYER_LOCATION, ResonanceModel::createBodyLayer);
        event.registerLayerDefinition(LostOneModel.LAYER_LOCATION, ResonanceModel::createBodyLayer);
    }

    public void entityAttributeEvent(EntityAttributeCreationEvent event) {
        event.put(ModEntities.LOST_ONE.get(), LostOneEntity.setAttribute());
    }


    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
        }
    }
}
