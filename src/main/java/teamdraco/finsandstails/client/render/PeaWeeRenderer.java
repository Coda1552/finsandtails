
package teamdraco.finsandstails.client.render;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import teamdraco.finsandstails.client.model.PeaWeeModel;
import teamdraco.finsandstails.common.entities.PeaWeeEntity;

public class PeaWeeRenderer extends GeoEntityRenderer<PeaWeeEntity> {

    public PeaWeeRenderer(EntityRendererProvider.Context context) {
        super(context, new PeaWeeModel());
        this.shadowRadius = 0.2F;
    }
}