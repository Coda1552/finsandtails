package teamdraco.finsandstails.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.client.render.BluWeeRenderer;
import teamdraco.finsandstails.client.screen.CrabCruncherScreen;
import teamdraco.finsandstails.client.screen.MudhorsePouchScreen;
import teamdraco.finsandstails.common.items.charms.SpindlyGemCharm;
import teamdraco.finsandstails.network.TriggerFlyingPacket;
import teamdraco.finsandstails.registry.FTBlocks;
import teamdraco.finsandstails.registry.FTContainers;
import teamdraco.finsandstails.registry.FTEntities;
import teamdraco.finsandstails.registry.FTItems;

@Mod.EventBusSubscriber(modid = FinsAndTails.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvents {

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(FTEntities.BLU_WEE.get(), BluWeeRenderer::new);
    }

    @SubscribeEvent
    public static void setupClient(FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(FTBlocks.CHAINED_TENTACLE.get(), RenderType.cutout());

        MenuScreens.register(FTContainers.MUDHORSE_POUCH.get(), MudhorsePouchScreen::new);
        MenuScreens.register(FTContainers.CRAB_CRUNCHER.get(), CrabCruncherScreen::new);

        ItemProperties.register(FTItems.GEM_CRAB_AMULET.get(), new ResourceLocation(FinsAndTails.MOD_ID, "broken"), (stack, world, player, i) -> SpindlyGemCharm.isUsable(stack) ? 0.0F : 1.0F);

        FinsAndTails.CALLBACKS.forEach(Runnable::run);
        FinsAndTails.CALLBACKS.clear();
    }

    @Mod.EventBusSubscriber(modid = FinsAndTails.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
    public static class ModBus {
        private static boolean wasJumping;

        @SubscribeEvent
        public static void clientTick(TickEvent.ClientTickEvent event) {
            if (event.phase == TickEvent.Phase.END) {
                Minecraft minecraft = Minecraft.getInstance();
                final LocalPlayer player = minecraft.player;
                if (player != null && player.getItemBySlot(EquipmentSlot.CHEST).getItem() == FTItems.GOPJET_JETPACK.get()) {
                    boolean jumping = minecraft.options.keyJump.isDown();
                    if (jumping != wasJumping) {
                        TriggerFlyingPacket packet = new TriggerFlyingPacket(jumping);
                        packet.handle(player);
                        FinsAndTails.NETWORK.sendToServer(packet);
                    }
                    wasJumping = jumping;
                }
            }
        }
    }
}
