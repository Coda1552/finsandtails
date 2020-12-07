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
public class OrnateBugfishModel<T extends Entity> extends EntityModel<T> {
    public ModelRenderer body;
    public ModelRenderer tailBase;
    public ModelRenderer finLeft;
    public ModelRenderer finRight;
    public ModelRenderer beakLeftTop;
    public ModelRenderer beakRightTop;
    public ModelRenderer tail;
    public ModelRenderer finBack;
    public ModelRenderer finTop;
    public ModelRenderer beakLeftBottom;
    public ModelRenderer beakRightBottom;

    public OrnateBugfishModel() {
        this.textureWidth = 36;
        this.textureHeight = 24;
        this.finRight = new ModelRenderer(this, 0, -3);
        this.finRight.setRotationPoint(-3.0F, 2.0F, 1.0F);
        this.finRight.addBox(0.0F, 0.0F, -1.0F, 0.0F, 3.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(finRight, 0.0F, 0.0F, 0.4363323129985824F);
        this.tail = new ModelRenderer(this, 13, 9);
        this.tail.setRotationPoint(0.0F, 0.0F, 7.0F);
        this.tail.addBox(0.0F, -3.5F, 0.0F, 0.0F, 7.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.finBack = new ModelRenderer(this, 0, -1);
        this.finBack.setRotationPoint(0.0F, 1.5F, 0.0F);
        this.finBack.addBox(0.0F, 0.0F, 0.0F, 0.0F, 2.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.body = new ModelRenderer(this, 0, 0);
        this.body.setRotationPoint(0.0F, 21.0F, 0.0F);
        this.body.addBox(-3.0F, -3.0F, -4.0F, 6.0F, 6.0F, 8.0F, 0.0F, 0.0F, 0.0F);
        this.finTop = new ModelRenderer(this, 20, -5);
        this.finTop.setRotationPoint(0.0F, -1.5F, 0.0F);
        this.finTop.addBox(0.0F, -3.0F, -3.0F, 0.0F, 3.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.beakRightBottom = new ModelRenderer(this, 1, 19);
        this.beakRightBottom.mirror = true;
        this.beakRightBottom.setRotationPoint(0.0F, 1.5F, -1.5F);
        this.beakRightBottom.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.finLeft = new ModelRenderer(this, 0, -3);
        this.finLeft.setRotationPoint(3.0F, 2.0F, 1.0F);
        this.finLeft.addBox(0.0F, 0.0F, -1.0F, 0.0F, 3.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(finLeft, 0.0F, 0.0F, -0.4363323129985824F);
        this.beakLeftTop = new ModelRenderer(this, 26, 3);
        this.beakLeftTop.mirror = true;
        this.beakLeftTop.setRotationPoint(0.0F, -0.5F, -4.0F);
        this.beakLeftTop.addBox(-1.5F, -1.5F, -2.0F, 2.0F, 3.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.beakLeftBottom = new ModelRenderer(this, 7, 19);
        this.beakLeftBottom.mirror = true;
        this.beakLeftBottom.setRotationPoint(0.0F, 1.5F, -1.5F);
        this.beakLeftBottom.addBox(-0.5F, 0.0F, -0.5F, 1.0F, 3.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.tailBase = new ModelRenderer(this, 18, 14);
        this.tailBase.setRotationPoint(0.0F, -0.5F, 4.0F);
        this.tailBase.addBox(-1.0F, -1.5F, 0.0F, 2.0F, 3.0F, 7.0F, 0.0F, 0.0F, 0.0F);
        this.beakRightTop = new ModelRenderer(this, 0, 14);
        this.beakRightTop.mirror = true;
        this.beakRightTop.setRotationPoint(0.0F, -0.5F, -4.0F);
        this.beakRightTop.addBox(-0.5F, -1.5F, -2.0F, 2.0F, 3.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.body.addChild(this.finRight);
        this.tailBase.addChild(this.tail);
        this.tailBase.addChild(this.finBack);
        this.tailBase.addChild(this.finTop);
        this.beakRightTop.addChild(this.beakRightBottom);
        this.body.addChild(this.finLeft);
        this.body.addChild(this.beakLeftTop);
        this.beakLeftTop.addChild(this.beakLeftBottom);
        this.body.addChild(this.tailBase);
        this.body.addChild(this.beakRightTop);
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
        this.tailBase.rotateAngleY = MathHelper.cos(f * speed * 0.4F) * degree * -0.5F * f1;
        this.beakLeftTop.rotateAngleY = MathHelper.cos(f * speed * 0.4F) * degree * -1.5F * f1 + 0.2f;
        this.beakRightTop.rotateAngleY = MathHelper.cos(f * speed * 0.4F) * degree * 1.5F * f1 - 0.2f;
        this.finLeft.rotateAngleZ = MathHelper.cos(f * speed * 0.4F) * degree * -1.2F * f1 - 0.3f;
        this.finRight.rotateAngleZ = MathHelper.cos(f * speed * 0.4F) * degree * 1.2F * f1 + 0.3f;
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
