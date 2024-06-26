package teamdraco.finsandstails.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.network.NetworkEvent;
import teamdraco.finsandstails.client.ClientUtils;

import java.util.function.Supplier;

public class TriggerFlyingPacket {
    private final boolean flying;

    public TriggerFlyingPacket(FriendlyByteBuf buffer) {
        this.flying = buffer.readBoolean();
    }

    public TriggerFlyingPacket(boolean flying) {
        this.flying = flying;
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
