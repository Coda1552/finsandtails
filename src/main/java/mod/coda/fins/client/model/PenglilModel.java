package mod.coda.fins.client.model;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import mod.coda.fins.entities.PenglilEntity;
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
		this.textureWidth = 32;
		this.textureHeight = 32;
		this.body = new ModelRenderer(this, 0, 0);
		this.body.setRotationPoint(0.0F, 20.0F, 0.0F);
		this.body.addBox(-2.5F, -3.0F, -2.5F, 5.0F, 6.0F, 5.0F, 0.0F, 0.0F, 0.0F);
		this.leftwing = new ModelRenderer(this, 0, 18);
		this.leftwing.mirror = true;
		this.leftwing.setRotationPoint(2.0F, 1.0F, 1.0F);
		this.leftwing.addBox(0.0F, -0.5F, -1.0F, 1.0F, 3.0F, 2.0F, 0.0F, 0.0F, 0.0F);
		this.part8 = new ModelRenderer(this, 20, 2);
		this.part8.setRotationPoint(1.5F, -2.5F, 0.0F);
		this.part8.addBox(-1.5F, -4.0F, -1.5F, 3.0F, 4.0F, 3.0F, 0.0F, 0.0F, 0.0F);
		this.setRotateAngle(part8, 0.0F, 0.0F, 0.4363323129985824F);
		this.rightfoot = new ModelRenderer(this, 0, 15);
		this.rightfoot.setRotationPoint(-1.25F, 3.0F, 0.5F);
		this.rightfoot.addBox(-1.0F, 0.0F, -2.0F, 2.0F, 1.0F, 2.0F, 0.0F, 0.0F, 0.0F);
		this.beak = new ModelRenderer(this, 10, 13);
		this.beak.setRotationPoint(0.0F, -1.5F, -2.5F);
		this.beak.addBox(-1.5F, -1.5F, -2.0F, 3.0F, 3.0F, 2.0F, 0.0F, 0.0F, 0.0F);
		this.leftfoot = new ModelRenderer(this, 0, 15);
		this.leftfoot.mirror = true;
		this.leftfoot.setRotationPoint(1.25F, 3.0F, 0.5F);
		this.leftfoot.addBox(-1.0F, 0.0F, -2.0F, 2.0F, 1.0F, 2.0F, 0.0F, 0.0F, 0.0F);
		this.rightwing = new ModelRenderer(this, 0, 18);
		this.rightwing.setRotationPoint(-2.0F, 1.0F, 1.0F);
		this.rightwing.addBox(-1.0F, -0.5F, -1.0F, 1.0F, 3.0F, 2.0F, 0.0F, 0.0F, 0.0F);
		this.tail = new ModelRenderer(this, 0, 11);
		this.tail.setRotationPoint(0.0F, 2.5F, 2.5F);
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
	public void setRotationAngles(PenglilEntity entityIn, float f, float f1, float ageInTicks, float netHeadYaw, float headPitch) {
		if(entityIn.isInWater()) {
			float speed = 4.0f;
			float degree = 1.0f;
			this.body.rotateAngleY = 0;
			this.body.rotateAngleZ = 0;
			this.tail.rotateAngleY = 0;
			this.rightfoot.rotateAngleY = 0;
			this.leftfoot.rotateAngleY = 0;
			this.body.rotateAngleX = f1 + 1.375F;
			this.tail.rotateAngleX = MathHelper.cos(0.25F + f * speed * 0.5F) * degree * 0.4F * f1 - 1.575F;
			this.leftwing.rotateAngleY = MathHelper.cos(f * speed * 0.5F) * degree * 1.5F * f1 - 1.575F;
			this.leftwing.rotateAngleX = f1 - 1.575F;
			this.rightwing.rotateAngleX = f1 - 1.575F;
			this.rightwing.rotateAngleY = MathHelper.cos(f * speed * 0.5F) * degree * -1.5F * f1 + 1.575F;
			this.leftfoot.rotateAngleX = MathHelper.cos(f * speed * 0.5F) * degree * 0.8F * f1 + 1.2F;
			this.rightfoot.rotateAngleX = MathHelper.cos(f * speed * 0.5F) * degree * -0.8F * f1 + 1.2F;
		}
		else if(entityIn.isEntitySleeping()) {
			this.rightwing.setRotationPoint(-2.0F, 0.5F, 1.0F);
			this.body.setRotationPoint(0.0F, 21.0F, 0.0F);
			this.rightfoot.setRotationPoint(-2.25F, 2.0F, -1.15F);
			this.leftfoot.setRotationPoint(2.25F, 2.0F, -1.15F);
			this.leftwing.setRotationPoint(2.0F, 0.5F, 1.0F);

			this.body.rotateAngleY = 0;
			this.body.rotateAngleX = 0;
			this.body.rotateAngleZ = 0;

			this.tail.rotateAngleY = 0;
			this.tail.rotateAngleX = 0;

			this.rightfoot.rotateAngleY = 0;
			this.rightfoot.rotateAngleX = 0;

			this.leftfoot.rotateAngleY = 0;
			this.leftfoot.rotateAngleX = 0;

			this.leftwing.rotateAngleY = 0;
			this.leftwing.rotateAngleX = 0;

			this.rightwing.rotateAngleX = 0;
			this.rightwing.rotateAngleY = 0;
		}
		else {
			float speed = 2.0f;
			float degree = 3.5f;
			f1 = MathHelper.clamp(f1, -0.275f, 0.275f);

			this.leftwing.rotateAngleY = 0;
			this.leftwing.rotateAngleX = 0;
			this.rightwing.rotateAngleX = 0;
			this.rightwing.rotateAngleY = 0;
			this.body.rotateAngleX = 0;
			this.body.rotateAngleY = MathHelper.cos(f * speed * 0.4F) * degree * 0.5F * f1;
			this.body.rotateAngleZ = MathHelper.cos(f * speed * 0.4F) * degree * 0.2F * f1;
			this.tail.rotateAngleY = MathHelper.cos(f * speed * 0.4F) * degree * 0.4F * f1;
			this.tail.rotateAngleX = MathHelper.cos(f1 + 0.5F);
			this.rightfoot.rotateAngleX = MathHelper.cos(f * speed * 0.5F) * degree * 0.8F * f1;
			this.leftfoot.rotateAngleX = MathHelper.cos(f * speed * 0.5F) * degree * -0.8F * f1;

			this.rightwing.setRotationPoint(-2.0F, 1.0F, 1.0F);
			this.body.setRotationPoint(0.0F, 20.0F, 0.0F);
			this.rightfoot.setRotationPoint(-1.25F, 3.0F, 0.5F);
			this.leftfoot.setRotationPoint(1.25F, 3.0F, 0.5F);
			this.leftwing.setRotationPoint(2.0F, 1.0F, 1.0F);
		}
	}

	@Override
	public void render(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn, float red, float green, float blue, float alpha) {
		ImmutableList.of(this.body).forEach((modelRenderer) -> {
			modelRenderer.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
		});
	}

	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
