package mod.coda.fins.client.render;

import mod.coda.fins.FinsAndTails;
import mod.coda.fins.client.model.FlatbackLeafSnailModel;
import mod.coda.fins.entities.FlatbackLeafSnailEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class FlatbackLeafSnailRenderer extends MobRenderer<FlatbackLeafSnailEntity, FlatbackLeafSnailModel<FlatbackLeafSnailEntity>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/flatback_leaf_snail.png");

    public FlatbackLeafSnailRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new FlatbackLeafSnailModel<>(), 0.3F);
    }

    public ResourceLocation getEntityTexture(FlatbackLeafSnailEntity entity) {
        return TEXTURE;
    }
}