
package teamdraco.finsandstails.client.render;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import teamdraco.finsandstails.client.model.SwampMuckerModel;
import teamdraco.finsandstails.common.entities.SwampMuckerEntity;

public class SwampMuckerRenderer extends GeoEntityRenderer<SwampMuckerEntity> {

    public SwampMuckerRenderer(EntityRendererProvider.Context context) {
        super(context, new SwampMuckerModel());
        this.shadowRadius = 0.3F;
    }

    @Override
    public RenderType getRenderType(SwampMuckerEntity animatable, ResourceLocation texture, @Nullable MultiBufferSource bufferSource, float partialTick) {
        return RenderType.entityCutout(texture);
    }
}