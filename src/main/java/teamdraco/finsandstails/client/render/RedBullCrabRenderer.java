
package teamdraco.finsandstails.client.render;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.finsandtails.geckolib3.renderers.geo.GeoEntityRenderer;
import teamdraco.finsandstails.client.model.RedBullCrabModel;
import teamdraco.finsandstails.common.entities.RedBullCrabEntity;

public class RedBullCrabRenderer extends GeoEntityRenderer<RedBullCrabEntity> {

    public RedBullCrabRenderer(EntityRendererProvider.Context context) {
        super(context, new RedBullCrabModel());
        this.shadowRadius = 0.2F;
    }
}