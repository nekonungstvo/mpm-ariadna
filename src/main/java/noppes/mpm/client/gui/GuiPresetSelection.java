package noppes.mpm.client.gui;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MPMEntityUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import noppes.mpm.ModelData;
import noppes.mpm.client.Preset;
import noppes.mpm.client.PresetController;
import noppes.mpm.client.gui.util.GuiInterface;
import noppes.mpm.client.gui.util.GuiListActionListener;
import noppes.mpm.client.gui.util.GuiNPCStringSlot;
import noppes.mpm.client.gui.util.GuiNpcButton;
import org.lwjgl.opengl.GL11;








public class GuiPresetSelection
  extends GuiInterface
  implements GuiListActionListener
{
  private GuiNPCStringSlot slot;
  private GuiCreationScreen parent;
  private NBTTagCompound prevData;
  private ModelData playerdata;
  private EntityPlayer player;
  
  public GuiPresetSelection(GuiCreationScreen parent, ModelData playerdata)
  {
    this.parent = parent;
    this.playerdata = playerdata;
    this.prevData = playerdata.writeToNBT();
    
    this.player = Minecraft.getMinecraft().thePlayer;
    
    PresetController.instance.load();
  }
  
  public void initGui()
  {
    super.initGui();
    Vector<String> list = new Vector();
    for (Preset preset : PresetController.instance.presets.values()) {
      list.add(preset.name);
    }
    Collections.sort(list, String.CASE_INSENSITIVE_ORDER);
    this.slot = new GuiNPCStringSlot(list, this, false, 18);
    this.slot.registerScrollButtons(4, 5);
    
    this.buttonList.add(new GuiNpcButton(2, this.width / 2 - 100, this.height - 44, 98, 20, "Back"));
    this.buttonList.add(new GuiNpcButton(3, this.width / 2 + 2, this.height - 44, 98, 20, "Load"));
    this.buttonList.add(new GuiNpcButton(4, this.width / 2 - 49, this.height - 22, 98, 20, "Remove"));
  }
  

  public void drawScreen(int i, int j, float f)
  {
//    EntityLivingBase player = this.playerdata.getEntity(this.mc.theWorld, this.mc.thePlayer);
//    if (player == null) {
//      player = this.player;
//    }
//    MPMEntityUtil.Copy(this.mc.thePlayer, player);

    EntityLivingBase player = this.player;
    MPMEntityUtil.Copy(this.mc.thePlayer, player);
    
    int l = this.width / 2 - 180;
    int i1 = this.height / 2 - 90;
    GL11.glEnable(32826);
    GL11.glEnable(2903);
    GL11.glPushMatrix();
    GL11.glTranslatef(l + 33, i1 + 131, 50.0F);
    GL11.glScalef(-50.0F, 50.0F, 50.0F);
    GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
    float f2 = player.renderYawOffset;
    float f3 = player.rotationYaw;
    float f4 = player.rotationPitch;
    float f5 = l + 33 - i;
    float f6 = i1 + 131 - 50 - j;
    GL11.glRotatef(135.0F, 0.0F, 1.0F, 0.0F);
    RenderHelper.enableStandardItemLighting();
    GL11.glRotatef(-135.0F, 0.0F, 1.0F, 0.0F);
    GL11.glRotatef(-(float)Math.atan(f6 / 40.0F) * 20.0F, 1.0F, 0.0F, 0.0F);
    player.renderYawOffset = ((float)Math.atan(f5 / 40.0F) * 20.0F);
    player.rotationYaw = ((float)Math.atan(f5 / 40.0F) * 40.0F);
    player.rotationPitch = (-(float)Math.atan(f6 / 40.0F) * 20.0F);
    player.rotationYawHead = player.rotationYaw;
    GL11.glTranslatef(0.0F, player.yOffset, 0.0F);
    RenderManager.instance.playerViewY = 180.0F;
    RenderManager.instance.renderEntityWithPosYaw(player, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F);
    player.renderYawOffset = f2;
    player.rotationYaw = f3;
    player.rotationPitch = f4;
    GL11.glPopMatrix();
    RenderHelper.disableStandardItemLighting();
    GL11.glDisable(32826);
    
    OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
    GL11.glDisable(3553);
    OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
    this.slot.drawScreen(i, j, f);
    super.drawScreen(i, j, f);
  }
  
  public void elementClicked() { Preset preset = PresetController.instance.getPreset(this.slot.selected);
    this.playerdata.readFromNBT(preset.data.writeToNBT());
  }
  
  public void doubleClicked() { close(); }
  


  public void keyTyped(char par1, int par2)
  {
    if (par2 == 1)
    {
      close(); }
  }
  
  private void close() {
    this.mc.displayGuiScreen(this.parent);
  }
  
  public FontRenderer getFontRenderer() {
    return this.fontRendererObj;
  }
  

  protected void actionPerformed(GuiButton button)
  {
    GuiNpcButton guibutton = (GuiNpcButton)button;
    if (guibutton.id == 2) {
      this.playerdata.readFromNBT(this.prevData);
      close();
    }
    if (guibutton.id == 3) {
      close();
    }
    if (guibutton.id == 4) {
      PresetController.instance.removePreset(this.slot.selected);
      Vector<String> list = new Vector();
      for (Preset preset : PresetController.instance.presets.values()) {
        list.add(preset.name);
      }
      Collections.sort(list, String.CASE_INSENSITIVE_ORDER);
      this.slot.setList(list);
      this.slot.selected = "";
    }
  }
}
