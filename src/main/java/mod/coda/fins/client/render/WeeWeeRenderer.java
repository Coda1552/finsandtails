package mod.coda.fins.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import mod.coda.fins.FinsAndTails;
import mod.coda.fins.client.model.WeeWeeModel;
import mod.coda.fins.entities.WeeWeeEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3f;

public class WeeWeeRenderer extends MobRenderer<WeeWeeEntity, WeeWeeModel<WeeWeeEntity>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/wee_wee.png");

    public WeeWeeRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new WeeWeeModel<>(), 0.2F);
    }

    public ResourceLocation getEntityTexture(WeeWeeEntity entity) {
        return TEXTURE;
    }

    protected void applyRotations(WeeWeeEntity entityLiving, MatrixStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
        float f = 4.3F * MathHelper.sin(0.6F * ageInTicks);
        matrixStackIn.rotate(Vector3f.YP.rotationDegrees(f));
        if (!entityLiving.isInWater()) {
            matrixStackIn.translate((double)0.1F, (double)0.1F, (double)-0.1F);
            matrixStackIn.rotate(Vector3f.ZP.rotationDegrees(90.0F));
        }
    }
}