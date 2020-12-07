package mod.coda.fins.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import mod.coda.fins.FinsAndTails;
import mod.coda.fins.client.model.HighfinnedBlueModel;
import mod.coda.fins.entity.HighfinnedBlueEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;

public class HighfinnedBlueRenderer extends MobRenderer<HighfinnedBlueEntity, HighfinnedBlueModel<HighfinnedBlueEntity>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/highfinned_blue.png");

    public HighfinnedBlueRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new HighfinnedBlueModel<>(), 0.2F);
    }

    public ResourceLocation getEntityTexture(HighfinnedBlueEntity entity) {
        return TEXTURE;
    }

    protected void applyRotations(HighfinnedBlueEntity entityLiving, MatrixStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
        float f = 4.3F * MathHelper.sin(0.6F * ageInTicks);
        matrixStackIn.rotate(Vector3f.YP.rotationDegrees(f));
        if (!entityLiving.isInWater()) {
            matrixStackIn.translate((double)0.1F, (double)0.1F, (double)-0.1F);
            matrixStackIn.rotate(Vector3f.ZP.rotationDegrees(90.0F));
        }
    }

    @Override
    protected void preRenderCallback(HighfinnedBlueEntity entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        super.preRenderCallback(entitylivingbaseIn, matrixStackIn, partialTickTime);
        matrixStackIn.translate(0.0f, -0.1f, 0.0f);
    }
}