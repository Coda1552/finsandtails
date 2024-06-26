
package teamdraco.finsandstails.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import teamdraco.finsandstails.client.model.WherbleModel;
import teamdraco.finsandstails.common.entities.WherbleEntity;

public class WherbleRenderer extends GeoEntityRenderer<WherbleEntity> {

    public WherbleRenderer(EntityRendererProvider.Context context) {
        super(context, new WherbleModel());
        this.shadowRadius = 0.3F;
    }

    @Override
    public void render(WherbleEntity entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        if (entity.isBaby()) {
            poseStack.scale(0.5F, 0.5F, 0.5F);
        }
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}