package mod.coda.fins.client.render;

import mod.coda.fins.FinsAndTails;
import mod.coda.fins.client.model.SwampMuckerModel;
import mod.coda.fins.entities.SwampMuckerEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class SwampMuckerRenderer extends MobRenderer<SwampMuckerEntity, SwampMuckerModel<SwampMuckerEntity>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/swamp_mucker.png");

    public SwampMuckerRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new SwampMuckerModel<>(), 0.3F);
    }

    public ResourceLocation getEntityTexture(SwampMuckerEntity entity) {
        return TEXTURE;
    }

}