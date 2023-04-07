package net.tombvali.topatomod.entities.armor;

import net.minecraft.resources.ResourceLocation;
import net.tombvali.topatomod.TopatoMod;
import net.tombvali.topatomod.item.custom.armor.StardustArmorItem;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class StardustArmorModel extends AnimatedGeoModel<StardustArmorItem> {
    @Override
    public ResourceLocation getModelResource(StardustArmorItem object) {
        return new ResourceLocation(TopatoMod.MODID, "geo/stardust_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(StardustArmorItem object) {
        return new ResourceLocation(TopatoMod.MODID, "textures/models/armor/stardust_armor.png");
    }

    @Override
    public ResourceLocation getAnimationResource(StardustArmorItem animatable) {
        return new ResourceLocation(TopatoMod.MODID, "animations/stardust_armor.animation.json");
    }
}
