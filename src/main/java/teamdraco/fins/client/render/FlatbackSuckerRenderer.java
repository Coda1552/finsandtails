package teamdraco.fins.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;
import teamdraco.fins.FinsAndTails;
import teamdraco.fins.client.model.FlatbackSuckerModel;
import teamdraco.fins.common.entities.FlatbackSuckerEntity;

public class FlatbackSuckerRenderer extends MobRenderer<FlatbackSuckerEntity, FlatbackSuckerModel<FlatbackSuckerEntity>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/flatback_sucker.png");

    public FlatbackSuckerRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new FlatbackSuckerModel<>(), 0.3F);
    }

    public ResourceLocation getTextureLocation(FlatbackSuckerEntity entity) {
        return TEXTURE;
    }

    protected void setupRotations(FlatbackSuckerEntity entityLiving, MatrixStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
        super.setupRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
        float f = 4.3F * MathHelper.sin(0.6F * ageInTicks);
        matrixStackIn.mulPose(Vector3f.YP.rotationDegrees(f));
    }
}