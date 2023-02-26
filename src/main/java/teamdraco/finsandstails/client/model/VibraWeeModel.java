package teamdraco.finsandstails.client.model;

import com.google.common.collect.Maps;
import net.minecraft.Util;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.entities.VibraWeeEntity;

import java.util.Map;

public class VibraWeeModel extends AnimatedGeoModel<VibraWeeEntity> {
    public static final Map<Integer, ResourceLocation> TEXTURES = Util.make(Maps.newHashMap(), (hashMap) -> {
        hashMap.put(0, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/vibra_wee/vibra_wee_1.png"));
        hashMap.put(1, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/vibra_wee/vibra_wee_2.png"));
        hashMap.put(2, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/vibra_wee/vibra_wee_3.png"));
        hashMap.put(3, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/vibra_wee/vibra_wee_4.png"));
        hashMap.put(4, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/vibra_wee/vibra_wee_5.png"));
        hashMap.put(5, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/vibra_wee/vibra_wee_6.png"));
        hashMap.put(6, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/vibra_wee/vibra_wee_7.png"));
        hashMap.put(7, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/vibra_wee/vibra_wee_8.png"));
        hashMap.put(8, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/vibra_wee/vibra_wee_9.png"));
        hashMap.put(9, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/vibra_wee/vibra_wee_10.png"));
        hashMap.put(10, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/vibra_wee/vibra_wee_11.png"));
        hashMap.put(11, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/vibra_wee/vibra_wee_12.png"));
        hashMap.put(12, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/vibra_wee/vibra_wee_13.png"));
        hashMap.put(13, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/vibra_wee/vibra_wee_14.png"));
        hashMap.put(14, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/vibra_wee/vibra_wee_15.png"));
    });

    @Override
    public ResourceLocation getModelResource(VibraWeeEntity wee) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "geo/entity/vibra_wee.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(VibraWeeEntity wee) {
        return TEXTURES.getOrDefault(wee.getVariant(), TEXTURES.get(0));
    }

    @Override
    public ResourceLocation getAnimationResource(VibraWeeEntity wee) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "animations/entity/vibra_wee.animations.json");
    }

    @Override
    public void setCustomAnimations(VibraWeeEntity entity, int uniqueID, AnimationEvent customPredicate) {
        super.setCustomAnimations(entity, uniqueID, customPredicate);
        IBone body = this.getAnimationProcessor().getBone("body");

        if (!entity.isInWater()) {
            body.setRotationZ(1.5708f);
        }
    }
}
