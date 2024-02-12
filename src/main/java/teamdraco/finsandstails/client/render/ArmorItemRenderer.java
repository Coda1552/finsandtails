package teamdraco.finsandstails.client.render;

import net.minecraft.world.item.ArmorItem;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.animatable.GeoItem;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

public class ArmorItemRenderer<T extends ArmorItem & GeoItem> extends GeoArmorRenderer<T> {
    public ArmorItemRenderer(GeoModel<T> model) {
        super(model);
    }

//    @Nullable
//    @Override
//    public GeoBone getBodyBone() {
//        return this.model.getBone("armorBody").orElse(null);
//    }
}
