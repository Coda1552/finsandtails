package teamdraco.finsandstails.client.old.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.client.old.model.WeeModel;
import teamdraco.finsandstails.common.entities.PeaWeeEntity;

public class PeaWeeRenderer extends MobRenderer<PeaWeeEntity, WeeModel<PeaWeeEntity>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/pea_wee.png");

    public PeaWeeRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new WeeModel<>(), 0.2F);
    }

    @Override
    public ResourceLocation getTextureLocation(PeaWeeEntity entity) {
        return TEXTURE;
    }

    @Override
    protected void setupRotations(PeaWeeEntity entityLiving, MatrixStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
        super.setupRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
        if (!entityLiving.isInWater()) {
            matrixStackIn.translate(0.1F, 0.1F, -0.1F);
            matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(90.0F));
        }
    }
}