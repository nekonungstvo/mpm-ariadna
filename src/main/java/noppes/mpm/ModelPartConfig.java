package noppes.mpm;

import net.minecraft.nbt.NBTTagCompound;

public class ModelPartConfig {
  public float scaleX = 1.0F; public float scaleY = 1.0F; public float scaleZ = 1.0F;
  public float transX = 0.0F; public float transY = 0.0F; public float transZ = 0.0F;
  
  public NBTTagCompound writeToNBT()
  {
    NBTTagCompound compound = new NBTTagCompound();
    compound.setFloat("ScaleX", this.scaleX);
    compound.setFloat("ScaleY", this.scaleY);
    compound.setFloat("ScaleZ", this.scaleZ);
    
    compound.setFloat("TransX", this.transX);
    compound.setFloat("TransY", this.transY);
    compound.setFloat("TransZ", this.transZ);
    return compound;
  }
  
  public void readFromNBT(NBTTagCompound compound) {
    this.scaleX = checkValue(compound.getFloat("ScaleX"), 0.5F, 1.5F);
    this.scaleY = checkValue(compound.getFloat("ScaleY"), 0.5F, 1.5F);
    this.scaleZ = checkValue(compound.getFloat("ScaleZ"), 0.5F, 1.5F);
    
    this.transX = checkValue(compound.getFloat("TransX"), 0.0F, 1.0F);
    this.transY = checkValue(compound.getFloat("TransY"), 0.0F, 1.0F);
    this.transZ = checkValue(compound.getFloat("TransZ"), 0.0F, 1.0F);
  }
  
  public String toString() {
    return "ScaleX: " + this.scaleX + " - ScaleY: " + this.scaleY + " - ScaleZ: " + this.scaleZ;
  }
  
  public void setScale(float x, float y, float z) {
    this.scaleX = x;
    this.scaleY = y;
    this.scaleZ = z;
  }
  
  public void setScale(float x, float y) { this.scaleZ = (this.scaleX = x);
    this.scaleY = y;
  }
  
  public float checkValue(float given, float min, float max) {
    if (given < min)
      return min;
    if (given > max)
      return max;
    return given;
  }
}
