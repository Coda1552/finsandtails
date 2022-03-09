
package teamdraco.finsandstails.client.render;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import teamdraco.finsandstails.client.model.WherbleModel;
import teamdraco.finsandstails.common.entities.WherbleEntity;

public class WherbleRenderer extends GeoEntityRenderer<WherbleEntity> {

    public WherbleRenderer(EntityRendererProvider.Context context) {
        super(context, new WherbleModel());
        this.shadowRadius = 0.3F;
    }
}