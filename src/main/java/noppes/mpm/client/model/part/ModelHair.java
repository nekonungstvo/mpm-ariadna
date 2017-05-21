package noppes.mpm.client.model.part;

import net.minecraft.client.model.ModelRenderer;
import noppes.mpm.ModelData;
import noppes.mpm.ModelPartData;
import noppes.mpm.client.model.Model2DRenderer;
import noppes.mpm.client.model.ModelMPM;
import noppes.mpm.client.model.ModelPartInterface;

public class ModelHair extends ModelPartInterface
{
  private Model2DRenderer model;
  
  public ModelHair(ModelMPM base)
  {
    super(base);
    this.model = new Model2DRenderer(base, 56.0F, 20.0F, 8, 12, 64.0F, 32.0F);
    this.model.setRotationPoint(-4.0F, 12.0F, 3.0F);
    this.model.setScale(0.75F);
    addChild(this.model);
  }
  

  public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, net.minecraft.entity.Entity entity)
  {
    ModelRenderer parent = this.base.bipedHead;
    if (parent.rotateAngleX < 0.0F) {
      this.rotateAngleX = (-parent.rotateAngleX * 1.2F);
      if (parent.rotateAngleX > -1.0F) {
        this.rotationPointY = (-parent.rotateAngleX * 1.5F);
        this.rotationPointZ = (-parent.rotateAngleX * 1.5F);
      }
    }
    else {
      this.rotateAngleX = 0.0F;
      this.rotationPointY = 0.0F;
      this.rotationPointZ = 0.0F;
    }
  }
  
  public void initData(ModelData data)
  {
    ModelPartData config = data.getPartData("hair");
    if (config == null)
    {
      this.isHidden = true;
      return;
    }
    this.color = config.color;
    this.isHidden = false;
    if (!config.playerTexture) {
      this.location = config.getResource();
    }
    else {
      this.location = null;
    }
  }
}
