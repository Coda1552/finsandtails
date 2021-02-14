package mod.coda.fins.client.model;

import com.google.common.collect.ImmutableList;
import mod.coda.fins.entities.RiverPebbleSnailEntity;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Collections;

@OnlyIn(Dist.CLIENT)
public class RiverPebbleSnailModel<T extends Entity> extends AgeableModel<RiverPebbleSnailEntity> {
    public ModelRenderer body;
    public ModelRenderer shell;
    public ModelRenderer stalks;
    public ModelRenderer mouth;

    public RiverPebbleSnailModel() {
        this.textureWidth = 20;
        this.textureHeight = 16;
        this.shell = new ModelRenderer(this, 1, 0);
        this.shell.setRotationPoint(0.0F, 1.0F, 0.0F);
        this.shell.addBox(-2.0F, -3.0F, 0.0F, 4.0F, 4.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(shell, 0.2617993877991494F, 0.0F, 0.0F);
        this.mouth = new ModelRenderer(this, 11, 9);
        this.mouth.setRotationPoint(0.0F, 1.5F, -2.5F);
        this.mouth.addBox(-1.0F, -0.5F, -1.0F, 2.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.stalks = new ModelRenderer(this, 11, 11);
        this.stalks.setRotationPoint(0.0F, 0.0F, -1.5F);
        this.stalks.addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 1.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(stalks, 0.5235987755982988F, 0.0F, 0.0F);
        this.body = new ModelRenderer(this, 0, 9);
        this.body.setRotationPoint(0.0F, 22.0F, 0.0F);
        this.body.addBox(-1.5F, 0.0F, -2.5F, 3.0F, 2.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.body.addChild(this.shell);
        this.body.addChild(this.mouth);
        this.body.addChild(this.stalks);
    }

    @Override
    protected Iterable<ModelRenderer> getHeadParts() {
        return Collections.EMPTY_LIST;
    }

    @Override
    protected Iterable<ModelRenderer> getBodyParts() {
        return ImmutableList.of(body);
    }

    @Override
    public void setRotationAngles(RiverPebbleSnailEntity entityIn, float f, float f1, float ageInTicks, float netHeadYaw, float headPitch) {
        float speed = 1.0f;
        float degree = 6.0f;
        this.shell.rotateAngleZ = MathHelper.cos(f * 2.0f * 0.4F) * 2.0f * 0.2F * f1;
        this.body.rotateAngleY = MathHelper.cos(f * speed * 0.4F) * degree * 0.2F * f1;
        this.stalks.rotateAngleX = MathHelper.cos(f * speed * 0.4F) * degree * 0.2F * f1 + 0.5F;
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
