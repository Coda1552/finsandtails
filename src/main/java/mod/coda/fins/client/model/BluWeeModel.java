package mod.coda.fins.client.model;

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
public class BluWeeModel<T extends Entity> extends EntityModel<T> {
    public ModelRenderer body;
    public ModelRenderer tail;
    public ModelRenderer dorsal;
    public ModelRenderer rightpectoral;
    public ModelRenderer leftpectoral;

    public BluWeeModel() {
        this.textureWidth = 34;
        this.textureHeight = 12;
        this.body = new ModelRenderer(this, 0, 0);
        this.body.setRotationPoint(0.0F, 23.0F, 0.0F);
        this.body.addBox(-1.0F, -1.0F, -1.8F, 2.0F, 2.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.rightpectoral = new ModelRenderer(this, 0, 7);
        this.rightpectoral.setRotationPoint(-0.5F, 0.9F, -0.5F);
        this.rightpectoral.addBox(-2.5F, 0.0F, -0.5F, 2.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.leftpectoral = new ModelRenderer(this, 0, 7);
        this.leftpectoral.mirror = true;
        this.leftpectoral.setRotationPoint(1.0F, 0.9F, -0.5F);
        this.leftpectoral.addBox(0.0F, 0.0F, -0.5F, 2.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.tail = new ModelRenderer(this, 14, -3);
        this.tail.setRotationPoint(0.0F, 0.0F, 2.0F);
        this.tail.addBox(0.0F, -2.0F, 0.0F, 0.0F, 3.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(tail, 0.0F, -0.008377580222319268F, 0.0F);
        this.dorsal = new ModelRenderer(this, 15, 3);
        this.dorsal.setRotationPoint(0.0F, -1.0F, 1.0F);
        this.dorsal.addBox(0.0F, -2.0F, -1.0F, 0.0F, 2.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.body.addChild(this.rightpectoral);
        this.body.addChild(this.leftpectoral);
        this.body.addChild(this.tail);
        this.body.addChild(this.dorsal);
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
        this.leftpectoral.rotateAngleZ = MathHelper.cos(f * speed * 0.4F) * degree * -1.2F * f1;
        this.rightpectoral.rotateAngleZ = MathHelper.cos(f * speed * 0.4F) * degree * 1.2F * f1;
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
