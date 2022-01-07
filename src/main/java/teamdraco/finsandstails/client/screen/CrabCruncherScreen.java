package teamdraco.finsandstails.client.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import teamdraco.finsandstails.FinsAndTails;
import teamdraco.finsandstails.common.container.CrabCruncherContainer;

@OnlyIn(Dist.CLIENT)
public class CrabCruncherScreen extends ContainerScreen<CrabCruncherContainer> {
    private static final ResourceLocation BLENDER_GUI = new ResourceLocation(FinsAndTails.MOD_ID, "textures/gui/crab_cruncher.png");

    public CrabCruncherScreen(CrabCruncherContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);

        this.leftPos = 0;
        this.topPos = 0;
        this.imageWidth = 176;
        this.imageHeight = 166;
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderTooltip(matrixStack, mouseX, mouseY);
    }

    @Override
    protected void renderLabels(MatrixStack matrixStack, int x, int y) {
        TranslationTextComponent textCompontent = new TranslationTextComponent("container.finsandtails.crab_cruncher");

        this.font.draw(matrixStack, this.inventory.getDisplayName(), (float) this.inventoryLabelX, (float) this.inventoryLabelY, 4210752);
        this.font.draw(matrixStack, textCompontent, ((float) imageWidth / 2 - font.width(textCompontent) / 2), 6f, 4210752);
    }

    @Override
    protected void renderBg(MatrixStack matrixStack, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.color4f(1f, 1f, 1f, 1f);
        this.minecraft.textureManager.bind(BLENDER_GUI);
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;
        this.blit(matrixStack, x, y, 0, 0, this.imageWidth, this.imageHeight);
    }
}
