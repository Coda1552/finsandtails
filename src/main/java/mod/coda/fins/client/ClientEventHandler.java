package mod.coda.fins.client;

import mod.coda.fins.FinsAndTails;
import mod.coda.fins.client.gui.MudhorsePorchScreen;
import mod.coda.fins.client.render.*;
import mod.coda.fins.init.FinsContainers;
import mod.coda.fins.init.FinsEntities;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.gui.screen.inventory.DispenserScreen;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;

public class ClientEventHandler {
    @OnlyIn(Dist.CLIENT)
    public static void init() {
        RenderingRegistry.registerEntityRenderingHandler(FinsEntities.BLU_WEE, BluWeeRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(FinsEntities.PEA_WEE, PeaWeeRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(FinsEntities.BANDED_REDBACK_SHRIMP, BandedRedbackShrimpRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(FinsEntities.TEAL_ARROWFISH, TealArrowfishRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(FinsEntities.SWAMP_MUCKER, SwampMuckerRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(FinsEntities.FLATBACK_SUCKER, FlatbackSuckerRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(FinsEntities.HIGHFINNED_BLUE, HighfinnedBlueRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(FinsEntities.MUDHORSE, MudhorseRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(FinsEntities.ORNATE_BUGFISH, OrnateBugfishRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(FinsEntities.PHANTOM_NUDIBRANCH, PhantomNudibranchRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(FinsEntities.PENGLIL, PenglilRenderer::new);
        RenderingRegistry.registerEntityRenderingHandler(FinsEntities.SPINDLY_GEM_CRAB, SpindlyGemCrabRenderer::new);
        //RenderingRegistry.registerEntityRenderingHandler(FinsEntities.RUBBER_BELLY_GLIDER, RubberBellyGliderRenderer.java::new);
        //RenderingRegistry.registerEntityRenderingHandler(FinsEntities.GOPJET, GopjetRenderer::new);

        ScreenManager.registerFactory(FinsContainers.MUDHORSE_POUCH.get(), MudhorsePorchScreen::new);
    }
}
