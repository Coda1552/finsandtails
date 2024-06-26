package teamdraco.finsandstails.client.model;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.model.data.EntityModelData;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.entities.TealArrowfishEntity;

public class TealArrowfishModel extends DefaultedEntityGeoModel<TealArrowfishEntity> {

    public TealArrowfishModel() {
        super(new ResourceLocation(FinsAndTails.MOD_ID, "teal_arrowfish"));
    }

    @Override
    public void setCustomAnimations(TealArrowfishEntity entity, long uniqueID, AnimationState<TealArrowfishEntity> customPredicate) {
        super.setCustomAnimations(entity, uniqueID, customPredicate);
        CoreGeoBone body = this.getAnimationProcessor().getBone("body");
        EntityModelData extraData = customPredicate.getData(DataTickets.ENTITY_MODEL_DATA);

        if (!entity.isInWater()) {
            body.setRotZ(1.5708f);
        }
        else {
            body.setRotX(extraData.headPitch() * ((float) Math.PI / 180F));
            body.setRotY(extraData.netHeadYaw() * ((float) Math.PI / 180F));
        }
    }
}
