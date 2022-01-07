package teamdraco.finsandstails.client.render;

import com.google.common.collect.Maps;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.client.model.WherbleModel;
import teamdraco.finsandstails.common.entities.WherbleEntity;

import java.util.Map;

public class WherbleRenderer extends MobRenderer<WherbleEntity, WherbleModel<WherbleEntity>> {
    public static final Map<Integer, ResourceLocation> TEXTURES = Util.make(Maps.newHashMap(), (hashMap) -> {
        hashMap.put(0, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/wherble/wherble_1.png"));
        hashMap.put(1, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/wherble/wherble_2.png"));
        hashMap.put(2, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/wherble/wherble_3.png"));
        hashMap.put(3, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/wherble/wherble_4.png"));
        hashMap.put(4, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/wherble/child.png"));
    });

    public WherbleRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new WherbleModel<>(), 0.35F);
    }

    @Override
    public ResourceLocation getTextureLocation(WherbleEntity entity) {
        return entity.isBaby() ? TEXTURES.get(4) : TEXTURES.getOrDefault(entity.getVariant(), TEXTURES.get(0));
    }
}