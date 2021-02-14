package mod.coda.fins.client.model;

import com.google.common.collect.ImmutableList;
import mod.coda.fins.entities.FlatbackLeafSnailEntity;
import net.minecraft.client.renderer.entity.model.AgeableModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Collections;

@OnlyIn(Dist.CLIENT)
public class FlatbackLeafSnailModel<T extends Entity> extends AgeableModel<FlatbackLeafSnailEntity> {
    public ModelRenderer body;
    public ModelRenderer shell;
    public ModelRenderer mandibles;
    public ModelRenderer eyes;

    public FlatbackLeafSnailModel() {
        this.textureWidth = 48;
        this.textureHeight = 32;
        this.shell = new ModelRenderer(this, 0, 13);
        this.shell.setRotationPoint(0.0F, -0.5F, -5.0F);
        this.shell.addBox(0.0F, 0.0F, 0.0F, 9.0F, 3.0F, 9.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(shell, 0.12217304763960307F, -0.7853981633974483F, -0.10471975511965977F);
        this.body = new ModelRenderer(this, 0, 0);
        this.body.setRotationPoint(0.0F, 21.0F, 3.0F);
        this.body.addBox(-2.0F, 0.0F, -8.0F, 4.0F, 3.0F, 10.0F, 0.0F, 0.0F, 0.0F);
        this.eyes = new ModelRenderer(this, 0, 4);
        this.eyes.setRotationPoint(0.0F, 0.0F, -7.5F);
        this.eyes.addBox(-1.5F, -3.0F, -1.0F, 3.0F, 4.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.setRotateAngle(eyes, 0.7853981633974483F, 0.0F, 0.017453292519943295F);
        this.mandibles = new ModelRenderer(this, 0, 0);
        this.mandibles.setRotationPoint(0.0F, 2.5F, -8.0F);
        this.mandibles.addBox(-1.0F, -0.5F, -2.0F, 2.0F, 1.0F, 2.0F, 0.0F, 0.0F, 0.0F);
        this.body.addChild(this.shell);
        this.body.addChild(this.eyes);
        this.body.addChild(this.mandibles);
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
    public void setRotationAngles(FlatbackLeafSnailEntity entityIn, float f, float f1, float ageInTicks, float netHeadYaw, float headPitch) {
        this.body.rotateAngleZ = MathHelper.cos(f * 2.0f * 0.4F) * 1.0f * 0.2F * f1;
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
