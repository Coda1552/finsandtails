
package teamdraco.finsandstails.client.render;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.finsandtails.geckolib3.renderers.geo.GeoEntityRenderer;
import teamdraco.finsandstails.client.model.WanderingSailorModel;
import teamdraco.finsandstails.common.entities.WanderingSailorEntity;

public class WanderingSailorRenderer extends GeoEntityRenderer<WanderingSailorEntity> {

    public WanderingSailorRenderer(EntityRendererProvider.Context context) {
        super(context, new WanderingSailorModel());
        this.shadowRadius = 0.45F;
    }
}