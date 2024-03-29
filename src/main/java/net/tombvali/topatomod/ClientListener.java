package net.tombvali.topatomod;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.tombvali.topatomod.item.entities.armor.StardustArmorRenderer;
import net.tombvali.topatomod.item.custom.armor.StardustArmorItem;

import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

@Mod.EventBusSubscriber(modid = TopatoMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientListener {


    @SubscribeEvent
    public static void registerRenderers(final EntityRenderersEvent.AddLayers event) {

        GeoArmorRenderer.registerArmorRenderer(StardustArmorItem.class, () -> new StardustArmorRenderer());

    }

}