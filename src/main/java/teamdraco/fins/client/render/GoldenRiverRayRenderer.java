package teamdraco.fins.client.render;

import com.google.common.collect.Maps;
import teamdraco.fins.FinsAndTails;
import teamdraco.fins.client.model.GoldenRiverRayModel;
import teamdraco.fins.common.entities.GoldenRiverRayEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;

import java.util.Map;

public class GoldenRiverRayRenderer extends MobRenderer<GoldenRiverRayEntity, GoldenRiverRayModel<GoldenRiverRayEntity>> {
    public static final Map<Integer, ResourceLocation> TEXTURES = Util.make(Maps.newHashMap(), (hashMap) -> {
        hashMap.put(0, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/golden_river_ray/golden_river_ray_1.png"));
        hashMap.put(1, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/golden_river_ray/golden_river_ray_2.png"));
        hashMap.put(2, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/golden_river_ray/golden_river_ray_3.png"));
    });

    public GoldenRiverRayRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new GoldenRiverRayModel<>(), 0.5F);
    }

    @Override
    public ResourceLocation getEntityTexture(GoldenRiverRayEntity entity) {
        return TEXTURES.getOrDefault(entity.getVariant(), TEXTURES.get(0));
    }
}