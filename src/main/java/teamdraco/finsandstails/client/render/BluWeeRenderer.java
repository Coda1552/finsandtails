
package teamdraco.finsandstails.client.render;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.finsandtails.geckolib3.renderers.geo.GeoEntityRenderer;
import teamdraco.finsandstails.client.model.BluWeeModel;
import teamdraco.finsandstails.common.entities.BluWeeEntity;

public class BluWeeRenderer extends GeoEntityRenderer<BluWeeEntity> {

    public BluWeeRenderer(EntityRendererProvider.Context context) {
        super(context, new BluWeeModel());
        this.shadowRadius = 0.2F;
    }
}