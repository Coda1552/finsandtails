package teamdraco.finsandstails.client.model;

import net.minecraft.resources.ResourceLocation;
import software.bernie.finsandtails.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.finsandtails.geckolib3.core.processor.IBone;
import software.bernie.finsandtails.geckolib3.model.AnimatedGeoModel;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.entities.BluWeeEntity;
import teamdraco.finsandstails.common.entities.TealArrowfishEntity;
import teamdraco.finsandstails.common.entities.item.TealArrowfishArrowEntity;

public class TealArrowfishArrowModel extends AnimatedGeoModel<TealArrowfishArrowEntity> {

    @Override
    public ResourceLocation getModelLocation(TealArrowfishArrowEntity wee) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "geo/projectile/teal_arrowfish_arrow.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(TealArrowfishArrowEntity wee) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/teal_arrowfish.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(TealArrowfishArrowEntity wee) {
        return null;
        //return new ResourceLocation(FinsAndTails.MOD_ID, "animations/entity/wee.animation.json");
    }
}
