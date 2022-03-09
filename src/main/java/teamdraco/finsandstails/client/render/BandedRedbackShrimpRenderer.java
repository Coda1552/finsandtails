
package teamdraco.finsandstails.client.render;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import teamdraco.finsandstails.client.model.BandedRedbackShrimpModel;
import teamdraco.finsandstails.common.entities.BandedRedbackShrimpEntity;

public class BandedRedbackShrimpRenderer extends GeoEntityRenderer<BandedRedbackShrimpEntity> {

    public BandedRedbackShrimpRenderer(EntityRendererProvider.Context context) {
        super(context, new BandedRedbackShrimpModel());
        this.shadowRadius = 0.2F;
    }
}