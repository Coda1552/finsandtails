package teamdraco.fins.client.render;

import teamdraco.fins.FinsAndTails;
import teamdraco.fins.client.model.GopjetModel;
import teamdraco.fins.common.entities.GopjetEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class GopjetRenderer extends MobRenderer<GopjetEntity, GopjetModel<GopjetEntity>> {
    private static final ResourceLocation STANDARD = new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/gopjet/standard.png");
    private static final ResourceLocation BOOSTING = new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/gopjet/boosting.png");

    public GopjetRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new GopjetModel<>(), 0.3F);
    }

    public ResourceLocation getTextureLocation(GopjetEntity entity) {
        return entity.isBoosting() ? BOOSTING : STANDARD;
    }
}