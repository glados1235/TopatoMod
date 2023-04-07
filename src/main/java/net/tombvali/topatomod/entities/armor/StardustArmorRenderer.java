package net.tombvali.topatomod.entities.armor;

import net.tombvali.topatomod.item.custom.armor.StardustArmorItem;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class StardustArmorRenderer extends GeoArmorRenderer<StardustArmorItem> {

    public StardustArmorRenderer() {

        super(new StardustArmorModel());

        this.headBone = "armorHead";
        this.bodyBone = "armorBody";
        this.rightArmBone = "armorRightArm";
        this.leftArmBone = "armorLeftArm";
        this.rightLegBone = "armorRightLeg";
        this.leftLegBone = "armorLeftLeg";
        this.rightBootBone = "armorRightBoot";
        this.leftBootBone = "armorLeftBoot";
    }
}
