
package teamdraco.finsandstails.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import teamdraco.finsandstails.client.model.SpindlyGemCrabModel;
import teamdraco.finsandstails.common.entities.OrnateBugfishEntity;
import teamdraco.finsandstails.common.entities.SpindlyGemCrabEntity;

public class SpindlyGemCrabRenderer extends GeoEntityRenderer<SpindlyGemCrabEntity> {

    public SpindlyGemCrabRenderer(EntityRendererProvider.Context context) {
        super(context, new SpindlyGemCrabModel());
        this.shadowRadius = 0.3F;
    }

    @Override
    public RenderType getRenderType(SpindlyGemCrabEntity animatable, float partialTicks, PoseStack stack, @Nullable MultiBufferSource renderTypeBuffer, @Nullable VertexConsumer vertexBuilder, int packedLightIn, ResourceLocation textureLocation) {
        return RenderType.entityTranslucent(textureLocation);
    }
}