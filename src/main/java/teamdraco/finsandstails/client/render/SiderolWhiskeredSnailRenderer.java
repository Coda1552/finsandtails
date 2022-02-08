
package teamdraco.finsandstails.client.render;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.finsandtails.geckolib3.renderers.geo.GeoEntityRenderer;
import teamdraco.finsandstails.client.model.SiderolWhiskeredSnailModel;
import teamdraco.finsandstails.common.entities.SiderolWhiskeredSnailEntity;

public class SiderolWhiskeredSnailRenderer extends GeoEntityRenderer<SiderolWhiskeredSnailEntity> {

    public SiderolWhiskeredSnailRenderer(EntityRendererProvider.Context context) {
        super(context, new SiderolWhiskeredSnailModel());
        this.shadowRadius = 0.2F;
    }
}