package teamdraco.fins.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;
import teamdraco.fins.FinsAndTails;
import teamdraco.fins.client.model.WeeModel;
import teamdraco.fins.common.entities.BluWeeEntity;

public class BluWeeRenderer extends MobRenderer<BluWeeEntity, WeeModel<BluWeeEntity>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/blu_wee.png");

    public BluWeeRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new WeeModel<>(), 0.2F);
    }

    @Override
    public ResourceLocation getTextureLocation(BluWeeEntity entity) {
        return TEXTURE;
    }

    @Override
    protected void setupRotations(BluWeeEntity entityLiving, MatrixStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
        super.setupRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
        if (!entityLiving.isInWater()) {
            matrixStackIn.translate(0.1F, 0.1F, -0.1F);
            matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(90.0F));
        }
    }
}