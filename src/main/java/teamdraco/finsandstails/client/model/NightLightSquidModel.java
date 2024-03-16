package teamdraco.finsandstails.client.model;

import com.google.common.collect.Maps;
import net.minecraft.Util;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.entities.NightLightSquidEntity;

import java.util.Map;

public class NightLightSquidModel extends GeoModel<NightLightSquidEntity> {
    public static final Map<Integer, ResourceLocation> TEXTURES = Util.make(Maps.newHashMap(), (hashMap) -> {
        hashMap.put(0, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/night_light_squid/night_light_squid_1.png"));
        hashMap.put(1, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/night_light_squid/night_light_squid_2.png"));
        hashMap.put(2, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/night_light_squid/night_light_squid_3.png"));
        hashMap.put(3, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/night_light_squid/night_light_squid_4.png"));
        hashMap.put(4, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/night_light_squid/night_light_squid_e.png"));
    });

    @Override
    public ResourceLocation getModelResource(NightLightSquidEntity squid) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "geo/entity/night_light_squid.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(NightLightSquidEntity squid) {
        return TEXTURES.getOrDefault(squid.getVariant(), TEXTURES.get(0));
    }

    @Override
    public ResourceLocation getAnimationResource(NightLightSquidEntity squid) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "animations/entity/night_light_squid.animations.json");
    }
}
