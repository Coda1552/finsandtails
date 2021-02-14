package mod.coda.fins.client.render;

import mod.coda.fins.FinsAndTails;
import mod.coda.fins.client.model.BullCrabModel;
import mod.coda.fins.entities.WhiteBullCrabEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class WhiteBullCrabRenderer extends MobRenderer<WhiteBullCrabEntity, BullCrabModel<WhiteBullCrabEntity>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/white_bull_crab.png");

    public WhiteBullCrabRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new BullCrabModel<>(), 0.3F);
    }

    public ResourceLocation getEntityTexture(WhiteBullCrabEntity entity) {
        return TEXTURE;
    }
}