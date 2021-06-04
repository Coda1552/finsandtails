package teamdraco.fins.client.render;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.matrix.MatrixStack;
import teamdraco.fins.FinsAndTails;
import teamdraco.fins.client.model.VibraWeeModel;
import teamdraco.fins.common.entities.VibraWeeEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;

import java.util.Map;

public class VibraWeeRenderer extends MobRenderer<VibraWeeEntity, VibraWeeModel<VibraWeeEntity>> {
    public static final Map<Integer, ResourceLocation> TEXTURES = Util.make(Maps.newHashMap(), (hashMap) -> {
        hashMap.put(0, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/vibra_wee/vibra_wee_1.png"));
        hashMap.put(1, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/vibra_wee/vibra_wee_2.png"));
        hashMap.put(2, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/vibra_wee/vibra_wee_3.png"));
        hashMap.put(3, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/vibra_wee/vibra_wee_4.png"));
        hashMap.put(4, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/vibra_wee/vibra_wee_5.png"));
        hashMap.put(5, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/vibra_wee/vibra_wee_6.png"));
        hashMap.put(6, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/vibra_wee/vibra_wee_7.png"));
        hashMap.put(7, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/vibra_wee/vibra_wee_8.png"));
        hashMap.put(8, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/vibra_wee/vibra_wee_9.png"));
        hashMap.put(9, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/vibra_wee/vibra_wee_10.png"));
        hashMap.put(10, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/vibra_wee/vibra_wee_11.png"));
        hashMap.put(11, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/vibra_wee/vibra_wee_12.png"));
        hashMap.put(12, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/vibra_wee/vibra_wee_13.png"));
        hashMap.put(13, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/vibra_wee/vibra_wee_14.png"));
        hashMap.put(14, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/vibra_wee/vibra_wee_15.png"));
    });
    public VibraWeeRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new VibraWeeModel<>(), 0.2F);
    }

    @Override
    public ResourceLocation getTextureLocation(VibraWeeEntity entity) {
        return TEXTURES.getOrDefault(entity.getVariant(), TEXTURES.get(0));
    }

    protected void setupRotations(VibraWeeEntity entityLiving, MatrixStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
        super.setupRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
        float f = 4.3F * MathHelper.sin(0.6F * ageInTicks);
        matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(f));
        if (!entityLiving.isInWater()) {
            matrixStackIn.translate((double)0.1F, (double)0.1F, (double)-0.1F);
            matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(90.0F));
        }
    }
}