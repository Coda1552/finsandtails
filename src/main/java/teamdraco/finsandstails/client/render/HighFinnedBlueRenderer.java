
package teamdraco.finsandstails.client.render;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import teamdraco.finsandstails.client.model.HighFinnedBlueModel;
import teamdraco.finsandstails.common.entities.HighFinnedBlueEntity;

public class HighFinnedBlueRenderer extends GeoEntityRenderer<HighFinnedBlueEntity> {

    public HighFinnedBlueRenderer(EntityRendererProvider.Context context) {
        super(context, new HighFinnedBlueModel());
        this.shadowRadius = 0.3F;
    }
}