package teamdraco.finsandstails.client.model;

import com.google.common.collect.Maps;
import net.minecraft.Util;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.entities.SpindlyGemCrabEntity;

import java.util.Map;

public class SpindlyGemCrabModel extends GeoModel<SpindlyGemCrabEntity> {
    public static final Map<Integer, ResourceLocation> TEXTURES = Util.make(Maps.newHashMap(), (hashMap) -> {
        hashMap.put(0, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/spindly_gem_crab/emerald.png"));
        hashMap.put(1, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/spindly_gem_crab/pearl.png"));
        hashMap.put(2, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/spindly_gem_crab/sapphire.png"));
        hashMap.put(3, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/spindly_gem_crab/ruby.png"));
        hashMap.put(4, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/spindly_gem_crab/amber.png"));
    });

    @Override
    public ResourceLocation getModelResource(SpindlyGemCrabEntity wee) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "geo/entity/spindly_gem_crab.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SpindlyGemCrabEntity wee) {
        return TEXTURES.getOrDefault(wee.getVariant(), TEXTURES.get(0));
    }

    @Override
    public ResourceLocation getAnimationResource(SpindlyGemCrabEntity wee) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "animations/entity/spindly_gem_crab.animations.json");
    }
}
