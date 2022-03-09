
package teamdraco.finsandstails.client.render;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import teamdraco.finsandstails.client.model.WeeWeeModel;
import teamdraco.finsandstails.common.entities.WeeWeeEntity;

public class WeeWeeRenderer extends GeoEntityRenderer<WeeWeeEntity> {

    public WeeWeeRenderer(EntityRendererProvider.Context context) {
        super(context, new WeeWeeModel());
        this.shadowRadius = 0.1F;
    }
}