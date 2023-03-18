package net.tombvali.client.renderer;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.tombvali.client.renderer.client.models.ResonanceModel;
import net.tombvali.topatomod.TopatoMod;
import net.tombvali.topatomod.entities.nonliving.Resonance;
import org.jetbrains.annotations.NotNull;

public class ResonanceRenderer extends EntityRenderer<Resonance> {

    private final ResonanceModel model;

    public ResonanceRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new ResonanceModel(context.bakeLayer(ResonanceModel.LAYER_LOCATION));

    }


    @Override
    public ResourceLocation getTextureLocation(Resonance entity) {
        return new ResourceLocation(TopatoMod.MODID, "textures/entities/resonance.png");
    }

    @Override
    public void render(Resonance resonance, float v, float v1, PoseStack stack, @NotNull MultiBufferSource multiBufferSource, int i) {
        stack.pushPose();
        VertexConsumer vertexconsumer = ItemRenderer.getFoilBufferDirect(multiBufferSource, this.model.renderType(this.getTextureLocation(resonance)), false, false);
        this.model.renderToBuffer(stack, vertexconsumer, i, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        stack.popPose();
        super.render(resonance, v, v1, stack, multiBufferSource, i);
    }
}