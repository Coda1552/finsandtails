package teamdraco.fins.client.model;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import teamdraco.fins.common.entities.MudhorseEntity;

import java.util.Collections;

@OnlyIn(Dist.CLIENT)
public class MudhorseModel<T extends Entity> extends AgeableModel<MudhorseEntity> {
    public ModelRenderer body;
    public ModelRenderer leftLeg;
    public ModelRenderer rightLeg;
    public ModelRenderer tail;
    public ModelRenderer neck;
    public ModelRenderer dorsalFin;
    public ModelRenderer tailTip;
    public ModelRenderer tailTip2;
    public ModelRenderer head;
    public ModelRenderer snout;
    public ModelRenderer leftPectoralFin;
    public ModelRenderer rightPectoralFin;
    private float neckXRot;

    public MudhorseModel() {
        this.texWidth = 80;
        this.texHeight = 48;
        this.tailTip = new ModelRenderer(this, 0, 21);
        this.tailTip.setPos(0.0F, 13.5F, 2.0F);
        this.tailTip.addBox(-2.5F, -1.5F, 0.5F, 5.0F, 3.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.rightPectoralFin = new ModelRenderer(this, 0, 0);
        this.rightPectoralFin.setPos(-4.0F, -4.0F, 2.5F);
        this.rightPectoralFin.addBox(0.0F, -4.0F, 0.0F, 0.0F, 8.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(rightPectoralFin, 0.0F, -0.39269908169872414F, 0.0F);
        this.rightLeg = new ModelRenderer(this, 40, 0);
        this.rightLeg.mirror = true;
        this.rightLeg.setPos(-3.0F, -3.0F, -2.0F);
        this.rightLeg.addBox(-1.5F, 0.0F, -2.5F, 3.0F, 15.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.neck = new ModelRenderer(this, 55, 20);
        this.neck.setPos(0.0F, 2.0F, -5.5F);
        this.neck.addBox(-2.5F, -10.0F, -3.0F, 5.0F, 10.0F, 6.0F, 0.0F, 0.0F, 0.0F);
        this.head = new ModelRenderer(this, 0, 28);
        this.head.setPos(0.0F, -10.0F, 0.0F);
        this.head.addBox(-4.0F, -8.0F, -4.5F, 8.0F, 8.0F, 9.0F, 0.0F, 0.0F, 0.0F);
        this.body = new ModelRenderer(this, 0, 0);
        this.body.setPos(0.0F, 12.0F, -1.0F);
        this.body.addBox(-3.5F, -4.0F, -6.5F, 7.0F, 8.0F, 13.0F, 0.0F, 0.0F, 0.0F);
        this.dorsalFin = new ModelRenderer(this, 27, -8);
        this.dorsalFin.setPos(0.0F, -4.0F, 3.0F);
        this.dorsalFin.addBox(0.0F, -4.0F, -4.5F, 0.0F, 4.0F, 9.0F, 0.0F, 0.0F, 0.0F);
        this.snout = new ModelRenderer(this, 49, 36);
        this.snout.setPos(0.0F, -1.5F, -4.5F);
        this.snout.addBox(-1.5F, -1.5F, -6.0F, 3.0F, 3.0F, 6.0F, 0.0F, 0.0F, 0.0F);
        this.tailTip2 = new ModelRenderer(this, 0, 0);
        this.tailTip2.setPos(0.0F, -1.5F, 3.0F);
        this.tailTip2.addBox(-1.5F, -2.0F, -1.5F, 3.0F, 2.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.tail = new ModelRenderer(this, 35, 20);
        this.tail.setPos(0.0F, -3.0F, 6.0F);
        this.tail.addBox(-2.5F, 0.0F, -2.5F, 5.0F, 15.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.leftLeg = new ModelRenderer(this, 40, 0);
        this.leftLeg.setPos(3.0F, -3.0F, -2.0F);
        this.leftLeg.addBox(-1.5F, 0.0F, -2.5F, 3.0F, 15.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.leftPectoralFin = new ModelRenderer(this, 0, 0);
        this.leftPectoralFin.setPos(4.0F, -4.0F, 2.5F);
        this.leftPectoralFin.addBox(0.0F, -4.0F, 0.0F, 0.0F, 8.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(leftPectoralFin, 0.0F, 0.39269908169872414F, 0.0F);
        this.tail.addChild(this.tailTip);
        this.head.addChild(this.rightPectoralFin);
        this.body.addChild(this.rightLeg);
        this.body.addChild(this.neck);
        this.neck.addChild(this.head);
        this.body.addChild(this.dorsalFin);
        this.head.addChild(this.snout);
        this.tailTip.addChild(this.tailTip2);
        this.body.addChild(this.tail);
        this.body.addChild(this.leftLeg);
        this.head.addChild(this.leftPectoralFin);
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
    public void setupAnim(MudhorseEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float speed = 1.5f;
        float degree = 1.0f;
        limbSwingAmount = MathHelper.clamp(limbSwingAmount, -0.5F, 0.5F);
        this.leftLeg.xRot = MathHelper.cos(limbSwing * speed * 0.4F) * degree * 0.8F * limbSwingAmount;
        this.rightLeg.xRot = MathHelper.cos(1.0F + limbSwing * speed * 0.4F) * degree * 0.8F * limbSwingAmount;
        this.tail.xRot = MathHelper.cos(3.0F + limbSwing * speed * 0.4F) * degree * 0.8F * limbSwingAmount;
        this.neck.xRot = MathHelper.cos(limbSwing * speed * 0.4F) * degree * 0.8F * limbSwingAmount;
        this.head.xRot = MathHelper.cos(3.0F + limbSwing * speed * 0.4F) * degree * 0.8F * limbSwingAmount;
        this.leftPectoralFin.yRot = MathHelper.cos(limbSwing * speed * 0.2F) * degree * 0.8F * limbSwingAmount + 0.4F;
        this.rightPectoralFin.yRot = MathHelper.cos(3.0F + limbSwing * speed * 0.2F) * degree * 0.8F * limbSwingAmount - 0.4F;
        this.body.xRot = MathHelper.cos(limbSwing * speed * 0.6F) * degree * 0.1F * limbSwingAmount;
        this.dorsalFin.zRot = MathHelper.cos(limbSwing * speed * 0.2F) * degree * 0.4F * limbSwingAmount;
        this.head.zRot = MathHelper.cos(limbSwing * speed * 0.2F) * degree * 0.4F * limbSwingAmount;

        if (entityIn.eatAnimationTick > 4 && entityIn.eatAnimationTick <= 36) {
            this.neck.xRot = this.neckXRot;
        }
    }

    @Override
    public void prepareMobModel(MudhorseEntity p_212843_1_, float p_212843_2_, float p_212843_3_, float p_212843_4_) {
        super.prepareMobModel(p_212843_1_, p_212843_2_, p_212843_3_, p_212843_4_);
        this.neckXRot = p_212843_1_.getHeadEatAngleScale(p_212843_4_) + 0.5F;
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}
