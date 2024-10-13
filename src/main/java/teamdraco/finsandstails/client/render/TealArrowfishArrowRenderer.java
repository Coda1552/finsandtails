package teamdraco.finsandstails.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.client.model.TealArrowfishModel;
import teamdraco.finsandstails.common.entities.item.TealArrowfishArrowEntity;
import teamdraco.finsandstails.registry.FTModelLayers;

public class TealArrowfishArrowRenderer extends EntityRenderer<TealArrowfishArrowEntity> {
    private static final ResourceLocation TEAL_ARROWFISH_LOCATION = new ResourceLocation(FinsAndTails.MOD_ID,"textures/entity/teal_arrowfish/teal_arrowfish.png");
    private TealArrowfishModel model;


    public TealArrowfishArrowRenderer(EntityRendererProvider.Context ctx) {
        super(ctx);
        this.model = new TealArrowfishModel(ctx.bakeLayer(FTModelLayers.TEAL_ARROWFISH_ARROW));
    }

    @Override
    public ResourceLocation getTextureLocation(TealArrowfishArrowEntity entity) {
        return TEAL_ARROWFISH_LOCATION;
    }

    @Override
    public void render(TealArrowfishArrowEntity p_113839_, float p_113840_, float p_113841_, PoseStack p_113842_, MultiBufferSource p_113843_, int p_113844_) {
        p_113842_.pushPose();
        //p_113842_.mulPose(Axis.YP.rotationDegrees(Mth.lerp(p_113841_, p_113839_.yRotO, p_113839_.getYRot()) - 90.0F));
        //p_113842_.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(p_113841_, p_113839_.xRotO, p_113839_.getXRot())));

        //VertexConsumer vertexconsumer = p_113843_.getBuffer(this.model.renderType(TEAL_ARROWFISH_LOCATION));
        //TODO this is broken
        //this.model.renderToBuffer(p_113842_, vertexconsumer, p_113844_, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);

        //float $$16 = (float)p_113839_.shakeTime - p_113841_;
        //if ($$16 > 0.0F) {
        //    float $$17 = -Mth.sin($$16 * 3.0F) * $$16;
        //    p_113842_.mulPose(Axis.ZP.rotationDegrees($$17));
        //}

        //p_113842_.mulPose(Axis.XP.rotationDegrees(45.0F));
        //p_113842_.scale(0.05625F, 0.05625F, 0.05625F);
        //p_113842_.translate(-4.0F, 0.0F, 0.0F);
        p_113842_.popPose();
        super.render(p_113839_, p_113840_, p_113841_, p_113842_, p_113843_, p_113844_);
    }

}
