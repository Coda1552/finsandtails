package mod.coda.fins.client.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import mod.coda.fins.entity.MudhorseEntity;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Collections;

@OnlyIn(Dist.CLIENT)
public class MudhorseModel<T extends Entity> extends AgeableModel<MudhorseEntity> {
    public ModelRenderer body;
    public ModelRenderer tail;
    public ModelRenderer armRight;
    public ModelRenderer armLeft;
    public ModelRenderer finBack;
    public ModelRenderer neck;
    public ModelRenderer tail2;
    public ModelRenderer finArmRight;
    public ModelRenderer finArmLeft;
    public ModelRenderer finNeck;
    public ModelRenderer head;
    public ModelRenderer snout;
    public ModelRenderer finLeft;
    public ModelRenderer finRight;
    public ModelRenderer tail3;

    public MudhorseModel() {
        this.textureWidth = 80;
        this.textureHeight = 112;
        this.finNeck = new ModelRenderer(this, 14, 68);
        this.finNeck.setRotationPoint(0.0F, -8.0F, 0.0F);
        this.finNeck.addBox(0.0F, -7.0F, 0.0F, 0.0F, 14.0F, 6.0F, 0.0F, 0.0F, 0.0F);
        this.armRight = new ModelRenderer(this, 0, 74);
        this.armRight.setRotationPoint(-5.0F, 0.0F, -4.0F);
        this.armRight.addBox(-2.0F, -4.0F, -2.0F, 3.0F, 22.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.snout = new ModelRenderer(this, 42, 0);
        this.snout.setRotationPoint(0.0F, 4.0F, -7.0F);
        this.snout.addBox(-2.0F, -1.5F, -12.0F, 4.0F, 3.0F, 12.0F, 0.0F, 0.0F, 0.0F);
        this.finLeft = new ModelRenderer(this, 0, 4);
        this.finLeft.setRotationPoint(4.5F, 1.5F, 3.0F);
        this.finLeft.addBox(0.0F, -4.0F, 0.0F, 0.0F, 10.0F, 8.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(finLeft, 0.0F, 0.3490658503988659F, 0.0F);
        this.finArmLeft = new ModelRenderer(this, 64, 64);
        this.finArmLeft.setRotationPoint(1.0F, 7.0F, 2.0F);
        this.finArmLeft.addBox(0.0F, -11.0F, 0.0F, 0.0F, 22.0F, 6.0F, 0.0F, 0.0F, 0.0F);
        this.tail3 = new ModelRenderer(this, 52, 53);
        this.tail3.setRotationPoint(0.0F, -4.0F, 8.0F);
        this.tail3.addBox(-3.0F, -3.0F, -4.0F, 6.0F, 3.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.finBack = new ModelRenderer(this, 0, 18);
        this.finBack.setRotationPoint(0.0F, -6.0F, 11.0F);
        this.finBack.addBox(0.0F, -8.0F, -12.0F, 0.0F, 12.0F, 16.0F, 0.0F, 0.0F, 0.0F);
        this.finRight = new ModelRenderer(this, 0, 4);
        this.finRight.setRotationPoint(-4.5F, 1.5F, 3.0F);
        this.finRight.addBox(0.0F, -4.0F, 0.0F, 0.0F, 10.0F, 8.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(finRight, 0.0F, -0.3490658503988659F, 0.0F);
        this.armLeft = new ModelRenderer(this, 0, 74);
        this.armLeft.setRotationPoint(5.0F, 0.0F, -4.0F);
        this.armLeft.addBox(-1.0F, -4.0F, -2.0F, 3.0F, 22.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.finArmRight = new ModelRenderer(this, 64, 64);
        this.finArmRight.mirror = true;
        this.finArmRight.setRotationPoint(-1.0F, 7.0F, 2.0F);
        this.finArmRight.addBox(0.0F, -11.0F, 0.0F, 0.0F, 22.0F, 6.0F, 0.0F, 0.0F, 0.0F);
        this.neck = new ModelRenderer(this, 0, 46);
        this.neck.setRotationPoint(0.0F, -5.0F, -10.0F);
        this.neck.addBox(-3.0F, -12.0F, -6.0F, 6.0F, 20.0F, 8.0F, 0.0F, 0.0F, 0.0F);
        this.head = new ModelRenderer(this, 32, 34);
        this.head.setRotationPoint(0.0F, -11.5F, -3.0F);
        this.head.addBox(-4.5F, -3.5F, -7.0F, 9.0F, 9.0F, 10.0F, 0.0F, 0.0F, 0.0F);
        this.body = new ModelRenderer(this, 0, 0);
        this.body.setRotationPoint(0.0F, 6.0F, 0.0F);
        this.body.addBox(-5.0F, -6.0F, -11.0F, 10.0F, 12.0F, 22.0F, 0.0F, 0.0F, 0.0F);
        this.tail2 = new ModelRenderer(this, 32, 93);
        this.tail2.setRotationPoint(0.0F, 15.0F, 5.0F);
        this.tail2.addBox(-3.0F, -4.0F, 0.0F, 6.0F, 5.0F, 8.0F, 0.0F, 0.0F, 0.0F);
        this.tail = new ModelRenderer(this, 28, 53);
        this.tail.setRotationPoint(0.0F,2.0F, 11.0F);
        this.tail.addBox(-3.0F, -4.0F, -1.0F, 6.0F, 20.0F, 6.0F, 0.0F, 0.0F, 0.0F);
        this.neck.addChild(this.finNeck);
        this.body.addChild(this.armRight);
        this.head.addChild(this.snout);
        this.head.addChild(this.finLeft);
        this.armLeft.addChild(this.finArmLeft);
        this.tail2.addChild(this.tail3);
        this.body.addChild(this.finBack);
        this.head.addChild(this.finRight);
        this.body.addChild(this.armLeft);
        this.armRight.addChild(this.finArmRight);
        this.body.addChild(this.neck);
        this.neck.addChild(this.head);
        this.tail.addChild(this.tail2);
        this.body.addChild(this.tail);
    }

    @Override
    protected Iterable<ModelRenderer> getHeadParts() {
        return Collections.EMPTY_LIST;
    }

    @Override
    protected Iterable<ModelRenderer> getBodyParts() {
        return ImmutableList.of(body);
    }

    @Override
    public void setRotationAngles(MudhorseEntity entityIn, float f, float f1, float ageInTicks, float netHeadYaw, float headPitch) {
        float speed = 1.5f;
        float degree = 0.8f;
        this.armRight.rotateAngleX = MathHelper.cos(f * speed * 0.4F) * degree * 1.2F * f1 - 0.2F;
        this.tail.rotateAngleX = MathHelper.cos(f * speed * 0.4F) * degree * -1.2F * f1 - 0.05F;
        this.armLeft.rotateAngleX = MathHelper.cos(f * speed * 0.4F) * degree * 1.2F * f1 - 0.2F;
        this.neck.rotateAngleX = MathHelper.cos(f * speed * 0.4F) * degree * 0.4F * f1 - 0.2F;
        this.head.rotateAngleX = MathHelper.cos(f * speed * 0.4F) * degree * 0.2F * f1 + 0.2F;
        this.finLeft.rotateAngleY = MathHelper.cos(f * speed * 0.4F) * degree * 0.8F * f1 + 0.3F;
        this.finRight.rotateAngleY = MathHelper.cos(f * speed * 0.4F) * degree * -0.8F * f1 - 0.3F;
        this.body.rotateAngleY = MathHelper.cos(f * speed * 0.4F) * degree * 0.2F * f1;
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
