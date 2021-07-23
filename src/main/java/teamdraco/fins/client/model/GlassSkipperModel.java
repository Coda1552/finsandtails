package teamdraco.fins.client.model;

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
public class GlassSkipperModel<T extends Entity> extends EntityModel<T> {
    public ModelRenderer body;
    public ModelRenderer leftWing;
    public ModelRenderer rightWing;
    public ModelRenderer rightAntenna;
    public ModelRenderer leftAntenna;
    public ModelRenderer legsFront;
    public ModelRenderer legsMid;
    public ModelRenderer legsBack;

    public GlassSkipperModel() {
        this.texWidth = 48;
        this.texHeight = 32;
        this.body = new ModelRenderer(this, 0, 0);
        this.body.setPos(0.0F, 20.0F, 0.0F);
        this.body.addBox(-2.5F, -2.0F, -6.0F, 5.0F, 4.0F, 12.0F, 0.0F, 0.0F, 0.0F);
        this.leftWing = new ModelRenderer(this, 0, -5);
        this.leftWing.setPos(2.0F, -2.0F, 0.0F);
        this.leftWing.addBox(0.0F, -14.0F, -6.0F, 0.0F, 16.0F, 21.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(leftWing, 0.0F, 0.0F, 1.117010721276371F);
        this.rightAntenna = new ModelRenderer(this, 0, 0);
        this.rightAntenna.setPos(-1.5F, -1.5F, -6.0F);
        this.rightAntenna.addBox(0.0F, -1.5F, -5.0F, 0.0F, 4.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.legsFront = new ModelRenderer(this, 0, 0);
        this.legsFront.setPos(0.0F, 2.0F, -3.5F);
        this.legsFront.addBox(-2.5F, 0.0F, 0.0F, 5.0F, 2.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.legsBack = new ModelRenderer(this, 0, 2);
        this.legsBack.setPos(0.0F, 2.0F, 1.0F);
        this.legsBack.addBox(-2.0F, 0.0F, 0.0F, 4.0F, 2.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.rightWing = new ModelRenderer(this, 0, -5);
        this.rightWing.setPos(-2.0F, -2.0F, 0.0F);
        this.rightWing.addBox(0.0F, -14.0F, -6.0F, 0.0F, 16.0F, 21.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(rightWing, 0.0F, 0.0F, -1.117010721276371F);
        this.rightWing.mirror = true;
        this.leftAntenna = new ModelRenderer(this, 0, 0);
        this.leftAntenna.setPos(1.5F, -1.5F, -6.0F);
        this.leftAntenna.addBox(0.0F, -1.5F, -5.0F, 0.0F, 4.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.legsMid = new ModelRenderer(this, 0, 0);
        this.legsMid.setPos(0.0F, 2.0F, -1.5F);
        this.legsMid.addBox(-2.5F, 0.0F, 0.0F, 5.0F, 2.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.body.addChild(this.leftWing);
        this.body.addChild(this.rightAntenna);
        this.body.addChild(this.legsFront);
        this.body.addChild(this.legsBack);
        this.body.addChild(this.rightWing);
        this.body.addChild(this.leftAntenna);
        this.body.addChild(this.legsMid);
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
        this.body.xRot = MathHelper.cos(ageInTicks * speed * 0.4F) * degree * 0.4F * 0.35f;
        this.leftWing.zRot = MathHelper.cos(ageInTicks * speed * 1.2F) * degree * 4.4F * 0.35f + 1.0F;
        this.rightWing.zRot = MathHelper.cos(3.0F + ageInTicks * speed * 1.2F) * degree * 4.4F * 0.35f - 1.0F;
        this.legsFront.xRot = MathHelper.cos(ageInTicks * speed * 0.4F) * degree * 0.8F * 0.35f + 0.4F;
        this.legsMid.xRot = MathHelper.cos(3.0F + ageInTicks * speed * 0.4F) * degree * 0.8F * 0.35f + 0.4F;
        this.legsBack.xRot = MathHelper.cos(1.5F + ageInTicks * speed * 0.4F) * degree * 0.8F * 0.35f + 0.4F;
        this.rightAntenna.xRot = MathHelper.cos(1.5F + ageInTicks * speed * 0.4F) * degree * 0.8F * 0.35f;
        this.leftAntenna.xRot = MathHelper.cos(1.5F + ageInTicks * speed * 0.4F) * degree * 0.8F * 0.35f;
//        this.leftWing.xRot = MathHelper.cos(ageInTicks * speed * 0.4F) * degree * 0.8F * 0.35f;
//        this.rightWing.xRot = MathHelper.cos(ageInTicks * speed * 0.4F) * degree * 0.8F * 0.35f;
        this.body.zRot = MathHelper.cos(ageInTicks * speed * 0.2F) * degree * 0.4F * 0.35f;
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}
