//package mod.coda.fins.client.render;
//
//import mod.coda.fins.FinsAndTails;
//import mod.coda.fins.client.model.RubberBellyGliderModel;
//import mod.coda.fins.entity.RubberBellyGliderEntity;
//import net.minecraft.client.renderer.entity.EntityRendererManager;
//import net.minecraft.client.renderer.entity.MobRenderer;
//import net.minecraft.util.ResourceLocation;
//
//public class RubberBellyGliderRenderer extends MobRenderer<RubberBellyGliderEntity, RubberBellyGliderModel<RubberBellyGliderEntity>> {
//    private static final ResourceLocation TEXTURE_NORMAL = new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/rubber_belly_glider/normal.png");
//    private static final ResourceLocation TEXTURE_PUFFED = new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/rubber_belly_glider/puffed.png");
//
//    public RubberBellyGliderRenderer(EntityRendererManager renderManagerIn) {
//        super(renderManagerIn, new RubberBellyGliderModel<>(), 0.4F);
//    }
//
//    public ResourceLocation getEntityTexture(RubberBellyGliderEntity entity) {
//        if(entity.getPuffState() == 1) {
//            return TEXTURE_PUFFED;
//        }
//        else {
//            return TEXTURE_NORMAL;
//        }
//    }
//}