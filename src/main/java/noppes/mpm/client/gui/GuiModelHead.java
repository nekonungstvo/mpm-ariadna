package noppes.mpm.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import noppes.mpm.ModelData;
import noppes.mpm.ModelPartData;
import noppes.mpm.client.gui.util.GuiModelInterface;
import noppes.mpm.client.gui.util.GuiNpcButton;
import noppes.mpm.client.gui.util.GuiNpcLabel;

public class GuiModelHead extends GuiModelInterface
{
  private GuiScreen parent;
  private final String[] arrHeadwear = { "gui.no", "gui.yes", "Solid" };
  private final String[] arrHair = { "gui.no", "Player", "Long", "Thin", "Stylish", "Ponytail" };
  private final String[] arrBeard = { "gui.no", "Player", "Standard", "Viking", "Long", "Short" };
  private final String[] arrMohawk = { "gui.no", "Type1" };
  private final String[] arrSnout = { "gui.no", "Player Small", "Player Medium", "Player Large", "Player Bunny", "Small1", "Medium1", "Large1", "Bunny1", "Beak1" };
  
  private final String[] arrEars = { "gui.no", "Player", "Player Bunny", "Bunny", "Type1" };
  private final String[] arrHorns = { "gui.no", "Player Bull", "Player Antlers", "Player AntennasB", "Player AntennasF", "Bull", "Antlers", "AntennasB", "AntennasF" };
  
  public GuiModelHead(GuiScreen parent) { this.parent = parent;
    this.xOffset = 60;
  }
  
  public void initGui()
  {
    super.initGui();
    
    int y = this.guiTop + 20;
    
    y += 22;addButton(new GuiNpcButton(0, this.guiLeft + 50, y, 70, 20, this.arrHeadwear, this.playerdata.headwear));
    addLabel(new GuiNpcLabel(0, "Headwear", this.guiLeft, y + 5, 16777215));
    
    ModelPartData hair = this.playerdata.getPartData("hair");
    y += 22;addButton(new GuiNpcButton(1, this.guiLeft + 50, y, 70, 20, this.arrHair, hair == null ? 0 : hair.type + 1));
    addLabel(new GuiNpcLabel(1, "Hair", this.guiLeft, y + 5, 16777215));
    if (hair != null) {
      addButton(new GuiNpcButton(11, this.guiLeft + 122, y, 40, 20, hair.getColor()));
    }
    ModelPartData mohawk = this.playerdata.getPartData("mohawk");
    y += 22;addButton(new GuiNpcButton(2, this.guiLeft + 50, y, 70, 20, this.arrMohawk, mohawk == null ? 0 : mohawk.type));
    addLabel(new GuiNpcLabel(2, "Mohawk", this.guiLeft, y + 5, 16777215));
    if (mohawk != null) {
      addButton(new GuiNpcButton(12, this.guiLeft + 122, y, 40, 20, mohawk.getColor()));
    }
    ModelPartData beard = this.playerdata.getPartData("beard");
    y += 22;addButton(new GuiNpcButton(3, this.guiLeft + 50, y, 70, 20, this.arrBeard, beard == null ? 0 : beard.type + 1));
    addLabel(new GuiNpcLabel(3, "Beard", this.guiLeft, y + 5, 16777215));
    if (beard != null) {
      addButton(new GuiNpcButton(13, this.guiLeft + 122, y, 40, 20, beard.getColor()));
    }
    ModelPartData snout = this.playerdata.getPartData("snout");
    y += 22;addButton(new GuiNpcButton(4, this.guiLeft + 50, y, 70, 20, this.arrSnout, snout == null ? 0 : snout.type + (snout.playerTexture ? 1 : 5)));
    addLabel(new GuiNpcLabel(4, "Snout", this.guiLeft, y + 5, 16777215));
    if (snout != null) {
      addButton(new GuiNpcButton(14, this.guiLeft + 122, y, 40, 20, snout.getColor()));
    }
    ModelPartData ears = this.playerdata.getPartData("ears");
    y += 22;addButton(new GuiNpcButton(5, this.guiLeft + 50, y, 70, 20, this.arrEars, getEars(ears)));
    addLabel(new GuiNpcLabel(5, "Ears", this.guiLeft, y + 5, 16777215));
    if (ears != null) {
      addButton(new GuiNpcButton(15, this.guiLeft + 122, y, 40, 20, ears.getColor()));
    }
    ModelPartData horns = this.playerdata.getPartData("horns");
    y += 22;addButton(new GuiNpcButton(6, this.guiLeft + 50, y, 70, 20, this.arrHorns, getHorns(horns)));
    addLabel(new GuiNpcLabel(6, "Horns", this.guiLeft, y + 5, 16777215));
    if (horns != null) {
      addButton(new GuiNpcButton(16, this.guiLeft + 122, y, 40, 20, horns.getColor()));
    }
  }
  
  private int getEars(ModelPartData data)
  {
    if (data == null)
      return 0;
    if ((data.playerTexture) && (data.type == 0))
      return 1;
    if ((data.playerTexture) && (data.type == 1))
      return 2;
    if (data.type == 0)
      return 4;
    if (data.type == 1) {
      return 3;
    }
    return 0;
  }
  
  private int getHorns(ModelPartData data) {
    if (data == null)
      return 0;
    if (data.playerTexture) {
      return data.type + 1;
    }
    return data.type + 5;
  }
  
  protected void actionPerformed(net.minecraft.client.gui.GuiButton btn)
  {
    super.actionPerformed(btn);
    GuiNpcButton button = (GuiNpcButton)btn;
    
    if (button.id == 0) {
      this.playerdata.headwear = ((byte)button.getValue());
    }
    
    if (button.id == 1) {
      if (button.getValue() == 0) {
        this.playerdata.removePart("hair");
      } else {
        ModelPartData data = this.playerdata.getOrCreatePart("hair");
        if (button.getValue() > 1)
          data.setTexture("hair/hair" + (button.getValue() - 1), button.getValue() - 1);
      }
      initGui();
    }
    
    if (button.id == 2) {
      if (button.getValue() == 0) {
        this.playerdata.removePart("mohawk");
      } else {
        ModelPartData data = this.playerdata.getOrCreatePart("mohawk");
        if (button.getValue() > 0)
          data.setTexture("hair/mohawk" + button.getValue(), button.getValue());
      }
      initGui();
    }
    
    if (button.id == 3) {
      if (button.getValue() == 0) {
        this.playerdata.removePart("beard");
      } else {
        ModelPartData data = this.playerdata.getOrCreatePart("beard");
        if (button.getValue() > 1)
          data.setTexture("beard/beard" + (button.getValue() - 1), button.getValue() - 1);
      }
      initGui();
    }
    
    if (button.id == 4) {
      if (button.getValue() == 0) {
        this.playerdata.removePart("snout");
      } else if (button.getValue() < 5) {
        ModelPartData data = this.playerdata.getOrCreatePart("snout");
        data.type = ((byte)(button.getValue() - 1));
      }
      else {
        ModelPartData data = this.playerdata.getOrCreatePart("snout");
        byte type = 0;
        if (button.displayString.startsWith("Medium"))
          type = 1;
        if (button.displayString.startsWith("Large"))
          type = 2;
        if (button.displayString.startsWith("Bunny"))
          type = 3;
        if (button.displayString.startsWith("Beak"))
          type = 4;
        data.setTexture("snout/" + button.displayString.toLowerCase(), type);
      }
      initGui();
    }
    
    if (button.id == 5) {
      int value = button.getValue();
      if (value == 0) {
        this.playerdata.removePart("ears");
      } else {
        ModelPartData data = this.playerdata.getOrCreatePart("ears");
        if (value == 1)
          data.setTexture("", 0);
        if (value == 2)
          data.setTexture("", 1);
        if (value == 3)
          data.setTexture("ears/bunny1", 1);
        if (value == 4)
          data.setTexture("ears/type1", 0);
      }
      initGui();
    }
    
    if (button.id == 6) {
      int value = button.getValue();
      if (value == 0) {
        this.playerdata.removePart("horns");
      } else {
        ModelPartData data = this.playerdata.getOrCreatePart("horns");
        if (value <= 4)
          data.setTexture("", value - 1);
        if (value == 5)
          data.setTexture("horns/bull", 0);
        if (value == 6)
          data.setTexture("horns/antlers", 1);
        if (value == 7)
          data.setTexture("horns/antennas", 2);
        if (value == 8)
          data.setTexture("horns/antennas", 3);
      }
      initGui();
    }
    
    if (button.id == 11) {
      this.mc.displayGuiScreen(new GuiModelColor(this, this.playerdata.getPartData("hair")));
    }
    if (button.id == 12) {
      this.mc.displayGuiScreen(new GuiModelColor(this, this.playerdata.getPartData("mohawk")));
    }
    if (button.id == 13) {
      this.mc.displayGuiScreen(new GuiModelColor(this, this.playerdata.getPartData("beard")));
    }
    if (button.id == 14) {
      this.mc.displayGuiScreen(new GuiModelColor(this, this.playerdata.getPartData("snout")));
    }
    if (button.id == 15) {
      this.mc.displayGuiScreen(new GuiModelColor(this, this.playerdata.getPartData("ears")));
    }
    if (button.id == 16) {
      this.mc.displayGuiScreen(new GuiModelColor(this, this.playerdata.getPartData("horns")));
    }
  }
  
  public void close()
  {
    this.mc.displayGuiScreen(this.parent);
  }
}
