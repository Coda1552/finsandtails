package teamdraco.finsandstails.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.client.model.WeeWeeModel;
import teamdraco.finsandstails.common.entities.WeeWeeEntity;

public class WeeWeeRenderer extends MobRenderer<WeeWeeEntity, WeeWeeModel<WeeWeeEntity>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/wee_wee.png");

    public WeeWeeRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new WeeWeeModel<>(), 0.2F);
    }

    public ResourceLocation getTextureLocation(WeeWeeEntity entity) {
        return TEXTURE;
    }

    protected void setupRotations(WeeWeeEntity entityLiving, MatrixStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
        super.setupRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
        if (!entityLiving.isInWater()) {
            matrixStackIn.translate((double)0.1F, (double)0.1F, (double)-0.1F);
            matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(90.0F));
        }
    }
}