package teamdraco.finsandstails.client.model.armor;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.items.ArmoredGopjetJetpackItem;

public class HorateeJetpackModel extends AnimatedGeoModel<ArmoredGopjetJetpackItem> {

	@Override
	public ResourceLocation getModelLocation(ArmoredGopjetJetpackItem object) {
		return new ResourceLocation(FinsAndTails.MOD_ID, "geo/armor/horatee_jetpack.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(ArmoredGopjetJetpackItem object) {
		return new ResourceLocation(FinsAndTails.MOD_ID, "textures/armor/horatee_jetpack.png");
	}

	@Override
	public ResourceLocation getAnimationFileLocation(ArmoredGopjetJetpackItem object) {
		return null;
	}
}
