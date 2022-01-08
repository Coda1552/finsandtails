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
public class GopjetModel<T extends Entity> extends EntityModel<T> {
	public ModelRenderer body;
	public ModelRenderer thruster;
	public ModelRenderer tail;
	public ModelRenderer finLeft;
	public ModelRenderer finRight;
	public ModelRenderer tailFin;

	public GopjetModel() {
		this.texWidth = 48;
		this.texHeight = 48;
		this.thruster = new ModelRenderer(this, 0, 24);
		this.thruster.setPos(0.0F, -2.5F, 1.0F);
		this.thruster.addBox(-2.0F, -2.0F, -3.5F, 4.0F, 2.0F, 7.0F, 0.0F, 0.0F, 0.0F);
		this.tail = new ModelRenderer(this, 15, 16);
		this.tail.setPos(0.0F, 1.0F, 5.5F);
		this.tail.addBox(-1.5F, -1.5F, 0.0F, 3.0F, 3.0F, 11.0F, 0.0F, 0.0F, 0.0F);
		this.tailFin = new ModelRenderer(this, 4, 5);
		this.tailFin.setPos(0.0F, -1.5F, 10.5F);
		this.tailFin.addBox(0.0F, -2.0F, -8.5F, 0.0F, 3.0F, 11.0F, 0.0F, 0.0F, 0.0F);
		this.body = new ModelRenderer(this, 0, 0);
		this.body.setPos(0.0F, 21.5F, -1.5F);
		this.body.addBox(-4.0F, -2.5F, -5.5F, 8.0F, 5.0F, 11.0F, 0.0F, 0.0F, 0.0F);
		this.finRight = new ModelRenderer(this, 10, 21);
		this.finRight.mirror = true;
		this.finRight.setPos(-4.0F, 2.5F, -0.5F);
		this.finRight.addBox(-5.0F, 0.0F, -1.0F, 5.0F, 0.0F, 3.0F, 0.0F, 0.0F, 0.0F);
		this.finLeft = new ModelRenderer(this, 10, 21);
		this.finLeft.setPos(4.0F, 2.5F, -0.5F);
		this.finLeft.addBox(0.0F, 0.0F, -1.0F, 5.0F, 0.0F, 3.0F, 0.0F, 0.0F, 0.0F);
		this.body.addChild(this.thruster);
		this.body.addChild(this.tail);
		this.tail.addChild(this.tailFin);
		this.body.addChild(this.finRight);
		this.body.addChild(this.finLeft);
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
		ImmutableList.of(this.body).forEach((modelRenderer) -> {
			modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
		});
	}

	@Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		float speed = 4.0f;
		float degree = 3.0f;
		limbSwingAmount = MathHelper.clamp(limbSwingAmount, -0.3f, 0.3f);
		this.body.yRot = MathHelper.cos(limbSwing * speed * 0.4F) * degree * 0.4F * limbSwingAmount;
		this.tail.yRot = MathHelper.cos(limbSwing * speed * 0.4F) * degree * 0.8F * limbSwingAmount;
		this.finLeft.zRot = MathHelper.cos(1.0F + limbSwing * speed * 0.4F) * degree * 0.8F * limbSwingAmount + 0.2F;
		this.finRight.zRot = MathHelper.cos(1.0F + limbSwing * speed * 0.4F) * degree * -0.8F * limbSwingAmount - 0.2F;
	}

	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}
