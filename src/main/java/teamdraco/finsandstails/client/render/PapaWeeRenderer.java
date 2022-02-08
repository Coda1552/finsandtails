
package teamdraco.finsandstails.client.render;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.finsandtails.geckolib3.renderers.geo.GeoEntityRenderer;
import teamdraco.finsandstails.client.model.PapaWeeModel;
import teamdraco.finsandstails.common.entities.PapaWeeEntity;

public class PapaWeeRenderer extends GeoEntityRenderer<PapaWeeEntity> {

    public PapaWeeRenderer(EntityRendererProvider.Context context) {
        super(context, new PapaWeeModel());
        this.shadowRadius = 0.25F;
    }
}