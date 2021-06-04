package teamdraco.fins.client.render;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;
import teamdraco.fins.FinsAndTails;
import teamdraco.fins.client.model.BluWeeModel;
import teamdraco.fins.client.model.WherbleModel;
import teamdraco.fins.common.entities.BluWeeEntity;
import teamdraco.fins.common.entities.WherbleEntity;

import java.util.Map;

public class WherbleRenderer extends MobRenderer<WherbleEntity, WherbleModel<WherbleEntity>> {
    public static final Map<Integer, ResourceLocation> TEXTURES = Util.make(Maps.newHashMap(), (hashMap) -> {
        hashMap.put(0, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/wherble/wherble_1.png"));
        hashMap.put(1, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/wherble/wherble_2.png"));
        hashMap.put(2, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/wherble/wherble_3.png"));
        hashMap.put(3, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/wherble/wherble_4.png"));
    });

    public WherbleRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new WherbleModel<>(), 0.35F);
    }

    @Override
    public ResourceLocation getTextureLocation(WherbleEntity entity) {
        return TEXTURES.getOrDefault(entity.getVariant(), TEXTURES.get(0));
    }
}