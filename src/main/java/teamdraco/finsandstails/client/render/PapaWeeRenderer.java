package teamdraco.finsandstails.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.client.model.PapaWeeModel;
import teamdraco.finsandstails.common.entities.PapaWeeEntity;

@OnlyIn(Dist.CLIENT)
public class PapaWeeRenderer extends MobRenderer<PapaWeeEntity, PapaWeeModel<PapaWeeEntity>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/papa_wee.png");

    public PapaWeeRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new PapaWeeModel<>(), 0.5F);
    }

    public ResourceLocation getTextureLocation(PapaWeeEntity entity) {
        return TEXTURE;
    }

    protected void setupRotations(PapaWeeEntity entityLiving, MatrixStack matrixStackIn, float ageInTicks, float rotationYaw, float partialTicks) {
        super.setupRotations(entityLiving, matrixStackIn, ageInTicks, rotationYaw, partialTicks);
        if (!entityLiving.isInWater()) {
            matrixStackIn.translate((double)0.1F, (double)0.1F, (double)-0.1F);
            matrixStackIn.mulPose(Vector3f.ZP.rotationDegrees(90.0F));
        }
    }
}