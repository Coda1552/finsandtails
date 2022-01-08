package teamdraco.finsandstails.client.old.model;

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
public class PhantomNudibranchModel<T extends Entity> extends EntityModel<T> {
    public ModelRenderer body;
    public ModelRenderer rightantenna;
    public ModelRenderer leftantenna;
    public ModelRenderer rightmantle;
    public ModelRenderer leftmantle;

    public PhantomNudibranchModel() {
        this.texWidth = 44;
        this.texHeight = 22;
        this.leftantenna = new ModelRenderer(this, 0, 0);
        this.leftantenna.setPos(1.0F, -1.0F, -2.5F);
        this.leftantenna.addBox(-1.0F, -4.0F, 0.0F, 2.0F, 4.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(leftantenna, -0.6981317007977318F, 0.0F, 0.3490658503988659F);
        this.body = new ModelRenderer(this, 0, 0);
        this.body.setPos(0.0F, 22.5F, 0.0F);
        this.body.addBox(-2.0F, -1.5F, -3.0F, 4.0F, 3.0F, 6.0F, 0.0F, 0.0F, 0.0F);
        this.rightantenna = new ModelRenderer(this, 0, 0);
        this.rightantenna.mirror = true;
        this.rightantenna.setPos(-1.0F, -1.0F, -2.5F);
        this.rightantenna.addBox(-1.0F, -4.0F, 0.0F, 2.0F, 4.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(rightantenna, -0.6981317007977318F, 0.0F, -0.3490658503988659F);
        this.rightmantle = new ModelRenderer(this, 15, 0);
        this.rightmantle.mirror = true;
        this.rightmantle.setPos(-2.0F, 0.5F, 1.0F);
        this.rightmantle.addBox(-3.0F, 0.0F, -3.0F, 5.0F, 0.0F, 7.0F, 0.0F, 0.0F, 0.0F);
        this.leftmantle = new ModelRenderer(this, 15, 0);
        this.leftmantle.setPos(2.0F, 0.5F, 1.0F);
        this.leftmantle.addBox(-2.0F, 0.0F, -3.0F, 5.0F, 0.0F, 7.0F, 0.0F, 0.0F, 0.0F);
        this.body.addChild(this.leftantenna);
        this.body.addChild(this.rightantenna);
        this.body.addChild(this.rightmantle);
        this.body.addChild(this.leftmantle);
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
        this.leftmantle.zRot = MathHelper.cos(f * speed * 0.4F) * degree * -1.2F * f1;
        this.rightmantle.zRot = MathHelper.cos(f * speed * 0.4F) * degree * 1.2F * f1;
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}
