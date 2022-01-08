package teamdraco.finsandstails.client.old.render;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.client.old.model.GoliathGardenCrabModel;
import teamdraco.finsandstails.common.entities.GoliathGardenCrabEntity;

public class GoliathGardenCrabRenderer extends MobRenderer<GoliathGardenCrabEntity, GoliathGardenCrabModel<GoliathGardenCrabEntity>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/goliath_garden_crab.png");

    public GoliathGardenCrabRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new GoliathGardenCrabModel<>(), 1.5F);
    }

    public ResourceLocation getTextureLocation(GoliathGardenCrabEntity entity) {
        return TEXTURE;
    }
}