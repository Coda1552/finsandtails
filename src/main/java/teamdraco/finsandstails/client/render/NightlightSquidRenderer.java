
package teamdraco.finsandstails.client.render;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import teamdraco.finsandstails.client.model.NightLightSquidModel;
import teamdraco.finsandstails.client.render.layer.NightLightSquidGlowLayer;
import teamdraco.finsandstails.common.entities.NightLightSquidEntity;

public class NightlightSquidRenderer extends GeoEntityRenderer<NightLightSquidEntity> {

    public NightlightSquidRenderer(EntityRendererProvider.Context context) {
        super(context, new NightLightSquidModel());
        this.addLayer(new NightLightSquidGlowLayer(this));
        this.shadowRadius = 0.3F;
    }
}