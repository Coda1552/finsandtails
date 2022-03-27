package teamdraco.finsandstails.client.render;

import net.minecraft.world.item.ArmorItem;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoArmorRenderer;

public class ArmorItemRenderer<T extends ArmorItem & IAnimatable> extends GeoArmorRenderer<T> {
    public ArmorItemRenderer(AnimatedGeoModel<T> model) {
        super(model);
        this.bodyBone = "armorBody";
    }
}
