package mod.coda.fins.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import mod.coda.fins.FinsAndTails;
import mod.coda.fins.client.model.BandedRedbackShrimpModel;
import mod.coda.fins.entity.BandedRedbackShrimpEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;

public class BandedRedbackShrimpRenderer extends MobRenderer<BandedRedbackShrimpEntity, BandedRedbackShrimpModel<BandedRedbackShrimpEntity>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/banded_redback_shrimp.png");

    public BandedRedbackShrimpRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new BandedRedbackShrimpModel<>(), 0.4F);
    }

    public ResourceLocation getEntityTexture(BandedRedbackShrimpEntity entity) {
        return TEXTURE;
    }

    protected void applyRotations(BandedRedbackShrimpEntity entityLiving, MatrixStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
        float f = 4.3F * MathHelper.sin(0.6F * ageInTicks);
        matrixStackIn.rotate(Vector3f.YP.rotationDegrees(f));
        if (!entityLiving.isInWater()) {
            matrixStackIn.translate((double)0.1F, (double)0.1F, (double)-0.1F);
            matrixStackIn.rotate(Vector3f.ZP.rotationDegrees(90.0F));
        }
    }
}