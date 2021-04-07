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
public class SpindlyGemCrabModel<T extends Entity> extends EntityModel<T> {
    public ModelRenderer body;
    public ModelRenderer tail;
    public ModelRenderer antemnas;
    public ModelRenderer left_leg_front;
    public ModelRenderer left_leg_mid;
    public ModelRenderer left_leg_back;
    public ModelRenderer right_leg_front;
    public ModelRenderer right_leg_mid;
    public ModelRenderer right_leg_back;
    public ModelRenderer right_pincher;
    public ModelRenderer left_pincher;

    public SpindlyGemCrabModel() {
        this.textureWidth = 32;
        this.textureHeight = 16;
        this.left_leg_mid = new ModelRenderer(this, 0, 0);
        this.left_leg_mid.setRotationPoint(1.5F, 1.0F, 0.0F);
        this.left_leg_mid.addBox(0.0F, 0.0F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(left_leg_mid, 0.0F, 0.0F, 0.3490658503988659F);
        this.right_leg_mid = new ModelRenderer(this, 0, 0);
        this.right_leg_mid.mirror = true;
        this.right_leg_mid.setRotationPoint(-1.5F, 1.0F, -1.0F);
        this.right_leg_mid.addBox(-2.0F, 0.0F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(right_leg_mid, 0.0F, -0.7853981633974483F, -0.3490658503988659F);
        this.tail = new ModelRenderer(this, 12, 0);
        this.tail.setRotationPoint(0.0F, -0.5F, 2.5F);
        this.tail.addBox(-2.0F, 0.0F, 0.0F, 4.0F, 1.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(tail, -0.6981317007977318F, 0.0F, 0.0F);
        this.antemnas = new ModelRenderer(this, 14, 6);
        this.antemnas.setRotationPoint(0.0F, 0.5F, -2.5F);
        this.antemnas.addBox(-1.0F, -1.5F, -3.0F, 2.0F, 2.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.left_pincher = new ModelRenderer(this, 0, 8);
        this.left_pincher.mirror = true;
        this.left_pincher.setRotationPoint(-1.5F, 0.5F, -2.5F);
        this.left_pincher.addBox(0.0F, 0.0F, -4.0F, 3.0F, 1.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(left_pincher, 0.0F, 0.7853981633974483F, 0.0F);
        this.body = new ModelRenderer(this, 1, 0);
        this.body.setRotationPoint(0.0F, 21.6F, 0.0F);
        this.body.addBox(-1.5F, -1.5F, -2.5F, 3.0F, 3.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.right_leg_front = new ModelRenderer(this, 0, 0);
        this.right_leg_front.mirror = true;
        this.right_leg_front.setRotationPoint(-1.5F, 1.0F, 0.0F);
        this.right_leg_front.addBox(-2.0F, 0.0F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(right_leg_front, -0.0F, -0.0F, -0.3490658503988659F);
        this.right_leg_back = new ModelRenderer(this, 0, 0);
        this.right_leg_back.mirror = true;
        this.right_leg_back.setRotationPoint(-1.5F, 1.0F, 1.0F);
        this.right_leg_back.addBox(-2.0F, 0.0F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(right_leg_back, 0.0F, 0.7853981633974483F, -0.3490658503988659F);
        this.left_leg_back = new ModelRenderer(this, 0, 0);
        this.left_leg_back.setRotationPoint(1.5F, 1.0F, 1.0F);
        this.left_leg_back.addBox(0.0F, 0.0F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(left_leg_back, 0.0F, -0.7853981633974483F, 0.3490658503988659F);
        this.right_pincher = new ModelRenderer(this, 0, 8);
        this.right_pincher.setRotationPoint(1.5F, 0.5F, -2.5F);
        this.right_pincher.addBox(-3.0F, 0.0F, -4.0F, 3.0F, 1.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(right_pincher, 0.0F, -0.7853981633974483F, 0.0F);
        this.left_leg_front = new ModelRenderer(this, 0, 0);
        this.left_leg_front.setRotationPoint(1.5F, 1.0F, -1.0F);
        this.left_leg_front.addBox(0.0F, 0.0F, -0.5F, 2.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(left_leg_front, 0.0F, 0.7853981633974483F, 0.3490658503988659F);
        this.body.addChild(this.left_leg_mid);
        this.body.addChild(this.right_leg_mid);
        this.body.addChild(this.tail);
        this.body.addChild(this.antemnas);
        this.body.addChild(this.left_pincher);
        this.body.addChild(this.right_leg_front);
        this.body.addChild(this.right_leg_back);
        this.body.addChild(this.left_leg_back);
        this.body.addChild(this.right_pincher);
        this.body.addChild(this.left_leg_front);
    }

    @Override
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) { 
        ImmutableList.of(this.body).forEach((modelRenderer) -> { 
            modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        });
    }

    @Override
    public void setRotationAngles(T entityIn, float f, float f1, float ageInTicks, float netHeadYaw, float headPitch) {
        float speed = 3.0f;
        float degree = 1.0f;
        this.body.rotateAngleY = MathHelper.cos(f * speed * 0.4F) * degree * 0.2F * f1;
        this.tail.rotateAngleX = MathHelper.cos(f * speed * 0.6F) * degree * 0.5F * f1 - 0.5F;
        this.left_pincher.rotateAngleY = MathHelper.cos(f * speed * 0.4F) * degree * 0.4F * f1 + 0.6F;
        this.left_leg_front.rotateAngleY = MathHelper.cos(f * speed * 0.4F) * degree * -1.2F * f1 + 0.6F;
        this.left_leg_mid.rotateAngleY = MathHelper.cos(f * speed * 0.4F) * degree * 1.2F * f1;
        this.right_leg_front.rotateAngleY = MathHelper.cos(f * speed * 0.4F) * degree * 1.2F * f1;
        this.left_leg_back.rotateAngleY = MathHelper.cos(f * speed * 0.4F) * degree * -1.2F * f1 - 0.5F;
        this.right_leg_back.rotateAngleY = MathHelper.cos(f * speed * 0.4F) * degree * -1.2F * f1 + 0.5F;
        this.right_pincher.rotateAngleY = MathHelper.cos(f * speed * 0.4F) * degree * -0.4F * f1 - 0.6F;
        this.right_leg_mid.rotateAngleY = MathHelper.cos(f * speed * 0.4F) * degree * -1.2F * f1 - 0.6F;
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
