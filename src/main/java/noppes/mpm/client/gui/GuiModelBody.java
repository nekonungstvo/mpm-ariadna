package noppes.mpm.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import noppes.mpm.ModelData;
import noppes.mpm.ModelPartData;
import noppes.mpm.client.gui.util.GuiNpcButton;
import noppes.mpm.client.gui.util.GuiNpcLabel;

public class GuiModelBody extends noppes.mpm.client.gui.util.GuiModelInterface
{
  private GuiScreen parent;
  private final String[] arrWing = { "gui.no", "Player", "Type1", "Type2", "Type3" };
  private final String[] arrBreasts = { "gui.no", "Type1", "Type2", "Type3" };
  private final String[] arrParticles = { "gui.no", "Player", "Type1", "Type2", "Rainbow" };
  private final String[] arrfins = { "gui.no", "Player", "Type1" };
  private final String[] arrskirt = { "gui.no", "Player", "Type1" };
  
  public GuiModelBody(GuiScreen parent) {
    this.parent = parent;
    this.xOffset = 60;
  }
  
  public void initGui()
  {
    super.initGui();
    int y = this.guiTop + 20;
    y += 22;addButton(new GuiNpcButton(1, this.guiLeft + 50, y, 70, 20, this.arrBreasts, this.playerdata.breasts));
    addLabel(new GuiNpcLabel(1, "Breasts", this.guiLeft, y + 5, 16777215));
    
    ModelPartData wing = this.playerdata.getPartData("wings");
    y += 22;addButton(new GuiNpcButton(0, this.guiLeft + 50, y, 70, 20, this.arrWing, wing == null ? 0 : wing.type + 1));
    addLabel(new GuiNpcLabel(0, "Wings", this.guiLeft, y + 5, 16777215));
    if (wing != null) {
      addButton(new GuiNpcButton(11, this.guiLeft + 122, y, 40, 20, wing.getColor()));
    }
    ModelPartData particles = this.playerdata.getPartData("particles");
    y += 22;addButton(new GuiNpcButton(2, this.guiLeft + 50, y, 70, 20, this.arrParticles, getParticleIndex(particles)));
    addLabel(new GuiNpcLabel(2, "Particles", this.guiLeft, y + 5, 16777215));
    if ((particles != null) && (particles.type != 1)) {
      addButton(new GuiNpcButton(12, this.guiLeft + 122, y, 40, 20, particles.getColor()));
    }
    ModelPartData fin = this.playerdata.getPartData("fin");
    y += 22;addButton(new GuiNpcButton(3, this.guiLeft + 50, y, 70, 20, this.arrfins, getFinIndex(fin)));
    addLabel(new GuiNpcLabel(3, "Fin", this.guiLeft, y + 5, 16777215));
    if (fin != null) {
      addButton(new GuiNpcButton(13, this.guiLeft + 122, y, 40, 20, fin.getColor()));
    }
    ModelPartData skirt = this.playerdata.getPartData("skirt");
    y += 22;addButton(new GuiNpcButton(4, this.guiLeft + 50, y, 70, 20, this.arrfins, getFinIndex(skirt)));
    addLabel(new GuiNpcLabel(4, "Skirt", this.guiLeft, y + 5, 16777215));
    if (skirt != null) {
      addButton(new GuiNpcButton(14, this.guiLeft + 122, y, 40, 20, skirt.getColor()));
    }
  }
  
  private int getFinIndex(ModelPartData fin)
  {
    if (fin == null)
      return 0;
    return fin.playerTexture ? 1 : 2;
  }
  
  private int getParticleIndex(ModelPartData particles) {
    if (particles == null)
      return 0;
    if (particles.type == 0) {
      if (particles.playerTexture)
        return 1;
      if (particles.texture.contains("1"))
        return 2;
      if (particles.texture.contains("2"))
        return 3;
    }
    if (particles.type == 1) {
      return 4;
    }
    
    return 0;
  }
  
  protected void actionPerformed(net.minecraft.client.gui.GuiButton btn)
  {
    super.actionPerformed(btn);
    GuiNpcButton button = (GuiNpcButton)btn;
    
    if (button.id == 0) {
      if (button.getValue() == 0) {
        this.playerdata.removePart("wings");
      } else {
        ModelPartData data = this.playerdata.getOrCreatePart("wings");
        if (button.getValue() > 1)
          data.setTexture("wings/wing" + (button.getValue() - 1), button.getValue() - 1);
      }
      initGui();
    }
    if (button.id == 1) {
      this.playerdata.breasts = ((byte)button.getValue());
    }
    if (button.id == 2) {
      int value = button.getValue();
      if (value == 0) {
        this.playerdata.removePart("particles");
      } else {
        ModelPartData particles = this.playerdata.getOrCreatePart("particles");
        if (value == 1)
          particles.setTexture("", 0);
        if (value == 2)
          particles.setTexture("particle/type1", 0);
        if (value == 3)
          particles.setTexture("particle/type2", 0);
        if (value == 4)
          particles.setTexture("", 1);
      }
      initGui();
    }
    if (button.id == 3) {
      int value = button.getValue();
      if (value == 0) {
        this.playerdata.removePart("fin");
      } else {
        ModelPartData particles = this.playerdata.getOrCreatePart("fin");
        if (value == 1)
          particles.setTexture("", 0);
        if (value == 2)
          particles.setTexture("fin/fin1", 0);
      }
      initGui();
    }
    if (button.id == 4) {
      int value = button.getValue();
      if (value == 0) {
        this.playerdata.removePart("skirt");
      } else {
        ModelPartData particles = this.playerdata.getOrCreatePart("skirt");
        if (value == 1)
          particles.setTexture("", 0);
        if (value == 2)
          particles.setTexture("skirt/skirt1", 0);
      }
      initGui();
    }
    
    if (button.id == 11) {
      this.mc.displayGuiScreen(new GuiModelColor(this, this.playerdata.getPartData("wings")));
    }
    if (button.id == 12) {
      this.mc.displayGuiScreen(new GuiModelColor(this, this.playerdata.getPartData("particles")));
    }
    if (button.id == 13) {
      this.mc.displayGuiScreen(new GuiModelColor(this, this.playerdata.getPartData("fin")));
    }
    if (button.id == 14) {
      this.mc.displayGuiScreen(new GuiModelColor(this, this.playerdata.getPartData("skirt")));
    }
  }
  
  public void close()
  {
    this.mc.displayGuiScreen(this.parent);
  }
}
