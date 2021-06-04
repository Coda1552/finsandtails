package teamdraco.fins.client.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import teamdraco.fins.common.entities.PenglilEntity;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;


@OnlyIn(Dist.CLIENT)
public class PenglilModel<T extends Entity> extends EntityModel<PenglilEntity> {
	public ModelRenderer body;
	public ModelRenderer beak;
	public ModelRenderer rightfoot;
	public ModelRenderer leftfoot;
	public ModelRenderer rightwing;
	public ModelRenderer leftwing;
	public ModelRenderer tail;
	public ModelRenderer part8;

	public PenglilModel() {
		this.texWidth = 32;
		this.texHeight = 32;
		this.body = new ModelRenderer(this, 0, 0);
		this.body.setPos(0.0F, 20.0F, 0.0F);
		this.body.addBox(-2.5F, -3.0F, -2.5F, 5.0F, 6.0F, 5.0F, 0.0F, 0.0F, 0.0F);
		this.leftwing = new ModelRenderer(this, 0, 18);
		this.leftwing.mirror = true;
		this.leftwing.setPos(2.0F, 1.0F, 1.0F);
		this.leftwing.addBox(0.0F, -0.5F, -1.0F, 1.0F, 3.0F, 2.0F, 0.0F, 0.0F, 0.0F);
		this.part8 = new ModelRenderer(this, 20, 2);
		this.part8.setPos(1.5F, -2.5F, 0.0F);
		this.part8.addBox(-1.5F, -4.0F, -1.5F, 3.0F, 4.0F, 3.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(part8, 0.0F, 0.0F, 0.4363323129985824F);
		this.rightfoot = new ModelRenderer(this, 0, 15);
		this.rightfoot.setPos(-1.25F, 3.0F, 0.5F);
		this.rightfoot.addBox(-1.0F, 0.0F, -2.0F, 2.0F, 1.0F, 2.0F, 0.0F, 0.0F, 0.0F);
		this.beak = new ModelRenderer(this, 10, 13);
		this.beak.setPos(0.0F, -1.5F, -2.5F);
		this.beak.addBox(-1.5F, -1.5F, -2.0F, 3.0F, 3.0F, 2.0F, 0.0F, 0.0F, 0.0F);
		this.leftfoot = new ModelRenderer(this, 0, 15);
		this.leftfoot.mirror = true;
		this.leftfoot.setPos(1.25F, 3.0F, 0.5F);
		this.leftfoot.addBox(-1.0F, 0.0F, -2.0F, 2.0F, 1.0F, 2.0F, 0.0F, 0.0F, 0.0F);
		this.rightwing = new ModelRenderer(this, 0, 18);
		this.rightwing.setPos(-2.0F, 1.0F, 1.0F);
		this.rightwing.addBox(-1.0F, -0.5F, -1.0F, 1.0F, 3.0F, 2.0F, 0.0F, 0.0F, 0.0F);
		this.tail = new ModelRenderer(this, 0, 11);
		this.tail.setPos(0.0F, 2.5F, 2.5F);
		this.tail.addBox(-1.5F, -0.5F, 0.0F, 3.0F, 1.0F, 3.0F, 0.0F, 0.0F, 0.0F);
		this.body.addChild(this.leftwing);
		this.body.addChild(this.part8);
		this.body.addChild(this.rightfoot);
		this.body.addChild(this.beak);
		this.body.addChild(this.leftfoot);
		this.body.addChild(this.rightwing);
		this.body.addChild(this.tail);
	}

	@Override
	public void setupAnim(PenglilEntity entityIn, float f, float f1, float ageInTicks, float netHeadYaw, float headPitch) {
		if(entityIn.isInWater()) {
			float speed = 4.0f;
			float degree = 1.0f;
			this.body.yRot = 0;
			this.body.zRot = 0;
			this.tail.yRot = 0;
			this.rightfoot.yRot = 0;
			this.leftfoot.yRot = 0;
			this.body.xRot = f1 + 1.375F;
			this.tail.xRot = MathHelper.cos(0.25F + f * speed * 0.5F) * degree * 0.4F * f1 - 1.575F;
			this.leftwing.yRot = MathHelper.cos(f * speed * 0.5F) * degree * 1.5F * f1 - 1.575F;
			this.leftwing.xRot = f1 - 1.575F;
			this.rightwing.xRot = f1 - 1.575F;
			this.rightwing.yRot = MathHelper.cos(f * speed * 0.5F) * degree * -1.5F * f1 + 1.575F;
			this.leftfoot.xRot = MathHelper.cos(f * speed * 0.5F) * degree * 0.8F * f1 + 1.2F;
			this.rightfoot.xRot = MathHelper.cos(f * speed * 0.5F) * degree * -0.8F * f1 + 1.2F;
		}
		else if(entityIn.isInSittingPose()) {
			this.rightwing.setPos(-2.0F, 0.5F, 1.0F);
			this.body.setPos(0.0F, 21.0F, 0.0F);
			this.rightfoot.setPos(-2.25F, 2.0F, -1.15F);
			this.leftfoot.setPos(2.25F, 2.0F, -1.15F);
			this.leftwing.setPos(2.0F, 0.5F, 1.0F);

			this.body.yRot = 0;
			this.body.xRot = 0;
			this.body.zRot = 0;

			this.tail.yRot = 0;
			this.tail.xRot = 0;

			this.rightfoot.yRot = 0;
			this.rightfoot.xRot = 0;

			this.leftfoot.yRot = 0;
			this.leftfoot.xRot = 0;

			this.leftwing.yRot = 0;
			this.leftwing.xRot = 0;

			this.rightwing.xRot = 0;
			this.rightwing.yRot = 0;
		}
		else {
			float speed = 2.0f;
			float degree = 3.5f;
			f1 = MathHelper.clamp(f1, -0.275f, 0.275f);

			this.leftwing.yRot = 0;
			this.leftwing.xRot = 0;
			this.rightwing.xRot = 0;
			this.rightwing.yRot = 0;
			this.body.xRot = 0;
			this.body.yRot = MathHelper.cos(f * speed * 0.4F) * degree * 0.5F * f1;
			this.body.zRot = MathHelper.cos(f * speed * 0.4F) * degree * 0.2F * f1;
			this.tail.yRot = MathHelper.cos(f * speed * 0.4F) * degree * 0.4F * f1;
			this.tail.xRot = MathHelper.cos(f1 + 0.5F);
			this.rightfoot.xRot = MathHelper.cos(f * speed * 0.5F) * degree * 0.8F * f1;
			this.leftfoot.xRot = MathHelper.cos(f * speed * 0.5F) * degree * -0.8F * f1;

			this.rightwing.setPos(-2.0F, 1.0F, 1.0F);
			this.body.setPos(0.0F, 20.0F, 0.0F);
			this.rightfoot.setPos(-1.25F, 3.0F, 0.5F);
			this.leftfoot.setPos(1.25F, 3.0F, 0.5F);
			this.leftwing.setPos(2.0F, 1.0F, 1.0F);
		}
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
		ImmutableList.of(this.body).forEach((modelRenderer) -> {
			modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
		});
	}

	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}
}
