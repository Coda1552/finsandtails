package teamdraco.fins.client.render;

import com.google.common.collect.Maps;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import teamdraco.fins.FinsAndTails;
import teamdraco.fins.client.model.SchnauzModel;
import teamdraco.fins.common.entities.SchnauzEntity;

import java.util.Map;

public class SchnauzRenderer extends MobRenderer<SchnauzEntity, SchnauzModel<SchnauzEntity>> {
    public static final Map<Integer, ResourceLocation> TEXTURES = Util.make(Maps.newHashMap(), (hashMap) -> {
        hashMap.put(0, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/schnauz/bland.png"));
        hashMap.put(1, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/schnauz/countershaded.png"));
        hashMap.put(2, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/schnauz/patterned.png"));
        hashMap.put(3, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/schnauz/striped.png"));
    });

    public SchnauzRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new SchnauzModel<>(), 0.2F);
    }

    @Override
    public ResourceLocation getTextureLocation(SchnauzEntity entity) {
        return entity.isBaby() ? TEXTURES.get(0) : TEXTURES.getOrDefault(entity.getVariant(), TEXTURES.get(0));
    }
}