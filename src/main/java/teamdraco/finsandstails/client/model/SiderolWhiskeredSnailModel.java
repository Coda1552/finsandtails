package teamdraco.finsandstails.client.model;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.model.GeoModel;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.entities.SiderolWhiskeredSnailEntity;

public class SiderolWhiskeredSnailModel extends DefaultedEntityGeoModel<SiderolWhiskeredSnailEntity> {

    public SiderolWhiskeredSnailModel() {
        super(new ResourceLocation(FinsAndTails.MOD_ID, "siderol_whiskered_snail"));
    }
}
