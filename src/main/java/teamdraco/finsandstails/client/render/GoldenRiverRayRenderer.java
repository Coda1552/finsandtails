
package teamdraco.finsandstails.client.render;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import teamdraco.finsandstails.client.model.GoldenRiverRayModel;
import teamdraco.finsandstails.common.entities.GoldenRiverRayEntity;

public class GoldenRiverRayRenderer extends GeoEntityRenderer<GoldenRiverRayEntity> {

    public GoldenRiverRayRenderer(EntityRendererProvider.Context context) {
        super(context, new GoldenRiverRayModel());
        this.shadowRadius = 0.4F;
    }

    @Override
    public RenderType getRenderType(GoldenRiverRayEntity animatable, ResourceLocation texture, @Nullable MultiBufferSource bufferSource, float partialTick) {
        return RenderType.entityCutout(texture);
    }
}