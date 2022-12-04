package teamdraco.finsandstails.client.model.armor;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.world.entity.LivingEntity;

public class HorateeJetpackModel<T extends LivingEntity> extends HumanoidModel<T> {
	private final ModelPart body;
	private final ModelPart left_arm;
	private final ModelPart right_arm;

	public HorateeJetpackModel(ModelPart root) {
		super(root);
		this.body = root.getChild("body");
		this.left_arm = root.getChild("left_arm");
		this.right_arm = root.getChild("right_arm");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = createMesh(new CubeDeformation(0), 0);
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(32, 112).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.5F))
				.texOffs(16, 115).addBox(0.0F, 1.0F, 4.0F, 1.0F, 9.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(0, 115).addBox(2.0F, 1.5F, 2.001F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(0, 115).addBox(-6.0F, 1.5F, 2.001F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(0, 103).addBox(-4.0F, 0.0F, 2.0F, 8.0F, 10.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition left_arm = partdefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(32, 95).addBox(-0.0607F, -2F, -1.9995F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.5F)), PartPose.offset(4.0607F, 2F, 0.0005F));

		PartDefinition cube_r1 = left_arm.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 99).mirror().addBox(-1.0F, -2.4F, -4.0F, 5.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-0.0607F, -3F, -0.0005F, 0.0F, -0.7854F, 0.0F));

		PartDefinition right_arm = partdefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(32, 95).mirror().addBox(-3.9393F, -2F, -1.9995F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.5F)).mirror(false), PartPose.offset(-4.0607F, 2F, 0.0005F));

		PartDefinition cube_r2 = right_arm.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 99).addBox(-4.0F, -3.4F, -4.0F, 5.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0607F, -3F, -0.0005F, 0.0F, 0.7854F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 128);
	}

	@Override
	protected Iterable<ModelPart> bodyParts() {
		return ImmutableList.of(this.body, this.right_arm, this.left_arm);
	}

	@Override
	protected Iterable<ModelPart> headParts() {
		return ImmutableList.of();
	}
}