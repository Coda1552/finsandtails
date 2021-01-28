package mod.coda.fins.client.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import mod.coda.fins.entity.SiderolWhiskeredSnailEntity;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Collections;

@OnlyIn(Dist.CLIENT)
public class SiderolWhiskeredSnailModel<T extends Entity> extends AgeableModel<SiderolWhiskeredSnailEntity> {
    public ModelRenderer body;
    public ModelRenderer shell;
    public ModelRenderer neck;
    public ModelRenderer stalks;
    public ModelRenderer whiskerLeft;
    public ModelRenderer whiskerRight;

    public SiderolWhiskeredSnailModel() {
        this.textureWidth = 32;
        this.textureHeight = 32;
        this.stalks = new ModelRenderer(this, 14, 11);
        this.stalks.setRotationPoint(0.0F, -8.0F, -0.5F);
        this.stalks.addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.body = new ModelRenderer(this, 0, 9);
        this.body.setRotationPoint(0.0F, 22.0F, 0.0F);
        this.body.addBox(-1.5F, 0.0F, -2.5F, 3.0F, 2.0F, 7.0F, 0.0F, 0.0F, 0.0F);
        this.neck = new ModelRenderer(this, 16, 0);
        this.neck.setRotationPoint(0.0F, 2.0F, -2.5F);
        this.neck.addBox(-1.0F, -8.0F, -1.0F, 2.0F, 8.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(neck, 0.17453292519943295F, 0.0F, 0.0F);
        this.whiskerRight = new ModelRenderer(this, 0, 9);
        this.whiskerRight.setRotationPoint(-1.0F, -7.0F, -0.5F);
        this.whiskerRight.addBox(0.0F, 0.0F, -0.5F, 0.0F, 4.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(whiskerRight, -0.17453292519943295F, 0.0F, 0.08726646259971647F);
        this.whiskerLeft = new ModelRenderer(this, 0, 9);
        this.whiskerLeft.mirror = true;
        this.whiskerLeft.setRotationPoint(1.0F, -7.0F, -0.5F);
        this.whiskerLeft.addBox(0.0F, 0.0F, -0.5F, 0.0F, 4.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(whiskerLeft, -0.17453292519943295F, 0.0F, -0.08726646259971647F);
        this.shell = new ModelRenderer(this, 0, 0);
        this.shell.setRotationPoint(0.0F, 1.0F, 1.0F);
        this.shell.addBox(-2.0F, -5.0F, -2.0F, 4.0F, 5.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(shell, -0.08726646259971647F, 0.0F, 0.0F);
        this.neck.addChild(this.stalks);
        this.body.addChild(this.neck);
        this.neck.addChild(this.whiskerRight);
        this.neck.addChild(this.whiskerLeft);
        this.body.addChild(this.shell);
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
    public void setRotationAngles(SiderolWhiskeredSnailEntity entityIn, float f, float f1, float ageInTicks, float netHeadYaw, float headPitch) {
        float speed = 3.0f;
        float degree = 2.0f;
        this.neck.rotateAngleX = MathHelper.cos(f * speed * 0.4F) * degree * 0.1F * f1 + 0.18F;
        this.whiskerLeft.rotateAngleX = MathHelper.cos(f * speed * 0.4F) * degree * 0.2F * f1;
        this.whiskerLeft.rotateAngleZ = MathHelper.cos(f * speed * 0.4F) * degree * 0.2F * f1 - 0.2F;
        this.whiskerRight.rotateAngleX = MathHelper.cos(f * speed * 0.4F) * degree * -0.2F * f1;
        this.whiskerRight.rotateAngleZ = MathHelper.cos(f * speed * 0.4F) * degree * -0.2F * f1 + 0.2F;
        this.shell.rotateAngleZ = MathHelper.cos(f * speed * 0.4F) * degree * 0.2F * f1;
        this.body.rotateAngleY = MathHelper.cos(f * speed * 0.4F) * degree * 0.1F * f1;
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
