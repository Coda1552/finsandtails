package teamdraco.finsandstails.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import teamdraco.finsandstails.client.model.CrownedHorateeModel;
import teamdraco.finsandstails.common.entities.CrownedHorateeEntity;

public class CrownedHorateeRenderer extends GeoEntityRenderer<CrownedHorateeEntity> {

	public CrownedHorateeRenderer(EntityRendererProvider.Context context) {
		super(context, new CrownedHorateeModel());
		this.shadowRadius = 0.5F;
	}

	@Override
	public RenderType getRenderType(CrownedHorateeEntity animatable, float partialTicks, PoseStack stack, @Nullable MultiBufferSource renderTypeBuffer, @Nullable VertexConsumer vertexBuilder, int packedLightIn, ResourceLocation textureLocation) {
		return RenderType.entityCutoutNoCull(textureLocation);
	}
}