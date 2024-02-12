
package teamdraco.finsandstails.client.render;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import teamdraco.finsandstails.client.model.OrnateBugfishModel;
import teamdraco.finsandstails.common.entities.OrnateBugfishEntity;

public class OrnateBugfishRenderer extends GeoEntityRenderer<OrnateBugfishEntity> {

    public OrnateBugfishRenderer(EntityRendererProvider.Context context) {
        super(context, new OrnateBugfishModel());
        this.shadowRadius = 0.3F;
    }

    @Override
    public RenderType getRenderType(OrnateBugfishEntity animatable, ResourceLocation texture, @Nullable MultiBufferSource bufferSource, float partialTick) {
        return RenderType.entityTranslucent(texture);
    }
}