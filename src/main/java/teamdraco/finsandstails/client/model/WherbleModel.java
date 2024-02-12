package teamdraco.finsandstails.client.model;

import com.google.common.collect.Maps;
import net.minecraft.Util;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.entities.WherbleEntity;

import java.util.Map;

public class WherbleModel extends GeoModel<WherbleEntity> {
    public static final Map<Integer, ResourceLocation> TEXTURES = Util.make(Maps.newHashMap(), (hashMap) -> {
        hashMap.put(0, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/wherble/wherble_1.png"));
        hashMap.put(1, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/wherble/wherble_2.png"));
        hashMap.put(2, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/wherble/wherble_3.png"));
        hashMap.put(3, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/wherble/wherble_4.png"));
        hashMap.put(4, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/wherble/child.png"));
    });

    @Override
    public ResourceLocation getModelResource(WherbleEntity wee) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "geo/entity/wherble.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(WherbleEntity wee) {
        return wee.isBaby() ? TEXTURES.get(4) : TEXTURES.getOrDefault(wee.getVariant(), TEXTURES.get(0));
    }

    @Override
    public ResourceLocation getAnimationResource(WherbleEntity wee) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "animations/entity/wherble.animations.json");
    }

    @Override
    public void setCustomAnimations(WherbleEntity entity, long uniqueID, AnimationState<WherbleEntity> customPredicate) {
        super.setCustomAnimations(entity, uniqueID, customPredicate);
        CoreGeoBone body = this.getAnimationProcessor().getBone("root");

        if (entity.isBaby()) {
            body.setScaleX(0.5F);
            body.setScaleY(0.5F);
            body.setScaleZ(0.5F);
            body.setPosY(-2.75F);
        }
    }
}
