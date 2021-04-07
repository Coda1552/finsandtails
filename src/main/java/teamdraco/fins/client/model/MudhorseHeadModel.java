package teamdraco.fins.client.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.Model;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

@OnlyIn(Dist.CLIENT)
public class MudhorseHeadModel extends Model {
    public ModelRenderer head;
    public ModelRenderer snout;
    public ModelRenderer finLeft;
    public ModelRenderer finRight;
    private final List<ModelRenderer> parts;

    public MudhorseHeadModel() {
        super(RenderType::getEntityTranslucent);
        this.textureWidth = 80;
        this.textureHeight = 112;
        this.head = new ModelRenderer(this, 32, 34);
        this.head.setRotationPoint(0.0F, 18.5F, -2.0F);
        this.head.addBox(-4.5F, -3.5F, -7.0F, 9.0F, 9.0F, 10.0F, 0.0F, 0.0F, 0.0F);
        this.finLeft = new ModelRenderer(this, 0, 4);
        this.finLeft.setRotationPoint(4.5F, 1.5F, 3.0F);
        this.finLeft.addBox(0.0F, -4.0F, 0.0F, 0.0F, 10.0F, 8.0F, 0.0F, 0.0F, 0.0F);
        this.snout = new ModelRenderer(this, 42, 0);
        this.snout.setRotationPoint(0.0F, 4.0F, -7.0F);
        this.snout.addBox(-2.0F, -1.5F, -12.0F, 4.0F, 3.0F, 12.0F, 0.0F, 0.0F, 0.0F);
        this.finRight = new ModelRenderer(this, 0, 4);
        this.finRight.setRotationPoint(-4.5F, 1.5F, 3.0F);
        this.finRight.addBox(0.0F, -4.0F, 0.0F, 0.0F, 10.0F, 8.0F, 0.0F, 0.0F, 0.0F);
        this.head.addChild(this.finLeft);
        this.head.addChild(this.snout);
        this.head.addChild(this.finRight);
        this.parts = ImmutableList.of(this.head);
    }

    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        this.renderAll(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        matrixStackIn.pop();
        matrixStackIn.rotate(Vector3f.YP.rotationDegrees(180));
        matrixStackIn.push();
    }

    public void renderAll(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.parts.forEach((p_228248_8_) -> {
            p_228248_8_.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        });
    }

    public void setHeadState() {
        this.finLeft.rotateAngleY = 0.3000000035018414F;
        this.finRight.rotateAngleY = -0.3000000035018414F;
        this.head.rotateAngleX = 3.141592653589793F;
    }
}
