
package teamdraco.finsandstails.client.render;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.finsandtails.geckolib3.renderers.geo.GeoEntityRenderer;
import teamdraco.finsandstails.client.model.OrnateBugfishModel;
import teamdraco.finsandstails.common.entities.OrnateBugfishEntity;

public class OrnateBugfishRenderer extends GeoEntityRenderer<OrnateBugfishEntity> {

    public OrnateBugfishRenderer(EntityRendererProvider.Context context) {
        super(context, new OrnateBugfishModel());
        this.shadowRadius = 0.3F;
    }
}