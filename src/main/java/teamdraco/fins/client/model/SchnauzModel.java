package teamdraco.fins.client.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
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
    public void renderToBuffer(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
        ImmutableList.of(this.body).forEach((modelRenderer) -> {
            modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
        });
    }

    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}
