
package teamdraco.finsandstails.client.render;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.client.model.OrnateBugfishModel;
import teamdraco.finsandstails.common.entities.OrnateBugfishEntity;
import teamdraco.finsandstails.registry.FTModelLayers;

public class OrnateBugfishRenderer extends MobRenderer<OrnateBugfishEntity, OrnateBugfishModel<OrnateBugfishEntity>> {
    private static final ResourceLocation ORNATE_BUGFISH_LOCATION = new ResourceLocation(FinsAndTails.MOD_ID,"textures/entity/ornate_bugfish/ornate_bugfish.png");

    public OrnateBugfishRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new OrnateBugfishModel<>(ctx.bakeLayer(FTModelLayers.ORNATE_BUGFISH)), 0.3f);
    }

    @Override
    public ResourceLocation getTextureLocation(OrnateBugfishEntity entity) {
        return ORNATE_BUGFISH_LOCATION;
    }
}
