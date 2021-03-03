package mod.coda.fins.items;

import mod.coda.fins.entities.item.TealArrowfishArrowEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class TealArrowfishItem extends ArrowItem {

    public TealArrowfishItem(Properties builder) {
        super(builder);
    }

    @Override
    public AbstractArrowEntity createArrow(World world, ItemStack stack, LivingEntity shooter) {
        TealArrowfishArrowEntity arrow = new TealArrowfishArrowEntity(world, this);
        arrow.setPosition(shooter.getPosX(), shooter.getPosYEye() - 0.1d, shooter.getPosZ());
        arrow.setShooter(shooter);
        arrow.setDamage(2.25);
        return arrow;
    }
}