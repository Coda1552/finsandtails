package teamdraco.finsandstails.client.model;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import teamdraco.finsandstails.common.entities.WherbleEntity;

import java.util.Collections;

@OnlyIn(Dist.CLIENT)
public class WherbleModel<T extends Entity> extends AgeableModel<WherbleEntity> {
    public ModelRenderer body;
    public ModelRenderer tail;
    public ModelRenderer legLeft;
    public ModelRenderer legRight;
    public ModelRenderer bodyFinLeft;
    public ModelRenderer bodyFinRight;
    public ModelRenderer tailFin;

    public WherbleModel() {
        this.texWidth = 32;
        this.texHeight = 32;
        this.tailFin = new ModelRenderer(this, 1, 5);
        this.tailFin.setPos(0.0F, -1.5F, 2.5F);
        this.tailFin.addBox(0.0F, -2.0F, -1.5F, 0.0F, 3.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.legLeft = new ModelRenderer(this, 18, 2);
        this.legLeft.mirror = true;
        this.legLeft.setPos(2.5F, 0.5F, 3.0F);
        this.legLeft.addBox(-1.0F, 0.0F, -1.5F, 2.0F, 5.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.legRight = new ModelRenderer(this, 18, 2);
        this.legRight.setPos(-2.5F, 0.5F, 3.0F);
        this.legRight.addBox(-1.0F, 0.0F, -1.5F, 2.0F, 5.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.bodyFinLeft = new ModelRenderer(this, 1, 19);
        this.bodyFinLeft.setPos(1.5F, -2.0F, 2.0F);
        this.bodyFinLeft.addBox(-0.5F, -3.0F, -1.5F, 1.0F, 4.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(bodyFinLeft, 0.0F, 0.0F, 0.3490658503988659F);
        this.tail = new ModelRenderer(this, 1, 3);
        this.tail.setPos(0.0F, 0.0F, 5.5F);
        this.tail.addBox(-1.5F, -1.5F, -1.0F, 3.0F, 3.0F, 9.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(tail, -0.35185837453889574F, 0.0F, 0.0F);
        this.bodyFinRight = new ModelRenderer(this, 1, 19);
        this.bodyFinRight.setPos(-1.5F, -2.0F, 2.0F);
        this.bodyFinRight.addBox(-0.5F, -3.0F, -1.5F, 1.0F, 4.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(bodyFinRight, 0.0F, 0.0F, -0.3490658503988659F);
        this.body = new ModelRenderer(this, 0, 16);
        this.body.setPos(0.0F, 18.5F, -2.5F);
        this.body.addBox(-2.5F, -2.5F, -5.5F, 5.0F, 5.0F, 11.0F, 0.0F, 0.0F, 0.0F);
        this.tail.addChild(this.tailFin);
        this.body.addChild(this.legLeft);
        this.body.addChild(this.legRight);
        this.body.addChild(this.bodyFinLeft);
        this.body.addChild(this.tail);
        this.body.addChild(this.bodyFinRight);
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
    public void setupAnim(WherbleEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float speed = 2.2f;
        float degree = 1.0f;
        this.legLeft.xRot = MathHelper.cos(limbSwing * speed * 0.4F) * degree * 1.25F * limbSwingAmount;
        this.legRight.xRot = MathHelper.cos(3.0F + limbSwing * speed * 0.4F) * degree * 1.25F * limbSwingAmount;
        this.body.xRot = MathHelper.cos(limbSwing * speed * 0.2F) * degree * 0.2F * limbSwingAmount;
        this.tail.xRot = MathHelper.cos(limbSwing * speed * 0.2F) * degree * 0.5F * limbSwingAmount - 0.25F;
        this.body.zRot = MathHelper.cos(limbSwing * speed * 0.1F) * degree * 0.2F * limbSwingAmount;
        this.tail.yRot = MathHelper.cos(limbSwing * speed * 0.2F) * degree * 0.4F * limbSwingAmount;
        this.tailFin.zRot = MathHelper.cos(limbSwing * speed * 0.2F) * degree * 0.6F * limbSwingAmount;
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}
