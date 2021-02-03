package mod.coda.fins.item;

import mod.coda.fins.entity.MudhorseEntity;
import mod.coda.fins.init.FinsEntities;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;

public class SwampDidgeridooItem extends Item {
    public SwampDidgeridooItem(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        if (context.getPlayer() != null) {
            for (MudhorseEntity mudhorseEntity : context.getWorld().getEntitiesWithinAABB(FinsEntities.MUDHORSE.get(), context.getPlayer().getBoundingBox().grow(8), entity -> entity.getCommander() == null)) {
                mudhorseEntity.setCommander(context.getPlayer());
            }
            context.getPlayer().getCooldownTracker().setCooldown(this, 1200);
        }
        return super.onItemUse(context);
    }
}
