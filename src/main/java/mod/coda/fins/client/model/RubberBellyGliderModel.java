//package mod.coda.fins.client.model;
//
//import com.google.common.collect.ImmutableList;
//import com.mojang.blaze3d.matrix.MatrixStack;
//import com.mojang.blaze3d.vertex.IVertexBuilder;
//import mod.coda.fins.entity.RubberBellyGliderEntity;
//import net.minecraft.client.renderer.entity.model.AgeableModel;
//import net.minecraft.client.renderer.model.ModelRenderer;
//import net.minecraft.entity.Entity;
//
//import java.util.Collections;
//
//public class RubberBellyGliderModel<T extends Entity> extends AgeableModel<RubberBellyGliderEntity> {
//	private final ModelRenderer body;
//	private final ModelRenderer tail;
//	private final ModelRenderer left_wing_front;
//	private final ModelRenderer right_wing_front;
//	private final ModelRenderer right_wing_mid;
//	private final ModelRenderer left_wing_mid;
//	private final ModelRenderer left_wing_back;
//	private final ModelRenderer right_wing_back;
//
//	public RubberBellyGliderModel() {
//		textureWidth = 64;
//		textureHeight = 48;
//
//		body = new ModelRenderer(this);
//		body.setRotationPoint(0.0F, 23.625F, -2.0F);
//		body.setTextureOffset(0, 0).addBox(-2.0F, -2.625F, -10.0F, 4.0F, 3.0F, 16.0F, 0.0F, false);
//		body.setTextureOffset(0, 19).addBox(-5.0F, -0.625F, -5.0F, 10.0F, 7.0F, 10.0F, 0.0F, false);
//		body.setTextureOffset(0, 6).addBox(-1.5F, -4.625F, -9.0F, 3.0F, 2.0F, 2.0F, 0.0F, false);
//
//		tail = new ModelRenderer(this);
//		tail.setRotationPoint(0.0F, -1.125F, 6.0F);
//		body.addChild(tail);
//		tail.setTextureOffset(24, 0).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 14.0F, 0.0F, false);
//		tail.setTextureOffset(0, 20).addBox(0.0F, -6.0F, -1.0F, 0.0F, 10.0F, 16.0F, 0.0F, false);
//
//		left_wing_front = new ModelRenderer(this);
//		left_wing_front.setRotationPoint(-2.0F, -2.125F, -6.0F);
//		body.addChild(left_wing_front);
//		left_wing_front.setTextureOffset(34, 34).addBox(-12.0F, 0.0F, 0.0F, 12.0F, 0.0F, 6.0F, 0.0F, true);
//
//		right_wing_front = new ModelRenderer(this);
//		right_wing_front.setRotationPoint(2.0F, -2.125F, -6.0F);
//		body.addChild(right_wing_front);
//		right_wing_front.setTextureOffset(34, 34).addBox(0.0F, 0.0F, 0.0F, 12.0F, 0.0F, 6.0F, 0.0F, false);
//
//		right_wing_mid = new ModelRenderer(this);
//		right_wing_mid.setRotationPoint(2.0F, -1.125F, -3.0F);
//		body.addChild(right_wing_mid);
//		right_wing_mid.setTextureOffset(0, 3).addBox(0.0F, 0.0F, 0.0F, 4.0F, 0.0F, 3.0F, 0.0F, false);
//
//		left_wing_mid = new ModelRenderer(this);
//		left_wing_mid.setRotationPoint(-2.0F, -1.125F, -3.0F);
//		body.addChild(left_wing_mid);
//		left_wing_mid.setTextureOffset(0, 3).addBox(-4.0F, 0.0F, 0.0F, 4.0F, 0.0F, 3.0F, 0.0F, true);
//
//		left_wing_back = new ModelRenderer(this);
//		left_wing_back.setRotationPoint(2.0F, -0.125F, 2.0F);
//		body.addChild(left_wing_back);
//		left_wing_back.setTextureOffset(0, 13).addBox(0.0F, -1.0F, 0.0F, 4.0F, 0.0F, 3.0F, 0.0F, false);
//
//		right_wing_back = new ModelRenderer(this);
//		right_wing_back.setRotationPoint(-2.0F, -0.125F, 2.0F);
//		body.addChild(right_wing_back);
//		right_wing_back.setTextureOffset(0, 13).addBox(-4.0F, -1.0F, 0.0F, 4.0F, 0.0F, 3.0F, 0.0F, true);
//	}
//
//	@Override
//	protected Iterable<ModelRenderer> getHeadParts() {
//		return Collections.EMPTY_LIST;
//	}
//
//	@Override
//	protected Iterable<ModelRenderer> getBodyParts() {
//		return ImmutableList.of(body);
//	}
//
//	@Override
//	public void setRotationAngles(RubberBellyGliderEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
//	}
//
//	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
//		modelRenderer.rotateAngleX = x;
//		modelRenderer.rotateAngleY = y;
//		modelRenderer.rotateAngleZ = z;
//	}
//}