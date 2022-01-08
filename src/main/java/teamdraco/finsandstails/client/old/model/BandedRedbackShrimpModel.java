package teamdraco.finsandstails.client.old.model;

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
public class BandedRedbackShrimpModel<T extends Entity> extends EntityModel<T> {
    public ModelRenderer body;
    public ModelRenderer rostrum;
    public ModelRenderer tail;
    public ModelRenderer antennaright;
    public ModelRenderer antennaleft;
    public ModelRenderer legs1;
    public ModelRenderer legs2;
    public ModelRenderer legs3;
    public ModelRenderer legs4;
    public ModelRenderer finRight;
    public ModelRenderer finLeft;
    public ModelRenderer tailfan;

    public BandedRedbackShrimpModel() {
        this.texWidth = 34;
        this.texHeight = 22;
        this.rostrum = new ModelRenderer(this, 0, 9);
        this.rostrum.setPos(0.0F, -1.0F, -2.5F);
        this.rostrum.addBox(-0.5F, -0.5F, -2.0F, 1.0F, 1.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.antennaright = new ModelRenderer(this, 0, 7);
        this.antennaright.setPos(-0.5F, -1.0F, -2.5F);
        this.antennaright.addBox(0.0F, -3.5F, -6.0F, 0.0F, 6.0F, 6.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(antennaright, 0.0F, 0.17453292519943295F, 0.0F);
        this.finRight = new ModelRenderer(this, 7, 9);
        this.finRight.mirror = true;
        this.finRight.setPos(1.5F, -0.5F, 0.0F);
        this.finRight.addBox(0.0F, -0.5F, -1.5F, 3.0F, 0.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.legs1 = new ModelRenderer(this, 26, 0);
        this.legs1.setPos(0.0F, 1.5F, -1.0F);
        this.legs1.addBox(-1.5F, 0.0F, 0.0F, 3.0F, 2.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.tail = new ModelRenderer(this, 17, 0);
        this.tail.setPos(0.0F, -0.5F, 2.5F);
        this.tail.addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.legs2 = new ModelRenderer(this, 0, 0);
        this.legs2.mirror = true;
        this.legs2.setPos(0.0F, 1.5F, 0.0F);
        this.legs2.addBox(-1.5F, 0.0F, 0.0F, 3.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.legs4 = new ModelRenderer(this, 0, 0);
        this.legs4.mirror = true;
        this.legs4.setPos(0.0F, 1.5F, 2.0F);
        this.legs4.addBox(-1.5F, 0.0F, 0.0F, 3.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.tailfan = new ModelRenderer(this, 9, 0);
        this.tailfan.setPos(0.0F, 0.0F, 4.0F);
        this.tailfan.addBox(-2.0F, 0.0F, 0.0F, 4.0F, 0.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.legs3 = new ModelRenderer(this, 0, 0);
        this.legs3.mirror = true;
        this.legs3.setPos(0.0F, 1.5F, 1.0F);
        this.legs3.addBox(-1.5F, 0.0F, 0.0F, 3.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.antennaleft = new ModelRenderer(this, 0, 7);
        this.antennaleft.mirror = true;
        this.antennaleft.setPos(0.5F, -1.0F, -2.5F);
        this.antennaleft.addBox(0.0F, -3.5F, -6.0F, 0.0F, 6.0F, 6.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(antennaleft, 0.0F, -0.17453292519943295F, 0.0F);
        this.finLeft = new ModelRenderer(this, 7, 9);
        this.finLeft.setPos(-1.5F, -0.5F, 0.0F);
        this.finLeft.addBox(-3.0F, -0.5F, -1.5F, 3.0F, 0.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.body = new ModelRenderer(this, 1, 0);
        this.body.setPos(0.0F, 21.5F, 0.0F);
        this.body.addBox(-1.5F, -1.5F, -2.5F, 3.0F, 3.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.body.addChild(this.rostrum);
        this.body.addChild(this.antennaright);
        this.body.addChild(this.finRight);
        this.body.addChild(this.legs1);
        this.body.addChild(this.tail);
        this.body.addChild(this.legs2);
        this.body.addChild(this.legs4);
        this.tail.addChild(this.tailfan);
        this.body.addChild(this.legs3);
        this.body.addChild(this.antennaleft);
        this.body.addChild(this.finLeft);
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) { 
        ImmutableList.of(this.body).forEach((modelRenderer) -> { 
            modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        });
    }

    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float speed = 1.0f;
        float degree = 1.0f;
        limbSwing = ageInTicks;
        limbSwingAmount = 0.25F;
        if (entityIn.isInWater()) {
            this.body.xRot = headPitch * (((float)Math.PI / 180F) / 2);
            this.body.yRot = netHeadYaw * (((float)Math.PI / 180F) / 2);
            this.body.xRot += MathHelper.cos(limbSwing * speed * 0.25F) * degree * 0.25F * limbSwingAmount;
        }
        else {
            this.body.xRot      = MathHelper.cos(limbSwing * speed * 0.25F) * degree * 0.25F * limbSwingAmount;
        }
        this.legs4.xRot = MathHelper.cos(limbSwing * speed * 1.0F) * degree * 2.0F * limbSwingAmount;
        this.legs3.xRot = MathHelper.cos(1.5F + limbSwing * speed * 1.0F) * degree * 2.0F * limbSwingAmount;
        this.legs2.xRot = MathHelper.cos(3.0F + limbSwing * speed * 1.0F) * degree * 2.0F * limbSwingAmount;
        this.legs1.xRot = MathHelper.cos(limbSwing * speed * 0.5F) * degree * 2.0F * limbSwingAmount;
        this.tail.xRot = MathHelper.cos(-1.0F + limbSwing * speed * 0.25F) * degree * 0.5F * limbSwingAmount;
        this.tailfan.xRot = MathHelper.cos(-1.0F + limbSwing * speed * 0.25F) * degree * 0.75F * limbSwingAmount;
        this.antennaleft.xRot = MathHelper.cos(limbSwing * speed * 0.2F) * degree * 1.0F * limbSwingAmount;
        this.antennaright.xRot = MathHelper.cos(1.0F + limbSwing * speed * 0.2F) * degree * 1.0F * limbSwingAmount;
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}
