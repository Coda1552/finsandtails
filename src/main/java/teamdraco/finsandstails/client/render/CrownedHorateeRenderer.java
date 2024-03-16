package teamdraco.finsandstails.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import teamdraco.finsandstails.client.model.CrownedHorateeModel;
import teamdraco.finsandstails.common.entities.CrownedHorateeEntity;

public class CrownedHorateeRenderer extends GeoEntityRenderer<CrownedHorateeEntity> {

	public CrownedHorateeRenderer(EntityRendererProvider.Context context) {
		super(context, new CrownedHorateeModel());
		this.shadowRadius = 0.5F;
	}

	@Override
	protected void applyRotations(CrownedHorateeEntity entityLiving, PoseStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
		super.applyRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
		if (entityLiving.isInWater() && !entityLiving.onGround()) {
			matrixStackIn.mulPose(Axis.XP.rotationDegrees(-entityLiving.getXRot()));
		}
	}

	@Override
	public RenderType getRenderType(CrownedHorateeEntity animatable, ResourceLocation texture, @Nullable MultiBufferSource bufferSource, float partialTick) {
		return RenderType.entityCutoutNoCull(texture);
	}
}