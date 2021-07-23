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
public class WanderingSailorModel<T extends Entity> extends EntityModel<T> {
    public ModelRenderer body;
    public ModelRenderer head;
    public ModelRenderer armRight;
    public ModelRenderer armLeft;
    public ModelRenderer overlay;
    public ModelRenderer legLeft;
    public ModelRenderer legRight;
    public ModelRenderer tentacles;
    public ModelRenderer hat;
    public ModelRenderer arms;

    public WanderingSailorModel() {
        this.texWidth = 64;
        this.texHeight = 64;
        this.legLeft = new ModelRenderer(this, 0, 22);
        this.legLeft.setPos(2.0F, 5.0F, 0.0F);
        this.legLeft.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.body = new ModelRenderer(this, 14, 48);
        this.body.setPos(0.0F, 13.0F, 0.0F);
        this.body.addBox(-4.0F, -5.0F, -3.0F, 8.0F, 10.0F, 6.0F, 0.0F, 0.0F, 0.0F);
        this.hat = new ModelRenderer(this, 32, 0);
        this.hat.setPos(0.0F, -5.0F, 0.0F);
        this.hat.addBox(-4.0F, -5.0F, -4.0F, 8.0F, 10.0F, 8.0F, 0.5F, 0.5F, 0.5F);
        this.head = new ModelRenderer(this, 0, 0);
        this.head.setPos(0.0F, -5.0F, 0.0F);
        this.head.addBox(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F, 0.0F, 0.0F, 0.0F);
        this.arms = new ModelRenderer(this, 40, 23);
        this.arms.setPos(4.0F, 4.0F, -0.5F);
        this.arms.addBox(-4.0F, -2.0F, -2.0F, 8.0F, 5.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(arms, 1.5707963267948966F, 0.0F, 0.0F);
        this.armRight = new ModelRenderer(this, 0, 35);
        this.armRight.mirror = true;
        this.armRight.setPos(-4.0F, -2.0F, 0.0F);
        this.armRight.addBox(-4.0F, -2.0F, -2.5F, 4.0F, 8.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(armRight, -0.9599310885968813F, 0.0F, 0.0F);
        this.legRight = new ModelRenderer(this, 0, 22);
        this.legRight.setPos(-2.0F, 5.0F, 0.0F);
        this.legRight.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.tentacles = new ModelRenderer(this, 24, 2);
        this.tentacles.setPos(0.0F, -2.0F, -4.0F);
        this.tentacles.addBox(-2.0F, -1.0F, -2.0F, 4.0F, 4.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.armLeft = new ModelRenderer(this, 0, 35);
        this.armLeft.setPos(4.0F, -2.0F, 0.0F);
        this.armLeft.addBox(0.0F, -2.0F, -2.5F, 4.0F, 8.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(armLeft, -0.9599310885968813F, 0.0F, 0.0F);
        this.overlay = new ModelRenderer(this, 18, 26);
        this.overlay.mirror = true;
        this.overlay.setPos(0.0F, 0.0F, 0.0F);
        this.overlay.addBox(-4.0F, -5.0F, -3.0F, 8.0F, 15.0F, 6.0F, 0.25F, 0.25F, 0.25F);
        this.body.addChild(this.legLeft);
        this.head.addChild(this.hat);
        this.body.addChild(this.head);
        this.armRight.addChild(this.arms);
        this.body.addChild(this.armRight);
        this.body.addChild(this.legRight);
        this.head.addChild(this.tentacles);
        this.body.addChild(this.armLeft);
        this.body.addChild(this.overlay);
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
        this.head.xRot = headPitch * ((float)Math.PI / 180F);
        this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
        this.head.xRot += MathHelper.cos(limbSwing * speed * 0.4F) * degree * 0.2F * limbSwingAmount;
        this.head.yRot = MathHelper.cos(limbSwing * speed * 0.2F) * degree * 0.2F * limbSwingAmount;
        this.legLeft.xRot = MathHelper.cos(limbSwing * speed * 0.4F) * degree * 0.7F * limbSwingAmount;
        this.legRight.xRot = MathHelper.cos(limbSwing * speed * 0.4F) * degree * -0.7F * limbSwingAmount;
        this.tentacles.xRot = MathHelper.cos(-1.0F + limbSwing * speed * 0.4F) * degree * 0.2F * limbSwingAmount + 0.1F;
        this.tentacles.yRot = MathHelper.cos(-1.0F + limbSwing * speed * 0.2F) * degree * 0.2F * limbSwingAmount;
        this.armRight.y = MathHelper.cos(-1.0F + limbSwing * speed * 0.4F) * degree * 0.25F * limbSwingAmount - 2.0F;
        this.armLeft.y = MathHelper.cos(-1.0F + limbSwing * speed * 0.4F) * degree * 0.25F * limbSwingAmount - 2.0F;
        this.body.zRot = MathHelper.cos(limbSwing * speed * 0.4F) * degree * 0.05F * limbSwingAmount;
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}
