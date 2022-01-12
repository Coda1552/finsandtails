package teamdraco.finsandstails.common.items;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import teamdraco.finsandstails.common.entities.item.TealArrowfishArrowEntity;

import java.util.List;

public class TealArrowfishItem extends ArrowItem {

    public TealArrowfishItem(Properties builder) {
        super(builder);
    }

    @Override
    public AbstractArrow createArrow(Level world, ItemStack stack, LivingEntity shooter) {
        TealArrowfishArrowEntity arrow = new TealArrowfishArrowEntity(world, this);
        arrow.setPos(shooter.getX(), shooter.getEyeY() - 0.1d, shooter.getZ());
        arrow.setOwner(shooter);
        arrow.setBaseDamage(2.25);
        return arrow;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> components, TooltipFlag flag) {
        super.appendHoverText(stack, world, components, flag);
        components.add(new TranslatableComponent("finsandtails.teal_arrowfish.desc").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
    }
}