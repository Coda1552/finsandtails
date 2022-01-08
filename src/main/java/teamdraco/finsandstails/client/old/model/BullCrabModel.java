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
public class BullCrabModel<T extends Entity> extends EntityModel<T> {
    public ModelRenderer body;
    public ModelRenderer rightclaw;
    public ModelRenderer leftclaw;
    public ModelRenderer rightleg1;
    public ModelRenderer rightleg2;
    public ModelRenderer leftleg1;
    public ModelRenderer leftleg2;
    public ModelRenderer righthorn;
    public ModelRenderer lefthorn;
    public ModelRenderer rightleg3;
    public ModelRenderer leftleg3;
    public ModelRenderer right_eye;
    public ModelRenderer left_eye;

    public BullCrabModel() {
        this.texWidth = 32;
        this.texHeight = 32;
        this.leftleg2 = new ModelRenderer(this, 0, 0);
        this.leftleg2.mirror = true;
        this.leftleg2.setPos(2.0F, 2.0F, 0.0F);
        this.leftleg2.addBox(0.0F, 0.0F, -0.5F, 2, 0, 1, 0.0F);
        this.setRotateAngle(leftleg2, 0.0F, 0.0F, 1.0471975511965976F);
        this.leftleg3 = new ModelRenderer(this, 0, 0);
        this.leftleg3.mirror = true;
        this.leftleg3.setPos(2.0F, 2.0F, 1.5F);
        this.leftleg3.addBox(0.0F, 0.0F, -0.5F, 2, 0, 1, 0.0F);
        this.setRotateAngle(leftleg3, 0.0F, 0.0F, 1.0471975511965976F);
        this.rightleg3 = new ModelRenderer(this, 0, 0);
        this.rightleg3.setPos(-2.0F, 2.0F, 1.5F);
        this.rightleg3.addBox(-2.0F, 0.0F, -0.5F, 2, 0, 1, 0.0F);
        this.setRotateAngle(rightleg3, 0.0F, 0.0F, -1.0471975511965976F);
        this.lefthorn = new ModelRenderer(this, 14, 10);
        this.lefthorn.setPos(1.5F, -1.5F, -1.5F);
        this.lefthorn.addBox(-0.5F, -1.0F, -3.0F, 1, 1, 3, 0.0F);
        this.setRotateAngle(lefthorn, -0.39269908169872414F, -0.39269908169872414F, 0.0F);
        this.leftleg1 = new ModelRenderer(this, 0, 0);
        this.leftleg1.mirror = true;
        this.leftleg1.setPos(2.0F, 2.0F, -1.5F);
        this.leftleg1.addBox(0.0F, 0.0F, -0.5F, 2, 0, 1, 0.0F);
        this.setRotateAngle(leftleg1, 0.0F, 0.0F, 1.0471975511965976F);
        this.right_eye = new ModelRenderer(this, 2, 3);
        this.right_eye.setPos(1.0F, 1.5F, -3.0F);
        this.right_eye.addBox(-0.5F, -3.0F, 0.0F, 1, 3, 0, 0.0F);
        this.setRotateAngle(right_eye, 0.39269908169872414F, 0.0F, 0.0F);
        this.rightleg1 = new ModelRenderer(this, 0, 0);
        this.rightleg1.setPos(-2.0F, 2.0F, -1.5F);
        this.rightleg1.addBox(-2.0F, 0.0F, -0.5F, 2, 0, 1, 0.0F);
        this.setRotateAngle(rightleg1, 0.0F, 0.0F, -1.0471975511965976F);
        this.rightleg2 = new ModelRenderer(this, 0, 0);
        this.rightleg2.setPos(-2.0F, 2.0F, 0.0F);
        this.rightleg2.addBox(-2.0F, 0.0F, -0.5F, 2, 0, 1, 0.0F);
        this.setRotateAngle(rightleg2, 0.0F, 0.0F, -1.0471975511965976F);
        this.rightclaw = new ModelRenderer(this, 0, 10);
        this.rightclaw.setPos(-3.0F, 1.5F, -2.0F);
        this.rightclaw.addBox(-2.0F, -3.0F, -4.0F, 3, 4, 4, 0.0F);
        this.setRotateAngle(rightclaw, 0.0F, 0.17453292519943295F, 0.0F);
        this.body = new ModelRenderer(this, 0, 0);
        this.body.setPos(0.0F, 20.5F, 0.0F);
        this.body.addBox(-3.0F, -2.0F, -3.0F, 6, 4, 6, 0.0F);
        this.leftclaw = new ModelRenderer(this, 0, 10);
        this.leftclaw.mirror = true;
        this.leftclaw.setPos(3.0F, 1.5F, -2.0F);
        this.leftclaw.addBox(-1.0F, -3.0F, -4.0F, 3, 4, 4, 0.0F);
        this.setRotateAngle(leftclaw, 0.0F, -0.17453292519943295F, 0.0F);
        this.left_eye = new ModelRenderer(this, 2, 3);
        this.left_eye.setPos(-1.0F, 1.5F, -3.0F);
        this.left_eye.addBox(-0.5F, -3.0F, 0.0F, 1, 3, 0, 0.0F);
        this.setRotateAngle(left_eye, 0.39269908169872414F, 0.0F, 0.0F);
        this.righthorn = new ModelRenderer(this, 14, 10);
        this.righthorn.setPos(-1.5F, -1.5F, -1.5F);
        this.righthorn.addBox(-0.5F, -1.0F, -3.0F, 1, 1, 3, 0.0F);
        this.setRotateAngle(righthorn, -0.39269908169872414F, 0.39269908169872414F, 0.0F);
        this.body.addChild(this.leftleg2);
        this.body.addChild(this.leftleg3);
        this.body.addChild(this.rightleg3);
        this.body.addChild(this.lefthorn);
        this.body.addChild(this.leftleg1);
        this.body.addChild(this.right_eye);
        this.body.addChild(this.rightleg1);
        this.body.addChild(this.rightleg2);
        this.body.addChild(this.rightclaw);
        this.body.addChild(this.leftclaw);
        this.body.addChild(this.left_eye);
        this.body.addChild(this.righthorn);
    }


    @Override
    public void renderToBuffer(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        ImmutableList.of(this.body).forEach((modelRenderer) -> {
            modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        });
    }

    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float speed = 2.0f;
        float degree = 1.0f;
        this.rightclaw.xRot = MathHelper.cos(limbSwing * speed * 0.4F) * degree * -0.4F * limbSwingAmount;
        this.rightclaw.yRot = MathHelper.cos(limbSwing * speed * 0.4F) * degree * -0.4F * limbSwingAmount;
        this.leftclaw.xRot = MathHelper.cos(limbSwing * speed * 0.4F) * degree * 0.4F * limbSwingAmount;
        this.leftclaw.yRot = MathHelper.cos(limbSwing * speed * 0.4F) * degree * -0.4F * limbSwingAmount;
        this.rightleg1.yRot = MathHelper.cos(limbSwing * speed * 0.4F) * degree * 0.8F * limbSwingAmount;
        this.rightleg2.yRot = MathHelper.cos(limbSwing * speed * 0.4F) * degree * -0.8F * limbSwingAmount;
        this.rightleg3.yRot = MathHelper.cos(0.5F + limbSwing * speed * 0.4F) * degree * 0.8F * limbSwingAmount + 0.2F;
        this.leftleg1.yRot = MathHelper.cos(limbSwing * speed * 0.4F) * degree * -0.8F * limbSwingAmount;
        this.leftleg2.yRot = MathHelper.cos(limbSwing * speed * 0.4F) * degree * 0.8F * limbSwingAmount;
        this.leftleg3.yRot = MathHelper.cos(0.5F + limbSwing * speed * 0.4F) * degree * -0.8F * limbSwingAmount - 0.2F;
        this.body.yRot = MathHelper.cos(limbSwing * speed * 0.4F) * degree * 0.1F * limbSwingAmount;
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}
