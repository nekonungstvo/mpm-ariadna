package noppes.mpm.client.gui.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.GameSettings.Options;
import org.lwjgl.opengl.GL11;

public class GuiSlider
  extends GuiButton
{
  public float sliderValue = 1.0F;
  

  public boolean dragging;
  
  private GameSettings.Options idFloat;
  

  public GuiSlider(int par1, int par2, int par3, GameSettings.Options par4EnumOptions, String par5Str, float par6)
  {
    super(par1, par2, par3, 150, 20, par5Str);
    this.idFloat = par4EnumOptions;
    this.sliderValue = par6;
  }
  

  public int getHoverState(boolean par1)
  {
    return 0;
  }
  




  public void mouseDragged(Minecraft par1Minecraft, int par2, int par3)
  {
    if (this.visible)
    {
      if (this.dragging)
      {
        this.sliderValue = (float)(par2 - (this.xPosition + 4)) / (float)(this.width - 8);
        
        if (this.sliderValue < 0.0F)
        {
          this.sliderValue = 0.0F;
        }
        
        if (this.sliderValue > 1.0F)
        {
          this.sliderValue = 1.0F;
        }
        
        par1Minecraft.gameSettings.setOptionFloatValue(this.idFloat, this.sliderValue);
        this.displayString = par1Minecraft.gameSettings.getKeyBinding(this.idFloat);
      }
      
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      drawTexturedModalRect(this.xPosition + (int)(this.sliderValue * (this.width - 8)), this.yPosition, 0, 66, 4, 20);
      drawTexturedModalRect(this.xPosition + (int)(this.sliderValue * (this.width - 8)) + 4, this.yPosition, 196, 66, 4, 20);
    }
  }
  





  public boolean mousePressed(Minecraft par1Minecraft, int par2, int par3)
  {
    if (super.mousePressed(par1Minecraft, par2, par3))
    {
      this.sliderValue = ((par2 - (this.xPosition + 4)) / (this.width - 8));
      
      if (this.sliderValue < 0.0F)
      {
        this.sliderValue = 0.0F;
      }
      
      if (this.sliderValue > 1.0F)
      {
        this.sliderValue = 1.0F;
      }
      
      par1Minecraft.gameSettings.setOptionFloatValue(this.idFloat, this.sliderValue);
      this.displayString = par1Minecraft.gameSettings.getKeyBinding(this.idFloat);
      this.dragging = true;
      return true;
    }
    

    return false;
  }
  





  public void func_146111_b(int par1, int par2)
  {
    this.dragging = false;
  }
}
