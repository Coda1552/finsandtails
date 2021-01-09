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

    public BullCrabModel() {
        this.textureWidth = 64;
        this.textureHeight = 22;
        this.body = new ModelRenderer(this, 0, 0);
        this.body.setRotationPoint(0.0F, 20.5F, 0.0F);
        this.body.addBox(-3.0F, -2.0F, -3.0F, 6.0F, 4.0F, 6.0F, 0.0F, 0.0F, 0.0F);
        this.rightclaw = new ModelRenderer(this, 0, 10);
        this.rightclaw.setRotationPoint(-3.0F, 1.5F, -2.0F);
        this.rightclaw.addBox(-2.0F, -3.0F, -4.0F, 3.0F, 4.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(rightclaw, 0.0F, 0.17453292519943295F, 0.0F);
        this.leftclaw = new ModelRenderer(this, 0, 10);
        this.leftclaw.mirror = true;
        this.leftclaw.setRotationPoint(3.0F, 1.5F, -2.0F);
        this.leftclaw.addBox(-1.0F, -3.0F, -4.0F, 3.0F, 4.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(leftclaw, 0.0F, -0.17453292519943295F, 0.0F);
        this.leftleg1 = new ModelRenderer(this, 0, 0);
        this.leftleg1.mirror = true;
        this.leftleg1.setRotationPoint(3.0F, 2.0F, -1.5F);
        this.leftleg1.addBox(0.0F, 0.0F, -0.5F, 2.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(leftleg1, 0.0F, 0.0F, 1.0471975511965976F);
        this.lefthorn = new ModelRenderer(this, 14, 10);
        this.lefthorn.setRotationPoint(1.5F, -1.5F, -1.5F);
        this.lefthorn.addBox(-0.5F, -1.0F, -3.0F, 1.0F, 1.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.rightleg1 = new ModelRenderer(this, 0, 0);
        this.rightleg1.setRotationPoint(-3.0F, 2.0F, -1.5F);
        this.rightleg1.addBox(-2.0F, 0.0F, -0.5F, 2.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(rightleg1, 0.0F, 0.0F, -1.0471975511965976F);
        this.leftleg3 = new ModelRenderer(this, 0, 0);
        this.leftleg3.mirror = true;
        this.leftleg3.setRotationPoint(3.0F, 2.0F, 0.5F);
        this.leftleg3.addBox(0.0F, 0.0F, 0.0F, 2.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(leftleg3, 0.0F, -0.3490658503988659F, 1.0471975511965976F);
        this.rightleg3 = new ModelRenderer(this, 0, 0);
        this.rightleg3.setRotationPoint(-3.0F, 2.0F, 0.5F);
        this.rightleg3.addBox(-2.0F, 0.0F, 0.0F, 2.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(rightleg3, 0.0F, 0.3490658503988659F, -1.0471975511965976F);
        this.rightleg2 = new ModelRenderer(this, 0, 0);
        this.rightleg2.setRotationPoint(-3.0F, 2.0F, 0.0F);
        this.rightleg2.addBox(-2.0F, 0.0F, -0.5F, 2.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(rightleg2, 0.0F, 0.0F, -1.0471975511965976F);
        this.leftleg2 = new ModelRenderer(this, 0, 0);
        this.leftleg2.mirror = true;
        this.leftleg2.setRotationPoint(3.0F, 2.0F, 0.0F);
        this.leftleg2.addBox(0.0F, 0.0F, -0.5F, 2.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(leftleg2, 0.0F, 0.0F, 1.0471975511965976F);
        this.righthorn = new ModelRenderer(this, 14, 10);
        this.righthorn.setRotationPoint(-1.5F, -1.5F, -1.5F);
        this.righthorn.addBox(-0.5F, -1.0F, -3.0F, 1.0F, 1.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.body.addChild(this.rightclaw);
        this.body.addChild(this.leftclaw);
        this.body.addChild(this.leftleg1);
        this.body.addChild(this.lefthorn);
        this.body.addChild(this.rightleg1);
        this.body.addChild(this.leftleg3);
        this.body.addChild(this.rightleg3);
        this.body.addChild(this.rightleg2);
        this.body.addChild(this.leftleg2);
        this.body.addChild(this.righthorn);
    }

    @Override
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) { 
        ImmutableList.of(this.body).forEach((modelRenderer) -> { 
            modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        });
    }

    @Override
    public void setRotationAngles(T entityIn, float f, float f1, float ageInTicks, float netHeadYaw, float headPitch) {
        float speed = 2.0f;
        float degree = 1.0f;
        this.rightclaw.rotateAngleX = MathHelper.cos(f * speed * 0.4F) * degree * -0.4F * f1;
        this.rightclaw.rotateAngleY = MathHelper.cos(f * speed * 0.4F) * degree * -0.4F * f1;
        this.leftclaw.rotateAngleX = MathHelper.cos(f * speed * 0.4F) * degree * 0.4F * f1;
        this.leftclaw.rotateAngleY = MathHelper.cos(f * speed * 0.4F) * degree * -0.4F * f1;
        this.rightleg1.rotateAngleY = MathHelper.cos(f * speed * 0.4F) * degree * 0.8F * f1;
        this.rightleg2.rotateAngleY = MathHelper.cos(f * speed * 0.4F) * degree * -0.8F * f1;
        this.rightleg3.rotateAngleY = MathHelper.cos(0.5F + f * speed * 0.4F) * degree * 0.8F * f1 + 0.2F;
        this.leftleg1.rotateAngleY = MathHelper.cos(f * speed * 0.4F) * degree * -0.8F * f1;
        this.leftleg2.rotateAngleY = MathHelper.cos(f * speed * 0.4F) * degree * 0.8F * f1;
        this.leftleg3.rotateAngleY = MathHelper.cos(0.5F + f * speed * 0.4F) * degree * -0.8F * f1 - 0.2F;
        this.body.rotateAngleY = MathHelper.cos(f * speed * 0.4F) * degree * 0.1F * f1;
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
