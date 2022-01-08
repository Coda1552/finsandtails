package teamdraco.finsandstails.client.old.render;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.client.old.model.PhantomNudibranchModel;
import teamdraco.finsandstails.common.entities.PhantomNudibranchEntity;

public class PhantomNudibranchRenderer extends MobRenderer<PhantomNudibranchEntity, PhantomNudibranchModel<PhantomNudibranchEntity>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/phantom_nudibranch.png");

    public PhantomNudibranchRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new PhantomNudibranchModel<>(), 0.2F);
        this.addLayer(new PhantomNudibranchGlowLayer(this));
    }

    public ResourceLocation getTextureLocation(PhantomNudibranchEntity entity) {
        return TEXTURE;
    }
}