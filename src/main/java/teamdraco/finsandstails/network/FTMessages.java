package teamdraco.finsandstails.network;

import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.simple.SimpleChannel;
import teamdraco.finsandstails.FinsAndTails;

import java.util.Optional;
import java.util.function.Function;

public class FTMessages {
    private static SimpleChannel INSTANCE;
    private static int currentNetworkId;

    private static int packetId = 0;
    private static int id() {
        return packetId++;
    }

    public static void register() {
        SimpleChannel net = NetworkRegistry.ChannelBuilder
                .named(new ResourceLocation(FinsAndTails.MOD_ID, "messages"))
                .networkProtocolVersion(() -> "1.0")
                .clientAcceptedVersions(s -> true)
                .serverAcceptedVersions(s -> true)
                .simpleChannel();

        INSTANCE = net;

        net.messageBuilder(HitComboSyncS2CPacket.class, id(), NetworkDirection.PLAY_TO_CLIENT)
                .decoder(HitComboSyncS2CPacket::new)
                .encoder(HitComboSyncS2CPacket::toBytes)
                .consumerMainThread(HitComboSyncS2CPacket::handle)
                .add();


        net.messageBuilder(TriggerFlyingPacket.class, id(), NetworkDirection.PLAY_TO_SERVER)
                .decoder(TriggerFlyingPacket::new)
                .encoder(TriggerFlyingPacket::toBytes)
                .consumerMainThread(TriggerFlyingPacket::handle)
                .add();
    }

    public static <MSG> void sendToPlayer(MSG message, ServerPlayer player) {
        INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), message);
    }
}