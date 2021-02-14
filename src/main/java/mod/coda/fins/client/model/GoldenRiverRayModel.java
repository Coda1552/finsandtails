package mod.coda.fins.client.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import mod.coda.fins.entities.GoldenRiverRayEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GoldenRiverRayModel<T extends Entity> extends EntityModel<GoldenRiverRayEntity> {
    public ModelRenderer body;
    public ModelRenderer tail;
    public ModelRenderer analfinright;
    public ModelRenderer analfinleft;
    public ModelRenderer dorsalfin;
    public ModelRenderer wingright;
    public ModelRenderer wingleft;
    public ModelRenderer tailfin;

    public GoldenRiverRayModel() {
        this.textureWidth = 40;
        this.textureHeight = 25;
        this.analfinleft = new ModelRenderer(this, -2, 12);
        this.analfinleft.mirror = true;
        this.analfinleft.setRotationPoint(1.5F, 0.0F, 3.0F);
        this.analfinleft.addBox(-1.0F, 0.0F, 0.0F, 3.0F, 0.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.tailfin = new ModelRenderer(this, 18, 10);
        this.tailfin.setRotationPoint(0.0F, 0.0F, 8.0F);
        this.tailfin.addBox(0.0F, -0.5F, -3.5F, 0.0F, 2.0F, 6.0F, 0.0F, 0.0F, 0.0F);
        this.wingleft = new ModelRenderer(this, 9, 0);
        this.wingleft.mirror = true;
        this.wingleft.setRotationPoint(1.5F, -1.0F, -1.5F);
        this.wingleft.addBox(0.0F, 0.0F, -1.5F, 10.0F, 0.0F, 6.0F, 0.0F, 0.0F, 0.0F);
        this.body = new ModelRenderer(this, 0, 0);
        this.body.setRotationPoint(0.0F, 23.0F, 0.0F);
        this.body.addBox(-1.5F, -1.0F, -4.0F, 3.0F, 2.0F, 8.0F, 0.0F, 0.0F, 0.0F);
        this.analfinright = new ModelRenderer(this, -2, 12);
        this.analfinright.setRotationPoint(-1.5F, 0.0F, 3.0F);
        this.analfinright.addBox(-2.0F, 0.0F, 0.0F, 3.0F, 0.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.tail = new ModelRenderer(this, 0, 10);
        this.tail.setRotationPoint(0.0F, -0.5F, 4.0F);
        this.tail.addBox(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 8.0F, 0.0F, 0.0F, 0.0F);
        this.wingright = new ModelRenderer(this, 9, 0);
        this.wingright.setRotationPoint(-1.5F, -1.0F, -1.5F);
        this.wingright.addBox(-10.0F, 0.0F, -1.5F, 10.0F, 0.0F, 6.0F, 0.0F, 0.0F, 0.0F);
        this.dorsalfin = new ModelRenderer(this, 0, 0);
        this.dorsalfin.setRotationPoint(0.0F, -1.0F, 3.0F);
        this.dorsalfin.addBox(0.0F, -2.0F, -0.5F, 0.0F, 2.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.body.addChild(this.analfinleft);
        this.tail.addChild(this.tailfin);
        this.body.addChild(this.wingleft);
        this.body.addChild(this.analfinright);
        this.body.addChild(this.tail);
        this.body.addChild(this.wingright);
        this.body.addChild(this.dorsalfin);
    }

    @Override
    public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) { 
        ImmutableList.of(this.body).forEach((modelRenderer) -> { 
            modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        });
    }

    @Override
    public void setRotationAngles(GoldenRiverRayEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float speed = 1.0f;
        float degree = 1.0f;
        this.wingright.rotateAngleZ = MathHelper.cos(limbSwing * speed * 0.4F) * degree * 1.0F * limbSwingAmount;
        this.wingleft.rotateAngleZ = MathHelper.cos(limbSwing * speed * 0.4F) * degree * -1.0F * limbSwingAmount;
        this.body.rotateAngleY = MathHelper.cos(limbSwing * speed * 0.4F) * degree * 0.2F * limbSwingAmount;
        this.tail.rotateAngleY = MathHelper.cos(limbSwing * speed * 0.4F) * degree * 0.4F * limbSwingAmount;
        this.tailfin.rotateAngleY = MathHelper.cos(limbSwing * speed * 0.4F) * degree * 0.3F * limbSwingAmount;
        this.analfinright.rotateAngleZ = MathHelper.cos(limbSwing * speed * 0.4F) * degree * -1.0F * limbSwingAmount;
        this.analfinleft.rotateAngleZ = MathHelper.cos(limbSwing * speed * 0.4F) * degree * 1.0F * limbSwingAmount;
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
