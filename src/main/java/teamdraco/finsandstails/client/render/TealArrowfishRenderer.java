
package teamdraco.finsandstails.client.render;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.client.model.TealArrowfishModel;
import teamdraco.finsandstails.common.entities.TealArrowfishEntity;
import teamdraco.finsandstails.registry.FTModelLayers;

public class TealArrowfishRenderer extends MobRenderer<TealArrowfishEntity, TealArrowfishModel<TealArrowfishEntity>> {
    private static final ResourceLocation TEAL_ARROWFISH_LOCATION = new ResourceLocation(FinsAndTails.MOD_ID,"textures/entity/teal_arrowfish/teal_arrowfish.png");

    public TealArrowfishRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new TealArrowfishModel<>(ctx.bakeLayer(FTModelLayers.TEAL_ARROWFISH)), 0.3F);
    }

    @Override
    public ResourceLocation getTextureLocation(TealArrowfishEntity entity) {
        return TEAL_ARROWFISH_LOCATION;
    }
}
