
package teamdraco.finsandstails.client.render;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import teamdraco.finsandstails.client.model.GopjetModel;
import teamdraco.finsandstails.common.entities.GopjetEntity;

public class GopjetRenderer extends GeoEntityRenderer<GopjetEntity> {

    public GopjetRenderer(EntityRendererProvider.Context context) {
        super(context, new GopjetModel());
        this.shadowRadius = 0.4F;
    }
}