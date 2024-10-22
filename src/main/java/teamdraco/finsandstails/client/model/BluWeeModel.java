package teamdraco.finsandstails.client.model;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.entities.WeeEntity;

public class BluWeeModel extends DefaultedEntityGeoModel<WeeEntity> {

    public BluWeeModel() {
        super(new ResourceLocation(FinsAndTails.MOD_ID, "wee"));
    }

    @Override
    public ResourceLocation getTextureResource(WeeEntity wee) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/blu_wee.png");
    }

    @Override
    public void setCustomAnimations(WeeEntity entity, long uniqueID, AnimationState<WeeEntity> customPredicate) {
        super.setCustomAnimations(entity, uniqueID, customPredicate);
        CoreGeoBone body = this.getAnimationProcessor().getBone("body");

        if (!entity.isInWater()) {
            body.setRotZ(1.5708f);
        }
    }
}
