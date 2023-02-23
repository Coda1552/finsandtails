package teamdraco.finsandstails.client.model.armor;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.items.ArmoredGopjetJetpackItem;

public class HorateeJetpackModel extends AnimatedGeoModel<ArmoredGopjetJetpackItem> {
	public static final ResourceLocation TEXTURE = new ResourceLocation(FinsAndTails.MOD_ID, "textures/armor/horatee_jetpack.png");
	public static final ResourceLocation MODEL = new ResourceLocation(FinsAndTails.MOD_ID, "geo/armor/horatee_jetpack.geo.json");

	public static final ResourceLocation ANIMATION = new ResourceLocation(FinsAndTails.MOD_ID, "animation/armor/gopjet_jetpack.animation.json");

	@Override
	public ResourceLocation getModelLocation(ArmoredGopjetJetpackItem object) {
		return MODEL;
	}

	@Override
	public ResourceLocation getTextureLocation(ArmoredGopjetJetpackItem object) {
		return TEXTURE;
	}

	@Override
	public ResourceLocation getAnimationFileLocation(ArmoredGopjetJetpackItem animatable) {
		return ANIMATION;
	}
}
