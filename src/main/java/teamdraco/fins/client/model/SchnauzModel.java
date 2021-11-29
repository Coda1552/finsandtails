package teamdraco.fins.client.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import teamdraco.fins.common.entities.SchnauzEntity;

import java.util.Collections;

@OnlyIn(Dist.CLIENT)
public class SchnauzModel<T extends SchnauzEntity> extends AgeableModel<T> {
    public ModelRenderer body;
    public ModelRenderer nose;
    public ModelRenderer leftFin;
    public ModelRenderer rightFin;
    public ModelRenderer tail;

    public SchnauzModel() {
        texWidth = 32;
        texHeight = 32;
        body = new ModelRenderer(this);
        body.setPos(0.0F, 21.5F, 0.0F);
        body.texOffs(0, 0).addBox(-2.5F, -2.5F, -4.5F, 5.0F, 5.0F, 9.0F, 0.0F, false);
        nose = new ModelRenderer(this);
        nose.setPos(0.0F, 0.5F, -4.5F);
        body.addChild(nose);
        nose.texOffs(19, 0).addBox(-1.5F, -1.0F, -2.0F, 3.0F, 5.0F, 2.0F, 0.0F, false);
        leftFin = new ModelRenderer(this);
        leftFin.setPos(2.5F, 2.5F, 0.0F);
        body.addChild(leftFin);
        setRotateAngle(leftFin, 0.0F, 0.0F, -0.3927F);
        leftFin.texOffs(0, 3).addBox(0.0F, 0.0F, -1.5F, 0.0F, 3.0F, 3.0F, 0.0F, false);
        rightFin = new ModelRenderer(this);
        rightFin.setPos(-2.5F, 2.5F, 0.0F);
        body.addChild(rightFin);
        setRotateAngle(rightFin, 0.0F, 0.0F, 0.3927F);
        rightFin.texOffs(0, 3).addBox(0.0F, 0.0F, -1.5F, 0.0F, 3.0F, 3.0F, 0.0F, false);
        tail = new ModelRenderer(this);
        tail.setPos(0.5F, 0.0F, 4.5F);
        body.addChild(tail);
        tail.texOffs(0, 14).addBox(-4.0F, -0.5F, 0.0F, 7.0F, 1.0F, 5.0F, 0.0F, false);
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
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        if (entity.isInWater()) {
            float speed = 1.5f;
            float degree = 1.0f;
            this.body.xRot = headPitch * (((float)Math.PI / 180F) / 2);
            this.body.yRot = netHeadYaw * (((float)Math.PI / 180F) / 2);

            this.body.xRot += MathHelper.cos(limbSwing * speed * 0.2F) * degree * 0.3F * limbSwingAmount;
            this.body.zRot = MathHelper.cos(limbSwing * speed * 0.1F) * degree * 0.2F * limbSwingAmount;
            this.body.y = MathHelper.cos(limbSwing * speed * 0.05F) * degree * 0.3F * limbSwingAmount + 21.5F;
            this.tail.xRot = MathHelper.cos(-1.0F + limbSwing * speed * 0.2F) * degree * 0.8F * limbSwingAmount;
            this.tail.yRot = MathHelper.cos(-1.0F + limbSwing * speed * 0.1F) * degree * 0.3F * limbSwingAmount;
            this.nose.xRot = MathHelper.cos(-1.0F + limbSwing * speed * 0.2F) * degree * 0.6F * limbSwingAmount + 0.1F;
            this.nose.zRot = MathHelper.cos(-1.0F + limbSwing * speed * 0.1F) * degree * 0.4F * limbSwingAmount;
            this.rightFin.zRot = MathHelper.cos(-1.0F + limbSwing * speed * 0.4F) * degree * 1.0F * limbSwingAmount + 0.4F;
            this.leftFin.zRot = MathHelper.cos(-1.0F + limbSwing * speed * 0.4F) * degree * -1.0F * limbSwingAmount - 0.4F;
        }
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}
