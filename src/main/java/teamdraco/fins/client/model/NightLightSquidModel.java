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
public class NightLightSquidModel<T extends Entity> extends EntityModel<T> {
    public ModelRenderer body;
    public ModelRenderer tentacles;
    public ModelRenderer tentacleright;
    public ModelRenderer tentacleleft;
    public ModelRenderer finright;
    public ModelRenderer finleft;

    public NightLightSquidModel() {
        this.texWidth = 34;
        this.texHeight = 24;
        this.finright = new ModelRenderer(this, 15, 0);
        this.finright.setPos(0.0F, 0.0F, 3.5F);
        this.finright.addBox(-4.0F, 0.0F, -4.0F, 4.0F, 0.0F, 7.0F, 0.0F, 0.0F, 0.0F);
        this.finleft = new ModelRenderer(this, 15, 0);
        this.finleft.mirror = true;
        this.finleft.setPos(0.0F, 0.0F, 3.5F);
        this.finleft.addBox(0.0F, 0.0F, -4.0F, 4.0F, 0.0F, 7.0F, 0.0F, 0.0F, 0.0F);
        this.tentacleleft = new ModelRenderer(this, 0, 11);
        this.tentacleleft.mirror = true;
        this.tentacleleft.setPos(1.0F, 1.0F, -3.5F);
        this.tentacleleft.addBox(-0.5F, -0.5F, -5.0F, 1.0F, 1.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(tentacleleft, 0.3490658503988659F, -0.3490658503988659F, 0.0F);
        this.tentacleright = new ModelRenderer(this, 0, 11);
        this.tentacleright.setPos(-1.0F, 1.0F, -3.5F);
        this.tentacleright.addBox(-0.5F, -0.5F, -5.0F, 1.0F, 1.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(tentacleright, 0.3490658503988659F, 0.3490658503988659F, 0.0F);
        this.body = new ModelRenderer(this, 0, 0);
        this.body.setPos(0.0F, 22.0F, 0.0F);
        this.body.addBox(-2.0F, -2.0F, -3.5F, 4.0F, 4.0F, 7.0F, 0.0F, 0.0F, 0.0F);
        this.tentacles = new ModelRenderer(this, 12, 11);
        this.tentacles.setPos(0.0F, 0.0F, -3.0F);
        this.tentacles.addBox(-1.5F, -1.0F, -3.0F, 3.0F, 2.0F, 3.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(tentacles, 0.3490658503988659F, 0.0F, 0.0F);
        this.body.addChild(this.finright);
        this.body.addChild(this.finleft);
        this.body.addChild(this.tentacleleft);
        this.body.addChild(this.tentacleright);
        this.body.addChild(this.tentacles);
    }

    @Override
    public void renderToBuffer(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        ImmutableList.of(this.body).forEach((modelRenderer) -> {
            modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        });
    }

    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        float speed = 1.0f;
        float degree = 1.0f;
        this.finright.zRot = MathHelper.cos(limbSwing * speed * 0.4F) * degree * 1.2F * limbSwingAmount;
        this.finleft.zRot = MathHelper.cos(limbSwing * speed * 0.4F) * degree * -1.2F * limbSwingAmount;
        this.tentacles.xRot = MathHelper.cos(limbSwing * speed * 0.4F) * degree * 0.3F * limbSwingAmount + 0.3F;
        this.body.xRot = MathHelper.cos(limbSwing * speed * 0.4F) * degree * 0.1F * limbSwingAmount + 0.1F;
        this.tentacleright.xRot = MathHelper.cos(2.0F + limbSwing * speed * 0.4F) * degree * 0.4F * limbSwingAmount + 0.4F;
        this.tentacleright.yRot = MathHelper.cos(2.0F + limbSwing * speed * 0.4F) * degree * 0.4F * limbSwingAmount + 0.4F;
        this.tentacleleft.xRot = MathHelper.cos(2.0F + limbSwing * speed * 0.4F) * degree * -0.4F * limbSwingAmount + 0.4F;
        this.tentacleleft.yRot = MathHelper.cos(2.0F + limbSwing * speed * 0.4F) * degree * 0.4F * limbSwingAmount - 0.4F;
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}
