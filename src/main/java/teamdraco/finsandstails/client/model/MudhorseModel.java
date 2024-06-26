package teamdraco.finsandstails.client.model;

import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.entities.MudhorseEntity;

public class MudhorseModel extends DefaultedEntityGeoModel<MudhorseEntity> {

    public MudhorseModel() {
        super(new ResourceLocation(FinsAndTails.MOD_ID, "mudhorse"));
    }

    // todo - make entranced texture an render layer
    @Override
    public ResourceLocation getTextureResource(MudhorseEntity wee) {
        return wee.commanderSetTime > 0 ? new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/mudhorse/entranced.png") : new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/mudhorse/mudhorse.png");
    }
}