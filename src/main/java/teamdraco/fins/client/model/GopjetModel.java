package teamdraco.fins.client.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class GopjetModel<T extends Entity> extends EntityModel<T> {
	private final ModelRenderer body;
	private final ModelRenderer tail;
	private final ModelRenderer jet_right;
	private final ModelRenderer jet_left;
	private final ModelRenderer fin_right;
	private final ModelRenderer fin_left;

	public GopjetModel() {
		textureWidth = 48;
		textureHeight = 48;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 21.5F, -2.5F);
		body.setTextureOffset(0, 0).addBox(-4.0F, -2.5F, -5.5F, 8.0F, 5.0F, 11.0F, 0.0F, false);

		tail = new ModelRenderer(this);
		tail.setRotationPoint(0.0F, 0.5F, 5.5F);
		body.addChild(tail);
		tail.setTextureOffset(15, 16).addBox(-1.5F, -1.0F, 0.0F, 3.0F, 3.0F, 11.0F, 0.0F, false);
		tail.setTextureOffset(0, 3).addBox(0.0F, -3.0F, 0.0F, 0.0F, 5.0F, 13.0F, 0.0F, false);

		jet_right = new ModelRenderer(this);
		jet_right.setRotationPoint(-4.0F, -3.0F, 0.5F);
		body.addChild(jet_right);
		setRotationAngle(jet_right, 0.0F, 0.0F, -0.6109F);
		jet_right.setTextureOffset(0, 30).addBox(-3.0F, -2.0F, 0.0F, 5.0F, 3.0F, 6.0F, 0.0F, true);

		jet_left = new ModelRenderer(this);
		jet_left.setRotationPoint(4.0F, -3.0F, 0.5F);
		body.addChild(jet_left);
		setRotationAngle(jet_left, 0.0F, 0.0F, 0.6109F);
		jet_left.setTextureOffset(0, 30).addBox(-2.0F, -2.0F, 0.0F, 5.0F, 3.0F, 6.0F, 0.0F, false);

		fin_right = new ModelRenderer(this);
		fin_right.setRotationPoint(-4.0F, 2.5F, 0.0F);
		body.addChild(fin_right);
		fin_right.setTextureOffset(10, 21).addBox(-5.0F, 0.0F, -1.5F, 5.0F, 0.0F, 3.0F, 0.0F, true);

		fin_left = new ModelRenderer(this);
		fin_left.setRotationPoint(4.0F, 2.5F, 0.0F);
		body.addChild(fin_left);
		fin_left.setTextureOffset(10, 21).addBox(0.0F, 0.0F, -1.5F, 5.0F, 0.0F, 3.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(Entity entity, float f, float f1, float ageInTicks, float netHeadYaw, float headPitch) {
		float degree = 1.0f;
		float speed = 3.0f;
		this.body.rotateAngleY = MathHelper.cos(f * speed * 0.4F) * degree * 0.5F * f1;
		this.tail.rotateAngleY = MathHelper.cos(f * speed * 0.4F) * degree * -0.5F * f1;
		this.fin_left.rotateAngleZ = MathHelper.cos(f * speed * 0.4F) * degree * -1.2F * f1 - 0.3f;
		this.fin_right.rotateAngleZ = MathHelper.cos(f * speed * 0.4F) * degree * 1.2F * f1 + 0.3f;
	}

		@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		body.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}