package teamdraco.finsandstails.client.model;

import net.minecraft.resources.ResourceLocation;
import software.bernie.finsandtails.geckolib3.model.AnimatedGeoModel;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.items.GopjetJetpackItem;

public class GopjetJetpackModel extends AnimatedGeoModel<GopjetJetpackItem> {

	@Override
	public ResourceLocation getModelLocation(GopjetJetpackItem object) {
		return new ResourceLocation(FinsAndTails.MOD_ID, "geo/armor/gopjet_jetpack.geo.json");
	}

	@Override
	public ResourceLocation getTextureLocation(GopjetJetpackItem object) {
		return new ResourceLocation(FinsAndTails.MOD_ID, "textures/armor/gopjet_jetpack.png");
	}

	@Override
	public ResourceLocation getAnimationFileLocation(GopjetJetpackItem object) {
		return new ResourceLocation(FinsAndTails.MOD_ID, "animations/armor/gopjet_jetpack.animation.json");
	}
}
