package teamdraco.fins.client.render;

import teamdraco.fins.FinsAndTails;
import teamdraco.fins.client.model.PhantomNudibranchModel;
import teamdraco.fins.common.entities.PhantomNudibranchEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class PhantomNudibranchRenderer extends MobRenderer<PhantomNudibranchEntity, PhantomNudibranchModel<PhantomNudibranchEntity>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/phantom_nudibranch.png");

    public PhantomNudibranchRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new PhantomNudibranchModel<>(), 0.2F);
        this.addLayer(new PhantomNudibranchGlowLayer(this));
    }

    public ResourceLocation getEntityTexture(PhantomNudibranchEntity entity) {
        return TEXTURE;
    }
}