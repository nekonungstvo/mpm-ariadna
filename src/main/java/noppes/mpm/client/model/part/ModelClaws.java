package noppes.mpm.client.model.part;

import noppes.mpm.ModelData;
import noppes.mpm.ModelPartData;
import noppes.mpm.client.model.Model2DRenderer;
import noppes.mpm.client.model.ModelMPM;
import noppes.mpm.client.model.ModelPartInterface;

public class ModelClaws
  extends ModelPartInterface
{
  private Model2DRenderer model;
  private boolean isRight = false;
  
  public ModelClaws(ModelMPM base, boolean isRight) {
    super(base);
    this.isRight = isRight;
    this.model = new Model2DRenderer(base, 0.0F, 16.0F, 4, 4);
    if (isRight) {
      this.model.setRotationPoint(-2.0F, 14.0F, -2.0F);
    } else
      this.model.setRotationPoint(3.0F, 14.0F, -2.0F);
    this.model.rotateAngleY = -1.5707964F;
    this.model.setScale(0.25F);
    addChild(this.model);
  }
  
  public void initData(ModelData data)
  {
    ModelPartData config = data.getPartData("claws");
    if ((config == null) || ((this.isRight) && (config.type == 1)) || ((!this.isRight) && (config.type == 2)))
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
