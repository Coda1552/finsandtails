package teamdraco.finsandstails.client.render;

import software.bernie.finsandtails.geckolib3.renderers.geo.GeoArmorRenderer;
import teamdraco.finsandstails.client.model.GopjetJetpackModel;
import teamdraco.finsandstails.common.items.GopjetJetpackItem;

public class GopjetJetpackRenderer extends GeoArmorRenderer<GopjetJetpackItem> {

    public GopjetJetpackRenderer() {
        super(new GopjetJetpackModel());

        //These values are what each bone name is in blockbench. So if your head bone is named "bone545",
        // make sure to do this.headBone = "bone545";

        // The default values are the ones that come with the default armor template in the geckolib blockbench plugin.
        this.headBone = "armorHead";
    }
}