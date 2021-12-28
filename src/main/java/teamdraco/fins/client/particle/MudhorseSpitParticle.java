package teamdraco.fins.client.particle;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BasicParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class MudhorseSpitParticle extends SpriteTexturedParticle {

    protected MudhorseSpitParticle(ClientWorld p_i232447_1_, double p_i232447_2_, double p_i232447_4_, double p_i232447_6_) {
        super(p_i232447_1_, p_i232447_2_, p_i232447_4_, p_i232447_6_);
        this.scale(2.5F);
        this.lifetime = 8;
        this.hasPhysics = true;
    }

    @Override
    public IParticleRenderType getRenderType() {
        return IParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    @OnlyIn(Dist.CLIENT)
    public static class Factory implements IParticleFactory<BasicParticleType> {
        private final IAnimatedSprite sprite;

        public Factory(IAnimatedSprite p_107739_) {
            this.sprite = p_107739_;
        }

        public Particle createParticle(BasicParticleType p_107750_, ClientWorld p_107751_, double p_107752_, double p_107753_, double p_107754_, double p_107755_, double p_107756_, double p_107757_) {
            MudhorseSpitParticle electricityParticle = new MudhorseSpitParticle(p_107751_, p_107752_, p_107753_, p_107754_);
            electricityParticle.setAlpha(1.0F);
            return electricityParticle;
        }
    }
}
