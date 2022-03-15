
package teamdraco.finsandstails.client.render;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import teamdraco.finsandstails.client.model.SpindlyGemCrabModel;
import teamdraco.finsandstails.common.entities.SpindlyGemCrabEntity;

public class SpindlyGemCrabRenderer extends GeoEntityRenderer<SpindlyGemCrabEntity> {

    public SpindlyGemCrabRenderer(EntityRendererProvider.Context context) {
        super(context, new SpindlyGemCrabModel());
        this.shadowRadius = 0.3F;
    }
}