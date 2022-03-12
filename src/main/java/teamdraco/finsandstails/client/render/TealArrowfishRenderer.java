
package teamdraco.finsandstails.client.render;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import teamdraco.finsandstails.client.model.TealArrowfishModel;
import teamdraco.finsandstails.common.entities.TealArrowfishEntity;

public class TealArrowfishRenderer extends GeoEntityRenderer<TealArrowfishEntity> {

    public TealArrowfishRenderer(EntityRendererProvider.Context context) {
        super(context, new TealArrowfishModel());
        this.shadowRadius = 0.2F;
    }
}