package teamdraco.finsandstails.client.model;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.entities.RedBullCrabEntity;

public class RedBullCrabModel extends DefaultedEntityGeoModel<RedBullCrabEntity> {

    public RedBullCrabModel() {
        super(new ResourceLocation(FinsAndTails.MOD_ID, "bull_crab"));
    }

    @Override
    public ResourceLocation getTextureResource(RedBullCrabEntity entity) {
        return new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/red_bull_crab.png");
    }
}