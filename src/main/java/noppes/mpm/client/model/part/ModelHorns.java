package noppes.mpm.client.model.part;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import noppes.mpm.ModelData;
import noppes.mpm.ModelPartData;
import noppes.mpm.client.model.ModelMPM;
import noppes.mpm.client.model.ModelPartInterface;
import noppes.mpm.client.model.part.horns.ModelAntennasFront;
import noppes.mpm.client.model.part.horns.ModelAntlerHorns;
import noppes.mpm.client.model.part.horns.ModelBullHorns;

public class ModelHorns extends ModelPartInterface
{
  private ModelRenderer bull;
  private ModelRenderer antlers;
  private ModelRenderer antennasBack;
  private ModelRenderer antennasFront;
  
  public ModelHorns(ModelMPM base)
  {
    super(base);
    
    addChild(this.bull = new ModelBullHorns(base));
    addChild(this.antlers = new ModelAntlerHorns(base));
    addChild(this.antennasBack = new noppes.mpm.client.model.part.horns.ModelAntennasBack(base));
    addChild(this.antennasFront = new ModelAntennasFront(base));
  }
  


  public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity) {}
  


  public void initData(ModelData data)
  {
    ModelPartData config = data.getPartData("horns");
    if (config == null)
    {
      this.isHidden = true;
      return;
    }
    this.color = config.color;
    this.isHidden = false;
    
    this.bull.isHidden = (config.type != 0);
    this.antlers.isHidden = (config.type != 1);
    this.antennasBack.isHidden = (config.type != 2);
    this.antennasFront.isHidden = (config.type != 3);
    
    if (!config.playerTexture) {
      this.location = config.getResource();
    }
    else {
      this.location = null;
    }
  }
}
