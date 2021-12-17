package teamdraco.fins.common.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import teamdraco.fins.init.FinsFeatures;

public class TestItem extends Item {

    public TestItem(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResultType useOn(ItemUseContext context) {
        World world = context.getLevel();
        if (world instanceof ServerWorld) {
            ServerWorld serverWorld = (ServerWorld) world;
            BlockPos growPos = context.getClickedPos().above();
            FinsFeatures.LAMINA_TREE_FEATURE.get().place(serverWorld, serverWorld.getChunkSource().generator, world.random, growPos, null);
            return ActionResultType.SUCCESS;
        }
        return super.useOn(context);
    }
}