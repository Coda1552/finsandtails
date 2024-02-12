package teamdraco.finsandstails.client.model;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.entities.CrownedHorateeEntity;

public class CrownedHorateeModel extends GeoModel<CrownedHorateeEntity> {
	public static final ResourceLocation TEXTURE = new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/crowned_horatee.png");
	public static final ResourceLocation ANIMATION = new ResourceLocation(FinsAndTails.MOD_ID, "animations/entity/crowned_horatee.animations.json");
	public static final ResourceLocation MODEL = new ResourceLocation(FinsAndTails.MOD_ID, "geo/entity/crowned_horatee.geo.json");

	@Override
	public ResourceLocation getModelResource(CrownedHorateeEntity entity) {
		return MODEL;
	}

	@Override
	public ResourceLocation getTextureResource(CrownedHorateeEntity entity) {
		return TEXTURE;
	}

	@Override
	public ResourceLocation getAnimationResource(CrownedHorateeEntity entity) {
		return ANIMATION;
	}

	@Override
	public void setCustomAnimations(CrownedHorateeEntity entity, long uniqueID, AnimationState<CrownedHorateeEntity> customPredicate) {
		super.setCustomAnimations(entity, uniqueID, customPredicate);
		CoreGeoBone root = this.getAnimationProcessor().getBone("root");
		CoreGeoBone body = this.getAnimationProcessor().getBone("body");
		CoreGeoBone tail = this.getAnimationProcessor().getBone("tail");
		CoreGeoBone tailFin = this.getAnimationProcessor().getBone("tailFin");
		CoreGeoBone head = this.getAnimationProcessor().getBone("head");
		float f7 = entity.tickCount + customPredicate.getPartialTick();
		EntityModelData extraData = customPredicate.getData(DataTickets.ENTITY_MODEL_DATA);

		if (entity.isBaby()) {
			root.setScaleX(0.5F);
			root.setScaleY(0.5F);
			root.setScaleZ(0.5F);
			root.setPosY(-13F);
		}
		head.setRotX(extraData.headPitch() * (float) Math.PI / 180F);
		head.setRotY(extraData.netHeadYaw() * (float) Math.PI / 180F);
	}
}
