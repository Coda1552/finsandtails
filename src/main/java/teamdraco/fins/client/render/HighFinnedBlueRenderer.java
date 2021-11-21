package teamdraco.fins.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;
import teamdraco.fins.FinsAndTails;
import teamdraco.fins.client.model.HighFinnedBlueModel;
import teamdraco.fins.common.entities.HighFinnedBlueEntity;

public class HighFinnedBlueRenderer extends MobRenderer<HighFinnedBlueEntity, HighFinnedBlueModel<HighFinnedBlueEntity>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/high_finned_blue.png");

    public HighFinnedBlueRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new HighFinnedBlueModel<>(), 0.2F);
    }

    public ResourceLocation getTextureLocation(HighFinnedBlueEntity entity) {
        return TEXTURE;
    }

    protected void setupRotations(HighFinnedBlueEntity entityLiving, MatrixStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
        super.setupRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
        float f = 4.3F * MathHelper.sin(0.6F * ageInTicks);
        matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(f));
        if (!entityLiving.isInWater()) {
            matrixStackIn.translate((double)0.1F, (double)0.1F, (double)-0.1F);
            matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(90.0F));
        }
    }

    @Override
    protected void scale(HighFinnedBlueEntity entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        super.scale(entitylivingbaseIn, matrixStackIn, partialTickTime);
        matrixStackIn.translate(0.0f, -0.1f, 0.0f);
    }
}