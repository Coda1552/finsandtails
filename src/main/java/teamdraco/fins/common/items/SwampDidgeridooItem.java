package teamdraco.fins.common.items;

import teamdraco.fins.common.entities.MudhorseEntity;
import teamdraco.fins.init.FinsEntities;
import teamdraco.fins.init.FinsSounds;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;

import java.util.List;

public class SwampDidgeridooItem extends Item {
    public SwampDidgeridooItem(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getHeldItem(hand);
        List<MudhorseEntity> mudhorses = world.getEntitiesWithinAABB(FinsEntities.MUDHORSE.get(), player.getBoundingBox().grow(8), entity -> entity.getCommander() == null);
        if (mudhorses.isEmpty()) {
            player.playSound(SoundEvents.ENTITY_VILLAGER_NO, 0.4f, 1);
            addParticleEffect(ParticleTypes.SMOKE, world, player.getPosX() - 0.5, player.getPosY() + 1, player.getPosZ() - 0.5);
            return ActionResult.resultPass(stack);
        }

        for (MudhorseEntity mudhorseEntity : mudhorses) {
            mudhorseEntity.setCommander(player);
            addParticleEffect(ParticleTypes.HAPPY_VILLAGER, world, mudhorseEntity.getPosX() - 0.5, mudhorseEntity.getPosY() + 1.4, mudhorseEntity.getPosZ() - 0.5);
        }
        player.playSound(FinsSounds.DIDGERIDOO_PLAY.get(), 0.4f, 1);
        player.getCooldownTracker().setCooldown(this, 600);
        stack.damageItem(1, player, entity -> entity.sendBreakAnimation(hand));
        return ActionResult.resultSuccess(stack);
    }

    private void addParticleEffect(IParticleData particleData, World world, double x, double y, double z) {
        for(int i = 0; i < 10; ++i) {
            double d2 = random.nextGaussian() * 0.02D;
            double d3 = random.nextGaussian() * 0.02D;
            double d4 = random.nextGaussian() * 0.02D;
            double d6 = x + random.nextDouble();
            double d7 = y + random.nextDouble() * 0.5;
            double d8 = z + random.nextDouble();
            world.addParticle(particleData, d6, d7, d8, d2, d3, d4);
        }
    }
}
