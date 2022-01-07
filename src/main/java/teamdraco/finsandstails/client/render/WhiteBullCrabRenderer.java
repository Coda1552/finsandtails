package teamdraco.finsandstails.client.render;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.client.model.BullCrabModel;
import teamdraco.finsandstails.common.entities.WhiteBullCrabEntity;

public class WhiteBullCrabRenderer extends MobRenderer<WhiteBullCrabEntity, BullCrabModel<WhiteBullCrabEntity>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/white_bull_crab.png");

    public WhiteBullCrabRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new BullCrabModel<>(), 0.3F);
    }

    public ResourceLocation getTextureLocation(WhiteBullCrabEntity entity) {
        return TEXTURE;
    }
}