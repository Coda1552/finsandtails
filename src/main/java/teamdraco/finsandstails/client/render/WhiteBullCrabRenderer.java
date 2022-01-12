
package teamdraco.finsandstails.client.render;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.finsandtails.geckolib3.renderers.geo.GeoEntityRenderer;
import teamdraco.finsandstails.client.model.WhiteBullCrabModel;
import teamdraco.finsandstails.common.entities.WhiteBullCrabEntity;

public class WhiteBullCrabRenderer extends GeoEntityRenderer<WhiteBullCrabEntity> {

    public WhiteBullCrabRenderer(EntityRendererProvider.Context context) {
        super(context, new WhiteBullCrabModel());
        this.shadowRadius = 0.2F;
    }
}