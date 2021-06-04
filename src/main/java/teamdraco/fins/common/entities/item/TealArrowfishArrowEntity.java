package teamdraco.fins.common.entities.item;

import teamdraco.fins.init.FinsEntities;
import teamdraco.fins.init.FinsItems;
import teamdraco.fins.common.items.TealArrowfishItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.network.PacketBuffer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.IEntityAdditionalSpawnData;
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
    protected ItemStack getPickupItem() {
        return new ItemStack(arrow);
    }

    protected float getWaterInertia() {
        return 0.99F;
    }

    @Override
    public void writeSpawnData(PacketBuffer buffer) {
        Entity shooter = getOwner();
        buffer.writeInt(shooter == null ? 0 : shooter.getId());
        buffer.writeVarInt(Item.getId(arrow));
    }

    @Override
    public void readSpawnData(PacketBuffer additionalData) {
    }

    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
