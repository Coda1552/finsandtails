package teamdraco.finsandstails.client.model;

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
public class SwampMuckerModel<T extends Entity> extends EntityModel<T> {
    public ModelRenderer body;
    public ModelRenderer tail;
    public ModelRenderer dorsalfin;
    public ModelRenderer pectoralfinleft;
    public ModelRenderer pectoralfinright;
    public ModelRenderer pelvicfinleft;
    public ModelRenderer pelvicfinright;

    public SwampMuckerModel() {
        this.texWidth = 54;
        this.texHeight = 22;
        this.pelvicfinleft = new ModelRenderer(this, 16, 2);
        this.pelvicfinleft.setPos(0.5F, 1.5F, -2.0F);
        this.pelvicfinleft.addBox(0.0F, 0.0F, -0.5F, 0.0F, 3.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.body = new ModelRenderer(this, 0, 0);
        this.body.setPos(0.0F, 22.5F, 0.0F);
        this.body.addBox(-1.5F, -1.5F, -4.5F, 3.0F, 3.0F, 9.0F, 0.0F, 0.0F, 0.0F);
        this.tail = new ModelRenderer(this, 0, 0);
        this.tail.setPos(0.0F, 0.0F, 4.5F);
        this.tail.addBox(0.0F, -1.5F, 0.0F, 0.0F, 3.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.pectoralfinleft = new ModelRenderer(this, 0, 14);
        this.pectoralfinleft.setPos(1.5F, -1.0F, -1.5F);
        this.pectoralfinleft.addBox(0.0F, 0.0F, -1.0F, 9.0F, 0.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.pectoralfinright = new ModelRenderer(this, 0, 14);
        this.pectoralfinright.mirror = true;
        this.pectoralfinright.setPos(-1.5F, -1.0F, -1.5F);
        this.pectoralfinright.addBox(-9.0F, 0.0F, -1.0F, 9.0F, 0.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.dorsalfin = new ModelRenderer(this, 16, -5);
        this.dorsalfin.setPos(0.0F, -1.5F, 2.0F);
        this.dorsalfin.addBox(0.0F, -2.0F, -2.5F, 0.0F, 2.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.pelvicfinright = new ModelRenderer(this, 16, 2);
        this.pelvicfinright.mirror = true;
        this.pelvicfinright.setPos(-0.5F, 1.5F, -2.0F);
        this.pelvicfinright.addBox(0.0F, 0.0F, -0.5F, 0.0F, 3.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.body.addChild(this.pelvicfinleft);
        this.body.addChild(this.tail);
        this.body.addChild(this.pectoralfinleft);
        this.body.addChild(this.pectoralfinright);
        this.body.addChild(this.dorsalfin);
        this.body.addChild(this.pelvicfinright);
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) { 
        ImmutableList.of(this.body).forEach((modelRenderer) -> { 
            modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        });
    }

    @Override
    public void setupAnim(T entityIn, float f, float f1, float ageInTicks, float netHeadYaw, float headPitch) {
        float degree = 1.0f;
        float speed = 3.0f;
        this.body.yRot = MathHelper.cos(f * speed * 0.4F) * degree * 0.5F * f1;
        this.tail.yRot = MathHelper.cos(f * speed * 0.4F) * degree * -0.5F * f1;
        this.pectoralfinleft.zRot = MathHelper.cos(f * speed * 0.4F) * degree * -1.2F * f1;
        this.pectoralfinright.zRot = MathHelper.cos(f * speed * 0.4F) * degree * 1.2F * f1;
        this.pelvicfinleft.zRot = MathHelper.cos(f * speed * 0.4F) * degree * -1.2F * f1 + -0.2f;
        this.pelvicfinright.zRot = MathHelper.cos(f * speed * 0.4F) * degree * 1.2F * f1 + 0.2f;
        if (!entityIn.hasImpulse) {
            this.body.xRot = headPitch * ((float)Math.PI / 180F);
        }
    }

//        this.body.rotateAngleY = netHeadYaw * ((float) Math.PI / 180F);
//        if (Entity.horizontalMag(entityIn.getMotion()) > 1.0E-7D) {
//        this.body.rotateAngleY += -0.05F + -0.05F * MathHelper.cos(ageInTicks * 0.3F);
//        this.tail.rotateAngleY = -0.15F * MathHelper.cos(ageInTicks * 0.3F);
//    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}
