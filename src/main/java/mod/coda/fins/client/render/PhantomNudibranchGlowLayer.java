package mod.coda.fins.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import mod.coda.fins.FinsAndTails;
import mod.coda.fins.client.model.PhantomNudibranchModel;
import mod.coda.fins.entity.PhantomNudibranchEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;

public class PhantomNudibranchGlowLayer<T extends PhantomNudibranchEntity, M extends PhantomNudibranchModel<T>>  extends LayerRenderer<T, M> {
    private static final RenderType TEXTURE = RenderType.getEyes(new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/phantom_nudibranch.png"));

    public PhantomNudibranchGlowLayer(IEntityRenderer<T, M> rendererIn) {
        super(rendererIn);
    }

    @Override
    public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, T entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        IVertexBuilder ivertexbuilder = bufferIn.getBuffer(this.getRenderType());
        this.getEntityModel().render(matrixStackIn, ivertexbuilder,  2000, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
    }

    public RenderType getRenderType() {
        return TEXTURE;
    }
}
