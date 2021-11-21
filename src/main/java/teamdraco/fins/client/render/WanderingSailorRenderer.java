package teamdraco.fins.client.render;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import teamdraco.fins.FinsAndTails;
import teamdraco.fins.client.model.WanderingSailorModel;
import teamdraco.fins.common.entities.WanderingSailorEntity;

public class WanderingSailorRenderer extends MobRenderer<WanderingSailorEntity, WanderingSailorModel<WanderingSailorEntity>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/wandering_sailor/wandering_sailor.png");

    public WanderingSailorRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new WanderingSailorModel<>(), 0.5F);
    }

    public ResourceLocation getTextureLocation(WanderingSailorEntity entity) {
        return TEXTURE;
    }
}