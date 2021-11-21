package teamdraco.fins.client.model;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import teamdraco.fins.common.entities.GlassSkipperEntity;

import java.util.Collections;

@OnlyIn(Dist.CLIENT)
public class GlassSkipperModel<T extends Entity> extends AgeableModel<GlassSkipperEntity> {
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
        this.texHeight = 48;
        this.rightAntenna = new ModelRenderer(this, 0, 0);
        this.rightAntenna.setPos(-1.5F, -1.5F, -6.0F);
        this.rightAntenna.addBox(0.0F, -1.5F, -5.0F, 0.0F, 4.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.body = new ModelRenderer(this, 0, 0);
        this.body.setPos(0.0F, 20.0F, 0.0F);
        this.body.addBox(-2.5F, -2.0F, -6.0F, 5.0F, 4.0F, 12.0F, 0.0F, 0.0F, 0.0F);
        this.leftWing = new ModelRenderer(this, -21, 16);
        this.leftWing.setPos(2.0F, -2.0F, 0.0F);
        this.leftWing.addBox(-2.0F, 0.0F, -6.0F, 16.0F, 0.0F, 21.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(leftWing, 0.0F, 0.0F, -0.45378560551852565F);
        this.legsBack = new ModelRenderer(this, 0, 2);
        this.legsBack.setPos(0.0F, 2.0F, 1.0F);
        this.legsBack.addBox(-2.0F, 0.0F, 0.0F, 4.0F, 2.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.legsMid = new ModelRenderer(this, 0, 0);
        this.legsMid.setPos(0.0F, 2.0F, -1.5F);
        this.legsMid.addBox(-2.5F, 0.0F, 0.0F, 5.0F, 2.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.rightWing = new ModelRenderer(this, -21, 16);
        this.rightWing.mirror = true;
        this.rightWing.setPos(-2.0F, -2.0F, 0.0F);
        this.rightWing.addBox(-14.0F, 0.0F, -6.0F, 16.0F, 0.0F, 21.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(rightWing, 0.0F, 0.0F, 0.45378560551852565F);
        this.leftAntenna = new ModelRenderer(this, 0, 0);
        this.leftAntenna.setPos(1.5F, -1.5F, -6.0F);
        this.leftAntenna.addBox(0.0F, -1.5F, -5.0F, 0.0F, 4.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.legsFront = new ModelRenderer(this, 0, 0);
        this.legsFront.setPos(0.0F, 2.0F, -3.5F);
        this.legsFront.addBox(-2.5F, 0.0F, 0.0F, 5.0F, 2.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.body.addChild(this.rightAntenna);
        this.body.addChild(this.leftWing);
        this.body.addChild(this.legsBack);
        this.body.addChild(this.legsMid);
        this.body.addChild(this.rightWing);
        this.body.addChild(this.leftAntenna);
        this.body.addChild(this.legsFront);
    }

    @Override
    protected Iterable<ModelRenderer> headParts() {
        return Collections.emptyList();
    }

    @Override
    protected Iterable<ModelRenderer> bodyParts() {
        return ImmutableList.of(body);
    }

    @Override
    public void setupAnim(GlassSkipperEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float speed = 1.0f;
        float degree = 1.0f;
        this.body.xRot = MathHelper.cos(ageInTicks * speed * 0.3F) * degree * 0.4F * 0.35f;
        this.leftWing.zRot = MathHelper.cos(ageInTicks * speed * 0.9F) * degree * 4.4F * 0.35f + 0.5F;
        this.rightWing.zRot = MathHelper.cos(3.0F + ageInTicks * speed * 0.9F) * degree * 4.4F * 0.35f - 0.5F;
        this.legsFront.xRot = MathHelper.cos(ageInTicks * speed * 0.3F) * degree * 0.8F * 0.35f + 0.4F;
        this.legsMid.xRot = MathHelper.cos(3.0F + ageInTicks * speed * 0.3F) * degree * 0.8F * 0.35f + 0.4F;
        this.legsBack.xRot = MathHelper.cos(1.5F + ageInTicks * speed * 0.3F) * degree * 0.8F * 0.35f + 0.4F;
        this.rightAntenna.xRot = MathHelper.cos(1.5F + ageInTicks * speed * 0.3F) * degree * 0.8F * 0.35f;
        this.leftAntenna.xRot = MathHelper.cos(1.5F + ageInTicks * speed * 0.3F) * degree * 0.8F * 0.35f;
//        this.leftWing.xRot = MathHelper.cos(ageInTicks * speed * 0.3F) * degree * 0.8F * 0.35f;
//        this.rightWing.xRot = MathHelper.cos(ageInTicks * speed * 0.3F) * degree * 0.8F * 0.35f;
        this.body.zRot = MathHelper.cos(ageInTicks * speed * 0.2F) * degree * 0.3F * 0.35f;
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}
