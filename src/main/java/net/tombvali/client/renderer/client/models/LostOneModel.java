package net.tombvali.client.renderer.client.models;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.tombvali.topatomod.TopatoMod;
import net.tombvali.topatomod.entities.mobs.LostOneEntity;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class LostOneModel extends AnimatedGeoModel<LostOneEntity> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(TopatoMod.MODID, "lost_one"), "main");
    @Override
    public ResourceLocation getModelResource(LostOneEntity object) {
        return new ResourceLocation(TopatoMod.MODID, "geo/lost_one.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(LostOneEntity object) {
        return new ResourceLocation(TopatoMod.MODID, "textures/entities/lost_one.png");
    }

    @Override
    public ResourceLocation getAnimationResource(LostOneEntity animatable) {
        return new ResourceLocation(TopatoMod.MODID, "animations/lost_one.animation.json");
    }
}
