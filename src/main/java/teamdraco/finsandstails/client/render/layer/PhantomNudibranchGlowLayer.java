package teamdraco.finsandstails.client.render.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import software.bernie.finsandtails.geckolib3.renderers.geo.GeoLayerRenderer;
import software.bernie.finsandtails.geckolib3.renderers.geo.IGeoRenderer;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.entities.PhantomNudibranchEntity;

public class PhantomNudibranchGlowLayer extends GeoLayerRenderer<PhantomNudibranchEntity> {
    private static final ResourceLocation OVERLAY = new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/phantom_nudibranch.png");
    private static final ResourceLocation MODEL = new ResourceLocation(FinsAndTails.MOD_ID, "geo/entity/phantom_nudibranch.geo.json");

    public PhantomNudibranchGlowLayer(IGeoRenderer<PhantomNudibranchEntity> entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, PhantomNudibranchEntity entityLivingBaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        RenderType cameo = RenderType.eyes(OVERLAY);
        this.getRenderer().render(this.getEntityModel().getModel(MODEL), entityLivingBaseIn, partialTicks, cameo, matrixStackIn, bufferIn,
                bufferIn.getBuffer(cameo), 2000, OverlayTexture.NO_OVERLAY, 0.5F, 0.5F, 0.5F, 0.5F);
    }
}
