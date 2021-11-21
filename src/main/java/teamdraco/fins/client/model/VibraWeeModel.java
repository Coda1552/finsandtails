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
    public ModelRenderer belly;
    public ModelRenderer tailFin;
    public ModelRenderer dorsalFin;
    public ModelRenderer leftPectoralFin;
    public ModelRenderer rightPectoralFin;
    public ModelRenderer leftPelvicFin;
    public ModelRenderer rightPelvicFin;

    public VibraWeeModel() {
        this.texWidth = 16;
        this.texHeight = 16;
        this.belly = new ModelRenderer(this, 0, 7);
        this.belly.setPos(0.0F, 1.0F, -0.5F);
        this.belly.addBox(-0.5F, 0.0F, -1.5F, 1, 2, 4, 0.0F);
        this.leftPelvicFin = new ModelRenderer(this, 8, 1);
        this.leftPelvicFin.setPos(0.5F, 2.0F, 1.0F);
        this.leftPelvicFin.addBox(0.0F, 0.0F, -0.5F, 2, 0, 1, 0.0F);
        this.tailFin = new ModelRenderer(this, 9, 0);
        this.tailFin.setPos(0.0F, -0.5F, 2.0F);
        this.tailFin.addBox(0.0F, -1.5F, 0.0F, 0, 3, 2, 0.0F);
        this.leftPectoralFin = new ModelRenderer(this, 0, 0);
        this.leftPectoralFin.setPos(1.0F, 1.0F, -0.5F);
        this.leftPectoralFin.addBox(0.0F, -0.5F, 0.0F, 0, 1, 1, 0.0F);
        this.dorsalFin = new ModelRenderer(this, 0, 1);
        this.dorsalFin.setPos(0.0F, -1.0F, 0.5F);
        this.dorsalFin.addBox(0.0F, -2.0F, -1.0F, 0, 2, 2, 0.0F);
        this.rightPectoralFin = new ModelRenderer(this, 0, 0);
        this.rightPectoralFin.setPos(-1.0F, 1.0F, -0.5F);
        this.rightPectoralFin.addBox(0.0F, -0.5F, 0.0F, 0, 1, 1, 0.0F);
        this.rightPelvicFin = new ModelRenderer(this, 8, 1);
        this.rightPelvicFin.mirror = true;
        this.rightPelvicFin.setPos(-0.5F, 2.0F, 1.0F);
        this.rightPelvicFin.addBox(-2.0F, 0.0F, -0.5F, 2, 0, 1, 0.0F);
        this.body = new ModelRenderer(this, 0, 0);
        this.body.setPos(0.0F, 21.0F, 0.0F);
        this.body.addBox(-1.0F, -1.0F, -2.5F, 2, 2, 5, 0.0F);
        this.body.addChild(this.belly);
        this.belly.addChild(this.leftPelvicFin);
        this.body.addChild(this.tailFin);
        this.body.addChild(this.leftPectoralFin);
        this.body.addChild(this.dorsalFin);
        this.body.addChild(this.rightPectoralFin);
        this.belly.addChild(this.rightPelvicFin);
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        ImmutableList.of(this.body).forEach((modelRenderer) -> {
            modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        });
    }

    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float speed = 1.6f;
        float degree = 2.0f;
        this.body.y = MathHelper.cos(limbSwing * speed * 0.3F) * degree * 3.5F * limbSwingAmount + 22.5F;
        this.body.yRot = MathHelper.cos(-1.0F + ageInTicks * speed * 0.2F) * degree * 0.4F * 0.1F;
        this.tailFin.yRot = MathHelper.cos(-2.0F + limbSwing * speed * 0.8F) * degree * 1.3F * limbSwingAmount;
        this.leftPectoralFin.yRot = MathHelper.cos(ageInTicks * speed * 0.6F) * degree * 1.2F * 0.1F + 0.4F;
        this.rightPectoralFin.yRot = MathHelper.cos(ageInTicks * speed * 0.6F) * degree * -1.2F * 0.1F - 0.4F;
        this.body.zRot = MathHelper.cos(ageInTicks * speed * 0.1F) * degree * 0.4F * 0.1F;
        this.leftPelvicFin.zRot = MathHelper.cos(ageInTicks * speed * 0.4F) * degree * 0.8F * 0.1F + 0.8F;
        this.rightPelvicFin.zRot = MathHelper.cos(ageInTicks * speed * 0.4F) * degree * -0.8F * 0.1F - 0.8F;
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}
