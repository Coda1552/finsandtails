package mod.coda.fins.client.render;

import com.google.common.collect.Maps;
import mod.coda.fins.FinsAndTails;
import mod.coda.fins.client.model.PenglilModel;
import mod.coda.fins.client.model.SpindlyGemCrabModel;
import mod.coda.fins.entity.PenglilEntity;
import mod.coda.fins.entity.SpindlyGemCrabEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;

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
    public ResourceLocation getEntityTexture(SpindlyGemCrabEntity entity) {
        return TEXTURES.getOrDefault(entity.getVariant(), TEXTURES.get(0));
    }
}