package teamdraco.finsandstails.client.model;

import com.google.common.collect.Maps;
import net.minecraft.Util;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.processor.IBone;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.model.provider.data.EntityModelData;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.entities.GoldenRiverRayEntity;

import java.util.Map;

public class GoldenRiverRayModel extends AnimatedGeoModel<GoldenRiverRayEntity> {
    public static final Map<Integer, ResourceLocation> TEXTURES = Util.make(Maps.newHashMap(), (hashMap) -> {
        hashMap.put(0, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/golden_river_ray/golden_river_ray_1.png"));
        hashMap.put(1, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/golden_river_ray/golden_river_ray_2.png"));
        hashMap.put(2, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/golden_river_ray/golden_river_ray_3.png"));
    });

    @Override
    public ResourceLocation getModelLocation(GoldenRiverRayEntity entity) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "geo/entity/golden_river_ray.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(GoldenRiverRayEntity entity) {
        return TEXTURES.getOrDefault(entity.getVariant(), TEXTURES.get(0));
    }

    @Override
    public ResourceLocation getAnimationFileLocation(GoldenRiverRayEntity entity) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "animations/entity/golden_river_ray.animations.json");
    }

    @Override
    public void setLivingAnimations(GoldenRiverRayEntity entity, Integer uniqueID, @Nullable AnimationEvent customPredicate) {
        super.setLivingAnimations(entity, uniqueID, customPredicate);
        IBone body = this.getAnimationProcessor().getBone("body");
        EntityModelData extraData = (EntityModelData) customPredicate.getExtraDataOfType(EntityModelData.class).get(0);

        if (entity.isInWater()) {
            body.setRotationX(extraData.headPitch * ((float)Math.PI / 180F));
            body.setRotationY(extraData.netHeadYaw * ((float)Math.PI / 180F));
        }
    }
}
