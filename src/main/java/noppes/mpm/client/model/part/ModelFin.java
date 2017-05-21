package noppes.mpm.client.model.part;

import noppes.mpm.ModelData;
import noppes.mpm.ModelPartData;
import noppes.mpm.client.model.Model2DRenderer;
import noppes.mpm.client.model.ModelMPM;
import noppes.mpm.client.model.ModelPartInterface;

public class ModelFin extends ModelPartInterface
{
  private Model2DRenderer model;
  
  public ModelFin(ModelMPM base)
  {
    super(base);
    this.model = new Model2DRenderer(base, 56.0F, 20.0F, 8, 12, 64.0F, 32.0F);
    this.model.setRotationPoint(-0.5F, 12.0F, 10.0F);
    this.model.setScale(0.74F);
    this.model.rotateAngleY = 1.5707964F;
    addChild(this.model);
  }
  
  public void initData(ModelData data)
  {
    ModelPartData config = data.getPartData("fin");
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
