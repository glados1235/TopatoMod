package net.tombvali.topatomod;

import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.tombvali.client.renderer.ResonanceRenderer;
import net.tombvali.client.renderer.TomatoGrenadeRenderer;
import net.tombvali.client.renderer.client.models.ResonanceModel;
import net.tombvali.client.renderer.client.models.TomatoGrenadeModel;
import net.tombvali.topatomod.block.ModBlocks;
import net.tombvali.topatomod.entities.ModEntities;
import net.tombvali.topatomod.entities.nonliving.Resonance;
import net.tombvali.topatomod.item.ModItems;
import net.tombvali.topatomod.networking.ModMessages;
import net.tombvali.topatomod.painting.ModPaintings;

import net.tombvali.topatomod.world.feature.ModConfiguredFeatures;
import net.tombvali.topatomod.world.feature.ModPlacedFeatures;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(TopatoMod.MODID)
public class TopatoMod
{
    public static final String MODID = "topatomod";

    private static final Logger LOGGER = LogUtils.getLogger();

    public TopatoMod()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModItems.ITEMS.register(modEventBus);
        ModEntities.ENTITY_TYPE.register(modEventBus);
        ModBlocks.BLOCKS.register(modEventBus);
        ModConfiguredFeatures.CONFIGURED_FEATURES.register(modEventBus);
        ModPlacedFeatures.PLACED_FEATURES.register(modEventBus);
        ModPaintings.PAINTING_VARIANTS.register(modEventBus);
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::registerEntityRenderers);
        modEventBus.addListener(this::bakeLayers);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(ModMessages::register);

    }

    public void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers renderer) {
        renderer.registerEntityRenderer(ModEntities.TOMATO_GRENADE.get(), TomatoGrenadeRenderer::new);
        renderer.registerEntityRenderer(ModEntities.RESONANCE.get(), ResonanceRenderer::new);
    }
    public void bakeLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(TomatoGrenadeModel.LAYER_LOCATION, TomatoGrenadeModel::createBodyLayer);
        event.registerLayerDefinition(ResonanceModel.LAYER_LOCATION, ResonanceModel::createBodyLayer);
    }


    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {

        }
    }
}
