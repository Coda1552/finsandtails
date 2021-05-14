package teamdraco.fins.network;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.PacketBuffer;

public class TriggerFlyingPacket implements INetworkPacket {
    private final boolean flying;

    public TriggerFlyingPacket(PacketBuffer buffer) {
        flying = buffer.readBoolean();
    }

    public TriggerFlyingPacket(boolean flying) {
        this.flying = flying;
    }

    @Override
    public void write(PacketBuffer buffer) {
        buffer.writeBoolean(flying);
    }

    @Override
    public void handle(PlayerEntity player) {
        player.getPersistentData().putBoolean("FinsFlying", flying);
    }
}
