
package teamdraco.finsandstails.client.render;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import teamdraco.finsandstails.client.model.BandedRedbackShrimpModel;
import teamdraco.finsandstails.common.entities.BandedRedbackShrimpEntity;

public class BandedRedbackShrimpRenderer extends GeoEntityRenderer<BandedRedbackShrimpEntity> {

    public BandedRedbackShrimpRenderer(EntityRendererProvider.Context context) {
        super(context, new BandedRedbackShrimpModel());
        this.shadowRadius = 0.2F;
    }

    @Override
    public RenderType getRenderType(BandedRedbackShrimpEntity animatable, ResourceLocation texture, @Nullable MultiBufferSource bufferSource, float partialTick) {
        return RenderType.entityCutout(texture);
    }
}