
package teamdraco.finsandstails.client.render;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import teamdraco.finsandstails.client.model.PenglilModel;
import teamdraco.finsandstails.common.entities.PenglilEntity;

public class PenglilRenderer extends GeoEntityRenderer<PenglilEntity> {

    public PenglilRenderer(EntityRendererProvider.Context context) {
        super(context, new PenglilModel());
        this.shadowRadius = 0.35F;
    }
}