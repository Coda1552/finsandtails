
package teamdraco.finsandstails.client.render;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.finsandtails.geckolib3.renderers.geo.GeoEntityRenderer;
import teamdraco.finsandstails.client.model.PhantomNudibranchModel;
import teamdraco.finsandstails.client.render.layer.PhantomNudibranchGlowLayer;
import teamdraco.finsandstails.common.entities.PapaWeeEntity;
import teamdraco.finsandstails.common.entities.PhantomNudibranchEntity;

public class PhantomNudibranchRenderer extends GeoEntityRenderer<PhantomNudibranchEntity> {

    public PhantomNudibranchRenderer(EntityRendererProvider.Context context) {
        super(context, new PhantomNudibranchModel());
        this.addLayer(new PhantomNudibranchGlowLayer(this));
        this.shadowRadius = 0.2F;
    }
}