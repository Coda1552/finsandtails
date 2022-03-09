
package teamdraco.finsandstails.client.render;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoProjectilesRenderer;
import teamdraco.finsandstails.client.model.TealArrowfishModel;
import teamdraco.finsandstails.common.entities.TealArrowfishEntity;

public class TealArrowfishRenderer extends GeoProjectilesRenderer<TealArrowfishEntity> {

    public TealArrowfishRenderer(EntityRendererProvider.Context context) {
        super(context, new TealArrowfishModel());
        this.shadowRadius = 0.2F;
    }
}