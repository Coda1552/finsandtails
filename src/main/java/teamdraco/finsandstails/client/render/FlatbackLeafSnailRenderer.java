
package teamdraco.finsandstails.client.render;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.finsandtails.geckolib3.renderers.geo.GeoEntityRenderer;
import teamdraco.finsandstails.client.model.FlatbackLeafSnailModel;
import teamdraco.finsandstails.common.entities.FlatbackLeafSnailEntity;

public class FlatbackLeafSnailRenderer extends GeoEntityRenderer<FlatbackLeafSnailEntity> {

    public FlatbackLeafSnailRenderer(EntityRendererProvider.Context context) {
        super(context, new FlatbackLeafSnailModel());
        this.shadowRadius = 0.3F;
    }
}