package teamdraco.fins.client.render;

import com.google.common.collect.Maps;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import teamdraco.fins.FinsAndTails;
import teamdraco.fins.client.model.GlassSkipperModel;
import teamdraco.fins.common.entities.GlassSkipperEntity;

import java.util.Map;

public class GlassSkipperRenderer extends MobRenderer<GlassSkipperEntity, GlassSkipperModel<GlassSkipperEntity>> {
    public static final Map<Integer, ResourceLocation> TEXTURES = Util.make(Maps.newHashMap(), (hashMap) -> {
        hashMap.put(0, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/glass_skipper/glass_skipper_1.png"));
        hashMap.put(1, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/glass_skipper/glass_skipper_2.png"));
        hashMap.put(2, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/glass_skipper/glass_skipper_3.png"));
        hashMap.put(3, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/glass_skipper/glass_skipper_4.png"));
        hashMap.put(4, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/glass_skipper/glass_skipper_5.png"));
    });

    public GlassSkipperRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new GlassSkipperModel<>(), 0.6F);
    }

    public ResourceLocation getTextureLocation(GlassSkipperEntity entity) {
         return TEXTURES.getOrDefault(entity.getVariant(), TEXTURES.get(0));
    }
}