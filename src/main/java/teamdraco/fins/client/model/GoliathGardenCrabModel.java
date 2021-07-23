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
import teamdraco.fins.common.entities.GoliathGardenCrabEntity;

@OnlyIn(Dist.CLIENT)
public class GoliathGardenCrabModel<T extends Entity> extends EntityModel<GoliathGardenCrabEntity> {
    public ModelRenderer body;
    public ModelRenderer LeftClaw;
    public ModelRenderer RightClaw;
    public ModelRenderer LeftForeLeg;
    public ModelRenderer LeftMidLeg;
    public ModelRenderer LeftBackLeg;
    public ModelRenderer RightForeLeg;
    public ModelRenderer RightMidLeg;
    public ModelRenderer RightBackLeg;
    public ModelRenderer RightEye;
    public ModelRenderer LeftEye;
    public ModelRenderer plant0;
    public ModelRenderer plant2;
    public ModelRenderer plant4;
    public ModelRenderer plant6;
    public ModelRenderer plant8;
    public ModelRenderer plant10;
    public ModelRenderer plant12;
    public ModelRenderer plant16;
    public ModelRenderer LeftClawBottom;
    public ModelRenderer RightClawBottom;
    public ModelRenderer plant14;
    public ModelRenderer plant15;
    public ModelRenderer plant1;
    public ModelRenderer plant3;
    public ModelRenderer plant5;
    public ModelRenderer plant7;
    public ModelRenderer plant9;
    public ModelRenderer plant11;
    public ModelRenderer plant13;
    public ModelRenderer plant17;

    public GoliathGardenCrabModel() {
        this.texWidth = 336;
        this.texHeight = 144;
        this.body = new ModelRenderer(this, 0, 0);
        this.body.setPos(0.0F, -8.0F, 0.0F);
        this.body.addBox(-36.0F, -24.0F, -32.0F, 72.0F, 48.0F, 64.0F, 0.0F, 0.0F, 0.0F);
        this.LeftForeLeg = new ModelRenderer(this, 0, 0);
        this.LeftForeLeg.mirror = true;
        this.LeftForeLeg.setPos(36.0F, 0.0F, -6.0F);
        this.LeftForeLeg.addBox(0.0F, 0.0F, -5.0F, 10.0F, 32.0F, 10.0F, 0.0F, 0.0F, 0.0F);
        this.plant14 = new ModelRenderer(this, 240, 112);
        this.plant14.setPos(4.0F, -15.0F, -11.0F);
        this.plant14.addBox(-8.0F, -8.0F, 0.0F, 16.0F, 16.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(plant14, 0.0F, -0.7853981633974483F, 0.0F);
        this.plant7 = new ModelRenderer(this, 240, 128);
        this.plant7.setPos(0.0F, 0.0F, 0.0F);
        this.plant7.addBox(-8.0F, -8.0F, 0.0F, 16.0F, 16.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(plant7, 0.0F, 1.5707963267948966F, 0.0F);
        this.plant2 = new ModelRenderer(this, 208, 112);
        this.plant2.setPos(26.0F, -32.0F, -17.0F);
        this.plant2.addBox(-8.0F, -8.0F, 0.0F, 16.0F, 16.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(plant2, 0.0F, -0.7853981633974483F, 0.0F);
        this.RightClawBottom = new ModelRenderer(this, 0, 112);
        this.RightClawBottom.setPos(0.0F, 0.0F, 0.0F);
        this.RightClawBottom.addBox(-4.0F, 17.0F, -18.0F, 34.0F, 2.0F, 24.0F, 0.0F, 0.0F, 0.0F);
        this.plant11 = new ModelRenderer(this, 240, 128);
        this.plant11.setPos(0.0F, 0.0F, 0.0F);
        this.plant11.addBox(-8.0F, -8.0F, 0.0F, 16.0F, 16.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(plant11, 0.0F, 1.5707963267948966F, 0.0F);
        this.LeftBackLeg = new ModelRenderer(this, 0, 0);
        this.LeftBackLeg.mirror = true;
        this.LeftBackLeg.setPos(36.0F, 0.0F, 22.0F);
        this.LeftBackLeg.addBox(0.0F, 0.0F, -5.0F, 10.0F, 32.0F, 10.0F, 0.0F, 0.0F, 0.0F);
        this.plant9 = new ModelRenderer(this, 240, 128);
        this.plant9.setPos(0.0F, 0.0F, 0.0F);
        this.plant9.addBox(-8.0F, -8.0F, 0.0F, 16.0F, 16.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(plant9, 0.0F, 1.5707963267948966F, 0.0F);
        this.plant1 = new ModelRenderer(this, 208, 128);
        this.plant1.setPos(0.0F, 0.0F, 0.0F);
        this.plant1.addBox(-8.0F, -8.0F, 0.0F, 16.0F, 16.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(plant1, 0.0F, 1.5707963267948966F, 0.0F);
        this.RightClaw = new ModelRenderer(this, 208, 0);
        this.RightClaw.setPos(-36.0F, 0.0F, -28.0F);
        this.RightClaw.addBox(-4.0F, -7.0F, -18.0F, 34.0F, 24.0F, 24.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(RightClaw, 0.0F, 0.17453292519943295F, 0.17453292519943295F);
        this.plant5 = new ModelRenderer(this, 272, 112);
        this.plant5.setPos(0.0F, 0.0F, 0.0F);
        this.plant5.addBox(-8.0F, -24.0F, 0.0F, 16.0F, 32.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(plant5, 0.0F, 1.5707963267948966F, 0.0F);
        this.plant12 = new ModelRenderer(this, 240, 128);
        this.plant12.setPos(6.0F, -32.0F, 22.0F);
        this.plant12.addBox(-8.0F, -8.0F, 0.0F, 16.0F, 16.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(plant12, 0.0F, -0.7853981633974483F, 0.0F);
        this.plant17 = new ModelRenderer(this, 272, 112);
        this.plant17.setPos(0.0F, 0.0F, 0.0F);
        this.plant17.addBox(-8.0F, -24.0F, 0.0F, 16.0F, 32.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(plant17, 0.0F, 1.5707963267948966F, 0.0F);
        this.LeftClaw = new ModelRenderer(this, 208, 0);
        this.LeftClaw.mirror = true;
        this.LeftClaw.setPos(36.0F, 0.0F, -28.0F);
        this.LeftClaw.addBox(-30.0F, -7.0F, -18.0F, 34.0F, 24.0F, 24.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(LeftClaw, 0.0F, -0.17453292519943295F, -0.17453292519943295F);
        this.LeftMidLeg = new ModelRenderer(this, 0, 0);
        this.LeftMidLeg.mirror = true;
        this.LeftMidLeg.setPos(36.0F, 0.0F, 8.0F);
        this.LeftMidLeg.addBox(0.0F, 0.0F, -5.0F, 10.0F, 32.0F, 10.0F, 0.0F, 0.0F, 0.0F);
        this.RightBackLeg = new ModelRenderer(this, 0, 0);
        this.RightBackLeg.setPos(-36.0F, 0.0F, 22.0F);
        this.RightBackLeg.addBox(-10.0F, 0.0F, -5.0F, 10.0F, 32.0F, 10.0F, 0.0F, 0.0F, 0.0F);
        this.plant15 = new ModelRenderer(this, 240, 112);
        this.plant15.setPos(0.0F, 0.0F, 0.0F);
        this.plant15.addBox(-8.0F, -8.0F, 0.0F, 16.0F, 16.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(plant15, 0.0F, 1.5707963267948966F, 0.0F);
        this.plant3 = new ModelRenderer(this, 208, 112);
        this.plant3.setPos(0.0F, 0.0F, 0.0F);
        this.plant3.addBox(-8.0F, -8.0F, 0.0F, 16.0F, 16.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(plant3, 0.0F, 1.5707963267948966F, 0.0F);
        this.plant10 = new ModelRenderer(this, 240, 128);
        this.plant10.setPos(-27.0F, -32.0F, 0.0F);
        this.plant10.addBox(-8.0F, -8.0F, 0.0F, 16.0F, 16.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(plant10, 0.0F, -0.7853981633974483F, 0.0F);
        this.plant13 = new ModelRenderer(this, 240, 128);
        this.plant13.setPos(0.0F, 0.0F, 0.0F);
        this.plant13.addBox(-8.0F, -8.0F, 0.0F, 16.0F, 16.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(plant13, 0.0F, 1.5707963267948966F, 0.0F);
        this.LeftEye = new ModelRenderer(this, 52, 0);
        this.LeftEye.setPos(-8.0F, -10.0F, -32.5F);
        this.LeftEye.addBox(-1.5F, -12.0F, -1.5F, 3.0F, 12.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(LeftEye, 0.7853981633974483F, 0.445058959258554F, 0.0F);
        this.plant6 = new ModelRenderer(this, 240, 128);
        this.plant6.setPos(-2.0F, -32.0F, -14.0F);
        this.plant6.addBox(-8.0F, -8.0F, 0.0F, 16.0F, 16.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(plant6, 0.0F, -0.7853981633974483F, 0.0F);
        this.LeftClawBottom = new ModelRenderer(this, 0, 112);
        this.LeftClawBottom.mirror = true;
        this.LeftClawBottom.setPos(0.0F, 0.0F, 0.0F);
        this.LeftClawBottom.addBox(-30.0F, 17.0F, -18.0F, 34.0F, 2.0F, 24.0F, 0.0F, 0.0F, 0.0F);
        this.plant0 = new ModelRenderer(this, 208, 128);
        this.plant0.setPos(-13.0F, -32.0F, 14.0F);
        this.plant0.addBox(-8.0F, -8.0F, 0.0F, 16.0F, 16.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(plant0, 0.0F, -0.7853981633974483F, 0.0F);
        this.RightMidLeg = new ModelRenderer(this, 0, 0);
        this.RightMidLeg.setPos(-36.0F, 0.0F, 8.0F);
        this.RightMidLeg.addBox(-10.0F, 0.0F, -5.0F, 10.0F, 32.0F, 10.0F, 0.0F, 0.0F, 0.0F);
        this.RightForeLeg = new ModelRenderer(this, 0, 0);
        this.RightForeLeg.setPos(-36.0F, 0.0F, -6.0F);
        this.RightForeLeg.addBox(-10.0F, 0.0F, -5.0F, 10.0F, 32.0F, 10.0F, 0.0F, 0.0F, 0.0F);
        this.plant16 = new ModelRenderer(this, 272, 112);
        this.plant16.setPos(-31.0F, -32.0F, -27.0F);
        this.plant16.addBox(-8.0F, -24.0F, 0.0F, 16.0F, 32.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(plant16, 0.0F, -0.7853981633974483F, 0.0F);
        this.RightEye = new ModelRenderer(this, 52, 0);
        this.RightEye.mirror = true;
        this.RightEye.setPos(8.0F, -10.0F, -32.5F);
        this.RightEye.addBox(-1.5F, -12.0F, -1.5F, 3.0F, 12.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(RightEye, 0.7853981633974483F, -0.39269908169872414F, 0.0F);
        this.plant4 = new ModelRenderer(this, 272, 112);
        this.plant4.setPos(9.0F, -32.0F, 4.0F);
        this.plant4.addBox(-8.0F, -24.0F, 0.0F, 16.0F, 32.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(plant4, 0.0F, -0.7853981633974483F, 0.0F);
        this.plant8 = new ModelRenderer(this, 240, 128);
        this.plant8.setPos(18.0F, -32.0F, 3.0F);
        this.plant8.addBox(-8.0F, -8.0F, 0.0F, 16.0F, 16.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(plant8, 0.0F, -0.7853981633974483F, 0.0F);
        this.body.addChild(this.LeftForeLeg);
        this.RightClaw.addChild(this.plant14);
        this.plant6.addChild(this.plant7);
        this.body.addChild(this.plant2);
        this.RightClaw.addChild(this.RightClawBottom);
        this.plant10.addChild(this.plant11);
        this.body.addChild(this.LeftBackLeg);
        this.plant8.addChild(this.plant9);
        this.plant0.addChild(this.plant1);
        this.body.addChild(this.RightClaw);
        this.plant4.addChild(this.plant5);
        this.body.addChild(this.plant12);
        this.plant16.addChild(this.plant17);
        this.body.addChild(this.LeftClaw);
        this.body.addChild(this.LeftMidLeg);
        this.body.addChild(this.RightBackLeg);
        this.plant14.addChild(this.plant15);
        this.plant2.addChild(this.plant3);
        this.body.addChild(this.plant10);
        this.plant12.addChild(this.plant13);
        this.body.addChild(this.LeftEye);
        this.body.addChild(this.plant6);
        this.LeftClaw.addChild(this.LeftClawBottom);
        this.body.addChild(this.plant0);
        this.body.addChild(this.RightMidLeg);
        this.body.addChild(this.RightForeLeg);
        this.body.addChild(this.plant16);
        this.body.addChild(this.RightEye);
        this.body.addChild(this.plant4);
        this.body.addChild(this.plant8);
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        ImmutableList.of(this.body).forEach((modelRenderer) -> { 
            modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        });
    }

    @Override
    public void setupAnim(GoliathGardenCrabEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float speed = 2.5f;
        float degree = 0.5f;
        float legLimbSwingAmount = MathHelper.clamp(limbSwingAmount, -0.35F, 0.35F);
        this.body.zRot = MathHelper.cos(limbSwing * speed * 0.05F) * degree * 0.05F * limbSwingAmount;
        this.RightEye.xRot = MathHelper.cos(limbSwing * speed * 0.1F) * degree * 0.8F * limbSwingAmount + 0.8F;
        this.LeftEye.xRot = MathHelper.cos(-1.0f + limbSwing * speed * 0.1F) * degree * 0.8F * limbSwingAmount + 0.8F;
        this.LeftClaw.xRot = MathHelper.cos(limbSwing * speed * 0.1F) * degree * 0.35F * limbSwingAmount;
        this.RightClaw.xRot = MathHelper.cos(3.0F + limbSwing * speed * 0.1F) * degree * 0.35F * limbSwingAmount;
        this.LeftForeLeg.zRot = MathHelper.cos(limbSwing * speed * 0.2F) * degree * 1.4F * legLimbSwingAmount;
        this.LeftBackLeg.zRot = MathHelper.cos(1.5F + limbSwing * speed * 0.2F) * degree * 1.4F * legLimbSwingAmount;
        this.LeftMidLeg.zRot = MathHelper.cos(3.0F + limbSwing * speed * 0.2F) * degree * 1.4F * legLimbSwingAmount;
        this.RightForeLeg.zRot = MathHelper.cos(3.0F + limbSwing * speed * 0.2F) * degree * 1.4F * legLimbSwingAmount;
        this.RightBackLeg.zRot = MathHelper.cos(1.5F + limbSwing * speed * 0.2F) * degree * 1.4F * legLimbSwingAmount;
        this.RightMidLeg.zRot = MathHelper.cos(limbSwing * speed * 0.2F) * degree * 1.4F * legLimbSwingAmount;
        this.body.yRot = MathHelper.cos(3.0F + limbSwing * speed * 0.05F) * degree * 0.05F * limbSwingAmount;
        this.body.xRot = MathHelper.cos(limbSwing * speed * 0.05F) * degree * 0.05F * limbSwingAmount;
    }

    @Override
    public void prepareMobModel(GoliathGardenCrabEntity p_212843_1_, float p_212843_2_, float p_212843_3_, float p_212843_4_) {
        int i = p_212843_1_.getAttackAnimationTick();
        if (i > 0) {
            float speed = 1.0f;
            float degree = 1.0f;
            this.RightClaw.y = MathHelper.cos(p_212843_4_ * speed * 0.4F) * degree * 2.5F * 1.5F + 0.6F;
            this.RightClaw.yRot = MathHelper.cos(p_212843_4_ * speed * 0.4F) * degree * 0.2F * 1.5F + 0.6F;
            this.RightClaw.zRot = MathHelper.cos(p_212843_4_ * speed * 0.4F) * degree * 0.75F * 1.5F;
            this.body.xRot = MathHelper.cos(3.0F + p_212843_4_ * speed * 0.4F) * degree * 0.2F * 1.5F;
            this.LeftClaw.yRot = MathHelper.cos(p_212843_4_ * speed * 0.4F) * degree * 0.2F * 1.5F - 0.4F;
            this.LeftForeLeg.zRot = MathHelper.cos(p_212843_4_ * speed * 0.2F) * degree * 0.6F * 1.5F;
            this.LeftBackLeg.zRot = MathHelper.cos(1.5F + p_212843_4_ * speed * 0.2F) * degree * 0.6F * 1.5F;
            this.LeftMidLeg.zRot = MathHelper.cos(3.0F + p_212843_4_ * speed * 0.2F) * degree * 0.6F * 1.5F;
            this.RightForeLeg.zRot = MathHelper.cos(1.5F + p_212843_4_ * speed * 0.2F) * degree * 0.6F * 1.5F;
            this.RightBackLeg.zRot = MathHelper.cos(p_212843_4_ * speed * 0.2F) * degree * 0.6F * 1.5F;
            this.RightMidLeg.zRot = MathHelper.cos(3.0F + p_212843_4_ * speed * 0.2F) * degree * 0.6F * 1.5F;
            this.RightEye.xRot = MathHelper.cos(3.0F + p_212843_4_ * speed * 0.4F) * degree * 1.0F * 1.5F + 0.8F;
            this.LeftEye.xRot = MathHelper.cos(3.0F + p_212843_4_ * speed * 0.4F) * degree * 1.0F * 1.5F + 0.8F;
        }
    }

        public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}
