package net.tombvali.topatomod.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.tombvali.topatomod.client.renderer.client.models.LostOneModel;
import net.tombvali.topatomod.TopatoMod;
import net.tombvali.topatomod.item.entities.mobs.LostOneEntity;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class LostOneRenderer  extends GeoEntityRenderer<LostOneEntity> {

    public LostOneRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new LostOneModel());
        this.shadowRadius = 1.4f;


    }

    @Override
    public ResourceLocation getTextureLocation(LostOneEntity animatable) {
        return new ResourceLocation(TopatoMod.MODID, "textures/entities/lost_one.png");
    }

    @Override
    public RenderType getRenderType(LostOneEntity animatable, float partialTick, PoseStack poseStack, @Nullable MultiBufferSource bufferSource, @Nullable VertexConsumer buffer, int packedLight, ResourceLocation texture) {
        return super.getRenderType(animatable, partialTick, poseStack, bufferSource, buffer, packedLight, texture);
    }
}
