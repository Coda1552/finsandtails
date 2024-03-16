
package teamdraco.finsandstails.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
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

    @Override
    public RenderType getRenderType(RubberBellyGliderEntity animatable, ResourceLocation texture, @Nullable MultiBufferSource bufferSource, float partialTick) {
        return RenderType.entityCutout(texture);
    }
}