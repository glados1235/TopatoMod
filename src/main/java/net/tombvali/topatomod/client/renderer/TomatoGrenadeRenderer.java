package net.tombvali.topatomod.client.renderer;


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.tombvali.topatomod.client.renderer.client.models.TomatoGrenadeModel;
import net.tombvali.topatomod.TopatoMod;
import net.tombvali.topatomod.entities.projectiles.TomatoGrenade;
import org.jetbrains.annotations.NotNull;

public class TomatoGrenadeRenderer extends EntityRenderer<TomatoGrenade> {

    private final TomatoGrenadeModel model;

    public TomatoGrenadeRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new TomatoGrenadeModel(context.bakeLayer(TomatoGrenadeModel.LAYER_LOCATION));

    }

    @Override
    public ResourceLocation getTextureLocation(TomatoGrenade entity) {
        return new ResourceLocation(TopatoMod.MODID, "textures/entities/tomato_grenade.png");
    }

    @Override
    public void render(TomatoGrenade p_116111_, float p_116112_, float p_116113_, PoseStack stack, @NotNull MultiBufferSource p_116115_, int p_116116_) {
        stack.pushPose();
        stack.translate(0, 1.5, 0);
        stack.mulPose(Vector3f.XP.rotationDegrees(180));
        VertexConsumer vertexconsumer = ItemRenderer.getFoilBufferDirect(p_116115_, this.model.renderType(this.getTextureLocation(p_116111_)), false, false);
        this.model.renderToBuffer(stack, vertexconsumer, p_116116_, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        stack.popPose();
        super.render(p_116111_, p_116112_, p_116113_, stack, p_116115_, p_116116_);
    }
}