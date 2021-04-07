package teamdraco.fins.client.render;

import com.google.common.collect.Maps;
import teamdraco.fins.FinsAndTails;
import teamdraco.fins.client.model.PenglilModel;
import teamdraco.fins.common.entities.PenglilEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;

import java.util.Map;

public class PenglilRenderer extends MobRenderer<PenglilEntity, PenglilModel<PenglilEntity>> {
    public static final Map<Integer, ResourceLocation> TEXTURES = Util.make(Maps.newHashMap(), (hashMap) -> {
        hashMap.put(0, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/penglil/penglil_1.png"));
        hashMap.put(1, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/penglil/penglil_2.png"));
        hashMap.put(2, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/penglil/penglil_3.png"));
        hashMap.put(3, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/penglil/penglil_4.png"));
        hashMap.put(4, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/penglil/penglil_5.png"));
        hashMap.put(5, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/penglil/penglil_6.png"));
        hashMap.put(6, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/penglil/penglil_7.png"));
        hashMap.put(7, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/penglil/penglil_8.png"));
        hashMap.put(8, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/penglil/lord_penglil.png"));
    });

    public PenglilRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new PenglilModel<>(), 0.2F);
    }

    @Override
    public ResourceLocation getEntityTexture(PenglilEntity entity) {
        if (entity.getName().getString().equals("Lord")) {
            return TEXTURES.get(8);
        }
        else if (entity.getName().getString().equals("Lord Penglil")) {
            return TEXTURES.get(8);
        }
        else if (entity.getName().getString().equals("Lord_Penglil")) {
            return TEXTURES.get(8);
        }
        else {
            return TEXTURES.getOrDefault(entity.getVariant(), TEXTURES.get(0));
        }
    }
}