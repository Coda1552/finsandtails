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
public class PapaWeeModel<T extends Entity> extends EntityModel<T> {
    public ModelRenderer body;
    public ModelRenderer lips;
    public ModelRenderer tail;
    public ModelRenderer secondary_dorsal_fin;
    public ModelRenderer dorsal_fin;
    public ModelRenderer anal_fin;
    public ModelRenderer right_pectoral_fin;
    public ModelRenderer left_pectoral_fin;
    public ModelRenderer caudal_fin;

    public PapaWeeModel() {
        this.texWidth = 32;
        this.texHeight = 32;
        this.lips = new ModelRenderer(this, 0, 16);
        this.lips.setPos(0.0F, 0.5F, -4.5F);
        this.lips.addBox(-4.0F, 0.0F, -1.0F, 8.0F, 1.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.body = new ModelRenderer(this, 0, 0);
        this.body.setPos(0.0F, 20.5F, 0.0F);
        this.body.addBox(-3.5F, -3.5F, -4.5F, 7.0F, 7.0F, 9.0F, 0.0F, 0.0F, 0.0F);
        this.right_pectoral_fin = new ModelRenderer(this, 0, -2);
        this.right_pectoral_fin.mirror = true;
        this.right_pectoral_fin.setPos(-3.0F, 3.5F, -0.5F);
        this.right_pectoral_fin.addBox(0.0F, 0.0F, -1.0F, 0.0F, 2.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.dorsal_fin = new ModelRenderer(this, 0, -2);
        this.dorsal_fin.setPos(0.0F, -3.5F, -1.5F);
        this.dorsal_fin.addBox(0.0F, -3.0F, -2.0F, 0.0F, 3.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.secondary_dorsal_fin = new ModelRenderer(this, 0, 2);
        this.secondary_dorsal_fin.setPos(0.0F, -2.5F, 3.5F);
        this.secondary_dorsal_fin.addBox(0.0F, -2.0F, -2.0F, 0.0F, 2.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.anal_fin = new ModelRenderer(this, 5, -2);
        this.anal_fin.setPos(0.0F, 2.5F, 3.5F);
        this.anal_fin.addBox(0.0F, 0.0F, 0.0F, 0.0F, 2.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.caudal_fin = new ModelRenderer(this, 0, 18);
        this.caudal_fin.setPos(0.0F, -1.0F, 2.0F);
        this.caudal_fin.addBox(0.0F, -4.5F, 0.0F, 0.0F, 9.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.left_pectoral_fin = new ModelRenderer(this, 0, -2);
        this.left_pectoral_fin.setPos(3.0F, 3.5F, -0.5F);
        this.left_pectoral_fin.addBox(0.0F, 0.0F, -1.0F, 0.0F, 2.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.tail = new ModelRenderer(this, 16, 24);
        this.tail.setPos(0.0F, -1.0F, 4.5F);
        this.tail.addBox(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.body.addChild(this.lips);
        this.body.addChild(this.right_pectoral_fin);
        this.body.addChild(this.dorsal_fin);
        this.body.addChild(this.secondary_dorsal_fin);
        this.body.addChild(this.anal_fin);
        this.tail.addChild(this.caudal_fin);
        this.body.addChild(this.left_pectoral_fin);
        this.body.addChild(this.tail);
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        ImmutableList.of(this.body).forEach((modelRenderer) -> {
            modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        });
    }

    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float speed = 1.8f;
        float degree = 1.0f;
        limbSwingAmount = MathHelper.clamp(limbSwingAmount, -0.5F, 0.5F);
        this.body.yRot = MathHelper.cos(limbSwing * speed * 0.2F) * degree * 0.6F * limbSwingAmount;
        this.tail.yRot = MathHelper.cos(limbSwing * speed * 0.2F) * degree * 1.0F * limbSwingAmount;
        this.caudal_fin.yRot = MathHelper.cos(limbSwing * speed * 0.2F) * degree * 1.0F * limbSwingAmount;
        this.body.y = MathHelper.cos(limbSwing * speed * 0.1F) * degree * 0.1F * limbSwingAmount + 20.5F;
        this.dorsal_fin.zRot = MathHelper.cos(limbSwing * speed * 0.2F) * degree * 0.6F * limbSwingAmount;
        this.anal_fin.yRot = MathHelper.cos(limbSwing * speed * 0.2F) * degree * 1.0F * limbSwingAmount;
        this.right_pectoral_fin.zRot = MathHelper.cos(limbSwing * speed * 0.4F) * degree * 0.6F * limbSwingAmount;
        this.left_pectoral_fin.zRot = MathHelper.cos(3.0F + limbSwing * speed * 0.4F) * degree * 0.6F * limbSwingAmount;
        this.body.xRot = MathHelper.cos(limbSwing * speed * 0.05F) * degree * 0.1F * limbSwingAmount;
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}
