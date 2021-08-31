package teamdraco.fins.client.render;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.matrix.MatrixStack;
import teamdraco.fins.FinsAndTails;
import teamdraco.fins.client.model.NightLightSquidModel;
import teamdraco.fins.common.entities.NightLightSquidEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraft.util.math.vector.Vector3f;

import java.util.Map;

public class NightLightSquidRenderer extends MobRenderer<NightLightSquidEntity, NightLightSquidModel<NightLightSquidEntity>> {
    public static final Map<Integer, ResourceLocation> TEXTURES = Util.make(Maps.newHashMap(), (hashMap) -> {
        hashMap.put(0, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/night_light_squid/night_light_squid_1.png"));
        hashMap.put(1, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/night_light_squid/night_light_squid_2.png"));
        hashMap.put(2, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/night_light_squid/night_light_squid_3.png"));
        hashMap.put(3, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/night_light_squid/night_light_squid_4.png"));
    });

    public NightLightSquidRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new NightLightSquidModel<>(), 0.2F);
        this.addLayer(new NightLightSquidGlowLayer<>(this));
    }

    @Override
    public ResourceLocation getTextureLocation(NightLightSquidEntity entity) {
        return TEXTURES.getOrDefault(entity.getVariant(), TEXTURES.get(0));
    }

    @Override
    protected void setupRotations(NightLightSquidEntity entityLiving, MatrixStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
        super.setupRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
        matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(180.0F));
    }
}