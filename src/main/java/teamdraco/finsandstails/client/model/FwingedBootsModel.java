
package teamdraco.finsandstails.client.model;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FwingedBootsModel extends BipedModel<LivingEntity> {
    public static final FwingedBootsModel INSTANCE = new FwingedBootsModel();

    public ModelRenderer bootBaseRight;
    public ModelRenderer bootFurRight;
    public ModelRenderer toeRight2;
    public ModelRenderer toeRight1;
    public ModelRenderer flapRight;
    public ModelRenderer bootBaseLeft;
    public ModelRenderer bootFurLeft;
    public ModelRenderer toeLeft1;
    public ModelRenderer toeLeft2;
    public ModelRenderer flapLeft;

    public FwingedBootsModel() {
        super(0);
        this.texWidth = 64;
        this.texHeight = 128;
        this.bootBaseRight = new ModelRenderer(this, 0, 112);
        this.bootBaseRight.mirror = true;
        this.bootBaseRight.setPos(0.0F, 0.0F, 0.0F);
        this.bootBaseRight.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.5F, 0.5F, 0.5F);
        this.toeRight2 = new ModelRenderer(this, 0, 104);
        this.toeRight2.setPos(-2.5F, 11.5F, -2.0F);
        this.toeRight2.addBox(-0.5F, -1.0F, -6.0F, 1.0F, 2.0F, 6.0F, 0.0F, 0.0F, 0.0F);
        this.toeLeft1 = new ModelRenderer(this, 0, 104);
        this.toeLeft1.setPos(-1.5F, 11.5F, -2.0F);
        this.toeLeft1.addBox(-0.5F, -1.0F, -6.0F, 1.0F, 2.0F, 6.0F, 0.0F, 0.0F, 0.0F);
        this.rightLeg = new ModelRenderer(this, 0, 16);
        this.rightLeg.setPos(-1.9F, 12.0F, 0.0F);
        this.rightLeg.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.bootFurLeft = new ModelRenderer(this, 16, 112);
        this.bootFurLeft.setPos(0.0F, 0.0F, 0.0F);
        this.bootFurLeft.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.75F, 0.75F, 0.75F);
        this.hat = new ModelRenderer(this, 32, 0);
        this.hat.setPos(0.0F, 0.0F, 0.0F);
        this.hat.addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.5F, 0.5F, 0.5F);
        this.hat.addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.5F, 0.5F, 0.5F);
        this.head = new ModelRenderer(this, 0, 0);
        this.head.setPos(0.0F, 0.0F, 0.0F);
        this.head.addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, 0.0F, 0.0F);
        this.bootFurRight = new ModelRenderer(this, 16, 112);
        this.bootFurRight.mirror = true;
        this.bootFurRight.setPos(0.0F, 0.0F, 0.0F);
        this.bootFurRight.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.75F, 0.75F, 0.75F);
        this.rightArm = new ModelRenderer(this, 40, 16);
        this.body.setPos(-5.0F, 2.0F, 0.0F);
        this.body.addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.bootBaseLeft = new ModelRenderer(this, 0, 112);
        this.bootBaseLeft.setPos(0.0F, 0.0F, 0.0F);
        this.bootBaseLeft.addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.5F, 0.5F, 0.5F);
        this.flapLeft = new ModelRenderer(this, 3, 104);
        this.flapLeft.mirror = true;
        this.flapLeft.setPos(0.5F, 11.5F, -2.0F);
        this.flapLeft.addBox(-2.0F, 0.0F, -5.0F, 4.0F, 0.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.toeRight1 = new ModelRenderer(this, 0, 104);
        this.toeRight1.setPos(1.5F, 11.5F, -2.0F);
        this.toeRight1.addBox(-0.5F, -1.0F, -6.0F, 1.0F, 2.0F, 6.0F, 0.0F, 0.0F, 0.0F);
        this.flapRight = new ModelRenderer(this, 3, 104);
        this.flapRight.mirror = true;
        this.flapRight.setPos(-0.5F, 11.5F, -2.0F);
        this.flapRight.addBox(-2.0F, 0.0F, -5.0F, 4.0F, 0.0F, 5.0F, 0.0F, 0.0F, 0.0F);
        this.toeLeft2 = new ModelRenderer(this, 0, 104);
        this.toeLeft2.setPos(2.5F, 11.5F, -2.0F);
        this.toeLeft2.addBox(-0.5F, -1.0F, -6.0F, 1.0F, 2.0F, 6.0F, 0.0F, 0.0F, 0.0F);
        this.body = new ModelRenderer(this, 16, 16);
        this.body.setPos(0.0F, 0.0F, 0.0F);
        this.body.addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.0F, 0.0F, 0.0F);
        this.rightLeg.addChild(this.bootBaseRight);
        this.bootBaseRight.addChild(this.toeRight2);
        this.bootBaseLeft.addChild(this.toeLeft1);
        this.bootBaseLeft.addChild(this.bootFurLeft);
        this.bootBaseRight.addChild(this.bootFurRight);
        this.leftLeg.addChild(this.bootBaseLeft);
        this.bootBaseLeft.addChild(this.flapLeft);
        this.bootBaseRight.addChild(this.toeRight1);
        this.bootBaseRight.addChild(this.flapRight);
        this.bootBaseLeft.addChild(this.toeLeft2);
    }

    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }
}
