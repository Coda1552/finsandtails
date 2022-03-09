
package teamdraco.finsandstails.client.render;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import teamdraco.finsandstails.client.model.VibraWeeModel;
import teamdraco.finsandstails.common.entities.VibraWeeEntity;

public class VibraWeeRenderer extends GeoEntityRenderer<VibraWeeEntity> {

    public VibraWeeRenderer(EntityRendererProvider.Context context) {
        super(context, new VibraWeeModel());
        this.shadowRadius = 0.2F;
    }
}