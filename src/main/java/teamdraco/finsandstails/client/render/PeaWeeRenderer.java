
package teamdraco.finsandstails.client.render;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import teamdraco.finsandstails.client.model.PeaWeeModel;
import teamdraco.finsandstails.common.entities.PeaWeeEntity;

public class PeaWeeRenderer extends GeoEntityRenderer<PeaWeeEntity> {

    public PeaWeeRenderer(EntityRendererProvider.Context context) {
        super(context, new PeaWeeModel());
        this.shadowRadius = 0.2F;
    }

    @Override
    public RenderType getRenderType(PeaWeeEntity animatable, ResourceLocation texture, @Nullable MultiBufferSource bufferSource, float partialTick) {
        return RenderType.entityCutout(texture);
    }
}