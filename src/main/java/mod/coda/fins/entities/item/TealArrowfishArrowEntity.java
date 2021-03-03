package mod.coda.fins.entities.item;

import mod.coda.fins.init.FinsEntities;
import mod.coda.fins.init.FinsItems;
import mod.coda.fins.items.TealArrowfishItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.network.NetworkHooks;

public class TealArrowfishArrowEntity extends AbstractArrowEntity implements IEntityAdditionalSpawnData {
    private final TealArrowfishItem arrow;

    public TealArrowfishArrowEntity(EntityType<? extends AbstractArrowEntity> type, World worldIn) {
        super(type, worldIn);
        this.arrow = (TealArrowfishItem) FinsItems.TEAL_ARROWFISH.get();
    }

    public TealArrowfishArrowEntity(World worldIn, Item item) {
        super(FinsEntities.TEAL_ARROWFISH_ARROW.get(), worldIn);
        this.arrow = (TealArrowfishItem) item;
    }

    @Override
    protected ItemStack getArrowStack() {
        return new ItemStack(arrow);
    }

    protected float getWaterDrag() {
        return 0.99F;
    }

    @Override
    public void writeSpawnData(PacketBuffer buffer) {
        Entity shooter = func_234616_v_();
        buffer.writeInt(shooter == null ? 0 : shooter.getEntityId());
        buffer.writeVarInt(Item.getIdFromItem(arrow));
    }

    @Override
    public void readSpawnData(PacketBuffer additionalData) {
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
