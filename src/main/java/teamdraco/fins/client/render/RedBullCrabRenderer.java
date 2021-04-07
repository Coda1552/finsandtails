package teamdraco.fins.client.render;

import teamdraco.fins.FinsAndTails;
import teamdraco.fins.client.model.BullCrabModel;
import teamdraco.fins.common.entities.RedBullCrabEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class RedBullCrabRenderer extends MobRenderer<RedBullCrabEntity, BullCrabModel<RedBullCrabEntity>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/red_bull_crab.png");

    public RedBullCrabRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new BullCrabModel<>(), 0.3F);
    }

    public ResourceLocation getEntityTexture(RedBullCrabEntity entity) {
        return TEXTURE;
    }
}