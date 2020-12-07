package mod.coda.fins.client.render;

import mod.coda.fins.FinsAndTails;
import mod.coda.fins.client.model.MudhorseModel;
import mod.coda.fins.entity.MudhorseEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class MudhorseRenderer extends MobRenderer<MudhorseEntity, MudhorseModel<MudhorseEntity>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/mudhorse.png");

    public MudhorseRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new MudhorseModel<>(), 0.6F);
    }

    public ResourceLocation getEntityTexture(MudhorseEntity entity) {
        return TEXTURE;
    }
}