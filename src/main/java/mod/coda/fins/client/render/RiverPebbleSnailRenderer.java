package mod.coda.fins.client.render;

import com.google.common.collect.Maps;
import mod.coda.fins.FinsAndTails;
import mod.coda.fins.client.model.FlatbackLeafSnailModel;
import mod.coda.fins.client.model.RiverPebbleSnailModel;
import mod.coda.fins.entity.FlatbackLeafSnailEntity;
import mod.coda.fins.entity.RiverPebbleSnailEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;

import java.util.Map;

public class RiverPebbleSnailRenderer extends MobRenderer<RiverPebbleSnailEntity, RiverPebbleSnailModel<RiverPebbleSnailEntity>> {
    public static final Map<Integer, ResourceLocation> TEXTURES = Util.make(Maps.newHashMap(), (hashMap) -> {
        hashMap.put(0, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/river_pebble_snail/river_pebble_snail_1.png"));
        hashMap.put(1, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/river_pebble_snail/river_pebble_snail_2.png"));
        hashMap.put(2, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/river_pebble_snail/river_pebble_snail_3.png"));
        hashMap.put(3, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/river_pebble_snail/river_pebble_snail_4.png"));
        hashMap.put(4, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/river_pebble_snail/river_pebble_snail_5.png"));
    });
    public RiverPebbleSnailRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new RiverPebbleSnailModel<>(), 0.3F);
    }

    public ResourceLocation getEntityTexture(RiverPebbleSnailEntity entity) {
        return TEXTURES.getOrDefault(entity.getVariant(), TEXTURES.get(0));
    }
}