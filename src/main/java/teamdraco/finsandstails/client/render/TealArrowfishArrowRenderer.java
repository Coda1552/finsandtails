package teamdraco.finsandstails.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import teamdraco.finsandstails.client.model.TealArrowfishArrowModel;
import teamdraco.finsandstails.common.entities.item.TealArrowfishArrowEntity;

public class TealArrowfishArrowRenderer extends GeoEntityRenderer<TealArrowfishArrowEntity> {

    public TealArrowfishArrowRenderer(EntityRendererProvider.Context context) {
        super(context, new TealArrowfishArrowModel());
    }

    @Override
    public RenderType getRenderType(TealArrowfishArrowEntity animatable, ResourceLocation texture, @Nullable MultiBufferSource bufferSource, float partialTick) {
        return RenderType.entityCutout(texture);
    }

    @Override
    public void render(TealArrowfishArrowEntity entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        poseStack.pushPose();
        poseStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(partialTick, entity.yRotO, entity.getYRot()) - 90.0F));
        poseStack.pushPose();
        poseStack.mulPose(Axis.ZP.rotationDegrees(Mth.lerp(partialTick, entity.xRotO, entity.getXRot())));

        float f = (float) entity.shakeTime - partialTick;
        if (f > 0.0F) {
            float f1 = -Mth.sin(f * 3.0F) * f;
            poseStack.mulPose(Axis.ZP.rotationDegrees(f1));
        }
        poseStack.mulPose(Axis.YP.rotationDegrees(90.0F));

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
        poseStack.popPose();
        poseStack.popPose();
    }

}