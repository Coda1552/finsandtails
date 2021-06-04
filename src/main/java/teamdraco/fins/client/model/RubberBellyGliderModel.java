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
		this.texWidth = 64;
		this.texHeight = 48;
		this.leftWingFront = new ModelRenderer(this, 34, 34);
		this.leftWingFront.mirror = true;
		this.leftWingFront.setPos(-2.0F, -1.0F, -5.5F);
		this.leftWingFront.addBox(-12.0F, 0.0F, -2.5F, 12.0F, 0.0F, 6.0F, 0.0F, 0.0F, 0.0F);
		this.leftWingBack = new ModelRenderer(this, -4, 13);
		this.leftWingBack.mirror = true;
		this.leftWingBack.setPos(-2.0F, 0.0F, 1.0F);
		this.leftWingBack.addBox(-4.0F, 0.0F, -1.0F, 4.0F, 0.0F, 3.0F, 0.0F, 0.0F, 0.0F);
		this.tail = new ModelRenderer(this, 24, 0);
		this.tail.setPos(0.0F, 0.0F, 4.0F);
		this.tail.addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 14.0F, 0.0F, 0.0F, 0.0F);
		this.horns = new ModelRenderer(this, 0, 6);
		this.horns.setPos(0.0F, -1.5F, -10.0F);
		this.horns.addBox(-1.5F, -2.0F, -1.0F, 3.0F, 2.0F, 2.0F, 0.0F, 0.0F, 0.0F);
		this.rightWingBack = new ModelRenderer(this, -4, 13);
		this.rightWingBack.setPos(2.0F, 0.0F, 1.0F);
		this.rightWingBack.addBox(0.0F, 0.0F, -1.0F, 4.0F, 0.0F, 3.0F, 0.0F, 0.0F, 0.0F);
		this.tailFin = new ModelRenderer(this, 0, 20);
		this.tailFin.setPos(0.0F, 0.0F, 0.0F);
		this.tailFin.addBox(0.0F, -6.0F, -1.0F, 0.0F, 10.0F, 16.0F, 0.0F, 0.0F, 0.0F);
		this.throat = new ModelRenderer(this, 0, 19);
		this.throat.setPos(0.0F, 0.0F, 0.0F);
		this.throat.addBox(-5.0F, 0.5F, -7.0F, 10.0F, 7.0F, 10.0F, 0.0F, 0.0F, 0.0F);
		this.rightWingFront = new ModelRenderer(this, 34, 34);
		this.rightWingFront.setPos(2.0F, -1.0F, -5.5F);
		this.rightWingFront.addBox(0.0F, 0.0F, -2.5F, 12.0F, 0.0F, 6.0F, 0.0F, 0.0F, 0.0F);
		this.body = new ModelRenderer(this, 0, 0);
		this.body.setPos(0.0F, 22.5F, 4.0F);
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
	protected Iterable<ModelRenderer> headParts() {
		return Collections.emptyList();
	}

	@Override
	protected Iterable<ModelRenderer> bodyParts() {
		return ImmutableList.of(body);
	}

	@Override
	public void setupAnim(RubberBellyGliderEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		if (entity.isOnGround() && !entity.isInWater()){
			float speed = 4.5f;
			float degree = 1.5f;
			limbSwingAmount = MathHelper.clamp(limbSwingAmount, -0.3f, 0.3f);
			this.body.yRot = MathHelper.cos(limbSwing * speed * 0.4F) * degree * 0.2F * limbSwingAmount;
			this.body.xRot = 0;
			this.body.zRot = 0;
			this.tail.yRot = MathHelper.cos(-1.0F + limbSwing * speed * 0.4F) * degree * 0.8F * limbSwingAmount;
			this.tail.xRot = 0;
			this.tail.zRot = 0;
			this.rightWingFront.yRot = MathHelper.cos(-2.0F + limbSwing * speed * 0.4F) * degree * 1.2F * limbSwingAmount;
			this.rightWingFront.xRot = MathHelper.cos(limbSwing * speed * 0.4F) * degree * 0.5F * limbSwingAmount;
			this.rightWingFront.zRot = MathHelper.cos(limbSwing * speed * 0.4F) * degree * 0.2F * limbSwingAmount + 0.2F;
			this.leftWingFront.yRot = MathHelper.cos(-2.0F + limbSwing * speed * 0.4F) * degree * -1.2F * limbSwingAmount;
			this.leftWingFront.xRot = MathHelper.cos(limbSwing * speed * 0.4F) * degree * 0.5F * limbSwingAmount;
			this.leftWingFront.zRot = MathHelper.cos(limbSwing * speed * 0.4F) * degree * -0.2F * limbSwingAmount - 0.2F;
			this.rightWingBack.zRot = MathHelper.cos(-1.0F + limbSwing * speed * 0.4F) * degree * 0.8F * limbSwingAmount + 0.25F;
			this.rightWingBack.yRot = MathHelper.cos(-1.0F + limbSwing * speed * 0.4F) * degree * 0.2F * limbSwingAmount - 0.25F;
			this.rightWingBack.xRot = 0;
			this.leftWingBack.zRot = MathHelper.cos(-1.0F + limbSwing * speed * 0.4F) * degree * -0.8F * limbSwingAmount - 0.25F;
			this.leftWingBack.yRot = MathHelper.cos(-1.0F + limbSwing * speed * 0.4F) * degree * -0.2F * limbSwingAmount + 0.25F;
			this.leftWingBack.xRot = 0;
		}
		else {
			float speed = 1.0f;
			float degree = 1.0f;
			this.body.zRot = MathHelper.cos(limbSwing * speed * 0.2F) * degree * 0.1F * limbSwingAmount;
			this.body.xRot = MathHelper.cos(limbSwing * speed * 0.4F) * degree * 0.2F * limbSwingAmount - 0.05F;
			this.body.yRot = 0;
			this.rightWingFront.zRot = MathHelper.cos(1.0F + limbSwing * speed * 0.4F) * degree * 1.2F * limbSwingAmount;
			this.rightWingFront.yRot = MathHelper.cos(1.0F + limbSwing * speed * 0.4F) * degree * 0.6F * limbSwingAmount - 0.2F;
			this.rightWingFront.xRot = 0;
			this.rightWingBack.zRot = MathHelper.cos(1.0F + limbSwing * speed * 0.4F) * degree * 1.2F * limbSwingAmount;
			this.rightWingBack.yRot = MathHelper.cos(1.0F + limbSwing * speed * 0.4F) * degree * 0.6F * limbSwingAmount - 0.2F;
			this.rightWingBack.xRot = 0;
			this.leftWingFront.zRot = MathHelper.cos(1.0F + limbSwing * speed * 0.4F) * degree * -1.2F * limbSwingAmount;
			this.leftWingFront.yRot = MathHelper.cos(1.0F + limbSwing * speed * 0.4F) * degree * -0.6F * limbSwingAmount + 0.2F;
			this.leftWingFront.xRot = 0;
			this.leftWingBack.zRot = MathHelper.cos(1.0F + limbSwing * speed * 0.4F) * degree * -1.2F * limbSwingAmount;
			this.leftWingBack.yRot = MathHelper.cos(1.0F + limbSwing * speed * 0.4F) * degree * -0.6F * limbSwingAmount + 0.2F;
			this.leftWingBack.xRot = 0;
			this.tail.zRot = MathHelper.cos(limbSwing * speed * 0.2F) * degree * 0.3F * limbSwingAmount;
			this.tail.xRot = MathHelper.cos(limbSwing * speed * 0.4F) * degree * 0.4F * limbSwingAmount;
			this.tail.yRot = 0;
		}
	}
}
