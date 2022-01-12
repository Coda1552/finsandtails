
package teamdraco.finsandstails.client.render;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.finsandtails.geckolib3.renderers.geo.GeoEntityRenderer;
import teamdraco.finsandstails.client.model.FlatbackSuckerModel;
import teamdraco.finsandstails.common.entities.FlatbackSuckerEntity;

public class FlatbackSuckerRenderer extends GeoEntityRenderer<FlatbackSuckerEntity> {

    public FlatbackSuckerRenderer(EntityRendererProvider.Context context) {
        super(context, new FlatbackSuckerModel());
        this.shadowRadius = 0.3F;
    }
}