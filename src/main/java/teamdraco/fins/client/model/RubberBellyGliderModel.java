package teamdraco.fins.client.model;

import com.google.common.collect.ImmutableList;
import teamdraco.fins.common.entities.RubberBellyGliderEntity;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

import java.util.Collections;

public class RubberBellyGliderModel<T extends Entity> extends AgeableModel<RubberBellyGliderEntity> {
	private final ModelRenderer body;
	private final ModelRenderer tail;
	private final ModelRenderer leftWingFront;
	private final ModelRenderer rightWingFront;
	private final ModelRenderer rightWingMid;
	private final ModelRenderer leftWingMid;
	private final ModelRenderer leftWingBack;
	private final ModelRenderer rightWingBack;
	private final Iterable<ModelRenderer> parts;

	public RubberBellyGliderModel() {
		textureWidth = 64;
		textureHeight = 48;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 23.625F, -2.0F);
		body.setTextureOffset(0, 0).addBox(-2.0F, -2.625F, -10.0F, 4.0F, 3.0F, 16.0F, 0.0F, false);
		body.setTextureOffset(0, 19).addBox(-5.0F, -0.625F, -5.0F, 10.0F, 7.0F, 10.0F, 0.0F, false);
		body.setTextureOffset(0, 6).addBox(-1.5F, -4.625F, -9.0F, 3.0F, 2.0F, 2.0F, 0.0F, false);

		tail = new ModelRenderer(this);
		tail.setRotationPoint(0.0F, -1.125F, 6.0F);
		body.addChild(tail);
		tail.setTextureOffset(24, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 14.0F, 0.0F, false);
		tail.setTextureOffset(0, 20).addBox(0.0F, -6.0F, -1.0F, 0.0F, 10.0F, 16.0F, 0.0F, false);

		leftWingFront = new ModelRenderer(this);
		leftWingFront.setRotationPoint(-2.0F, -2.125F, -6.0F);
		body.addChild(leftWingFront);
		leftWingFront.setTextureOffset(34, 34).addBox(-12.0F, 0.0F, 0.0F, 12.0F, 0.0F, 6.0F, 0.0F, true);

		rightWingFront = new ModelRenderer(this);
		rightWingFront.setRotationPoint(2.0F, -2.125F, -6.0F);
		body.addChild(rightWingFront);
		rightWingFront.setTextureOffset(34, 34).addBox(0.0F, 0.0F, 0.0F, 12.0F, 0.0F, 6.0F, 0.0F, false);

		rightWingMid = new ModelRenderer(this);
		rightWingMid.setRotationPoint(2.0F, -1.125F, -3.0F);
		body.addChild(rightWingMid);
		rightWingMid.setTextureOffset(0, 3).addBox(0.0F, 0.0F, 0.0F, 4.0F, 0.0F, 3.0F, 0.0F, false);

		leftWingMid = new ModelRenderer(this);
		leftWingMid.setRotationPoint(-2.0F, -1.125F, -3.0F);
		body.addChild(leftWingMid);
		leftWingMid.setTextureOffset(0, 3).addBox(-4.0F, 0.0F, 0.0F, 4.0F, 0.0F, 3.0F, 0.0F, true);

		leftWingBack = new ModelRenderer(this);
		leftWingBack.setRotationPoint(2.0F, -0.125F, 2.0F);
		body.addChild(leftWingBack);
		leftWingBack.setTextureOffset(0, 13).addBox(0.0F, -1.0F, 0.0F, 4.0F, 0.0F, 3.0F, 0.0F, false);

		rightWingBack = new ModelRenderer(this);
		rightWingBack.setRotationPoint(-2.0F, -0.125F, 2.0F);
		body.addChild(rightWingBack);
		rightWingBack.setTextureOffset(0, 13).addBox(-4.0F, -1.0F, 0.0F, 4.0F, 0.0F, 3.0F, 0.0F, true);

		parts = ImmutableList.of(body);
	}

	@Override
	protected Iterable<ModelRenderer> getHeadParts() {
		return Collections.emptyList();
	}

	@Override
	protected Iterable<ModelRenderer> getBodyParts() {
		return parts;
	}

	@Override
	public void setRotationAngles(RubberBellyGliderEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
	}
}
