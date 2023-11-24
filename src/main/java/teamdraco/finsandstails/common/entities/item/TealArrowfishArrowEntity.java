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
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;
import teamdraco.finsandstails.common.items.TealArrowfishItem;
import teamdraco.finsandstails.registry.FTEntities;
import teamdraco.finsandstails.registry.FTItems;

public class TealArrowfishArrowEntity extends AbstractArrow implements IEntityAdditionalSpawnData, IAnimatable {
    private final AnimationFactory factory = GeckoLibUtil.createFactory(this);
    private final TealArrowfishItem arrow;

    public TealArrowfishArrowEntity(EntityType<? extends AbstractArrow> type, Level worldIn) {
        super(type, worldIn);
        this.arrow = (TealArrowfishItem) FTItems.TEAL_ARROWFISH.get();
    }

    public TealArrowfishArrowEntity(Level worldIn, Item item) {
        super(FTEntities.TEAL_ARROWFISH_ARROW.get(), worldIn);
        this.arrow = (TealArrowfishItem) item;
    }

    public TealArrowfishArrowEntity(Level worldIn, double x, double y, double z) {
        this(FTEntities.TEAL_ARROWFISH_ARROW.get(), worldIn);
        setPos(x, y, z);
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

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "controller", 5, this::predicate));
    }

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.arrowfish.swim"));
        return PlayState.CONTINUE;
    }

    @Override
    public AnimationFactory getFactory() {
        return factory;
    }
}
