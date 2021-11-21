package teamdraco.fins.common.items;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import teamdraco.fins.common.entities.item.TealArrowfishArrowEntity;

import java.util.List;

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

    @Override
    public void appendHoverText(ItemStack p_77624_1_, @Nullable World p_77624_2_, List<ITextComponent> p_77624_3_, ITooltipFlag p_77624_4_) {
        super.appendHoverText(p_77624_1_, p_77624_2_, p_77624_3_, p_77624_4_);
        p_77624_3_.add(new TranslationTextComponent("fins.teal_arrowfish.desc").withStyle(TextFormatting.GRAY).withStyle(TextFormatting.ITALIC));
    }
}