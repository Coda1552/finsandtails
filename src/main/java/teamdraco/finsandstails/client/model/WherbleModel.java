package teamdraco.finsandstails.client.model;

import com.google.common.collect.Maps;
import net.minecraft.Util;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedEntityGeoModel;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.entities.WherbleEntity;

import java.util.Map;

public class WherbleModel extends DefaultedEntityGeoModel<WherbleEntity> {
    public static final Map<Integer, ResourceLocation> TEXTURES = Util.make(Maps.newHashMap(), (hashMap) -> {
        hashMap.put(0, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/wherble/wherble_1.png"));
        hashMap.put(1, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/wherble/wherble_2.png"));
        hashMap.put(2, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/wherble/wherble_3.png"));
        hashMap.put(3, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/wherble/wherble_4.png"));
        hashMap.put(4, new ResourceLocation(FinsAndTails.MOD_ID, "textures/entity/wherble/child.png"));
    });

    public WherbleModel() {
        super(new ResourceLocation(FinsAndTails.MOD_ID, "wherble"));
    }

    @Override
    public ResourceLocation getTextureResource(WherbleEntity wee) {
        return wee.isBaby() ? TEXTURES.get(4) : TEXTURES.getOrDefault(wee.getVariant(), TEXTURES.get(0));
    }
}
