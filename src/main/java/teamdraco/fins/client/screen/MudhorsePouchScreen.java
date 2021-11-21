package teamdraco.fins.client.screen;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import teamdraco.fins.common.container.MudhorsePouchContainer;

@OnlyIn(Dist.CLIENT)
public class MudhorsePouchScreen extends ContainerScreen<MudhorsePouchContainer> {
   private static final ResourceLocation TEXTURE = new ResourceLocation("textures/gui/container/dispenser.png");

   public MudhorsePouchScreen(MudhorsePouchContainer container, PlayerInventory playerInventory, ITextComponent title) {
      super(container, playerInventory, title);
      this.passEvents = false;
      this.inventoryLabelY = this.imageHeight - 94;
   }

   protected void init() {
      super.init();
      this.titleLabelX = (this.imageWidth - this.font.width(this.title)) / 2;
   }

   @Override
   protected void renderBg(MatrixStack matrixStack, float partialTicks, int x, int y) {
      RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
      this.minecraft.getTextureManager().bind(TEXTURE);
      int i = (this.width - this.imageWidth) / 2;
      int j = (this.height - this.imageHeight) / 2;
      this.blit(matrixStack, i, j, 0, 0, this.imageWidth, this.imageHeight);
   }

   @Override
   public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
      this.renderBackground(matrixStack);
      super.render(matrixStack, mouseX, mouseY, partialTicks);
      this.renderTooltip(matrixStack, mouseX, mouseY);
   }
}
