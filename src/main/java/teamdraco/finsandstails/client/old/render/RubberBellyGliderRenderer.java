package teamdraco.finsandstails.client.old.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.client.old.model.RubberBellyGliderModel;
import teamdraco.finsandstails.common.entities.RubberBellyGliderEntity;

public class RubberBellyGliderRenderer extends MobRenderer<RubberBellyGliderEntity, RubberBellyGliderModel<RubberBellyGliderEntity>> {
    private static final ResourceLocation TEXTURE_NORMAL = new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/rubber_belly_glider/normal.png");
    private static final ResourceLocation TEXTURE_PUFFED = new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/rubber_belly_glider/puffed.png");

    public RubberBellyGliderRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new RubberBellyGliderModel<>(), 0.4F);
    }

    public ResourceLocation getTextureLocation(RubberBellyGliderEntity entity) {
        return entity.isPuffed() ? TEXTURE_PUFFED : TEXTURE_NORMAL;
    }

    @Override
    protected void scale(RubberBellyGliderEntity entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        super.scale(entitylivingbaseIn, matrixStackIn, partialTickTime);
        matrixStackIn.translate(0, 0, 0.0);
        if (entitylivingbaseIn.isPuffed()) {
            matrixStackIn.translate(0, -0.25, 0);
        }
    }
}
