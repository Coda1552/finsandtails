
package teamdraco.finsandstails.client.render;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.finsandtails.geckolib3.renderers.geo.GeoEntityRenderer;
import teamdraco.finsandstails.client.model.GoldenRiverRayModel;
import teamdraco.finsandstails.common.entities.GoldenRiverRayEntity;

public class GoldenRiverRayRenderer extends GeoEntityRenderer<GoldenRiverRayEntity> {

    public GoldenRiverRayRenderer(EntityRendererProvider.Context context) {
        super(context, new GoldenRiverRayModel());
        this.shadowRadius = 0.4F;
    }
}