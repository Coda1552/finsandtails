package teamdraco.finsandstails.client.model;

import com.google.common.collect.Maps;
import net.minecraft.Util;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.model.GeoModel;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.entities.RiverPebbleSnailEntity;

import java.util.Map;

public class RiverPebbleSnailModel extends DefaultedEntityGeoModel<RiverPebbleSnailEntity> {
    public static final Map<Integer, ResourceLocation> TEXTURES = Util.make(Maps.newHashMap(), (hashMap) -> {
        hashMap.put(0, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/river_pebble_snail/river_pebble_snail_1.png"));
        hashMap.put(1, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/river_pebble_snail/river_pebble_snail_2.png"));
        hashMap.put(2, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/river_pebble_snail/river_pebble_snail_3.png"));
        hashMap.put(3, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/river_pebble_snail/river_pebble_snail_4.png"));
        hashMap.put(4, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/river_pebble_snail/river_pebble_snail_5.png"));
    });

    public RiverPebbleSnailModel() {
        super(new ResourceLocation(FinsAndTails.MOD_ID, "river_pebble_snail"));
    }

    @Override
    public ResourceLocation getTextureResource(RiverPebbleSnailEntity wee) {
        return TEXTURES.getOrDefault(wee.getVariant(), TEXTURES.get(0));
    }

    @Override
    public void setCustomAnimations(RiverPebbleSnailEntity entity, long uniqueID, AnimationState<RiverPebbleSnailEntity> customPredicate) {
        super.setCustomAnimations(entity, uniqueID, customPredicate);
        CoreGeoBone body = this.getAnimationProcessor().getBone("body");
        CoreGeoBone shell = this.getAnimationProcessor().getBone("shell");

        if (entity.isBaby()) {
            body.setScaleX(0.5F);
            body.setScaleY(0.5F);
            body.setScaleZ(0.5F);
            body.setPosY(-1F);

            shell.setScaleX(0.65F);
            shell.setScaleY(0.65F);
            shell.setScaleZ(0.65F);
            shell.setPosY(-0.25F);
        }
    }
}
