
package teamdraco.finsandstails.client.render;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.client.model.SwampMuckerModel;
import teamdraco.finsandstails.common.entities.SwampMuckerEntity;
import teamdraco.finsandstails.registry.FTModelLayers;

public class SwampMuckerRenderer extends MobRenderer<SwampMuckerEntity, SwampMuckerModel<SwampMuckerEntity>> {
    private static final ResourceLocation SWAMP_MUCKER_LOCATION = new ResourceLocation(FinsAndTails.MOD_ID,"textures/entity/swamp_mucker/swamp_mucker.png");

    public SwampMuckerRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new SwampMuckerModel<>(ctx.bakeLayer(FTModelLayers.SWAMP_MUCKER)), 0.4F);
    }

    @Override
    public ResourceLocation getTextureLocation(SwampMuckerEntity entity) {
        return SWAMP_MUCKER_LOCATION;
    }
}
