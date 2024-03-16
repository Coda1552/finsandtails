
package teamdraco.finsandstails.client.render;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import teamdraco.finsandstails.client.model.PhantomNudibranchModel;
import teamdraco.finsandstails.client.render.layer.PhantomNudibranchGlowLayer;
import teamdraco.finsandstails.common.entities.PhantomNudibranchEntity;

public class PhantomNudibranchRenderer extends GeoEntityRenderer<PhantomNudibranchEntity> {

    public PhantomNudibranchRenderer(EntityRendererProvider.Context context) {
        super(context, new PhantomNudibranchModel());
        this.addRenderLayer(new PhantomNudibranchGlowLayer(this));
        this.shadowRadius = 0.2F;
    }

    @Override
    public RenderType getRenderType(PhantomNudibranchEntity animatable, ResourceLocation texture, @Nullable MultiBufferSource bufferSource, float partialTick) {
        return RenderType.entityCutout(texture);
    }
}