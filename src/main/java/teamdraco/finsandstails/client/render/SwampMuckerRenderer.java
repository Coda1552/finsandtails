package teamdraco.finsandstails.client.render;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.client.model.SwampMuckerModel;
import teamdraco.finsandstails.common.entities.SwampMuckerEntity;

public class SwampMuckerRenderer extends MobRenderer<SwampMuckerEntity, SwampMuckerModel<SwampMuckerEntity>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/swamp_mucker.png");

    public SwampMuckerRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new SwampMuckerModel<>(), 0.3F);
    }

    public ResourceLocation getTextureLocation(SwampMuckerEntity entity) {
        return TEXTURE;
    }

}