package teamdraco.fins.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import teamdraco.fins.FinsAndTails;
import teamdraco.fins.client.model.PeaWeeModel;
import teamdraco.fins.common.entities.PeaWeeEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;

public class PeaWeeRenderer extends MobRenderer<PeaWeeEntity, PeaWeeModel<PeaWeeEntity>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/pea_wee.png");

    public PeaWeeRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new PeaWeeModel<>(), 0.2F);
    }

    public ResourceLocation getTextureLocation(PeaWeeEntity entity) {
        return TEXTURE;
    }

    protected void setupRotations(PeaWeeEntity entityLiving, MatrixStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
        super.setupRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
        float f = 4.3F * MathHelper.sin(0.6F * ageInTicks);
        matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(f));
        if (!entityLiving.isInWater()) {
            matrixStackIn.translate((double)0.1F, (double)0.1F, (double)-0.1F);
            matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(90.0F));
        }
    }
}