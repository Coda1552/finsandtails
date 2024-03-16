
package teamdraco.finsandstails.client.render;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import teamdraco.finsandstails.client.model.GopjetModel;
import teamdraco.finsandstails.common.entities.GopjetEntity;

public class GopjetRenderer extends GeoEntityRenderer<GopjetEntity> {

    public GopjetRenderer(EntityRendererProvider.Context context) {
        super(context, new GopjetModel());
        this.shadowRadius = 0.4F;
    }

    @Override
    public RenderType getRenderType(GopjetEntity animatable, ResourceLocation texture, @Nullable MultiBufferSource bufferSource, float partialTick) {
        return RenderType.entityCutout(texture);
    }
}