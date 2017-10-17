package noppes.mpm.client.fx;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.particle.EntityPortalFX;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import noppes.mpm.ModelPartData;
import noppes.mpm.client.ClientProxy;
import org.lwjgl.opengl.GL11;

public class EntityEnderFX extends EntityPortalFX {
    private static final ResourceLocation resource = new ResourceLocation("textures/particle/particles.png");
    private final ResourceLocation location;
    private float portalParticleScale;
    private int particleNumber;
    private AbstractClientPlayer player;
    private boolean move = true;
    private float startX = 0.0F;
    private float startY = 0.0F;
    private float startZ = 0.0F;

    public EntityEnderFX(AbstractClientPlayer player, double par2, double par4, double par6, double par8, double par10, double par12, ModelPartData data) {
        super(player.worldObj, par2, par4, par6, par8, par10, par12);

        this.player = player;
        this.particleNumber = player.getRNG().nextInt(2);
        this.portalParticleScale = (this.particleScale = this.rand.nextFloat() * 0.2F + 0.5F);

        this.particleRed = ((data.color >> 16 & 0xFF) / 255.0F);
        this.particleGreen = ((data.color >> 8 & 0xFF) / 255.0F);
        this.particleBlue = ((data.color & 0xFF) / 255.0F);

        if (player.getRNG().nextInt(3) == 1) {
            this.move = false;
            this.startX = ((float) player.posX);
            this.startY = ((float) player.posY);
            this.startZ = ((float) player.posZ);
        }

        if (data.playerTexture) {
            this.location = player.getLocationSkin();
        } else {
            this.location = new ResourceLocation(data.texture);
        }
    }

    public void renderParticle(Tessellator par1Tessellator, float par2, float par3, float par4, float par5, float par6, float par7) {
        if (this.move) {
            this.startX = ((float) (this.player.prevPosX + (this.player.posX - this.player.prevPosX) * par2));
            this.startY = ((float) (this.player.prevPosY + (this.player.posY - this.player.prevPosY) * par2));
            this.startZ = ((float) (this.player.prevPosZ + (this.player.posZ - this.player.prevPosZ) * par2));
        }
        Tessellator tessellator = Tessellator.instance;
        tessellator.draw();
        float scale = (this.particleAge + par2) / this.particleMaxAge;
        scale = 1.0F - scale;
        scale *= scale;
        scale = 1.0F - scale;
        this.particleScale = (this.portalParticleScale * scale);

        ClientProxy.bindTexture(this.location);

        float f = 0.875F;
        float f1 = f + 0.125F;
        float f2 = 0.75F - this.particleNumber * 0.25F;
        float f3 = f2 + 0.25F;
        float f4 = 0.1F * this.particleScale;
        float f5 = (float) (this.prevPosX + (this.posX - this.prevPosX) * par2 - interpPosX + this.startX);
        float f6 = (float) (this.prevPosY + (this.posY - this.prevPosY) * par2 - interpPosY + this.startY);
        float f7 = (float) (this.prevPosZ + (this.posZ - this.prevPosZ) * par2 - interpPosZ + this.startZ);

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        tessellator.startDrawingQuads();
        tessellator.setBrightness(240);
        par1Tessellator.setColorOpaque_F(1.0F, 1.0F, 1.0F);
        par1Tessellator.setColorRGBA_F(this.particleRed, this.particleGreen, this.particleBlue, 1.0F);
        par1Tessellator.addVertexWithUV(f5 - par3 * f4 - par6 * f4, f6 - par4 * f4, f7 - par5 * f4 - par7 * f4, f1, f3);
        par1Tessellator.addVertexWithUV(f5 - par3 * f4 + par6 * f4, f6 + par4 * f4, f7 - par5 * f4 + par7 * f4, f1, f2);
        par1Tessellator.addVertexWithUV(f5 + par3 * f4 + par6 * f4, f6 + par4 * f4, f7 + par5 * f4 + par7 * f4, f, f2);
        par1Tessellator.addVertexWithUV(f5 + par3 * f4 - par6 * f4, f6 - par4 * f4, f7 + par5 * f4 - par7 * f4, f, f3);

        tessellator.draw();
        ClientProxy.bindTexture(resource);
        tessellator.startDrawingQuads();
    }

    public int getFXLayer() {
        return 0;
    }
}
