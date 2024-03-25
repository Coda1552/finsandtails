
package teamdraco.finsandstails.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import teamdraco.finsandstails.client.model.RiverPebbleSnailModel;
import teamdraco.finsandstails.common.entities.RiverPebbleSnailEntity;
import teamdraco.finsandstails.common.entities.SiderolWhiskeredSnailEntity;

public class RiverPebbleSnailRenderer extends GeoEntityRenderer<RiverPebbleSnailEntity> {

    public RiverPebbleSnailRenderer(EntityRendererProvider.Context context) {
        super(context, new RiverPebbleSnailModel());
        this.shadowRadius = 0.2F;
    }

    @Override
    public void render(RiverPebbleSnailEntity entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        if (entity.isBaby()) {
            poseStack.scale(0.5F, 0.5F, 0.5F);
        }
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}