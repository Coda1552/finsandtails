package teamdraco.finsandstails.client.render;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.client.model.FlatbackLeafSnailModel;
import teamdraco.finsandstails.common.entities.FlatbackLeafSnailEntity;

public class FlatbackLeafSnailRenderer extends MobRenderer<FlatbackLeafSnailEntity, FlatbackLeafSnailModel<FlatbackLeafSnailEntity>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/flatback_leaf_snail.png");

    public FlatbackLeafSnailRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new FlatbackLeafSnailModel<>(), 0.3F);
    }

    public ResourceLocation getTextureLocation(FlatbackLeafSnailEntity entity) {
        return TEXTURE;
    }
}