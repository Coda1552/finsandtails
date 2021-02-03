package mod.coda.fins.item;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityType;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.FishBucketItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Supplier;

public class FinsBucketItem extends FishBucketItem {
    public FinsBucketItem(Supplier<? extends EntityType<?>> fishType, Supplier<? extends Fluid> fluid, Properties builder) {
        super(fishType, fluid, builder);
    }

    @OnlyIn(Dist.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
        super.addInformation(stack, world, tooltip, flag);
        if (stack.hasTag()) {
            tooltip.add(new TranslationTextComponent(getFishType().getTranslationKey() + "." + stack.getTag().getInt("BucketVariantTag")).mergeStyle(TextFormatting.GRAY).mergeStyle(TextFormatting.ITALIC));
        }
    }
}
