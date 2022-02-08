
package teamdraco.finsandstails.client.render;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.finsandtails.geckolib3.renderers.geo.GeoEntityRenderer;
import teamdraco.finsandstails.client.model.NightLightSquidModel;
import teamdraco.finsandstails.common.entities.NightLightSquidEntity;

public class NightlightSquidRenderer extends GeoEntityRenderer<NightLightSquidEntity> {

    public NightlightSquidRenderer(EntityRendererProvider.Context context) {
        super(context, new NightLightSquidModel());
        this.shadowRadius = 0.3F;
    }
}