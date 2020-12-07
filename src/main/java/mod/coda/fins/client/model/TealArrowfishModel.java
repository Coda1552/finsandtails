package mod.coda.fins.client.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class TealArrowfishModel<T extends Entity> extends EntityModel<T> {
    public ModelRenderer body;
    public ModelRenderer nose;
    public ModelRenderer tail;
    public ModelRenderer finLeft;
    public ModelRenderer finRight;
    public ModelRenderer finTop;
    public ModelRenderer finBottom;

    public TealArrowfishModel() {
        this.textureWidth = 32;
        this.textureHeight = 32;
        this.nose = new ModelRenderer(this, 0, 0);
        this.nose.setRotationPoint(0.0F, -0.5F, -4.0F);
        this.nose.addBox(-0.5F, -0.5F, -4.0F, 1.0F, 1.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.finLeft = new ModelRenderer(this, 0, 0);
        this.finLeft.setRotationPoint(1.0F, 1.0F, -1.5F);
        this.finLeft.addBox(0.0F, 0.0F, -0.5F, 1.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.finRight = new ModelRenderer(this, 0, 0);
        this.finRight.setRotationPoint(-1.0F, 1.0F, -1.5F);
        this.finRight.addBox(-1.0F, 0.0F, -0.5F, 1.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.finTop = new ModelRenderer(this, 0, 3);
        this.finTop.setRotationPoint(0.0F, -1.0F, 3.0F);
        this.finTop.addBox(0.0F, -2.0F, -1.0F, 0.0F, 2.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.finBottom = new ModelRenderer(this, 4, 3);
        this.finBottom.setRotationPoint(0.0F, 1.0F, 3.0F);
        this.finBottom.addBox(0.0F, 0.0F, -1.0F, 0.0F, 2.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.tail = new ModelRenderer(this, 0, 6);
        this.tail.setRotationPoint(0.0F, 0.0F, 4.0F);
        this.tail.addBox(0.0F, -2.0F, 0.0F, 0.0F, 3.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.body = new ModelRenderer(this, 0, 0);
        this.body.setRotationPoint(0.0F, 23.0F, 0.0F);
        this.body.setTextureOffset(3, 0).addBox(-1.0F, -1.0F, -4.0F, 2.0F, 2.0F, 8.0F, 0.0F, 0.0F, 0.0F);
        this.body.addChild(this.nose);
        this.body.addChild(this.finLeft);
        this.body.addChild(this.finRight);
        this.body.addChild(this.finTop);
        this.body.addChild(this.finBottom);
        this.body.addChild(this.tail);
    }

    @Override
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) { 
        ImmutableList.of(this.body).forEach((modelRenderer) -> { 
            modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        });
    }

    @Override
    public void setRotationAngles(T entityIn, float f, float f1, float ageInTicks, float netHeadYaw, float headPitch) {
        float degree = 1.0f;
        float speed = 3.0f;
        this.body.rotateAngleY = MathHelper.cos(f * speed * 0.4F) * degree * 0.5F * f1;
        this.tail.rotateAngleY = MathHelper.cos(f * speed * 0.4F) * degree * -0.5F * f1;
        this.finLeft.rotateAngleZ = MathHelper.cos(f * speed * 0.4F) * degree * -1.2F * f1;
        this.finRight.rotateAngleZ = MathHelper.cos(f * speed * 0.4F) * degree * 1.2F * f1;
    }
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
