
package teamdraco.finsandstails.client.render;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.client.model.BandedRedbackShrimpModel;
import teamdraco.finsandstails.common.entities.BandedRedbackShrimpEntity;
import teamdraco.finsandstails.registry.FTModelLayers;

@OnlyIn(Dist.CLIENT)
public class BandedRedbackShrimpRenderer extends MobRenderer<BandedRedbackShrimpEntity, BandedRedbackShrimpModel<BandedRedbackShrimpEntity>> {
    private static final ResourceLocation BANDED_REDBACK_SHRIMP_LOCATION = new ResourceLocation(FinsAndTails.MOD_ID,"textures/entity/banded_redback_shrimp/banded_redback_shrimp.png");

    public BandedRedbackShrimpRenderer(EntityRendererProvider.Context ctx) {
        super(ctx, new BandedRedbackShrimpModel<>(ctx.bakeLayer(FTModelLayers.BANDED_REDBACK_SHRIMP)), 0.3f);
    }



    @Override
    public ResourceLocation getTextureLocation(BandedRedbackShrimpEntity entity) {
        return BANDED_REDBACK_SHRIMP_LOCATION;
    }
}
