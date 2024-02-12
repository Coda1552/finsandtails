
package teamdraco.finsandstails.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.util.Mth;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import teamdraco.finsandstails.client.model.TealArrowfishArrowModel;
import teamdraco.finsandstails.common.entities.item.TealArrowfishArrowEntity;

public class TealArrowfishArrowRenderer extends GeoEntityRenderer<TealArrowfishArrowEntity> {

    public TealArrowfishArrowRenderer(EntityRendererProvider.Context context) {
        super(context, new TealArrowfishArrowModel());
    }

    @Override
    public void render(TealArrowfishArrowEntity entityIn, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
        matrixStackIn.pushPose();
        matrixStackIn.mulPose(Axis.YP.rotationDegrees(Mth.lerp(partialTicks, entityIn.yRotO, entityIn.getYRot()) - 90.0F));
        matrixStackIn.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(partialTicks, entityIn.xRotO, entityIn.getXRot()) + 90.0F));
        matrixStackIn.translate(0.0, -1.3f, 0);
        float f = (float) entityIn.shakeTime - partialTicks;
        if (f > 0.0F) {
            float f1 = -Mth.sin(f * 3.0F) * f;
            matrixStackIn.mulPose(Axis.ZP.rotationDegrees(f1));
        }

        matrixStackIn.scale(0.05625F, 0.05625F, 0.05625F);
        matrixStackIn.popPose();

        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }
}