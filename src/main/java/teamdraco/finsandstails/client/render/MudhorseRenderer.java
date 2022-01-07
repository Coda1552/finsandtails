package teamdraco.finsandstails.client.render;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.client.model.MudhorseModel;
import teamdraco.finsandstails.common.entities.MudhorseEntity;

public class MudhorseRenderer extends MobRenderer<MudhorseEntity, MudhorseModel<MudhorseEntity>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/mudhorse.png");

    public MudhorseRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new MudhorseModel<>(), 0.6F);
    }

    public ResourceLocation getTextureLocation(MudhorseEntity entity) {
        return TEXTURE;
    }
}