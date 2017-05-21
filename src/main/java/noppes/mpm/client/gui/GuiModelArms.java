package noppes.mpm.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import noppes.mpm.ModelData;
import noppes.mpm.ModelPartData;
import noppes.mpm.client.gui.util.GuiModelInterface;
import noppes.mpm.client.gui.util.GuiNpcButton;

public class GuiModelArms extends GuiModelInterface
{
  private final String[] arrParticles = { "gui.no", "Both", "Left", "Right" };
  private GuiScreen parent;
  
  public GuiModelArms(GuiScreen parent) {
    this.parent = parent;
    this.xOffset = 60;
  }
  
  public void initGui()
  {
    super.initGui();
    int y = this.guiTop + 20;
    
    ModelPartData claws = this.playerdata.getPartData("claws");
    y += 22;addButton(new GuiNpcButton(0, this.guiLeft + 50, y, 70, 20, this.arrParticles, claws == null ? 0 : claws.type + 1));
    addLabel(new noppes.mpm.client.gui.util.GuiNpcLabel(0, "Claws", this.guiLeft, y + 5, 16777215));
    if (claws != null) {
      addButton(new GuiNpcButton(10, this.guiLeft + 122, y, 40, 20, claws.getColor()));
    }
  }
  
  protected void actionPerformed(net.minecraft.client.gui.GuiButton btn)
  {
    super.actionPerformed(btn);
    GuiNpcButton button = (GuiNpcButton)btn;
    
    if (button.id == 0) {
      if (button.getValue() == 0) {
        this.playerdata.removePart("claws");
      } else {
        ModelPartData data = this.playerdata.getOrCreatePart("claws");
        data.type = ((byte)(button.getValue() - 1));
      }
      initGui();
    }
    if (button.id == 10) {
      this.mc.displayGuiScreen(new GuiModelColor(this, this.playerdata.getPartData("claws")));
    }
  }
  
  public void close()
  {
    this.mc.displayGuiScreen(this.parent);
  }
}
