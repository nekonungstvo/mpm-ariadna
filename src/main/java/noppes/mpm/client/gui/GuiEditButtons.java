package noppes.mpm.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import noppes.mpm.MorePlayerModels;
import noppes.mpm.client.gui.util.GuiModelInterface;
import noppes.mpm.client.gui.util.GuiNpcButton;
import noppes.mpm.client.gui.util.GuiNpcLabel;
import noppes.mpm.config.ConfigLoader;

public class GuiEditButtons extends GuiModelInterface
{
  private GuiScreen parent;
  private final String[] animations = { "None", "Sleep", "Crawl", "Hug", "Sit", "Dance", "Wave", "Wag", "Bow", "Cry" };
  
  public GuiEditButtons(GuiScreen parent) {
    this.parent = parent;
  }
  
  public void initGui()
  {
    super.initGui();
    int y = this.guiTop + 20;
    
    addLabel(new GuiNpcLabel(0, "This is only to change the animation/commands linked to a button.", this.guiLeft, y, 16777215));
    addLabel(new GuiNpcLabel(6, "To change the actual button use minecraft options -> contols", this.guiLeft, y + 11, 16777215));
    
    y += 22;addButton(1, y, "MPM 1", MorePlayerModels.button1);
    y += 22;addButton(2, y, "MPM 2", MorePlayerModels.button2);
    y += 22;addButton(3, y, "MPM 3", MorePlayerModels.button3);
    y += 22;addButton(4, y, "MPM 4", MorePlayerModels.button4);
    y += 22;addButton(5, y, "MPM 5", MorePlayerModels.button5);
  }
  
  private void addButton(int id, int y, String title, int value)
  {
    for (KeyBinding key : Minecraft.getMinecraft().gameSettings.keyBindings) {
      if (key.getKeyDescription().equals(title)) {
        title = title + " (" + org.lwjgl.input.Keyboard.getKeyName(key.getKeyCode()) + ")";
        break;
      }
    }
    value = getValue(value);
    addButton(new GuiNpcButton(id, this.guiLeft + 50, y, 70, 20, this.animations, value));
    addLabel(new GuiNpcLabel(id, title, this.guiLeft, y + 5, 16777215));
  }
  
  private int getValue(int i) { if (i == 0)
      return 0;
    if ((i >= 1) && (i <= 4)) {
      return 1;
    }
    return i - 3;
  }
  
  protected void actionPerformed(GuiButton btn)
  {
    super.actionPerformed(btn);
    GuiNpcButton button = (GuiNpcButton)btn;
    
    if (button.id == 1) {
      MorePlayerModels.button1 = getValue(button);
      MorePlayerModels.instance.configLoader.updateConfig();
    }
    if (button.id == 2) {
      MorePlayerModels.button2 = getValue(button);
      MorePlayerModels.instance.configLoader.updateConfig();
    }
    if (button.id == 3) {
      MorePlayerModels.button3 = getValue(button);
      MorePlayerModels.instance.configLoader.updateConfig();
    }
    if (button.id == 4) {
      MorePlayerModels.button4 = getValue(button);
      MorePlayerModels.instance.configLoader.updateConfig();
    }
    if (button.id == 5) {
      MorePlayerModels.button5 = getValue(button);
      MorePlayerModels.instance.configLoader.updateConfig();
    }
    if (button.id == 66) {
      close();
    }
  }
  
  private int getValue(GuiNpcButton button) {
    int value = button.getValue();
    if (value <= 1)
      return value;
    return value + 3;
  }
  
  public void close()
  {
    this.mc.displayGuiScreen(this.parent);
  }
}
