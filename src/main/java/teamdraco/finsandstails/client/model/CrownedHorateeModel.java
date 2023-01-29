package teamdraco.finsandstails.client.model;

import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.entities.CrownedHorateeEntity;

public class CrownedHorateeModel extends AnimatedGeoModel<CrownedHorateeEntity> {
	public static final ResourceLocation TEXTURE = new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/crowned_horatee.png");

	@Override
	public ResourceLocation getModelLocation(CrownedHorateeEntity entity) {
		return new ResourceLocation(FinsAndTails.MOD_ID, "geo/entity/crowned_horatee.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(CrownedHorateeEntity entity) {
		return TEXTURE;
	}

	@Override
	public ResourceLocation getAnimationFileLocation(CrownedHorateeEntity entity) {
		return new ResourceLocation(FinsAndTails.MOD_ID, "animations/entity/crowned_horatee.animations.json");
	}

	@Override
	public void setLivingAnimations(CrownedHorateeEntity entity, Integer uniqueID, AnimationEvent customPredicate) {
		super.setLivingAnimations(entity, uniqueID, customPredicate);
		IBone root = this.getAnimationProcessor().getBone("root");
		IBone body = this.getAnimationProcessor().getBone("body");
		IBone tail = this.getAnimationProcessor().getBone("tail");
		IBone tailFin = this.getAnimationProcessor().getBone("tailFin");
		IBone head = this.getAnimationProcessor().getBone("head");
		float f7 = entity.tickCount + Minecraft.getInstance().getFrameTime();
		EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);

		if (entity.isBaby()) {
			root.setScaleX(0.5F);
			root.setScaleY(0.5F);
			root.setScaleZ(0.5F);
			root.setPositionY(-13F);
		}
		if (entity.getDeltaMovement().lengthSqr() > 1.0E-7D) {
			if (entity.isInWater()) {
				if (entity.isOnGround()) {
					head.setRotationX(extraData.headPitch * (float) Math.PI / 180F);
					head.setRotationY(extraData.netHeadYaw * (float) Math.PI / 180F);
				}
			} else {
				head.setRotationX(extraData.headPitch * (float) Math.PI / 180F);
				head.setRotationY(extraData.netHeadYaw * (float) Math.PI / 180F);
			}
		}
	}
}
