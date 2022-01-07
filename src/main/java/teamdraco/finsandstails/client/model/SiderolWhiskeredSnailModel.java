package teamdraco.finsandstails.client.model;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import teamdraco.finsandstails.common.entities.SiderolWhiskeredSnailEntity;

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
        this.texWidth = 32;
        this.texHeight = 32;
        this.stalks = new ModelRenderer(this, 14, 11);
        this.stalks.setPos(0.0F, -8.0F, -0.5F);
        this.stalks.addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.body = new ModelRenderer(this, 0, 9);
        this.body.setPos(0.0F, 22.0F, 0.0F);
        this.body.addBox(-1.5F, 0.0F, -2.5F, 3.0F, 2.0F, 7.0F, 0.0F, 0.0F, 0.0F);
        this.neck = new ModelRenderer(this, 16, 0);
        this.neck.setPos(0.0F, 2.0F, -2.5F);
        this.neck.addBox(-1.0F, -8.0F, -1.0F, 2.0F, 8.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(neck, 0.17453292519943295F, 0.0F, 0.0F);
        this.whiskerRight = new ModelRenderer(this, 0, 9);
        this.whiskerRight.setPos(-1.0F, -7.0F, -0.5F);
        this.whiskerRight.addBox(0.0F, 0.0F, -0.5F, 0.0F, 4.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(whiskerRight, -0.17453292519943295F, 0.0F, 0.08726646259971647F);
        this.whiskerLeft = new ModelRenderer(this, 0, 9);
        this.whiskerLeft.mirror = true;
        this.whiskerLeft.setPos(1.0F, -7.0F, -0.5F);
        this.whiskerLeft.addBox(0.0F, 0.0F, -0.5F, 0.0F, 4.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(whiskerLeft, -0.17453292519943295F, 0.0F, -0.08726646259971647F);
        this.shell = new ModelRenderer(this, 0, 0);
        this.shell.setPos(0.0F, 1.0F, 1.0F);
        this.shell.addBox(-2.0F, -5.0F, -2.0F, 4.0F, 5.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(shell, -0.08726646259971647F, 0.0F, 0.0F);
        this.neck.addChild(this.stalks);
        this.body.addChild(this.neck);
        this.neck.addChild(this.whiskerRight);
        this.neck.addChild(this.whiskerLeft);
        this.body.addChild(this.shell);
    }

    @Override
    protected Iterable<ModelRenderer> headParts() {
        return Collections.EMPTY_LIST;
    }

    @Override
    protected Iterable<ModelRenderer> bodyParts() {
        return ImmutableList.of(body);
    }

    @Override
    public void setupAnim(SiderolWhiskeredSnailEntity entityIn, float f, float f1, float ageInTicks, float netHeadYaw, float headPitch) {
        float speed = 3.0f;
        float degree = 2.0f;
        this.neck.xRot = MathHelper.cos(f * speed * 0.4F) * degree * 0.1F * f1 + 0.18F;
        this.whiskerLeft.xRot = MathHelper.cos(f * speed * 0.4F) * degree * 0.2F * f1;
        this.whiskerLeft.zRot = MathHelper.cos(f * speed * 0.4F) * degree * 0.2F * f1 - 0.2F;
        this.whiskerRight.xRot = MathHelper.cos(f * speed * 0.4F) * degree * -0.2F * f1;
        this.whiskerRight.zRot = MathHelper.cos(f * speed * 0.4F) * degree * -0.2F * f1 + 0.2F;
        this.shell.zRot = MathHelper.cos(f * speed * 0.4F) * degree * 0.2F * f1;
        this.body.yRot = MathHelper.cos(f * speed * 0.4F) * degree * 0.1F * f1;
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}
