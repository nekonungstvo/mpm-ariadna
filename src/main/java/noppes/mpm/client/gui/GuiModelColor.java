package noppes.mpm.client.gui;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import noppes.mpm.ModelPartData;
import noppes.mpm.client.gui.util.GuiModelInterface;
import noppes.mpm.client.gui.util.GuiNpcTextField;
import noppes.mpm.client.gui.util.ITextfieldListener;
import org.lwjgl.opengl.GL11;

public class GuiModelColor extends GuiModelInterface implements ITextfieldListener
{
  private GuiScreen parent;
  private static final ResourceLocation color = new ResourceLocation("moreplayermodels:textures/gui/color.png");
  private int colorX;
  private int colorY;
  private GuiNpcTextField textfield;
  private ModelPartData data;
  
  public GuiModelColor(GuiScreen parent, ModelPartData data)
  {
    this.parent = parent;
    this.data = data;
    this.xOffset = 60;
    this.ySize = 230;
  }
  
  public void initGui()
  {
    super.initGui();
    this.colorX = (this.guiLeft + 4);
    this.colorY = (this.guiTop + 50);
    addTextField(this.textfield = new GuiNpcTextField(0, this, this.guiLeft + 25, this.guiTop + 20, 70, 20, this.data.getColor()));
  }
  


  public void keyTyped(char c, int i)
  {
    String prev = this.textfield.getText();
    super.keyTyped(c, i);
    String newText = this.textfield.getText();
    if (newText.equals(prev))
      return;
    try {
      int color = Integer.parseInt(this.textfield.getText(), 16);
      this.data.color = color;
      this.textfield.setTextColor(color);
    }
    catch (NumberFormatException e) {
      this.textfield.setText(prev);
    }
  }
  
  protected void actionPerformed(GuiButton btn)
  {
    super.actionPerformed(btn);
  }
  
  public void close()
  {
    this.mc.displayGuiScreen(this.parent);
  }
  

  public void drawScreen(int par1, int par2, float par3)
  {
    super.drawScreen(par1, par2, par3);
    

    this.mc.getTextureManager().bindTexture(color);
    
    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    drawTexturedModalRect(this.colorX, this.colorY, 0, 0, 120, 120);
  }
  
  public void mouseClicked(int i, int j, int k)
  {
    super.mouseClicked(i, j, k);
    if ((i < this.colorX) || (i > this.colorX + 120) || (j < this.colorY) || (j > this.colorY + 120))
      return;
    InputStream stream = null;
    try {
      IResource resource = this.mc.getResourceManager().getResource(color);
      BufferedImage bufferedimage = ImageIO.read(stream = resource.getInputStream());
      int color = bufferedimage.getRGB((i - this.guiLeft - 4) * 4, (j - this.guiTop - 50) * 4) & 0xFFFFFF;
      if (color != 0) {
        this.data.color = color;
        this.textfield.setTextColor(color);
        this.textfield.setText(this.data.getColor());
      }
      return;
    }
    catch (IOException e) {}finally
    {
      if (stream != null) {
        try {
          stream.close();
        }
        catch (IOException e) {}
      }
    }
  }
  

  public void unFocused(GuiNpcTextField textfield)
  {
    int color = 0;
    try {
      color = Integer.parseInt(textfield.getText(), 16);
    }
    catch (NumberFormatException e) {
      color = 0;
    }
    this.data.color = color;
    textfield.setTextColor(color);
  }
}
