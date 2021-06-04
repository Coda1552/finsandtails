package teamdraco.fins.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import teamdraco.fins.FinsAndTails;
import teamdraco.fins.client.model.BluWeeModel;
import teamdraco.fins.common.entities.BluWeeEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;

public class BluWeeRenderer extends MobRenderer<BluWeeEntity, BluWeeModel<BluWeeEntity>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/blu_wee.png");

    public BluWeeRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new BluWeeModel<>(), 0.2F);
    }

    public ResourceLocation getTextureLocation(BluWeeEntity entity) {
        return TEXTURE;
    }

    protected void setupRotations(BluWeeEntity entityLiving, MatrixStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
        super.setupRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
        float f = 4.3F * MathHelper.sin(0.6F * ageInTicks);
        matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(f));
        if (!entityLiving.isInWater()) {
            matrixStackIn.translate((double)0.1F, (double)0.1F, (double)-0.1F);
            matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(90.0F));
        }
    }
}