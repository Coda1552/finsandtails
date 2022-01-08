package teamdraco.finsandstails.client.old.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.client.old.model.PhantomNudibranchModel;
import teamdraco.finsandstails.common.entities.PhantomNudibranchEntity;

public class PhantomNudibranchGlowLayer<T extends PhantomNudibranchEntity, M extends PhantomNudibranchModel<T>>  extends LayerRenderer<T, M> {
    private static final RenderType TEXTURE = RenderType.eyes(new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/phantom_nudibranch.png"));

    public PhantomNudibranchGlowLayer(IEntityRenderer<T, M> rendererIn) {
        super(rendererIn);
    }

    @Override
    public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        IVertexBuilder ivertexbuilder = bufferIn.getBuffer(this.getRenderType());
        this.getParentModel().renderToBuffer(matrixStackIn, ivertexbuilder, 2000, OverlayTexture.NO_OVERLAY, 0.5F, 0.5F, 0.5F, 0.5F);
    }

    public RenderType getRenderType() {
        return TEXTURE;
    }
}
