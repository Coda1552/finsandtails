package teamdraco.finsandstails.common.entities.item;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.entity.IEntityAdditionalSpawnData;
import net.minecraftforge.network.NetworkHooks;
import teamdraco.finsandstails.common.items.TealArrowfishItem;
import teamdraco.finsandstails.registry.FTItems;
import teamdraco.finsandstails.registry.FTEntities;

public class TealArrowfishArrowEntity extends AbstractArrow implements IEntityAdditionalSpawnData {
    private final TealArrowfishItem arrow;

    public TealArrowfishArrowEntity(EntityType<? extends AbstractArrow> type, Level worldIn) {
        super(type, worldIn);
        this.arrow = (TealArrowfishItem) FTItems.TEAL_ARROWFISH.get();
    }

    public TealArrowfishArrowEntity(Level worldIn, Item item) {
        super(FTEntities.TEAL_ARROWFISH_ARROW.get(), worldIn);
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
    public void writeSpawnData(FriendlyByteBuf buffer) {
        Entity shooter = getOwner();
        buffer.writeInt(shooter == null ? 0 : shooter.getId());
        buffer.writeVarInt(Item.getId(arrow));
    }

    @Override
    public void readSpawnData(FriendlyByteBuf additionalData) {
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}
