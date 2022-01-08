package teamdraco.finsandstails.client.old.render;

import com.google.common.collect.Maps;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.client.old.model.SpindlyGemCrabModel;
import teamdraco.finsandstails.common.entities.SpindlyGemCrabEntity;

import java.util.Map;

public class SpindlyGemCrabRenderer extends MobRenderer<SpindlyGemCrabEntity, SpindlyGemCrabModel<SpindlyGemCrabEntity>> {
    public static final Map<Integer, ResourceLocation> TEXTURES = Util.make(Maps.newHashMap(), (hashMap) -> {
        hashMap.put(0, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/spindly_gem_crab/emerald.png"));
        hashMap.put(1, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/spindly_gem_crab/pearl.png"));
        hashMap.put(2, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/spindly_gem_crab/sapphire.png"));
        hashMap.put(3, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/spindly_gem_crab/ruby.png"));
        hashMap.put(4, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/spindly_gem_crab/amber.png"));
    });

    public SpindlyGemCrabRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new SpindlyGemCrabModel<>(), 0.2F);
    }

    @Override
    public ResourceLocation getTextureLocation(SpindlyGemCrabEntity entity) {
        return TEXTURES.getOrDefault(entity.getVariant(), TEXTURES.get(0));
    }
}