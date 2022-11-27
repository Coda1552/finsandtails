package teamdraco.finsandstails.client.model;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
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
		IBone body = this.getAnimationProcessor().getBone("root");

		if (entity.isBaby()) {
			body.setScaleX(0.5F);
			body.setScaleY(0.5F);
			body.setScaleZ(0.5F);
			body.setPositionY(-13F);
		}
	}
}
