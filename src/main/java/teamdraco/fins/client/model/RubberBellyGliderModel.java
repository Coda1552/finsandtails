package teamdraco.fins.client.model;

import com.google.common.collect.ImmutableList;
import net.minecraft.util.math.MathHelper;
import teamdraco.fins.common.entities.RubberBellyGliderEntity;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

import java.util.Collections;

public class RubberBellyGliderModel<T extends Entity> extends AgeableModel<RubberBellyGliderEntity> {
	public ModelRenderer body;
	public ModelRenderer tail;
	public ModelRenderer rightWingFront;
	public ModelRenderer leftWingFront;
	public ModelRenderer rightWingBack;
	public ModelRenderer leftWingBack;
	public ModelRenderer throat;
	public ModelRenderer horns;
	public ModelRenderer tailFin;

	public RubberBellyGliderModel() {
		this.textureWidth = 64;
		this.textureHeight = 48;
		this.leftWingFront = new ModelRenderer(this, 34, 34);
		this.leftWingFront.mirror = true;
		this.leftWingFront.setRotationPoint(-2.0F, -1.0F, -5.5F);
		this.leftWingFront.addBox(-12.0F, 0.0F, -2.5F, 12.0F, 0.0F, 6.0F, 0.0F, 0.0F, 0.0F);
		this.leftWingBack = new ModelRenderer(this, -4, 13);
		this.leftWingBack.mirror = true;
		this.leftWingBack.setRotationPoint(-2.0F, 0.0F, 1.0F);
		this.leftWingBack.addBox(-4.0F, 0.0F, -1.0F, 4.0F, 0.0F, 3.0F, 0.0F, 0.0F, 0.0F);
		this.tail = new ModelRenderer(this, 24, 0);
		this.tail.setRotationPoint(0.0F, 0.0F, 4.0F);
		this.tail.addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 14.0F, 0.0F, 0.0F, 0.0F);
		this.horns = new ModelRenderer(this, 0, 6);
		this.horns.setRotationPoint(0.0F, -1.5F, -10.0F);
		this.horns.addBox(-1.5F, -2.0F, -1.0F, 3.0F, 2.0F, 2.0F, 0.0F, 0.0F, 0.0F);
		this.rightWingBack = new ModelRenderer(this, -4, 13);
		this.rightWingBack.setRotationPoint(2.0F, 0.0F, 1.0F);
		this.rightWingBack.addBox(0.0F, 0.0F, -1.0F, 4.0F, 0.0F, 3.0F, 0.0F, 0.0F, 0.0F);
		this.tailFin = new ModelRenderer(this, 0, 20);
		this.tailFin.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.tailFin.addBox(0.0F, -6.0F, -1.0F, 0.0F, 10.0F, 16.0F, 0.0F, 0.0F, 0.0F);
		this.throat = new ModelRenderer(this, 0, 19);
		this.throat.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.throat.addBox(-5.0F, 0.5F, -7.0F, 10.0F, 7.0F, 10.0F, 0.0F, 0.0F, 0.0F);
		this.rightWingFront = new ModelRenderer(this, 34, 34);
		this.rightWingFront.setRotationPoint(2.0F, -1.0F, -5.5F);
		this.rightWingFront.addBox(0.0F, 0.0F, -2.5F, 12.0F, 0.0F, 6.0F, 0.0F, 0.0F, 0.0F);
		this.body = new ModelRenderer(this, 0, 0);
		this.body.setRotationPoint(0.0F, 22.5F, 4.0F);
		this.body.addBox(-2.0F, -1.5F, -12.0F, 4.0F, 3.0F, 16.0F, 0.0F, 0.0F, 0.0F);
		this.body.addChild(this.leftWingFront);
		this.body.addChild(this.leftWingBack);
		this.body.addChild(this.tail);
		this.body.addChild(this.horns);
		this.body.addChild(this.rightWingBack);
		this.tail.addChild(this.tailFin);
		this.body.addChild(this.throat);
		this.body.addChild(this.rightWingFront);
	}

	@Override
	protected Iterable<ModelRenderer> getHeadParts() {
		return Collections.emptyList();
	}

	@Override
	protected Iterable<ModelRenderer> getBodyParts() {
		return ImmutableList.of(body);
	}

	@Override
	public void setRotationAngles(RubberBellyGliderEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		if (entity.isOnGround() && !entity.isInWater()){
			float speed = 4.5f;
			float degree = 1.5f;
			limbSwingAmount = MathHelper.clamp(limbSwingAmount, -0.3f, 0.3f);
			this.body.rotateAngleY = MathHelper.cos(limbSwing * speed * 0.4F) * degree * 0.2F * limbSwingAmount;
			this.body.rotateAngleX = 0;
			this.body.rotateAngleZ = 0;
			this.tail.rotateAngleY = MathHelper.cos(-1.0F + limbSwing * speed * 0.4F) * degree * 0.8F * limbSwingAmount;
			this.tail.rotateAngleX = 0;
			this.tail.rotateAngleZ = 0;
			this.rightWingFront.rotateAngleY = MathHelper.cos(-2.0F + limbSwing * speed * 0.4F) * degree * 1.2F * limbSwingAmount;
			this.rightWingFront.rotateAngleX = MathHelper.cos(limbSwing * speed * 0.4F) * degree * 0.5F * limbSwingAmount;
			this.rightWingFront.rotateAngleZ = MathHelper.cos(limbSwing * speed * 0.4F) * degree * 0.2F * limbSwingAmount + 0.2F;
			this.leftWingFront.rotateAngleY = MathHelper.cos(-2.0F + limbSwing * speed * 0.4F) * degree * -1.2F * limbSwingAmount;
			this.leftWingFront.rotateAngleX = MathHelper.cos(limbSwing * speed * 0.4F) * degree * 0.5F * limbSwingAmount;
			this.leftWingFront.rotateAngleZ = MathHelper.cos(limbSwing * speed * 0.4F) * degree * -0.2F * limbSwingAmount - 0.2F;
			this.rightWingBack.rotateAngleZ = MathHelper.cos(-1.0F + limbSwing * speed * 0.4F) * degree * 0.8F * limbSwingAmount + 0.25F;
			this.rightWingBack.rotateAngleY = MathHelper.cos(-1.0F + limbSwing * speed * 0.4F) * degree * 0.2F * limbSwingAmount - 0.25F;
			this.rightWingBack.rotateAngleX = 0;
			this.leftWingBack.rotateAngleZ = MathHelper.cos(-1.0F + limbSwing * speed * 0.4F) * degree * -0.8F * limbSwingAmount - 0.25F;
			this.leftWingBack.rotateAngleY = MathHelper.cos(-1.0F + limbSwing * speed * 0.4F) * degree * -0.2F * limbSwingAmount + 0.25F;
			this.leftWingBack.rotateAngleX = 0;
		}
		else {
			float speed = 1.0f;
			float degree = 1.0f;
			this.body.rotateAngleZ = MathHelper.cos(limbSwing * speed * 0.2F) * degree * 0.1F * limbSwingAmount;
			this.body.rotateAngleX = MathHelper.cos(limbSwing * speed * 0.4F) * degree * 0.2F * limbSwingAmount - 0.05F;
			this.body.rotateAngleY = 0;
			this.rightWingFront.rotateAngleZ = MathHelper.cos(1.0F + limbSwing * speed * 0.4F) * degree * 1.2F * limbSwingAmount;
			this.rightWingFront.rotateAngleY = MathHelper.cos(1.0F + limbSwing * speed * 0.4F) * degree * 0.6F * limbSwingAmount - 0.2F;
			this.rightWingFront.rotateAngleX = 0;
			this.rightWingBack.rotateAngleZ = MathHelper.cos(1.0F + limbSwing * speed * 0.4F) * degree * 1.2F * limbSwingAmount;
			this.rightWingBack.rotateAngleY = MathHelper.cos(1.0F + limbSwing * speed * 0.4F) * degree * 0.6F * limbSwingAmount - 0.2F;
			this.rightWingBack.rotateAngleX = 0;
			this.leftWingFront.rotateAngleZ = MathHelper.cos(1.0F + limbSwing * speed * 0.4F) * degree * -1.2F * limbSwingAmount;
			this.leftWingFront.rotateAngleY = MathHelper.cos(1.0F + limbSwing * speed * 0.4F) * degree * -0.6F * limbSwingAmount + 0.2F;
			this.leftWingFront.rotateAngleX = 0;
			this.leftWingBack.rotateAngleZ = MathHelper.cos(1.0F + limbSwing * speed * 0.4F) * degree * -1.2F * limbSwingAmount;
			this.leftWingBack.rotateAngleY = MathHelper.cos(1.0F + limbSwing * speed * 0.4F) * degree * -0.6F * limbSwingAmount + 0.2F;
			this.leftWingBack.rotateAngleX = 0;
			this.tail.rotateAngleZ = MathHelper.cos(limbSwing * speed * 0.2F) * degree * 0.3F * limbSwingAmount;
			this.tail.rotateAngleX = MathHelper.cos(limbSwing * speed * 0.4F) * degree * 0.4F * limbSwingAmount;
			this.tail.rotateAngleY = 0;
		}
	}
}
