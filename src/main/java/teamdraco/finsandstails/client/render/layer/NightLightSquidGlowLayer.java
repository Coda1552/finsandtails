package teamdraco.finsandstails.client.render.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.renderers.geo.GeoLayerRenderer;
import software.bernie.geckolib3.renderers.geo.IGeoRenderer;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.entities.NightLightSquidEntity;

public class NightLightSquidGlowLayer extends GeoLayerRenderer<NightLightSquidEntity> {
    private static final ResourceLocation OVERLAY = new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/night_light_squid/night_light_squid_e.png");
    private static final ResourceLocation MODEL = new ResourceLocation(FinsAndTails.MOD_ID, "geo/entity/night_light_squid.geo.json");

    public NightLightSquidGlowLayer(IGeoRenderer<NightLightSquidEntity> entityRendererIn) {
        super(entityRendererIn);
    }

    @Override
    public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, NightLightSquidEntity entityLivingBaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        RenderType cameo = RenderType.eyes(OVERLAY);
        this.getRenderer().render(this.getEntityModel().getModel(MODEL), entityLivingBaseIn, partialTicks, cameo, matrixStackIn, bufferIn,
                bufferIn.getBuffer(cameo), 15000, OverlayTexture.NO_OVERLAY, 1f, 1f, 1f, 1.0f);
    }
}
