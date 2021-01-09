package mod.coda.fins.event;

import mod.coda.fins.FinsAndTails;
//import mod.coda.fins.network.TriggerFlyingPacket;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = FinsAndTails.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ClientEvents {
    private static boolean wasJumping;

/*    @SubscribeEvent
    public static void playerTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            Minecraft minecraft = Minecraft.getInstance();
            boolean jumping = minecraft.gameSettings.keyBindJump.isKeyDown();
            if (jumping != wasJumping) {
                TriggerFlyingPacket packet = new TriggerFlyingPacket(jumping);
                packet.handle(minecraft.player);
                FinsAndTails.NETWORK.sendToServer(packet);
            }
            wasJumping = jumping;
        }
    }*/
}
