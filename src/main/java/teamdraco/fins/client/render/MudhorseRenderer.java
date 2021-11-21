package teamdraco.fins.client.render;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import teamdraco.fins.FinsAndTails;
import teamdraco.fins.client.model.MudhorseModel;
import teamdraco.fins.common.entities.MudhorseEntity;

public class MudhorseRenderer extends MobRenderer<MudhorseEntity, MudhorseModel<MudhorseEntity>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/mudhorse.png");

    public MudhorseRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new MudhorseModel<>(), 0.6F);
    }

    public ResourceLocation getTextureLocation(MudhorseEntity entity) {
        return TEXTURE;
    }
}