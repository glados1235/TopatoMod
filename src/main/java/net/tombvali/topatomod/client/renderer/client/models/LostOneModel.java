package net.tombvali.topatomod.client.renderer.client.models;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.tombvali.topatomod.TopatoMod;
import net.tombvali.topatomod.entities.mobs.LostOneEntity;
import software.bernie.example.entity.GeoExampleEntity;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;

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

    @Override
    public void setCustomAnimations(LostOneEntity animatable, int instanceId, AnimationEvent animationEvent) {
        super.setCustomAnimations(animatable, instanceId, animationEvent);
        IBone head = this.getAnimationProcessor().getBone("Neck");

        EntityModelData extraData = (EntityModelData) animationEvent.getExtraDataOfType(EntityModelData.class).get(0);
        if (head != null) {
            head.setRotationX(extraData.headPitch * Mth.DEG_TO_RAD);
            head.setRotationY(extraData.netHeadYaw * Mth.DEG_TO_RAD);
        }
    }
}
