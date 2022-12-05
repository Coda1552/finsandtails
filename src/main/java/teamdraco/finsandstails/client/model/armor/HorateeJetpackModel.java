package teamdraco.finsandstails.client.model.armor;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.items.HorateeJetpackItem;

public class HorateeJetpackModel extends AnimatedGeoModel<HorateeJetpackItem> {

	@Override
	public ResourceLocation getModelLocation(HorateeJetpackItem object) {
		return new ResourceLocation(FinsAndTails.MOD_ID, "geo/armor/horatee_jetpack.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(HorateeJetpackItem object) {
		return new ResourceLocation(FinsAndTails.MOD_ID, "textures/armor/horatee_jetpack.png");
	}

	@Override
	public ResourceLocation getAnimationFileLocation(HorateeJetpackItem object) {
		return null;
	}
}
