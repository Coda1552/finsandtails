
package teamdraco.finsandstails.client.render;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import teamdraco.finsandstails.client.model.BluWeeModel;
import teamdraco.finsandstails.client.model.PenglilModel;
import teamdraco.finsandstails.common.entities.BluWeeEntity;
import teamdraco.finsandstails.common.entities.PenglilEntity;

public class PenglilRenderer extends GeoEntityRenderer<PenglilEntity> {

    public PenglilRenderer(EntityRendererProvider.Context context) {
        super(context, new PenglilModel());
        this.shadowRadius = 0.35F;
    }
}