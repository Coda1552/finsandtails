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
public class VibraWeeModel<T extends Entity> extends EntityModel<T> {
    public ModelRenderer body;
    public ModelRenderer tail;
    public ModelRenderer dorsalfin;
    public ModelRenderer belly;
    public ModelRenderer pelvicfinright;
    public ModelRenderer pelvicfinleft;

    public VibraWeeModel() {
        this.textureWidth = 34;
        this.textureHeight = 12;
        this.dorsalfin = new ModelRenderer(this, 12, 0);
        this.dorsalfin.setRotationPoint(0.0F, -1.0F, 0.0F);
        this.dorsalfin.addBox(0.0F, -2.0F, 0.0F, 0.0F, 2.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.tail = new ModelRenderer(this, 12, 4);
        this.tail.setRotationPoint(0.0F, 0.0F, 2.0F);
        this.tail.addBox(0.0F, -2.0F, 0.0F, 0.0F, 3.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.body = new ModelRenderer(this, 0, 0);
        this.body.setRotationPoint(0.0F, 21.0F, 0.0F);
        this.body.addBox(-1.0F, -1.0F, -2.0F, 2.0F, 2.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.pelvicfinright = new ModelRenderer(this, 7, 0);
        this.pelvicfinright.mirror = true;
        this.pelvicfinright.setRotationPoint(-0.5F, 2.0F, 0.0F);
        this.pelvicfinright.addBox(-2.0F, 0.0F, -0.5F, 2.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(pelvicfinright, 0.0F, 0.0F, -1.0471975511965976F);
        this.belly = new ModelRenderer(this, 0, 6);
        this.belly.setRotationPoint(0.0F, 1.0F, -0.5F);
        this.belly.addBox(-0.5F, 0.0F, -1.5F, 1.0F, 2.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.pelvicfinleft = new ModelRenderer(this, 7, 0);
        this.pelvicfinleft.setRotationPoint(0.5F, 2.0F, 0.0F);
        this.pelvicfinleft.addBox(0.0F, 0.0F, -0.5F, 2.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(pelvicfinleft, 0.0F, 0.0F, 1.0471975511965976F);
        this.body.addChild(this.dorsalfin);
        this.body.addChild(this.tail);
        this.belly.addChild(this.pelvicfinright);
        this.body.addChild(this.belly);
        this.belly.addChild(this.pelvicfinleft);
    }

    @Override
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) { 
        ImmutableList.of(this.body).forEach((modelRenderer) -> { 
            modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        });
    }

    @Override
    public void setRotationAngles(T entityIn, float f, float f1, float ageInTicks, float netHeadYaw, float headPitch) {
        float degree = 1.0f;
        float speed = 3.0f;
        this.body.rotateAngleY = MathHelper.cos(f * speed * 0.4F) * degree * 0.5F * f1;
        this.tail.rotateAngleY = MathHelper.cos(f * speed * 0.4F) * degree * -0.5F * f1;
        this.pelvicfinleft.rotateAngleZ = MathHelper.cos(f * speed * 0.4F) * degree * -1.2F * f1 + 0.5f;
        this.pelvicfinright.rotateAngleZ = MathHelper.cos(f * speed * 0.4F) * degree * 1.2F * f1 - 0.5f;
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
