package teamdraco.fins.common.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import teamdraco.fins.FinsAndTails;
import teamdraco.fins.init.FinsItems;

public class SpindlyGemCrabItem extends Item {
    public SpindlyGemCrabItem(Properties properties) {
        super(properties);
        DistExecutor.unsafeCallWhenOn(Dist.CLIENT, () -> () -> FinsAndTails.CALLBACKS.add(() -> ItemModelsProperties.registerProperty(this, new ResourceLocation(FinsAndTails.MOD_ID, "crab"), (stack, world, player) -> stack.hasTag() ? stack.getTag().getInt("Crab") : 0)));
    }
}
