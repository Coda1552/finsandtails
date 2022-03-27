package teamdraco.finsandstails.client.render.armor;

import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;
import teamdraco.finsandstails.client.model.armor.SpindlyGemModel;
import teamdraco.finsandstails.common.items.charms.SpindlyEmeraldCharm;
import teamdraco.finsandstails.common.items.charms.SpindlyGemCharm;

public class SpindlyGemRenderer extends GeoArmorRenderer<SpindlyGemCharm> {

    public SpindlyGemRenderer() {
        super(new SpindlyGemModel());

        //These values are what each bone name is in blockbench. So if your head bone is named "bone545",
        // make sure to do this.headBone = "bone545";

        // The default values are the ones that come with the default armor template in the geckolib blockbench plugin.
        this.bodyBone = "armorBody";
    }
}