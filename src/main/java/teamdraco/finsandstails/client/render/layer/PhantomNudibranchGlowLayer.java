package teamdraco.finsandstails.client.render.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoRenderer;
import software.bernie.geckolib.renderer.layer.GeoRenderLayer;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.entities.PhantomNudibranchEntity;

public class PhantomNudibranchGlowLayer extends GeoRenderLayer<PhantomNudibranchEntity> {
    private static final ResourceLocation OVERLAY = new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/phantom_nudibranch.png");
    private static final ResourceLocation MODEL = new ResourceLocation(FinsAndTails.MOD_ID, "geo/entity/phantom_nudibranch.geo.json");

    public PhantomNudibranchGlowLayer(GeoRenderer<PhantomNudibranchEntity> entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(PoseStack poseStack, PhantomNudibranchEntity animatable, BakedGeoModel bakedModel, RenderType renderType, MultiBufferSource bufferSource, VertexConsumer buffer, float partialTick, int packedLight, int packedOverlay) {
        RenderType cameo = RenderType.eyes(OVERLAY);
        this.getRenderer().reRender(this.getGeoModel().getBakedModel(MODEL), poseStack, bufferSource, animatable, cameo, bufferSource.getBuffer(cameo), partialTick, packedLight, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
    }

}
