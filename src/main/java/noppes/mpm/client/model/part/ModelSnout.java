package noppes.mpm.client.model.part;

import net.minecraft.client.model.ModelRenderer;
import noppes.mpm.ModelData;
import noppes.mpm.ModelPartData;
import noppes.mpm.client.model.ModelMPM;
import noppes.mpm.client.model.ModelPartInterface;

public class ModelSnout extends ModelPartInterface
{
  private ModelRenderer small;
  private ModelRenderer medium;
  private ModelRenderer large;
  private ModelRenderer bunny;
  private ModelRenderer beak;
  
  public ModelSnout(ModelMPM base)
  {
    super(base);
    
    this.small = new ModelRenderer(base, 24, 0);
    this.small.addBox(0.0F, 0.0F, 0.0F, 4, 3, 1);
    this.small.setRotationPoint(-2.0F, -3.0F, -5.0F);
    addChild(this.small);
    
    this.medium = new ModelRenderer(base, 24, 0);
    this.medium.addBox(0.0F, 0.0F, 0.0F, 4, 3, 2);
    this.medium.setRotationPoint(-2.0F, -3.0F, -6.0F);
    addChild(this.medium);
    
    this.large = new ModelRenderer(base, 24, 0);
    this.large.addBox(0.0F, 0.0F, 0.0F, 4, 3, 3);
    this.large.setRotationPoint(-2.0F, -3.0F, -7.0F);
    addChild(this.large);
    
    this.bunny = new ModelRenderer(base, 24, 0);
    this.bunny.addBox(1.0F, 1.0F, 0.0F, 4, 2, 1);
    this.bunny.setRotationPoint(-3.0F, -4.0F, -5.0F);
    addChild(this.bunny);
    
    ModelRenderer tooth = new ModelRenderer(base, 24, 3);
    tooth.addBox(2.0F, 3.0F, 0.0F, 2, 1, 1);
    tooth.setRotationPoint(0.0F, 0.0F, 0.0F);
    this.bunny.addChild(tooth);
    
    this.beak = new ModelDuckBeak(base);
    this.beak.setRotationPoint(0.0F, 0.0F, -4.0F);
    addChild(this.beak);
  }
  


  public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, net.minecraft.entity.Entity entity) {}
  


  public void initData(ModelData data)
  {
    ModelPartData config = data.getPartData("snout");
    if (config == null)
    {
      this.isHidden = true;
      return;
    }
    
    this.color = config.color;
    this.isHidden = false;
    this.small.isHidden = (config.type != 0);
    this.medium.isHidden = (config.type != 1);
    this.large.isHidden = (config.type != 2);
    this.bunny.isHidden = (config.type != 3);
    this.beak.isHidden = (config.type != 4);
    
    if (!config.playerTexture) {
      this.location = config.getResource();
    }
    else {
      this.location = null;
    }
  }
}
