package teamdraco.finsandstails.client.old.render;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.client.old.model.BullCrabModel;
import teamdraco.finsandstails.common.entities.RedBullCrabEntity;

public class RedBullCrabRenderer extends MobRenderer<RedBullCrabEntity, BullCrabModel<RedBullCrabEntity>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/red_bull_crab.png");

    public RedBullCrabRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new BullCrabModel<>(), 0.3F);
    }

    public ResourceLocation getTextureLocation(RedBullCrabEntity entity) {
        return TEXTURE;
    }
}