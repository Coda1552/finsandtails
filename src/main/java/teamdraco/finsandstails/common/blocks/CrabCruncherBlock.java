package teamdraco.finsandstails.common.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.container.CrabCruncherContainer;

public class CrabCruncherBlock extends Block {
    private static final Component CONTAINER_NAME = new TranslatableComponent("container." + FinsAndTails.MOD_ID + "crab_cruncher");

    public CrabCruncherBlock(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(BlockState state, Level worldIn, BlockPos pos, Player player, InteractionHand handIn, BlockHitResult hit) {
        if (!worldIn.isClientSide) {
            NetworkHooks.openGui((ServerPlayer) player, state.getMenuProvider(worldIn, pos));
            return InteractionResult.CONSUME;
        } else {
            return InteractionResult.SUCCESS;
        }
    }

    @Nullable
    @Override
    public MenuProvider getMenuProvider(BlockState state, Level worldIn, BlockPos pos) {
        return new SimpleMenuProvider((id, inventory, player) -> new CrabCruncherContainer(id, inventory, IWorldPosCallable.create(worldIn, pos)), CONTAINER_NAME);
    }

    @Override
    public void onRemove(BlockState state, Level worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (!state.is(newState.getBlock())) {
            BlockEntity tileentity = worldIn.getBlockEntity(pos);
            if (tileentity instanceof Container) {
                Containers.dropContents(worldIn, pos, (Container)tileentity);
                worldIn.updateNeighbourForOutputSignal(pos, this);
            }

            super.onRemove(state, worldIn, pos, newState, isMoving);
        }
    }
}
