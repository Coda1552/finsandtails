package teamdraco.finsandstails.client.model;

import com.google.common.collect.Maps;
import net.minecraft.Util;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import software.bernie.geckolib.model.data.EntityModelData;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.entities.GoldenRiverRayEntity;

import java.util.Map;

public class GoldenRiverRayModel extends DefaultedEntityGeoModel<GoldenRiverRayEntity> {
    public static final Map<Integer, ResourceLocation> TEXTURES = Util.make(Maps.newHashMap(), (hashMap) -> {
        hashMap.put(0, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/golden_river_ray/golden_river_ray_1.png"));
        hashMap.put(1, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/golden_river_ray/golden_river_ray_2.png"));
        hashMap.put(2, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/golden_river_ray/golden_river_ray_3.png"));
    });

    public GoldenRiverRayModel() {
        super(new ResourceLocation(FinsAndTails.MOD_ID, "golden_river_ray"));
    }

    @Override
    public ResourceLocation getTextureResource(GoldenRiverRayEntity entity) {
        return TEXTURES.getOrDefault(entity.getVariant(), TEXTURES.get(0));
    }

    @Override
    public void setCustomAnimations(GoldenRiverRayEntity entity, long uniqueID, @Nullable AnimationState<GoldenRiverRayEntity> customPredicate) {
        super.setCustomAnimations(entity, uniqueID, customPredicate);
        CoreGeoBone body = this.getAnimationProcessor().getBone("body");
        EntityModelData extraData = customPredicate.getData(DataTickets.ENTITY_MODEL_DATA);

        if (entity.isInWater()) {
            body.setRotX(extraData.headPitch() * ((float) Math.PI / 180F));
            body.setRotY(extraData.netHeadYaw() * ((float) Math.PI / 180F));
        }
    }
}
