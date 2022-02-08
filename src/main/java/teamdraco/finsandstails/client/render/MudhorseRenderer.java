
package teamdraco.finsandstails.client.render;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.finsandtails.geckolib3.renderers.geo.GeoEntityRenderer;
import teamdraco.finsandstails.client.model.MudhorseModel;
import teamdraco.finsandstails.common.entities.MudhorseEntity;

public class MudhorseRenderer extends GeoEntityRenderer<MudhorseEntity> {

    public MudhorseRenderer(EntityRendererProvider.Context context) {
        super(context, new MudhorseModel());
        this.shadowRadius = 0.8F;
    }
}