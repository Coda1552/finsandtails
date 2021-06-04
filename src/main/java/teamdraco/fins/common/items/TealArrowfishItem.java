package teamdraco.fins.common.items;

import teamdraco.fins.common.entities.item.TealArrowfishArrowEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import net.minecraft.item.Item.Properties;

public class TealArrowfishItem extends ArrowItem {

    public TealArrowfishItem(Properties builder) {
        super(builder);
    }

    @Override
    public AbstractArrowEntity createArrow(World world, ItemStack stack, LivingEntity shooter) {
        TealArrowfishArrowEntity arrow = new TealArrowfishArrowEntity(world, this);
        arrow.setPos(shooter.getX(), shooter.getEyeY() - 0.1d, shooter.getZ());
        arrow.setOwner(shooter);
        arrow.setBaseDamage(2.25);
        return arrow;
    }

}