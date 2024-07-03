package teamdraco.finsandstails.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.network.NetworkEvent;
import teamdraco.finsandstails.client.ClientEvents;
import teamdraco.finsandstails.client.ClientUtils;

import java.util.function.Supplier;

public class TriggerFlyingPacket implements INetworkPacket {
    private final boolean flying;

    public TriggerFlyingPacket(FriendlyByteBuf buffer) {
        flying = buffer.readBoolean();
    }

    public TriggerFlyingPacket(boolean flying) {
        this.flying = flying;
    }

    @Override
    public void write(FriendlyByteBuf buffer) {
        buffer.writeBoolean(flying);
    }

    @Override
    public void handle(Player player) {
        player.getPersistentData().putBoolean("FinsFlying", flying);
    }

    public void toBytes(FriendlyByteBuf buffer) {
        buffer.writeBoolean(flying);
    }

    public void handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            Player playerSided = context.getSender();
            if (context.getDirection().getReceptionSide() == LogicalSide.CLIENT) {
                playerSided = ClientUtils.getClientPlayer();
            }
            playerSided.getPersistentData().putBoolean("FinsFlying", flying);
        });
        context.setPacketHandled(true);
    }
}
