package noppes.mpm.client.model;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import noppes.mpm.ModelData;
import noppes.mpm.ModelPartData;
import noppes.mpm.client.ClientProxy;
import org.lwjgl.opengl.GL11;

public abstract class ModelPartInterface
  extends ModelRenderer
{
  public ModelData data;
  private EntityLivingBase entity;
  public float scale = 1.0F;
  protected ResourceLocation location;
  public int color = 16777215;
  public ModelMPM base;
  
  public ModelPartInterface(ModelMPM par1ModelBase)
  {
    super(par1ModelBase);
    this.base = par1ModelBase;
    setTextureSize(0, 0);
  }
  
  public void setRotation(ModelRenderer model, float x, float y, float z) {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  

  public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity) {}
  

  public void setLivingAnimations(ModelPartData data, EntityLivingBase entityliving, float f, float f1, float f2) {}
  

  public void setData(ModelData data, EntityLivingBase entity)
  {
    this.data = data;
    this.entity = entity;
    initData(data);
  }
  
  public void render(float par1)
  {
    if ((this.isHidden) || (!this.showModel))
      return;
    if (!this.base.isArmor) {
      if (this.location != null) {
        ClientProxy.bindTexture(this.location);
        this.base.currentlyPlayerTexture = false;
      }
      else if (!this.base.currentlyPlayerTexture) {
        ClientProxy.bindTexture(this.data.playerResource);
        this.base.currentlyPlayerTexture = true;
      }
    }
    boolean bo = (this.entity.hurtTime <= 0) && (this.entity.deathTime <= 0) && (!this.base.isArmor);
    if (bo) {
      float red = (this.color >> 16 & 0xFF) / 255.0F;
      float green = (this.color >> 8 & 0xFF) / 255.0F;
      float blue = (this.color & 0xFF) / 255.0F;
      GL11.glColor4f(red, green, blue, 1.0F);
    }
    super.render(par1);
    renderParts(par1);
    if (bo) {
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }
  }
  
  public void renderParts(float par1) {}
  
  public abstract void initData(ModelData paramModelData);
}
