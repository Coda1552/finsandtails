
package teamdraco.finsandstails.client.render;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.finsandtails.geckolib3.renderers.geo.GeoEntityRenderer;
import teamdraco.finsandstails.client.model.SwampMuckerModel;
import teamdraco.finsandstails.common.entities.SwampMuckerEntity;

public class SwampMuckerRenderer extends GeoEntityRenderer<SwampMuckerEntity> {

    public SwampMuckerRenderer(EntityRendererProvider.Context context) {
        super(context, new SwampMuckerModel());
        this.shadowRadius = 0.3F;
    }
}