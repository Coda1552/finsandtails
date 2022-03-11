package teamdraco.finsandstails.common.entities.ai;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import teamdraco.finsandstails.common.entities.MudhorseEntity;

import java.util.EnumSet;

public class MudhorseForageGoal extends Goal {
    // private static final ResourceLocation FORAGING_LOOT = new ResourceLocation(FinsAndTails.MOD_ID, "gameplay/mudhorse_foraging");
    private final MudhorseEntity mudhorse;
    private final Level level;
    private int eatAnimationTick;

    public MudhorseForageGoal(MudhorseEntity p_i45314_1_) {
        this.mudhorse = p_i45314_1_;
        this.level = p_i45314_1_.level;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK, Flag.JUMP));
    }

    @Override
    public boolean canUse() {
        if (this.mudhorse.getRandom().nextInt(this.mudhorse.isBaby() ? 50 : 1000) != 0) {
            return false;
        } else {
            BlockPos blockpos = this.mudhorse.blockPosition();
            return this.level.getBlockState(blockpos).is(Blocks.WATER) && this.level.getBlockState(blockpos.below()).is(Blocks.SAND) ;
        }
    }

    @Override
    public void start() {
        this.eatAnimationTick = 40;
        this.level.broadcastEntityEvent(this.mudhorse, (byte)10);
        this.mudhorse.getNavigation().stop();
    }

    @Override
    public void stop() {
        this.eatAnimationTick = 0;
    }

    @Override
    public boolean canContinueToUse() {
        return this.eatAnimationTick > 0;
    }

    public int getEatAnimationTick() {
        return this.eatAnimationTick;
    }

    @Override
    public void tick() {
        this.eatAnimationTick = Math.max(0, this.eatAnimationTick - 1);
        if (this.eatAnimationTick == 4) {
            BlockPos blockpos = this.mudhorse.blockPosition();
            BlockPos blockpos1 = blockpos.below();
            if (this.level.getBlockState(blockpos1).is(Blocks.SAND) && this.level.getBlockState(blockpos).is(Blocks.WATER)) {
                if (net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.level, this.mudhorse)) {
                    this.level.levelEvent(2001, blockpos, Block.getId(Blocks.SAND.defaultBlockState()));
                }
                this.mudhorse.ate();
                // List<ItemStack> items = mudhorse.level.getServer().getLootTables().get(FORAGING_LOOT).getRandomItems(new LootContext.Builder((ServerWorld) mudhorse.level).withRandom(mudhorse.getRandom()).create(LootParameterSets.EMPTY));
                // InventoryHelper.dropContents(mudhorse.level, blockpos, NonNullList.of(ItemStack.EMPTY, items.toArray(new ItemStack[0])));
            }
        }
    }
}
