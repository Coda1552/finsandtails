
package teamdraco.finsandstails.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import teamdraco.finsandstails.client.model.RubberBellyGliderModel;
import teamdraco.finsandstails.common.entities.RubberBellyGliderEntity;

public class RubberBellyGliderRenderer extends GeoEntityRenderer<RubberBellyGliderEntity> {

    public RubberBellyGliderRenderer(EntityRendererProvider.Context context) {
        super(context, new RubberBellyGliderModel());
        this.shadowRadius = 0.4F;
    }

    @Override
    public void render(RubberBellyGliderEntity entity, float entityYaw, float partialTicks, PoseStack stack, MultiBufferSource bufferIn, int packedLightIn) {
        super.render(entity, entityYaw, partialTicks, stack, bufferIn, packedLightIn);
        if (entity.isPuffed()) {
            stack.translate(0, -0.25, 0);
        }
    }
}