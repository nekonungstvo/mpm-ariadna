package noppes.mpm.client.gui;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MPMEntityUtil;
import net.minecraft.entity.player.EntityPlayer;
import noppes.mpm.ModelData;
import noppes.mpm.client.gui.util.GuiListActionListener;
import noppes.mpm.client.gui.util.GuiNPCStringSlot;
import org.lwjgl.opengl.GL11;






public class GuiEntitySelection
  extends GuiScreen
  implements GuiListActionListener
{
  private GuiNPCStringSlot slot;
  private GuiCreationScreen parent;
  private Class<? extends EntityLivingBase> prevModel;
  private ModelData playerdata;
  private EntityPlayer player;
  private int width;
  private int height;
  
  public GuiEntitySelection(GuiCreationScreen parent, ModelData playerdata)
  {
    this.parent = parent;
    this.playerdata = playerdata;
    this.prevModel = playerdata.getEntityClass();
    
    this.player = Minecraft.getMinecraft().thePlayer;
  }
  

  public void initGui()
  {
    super.initGui();
    Vector<String> list = new Vector(this.parent.data.keySet());
    list.add("Player");
    Collections.sort(list, String.CASE_INSENSITIVE_ORDER);
    this.slot = new GuiNPCStringSlot(list, this, false, 18);
    if (this.playerdata.getEntityClass() != null) {
      this.slot.selected = ((String)EntityList.classToStringMapping.get(this.playerdata.getEntityClass()));
    } else {
      this.slot.selected = "Player";
    }
    this.slot.registerScrollButtons(4, 5);
    
    this.buttonList.add(new GuiButton(2, this.width / 2 - 100, this.height - 44, 98, 20, "Back"));
  }
  

  public void drawScreen(int i, int j, float f)
  {
//    EntityLivingBase player = this.playerdata.getEntity(this.mc.theWorld, this.mc.thePlayer);
//    if (player == null) {
//      player = this.player;
//    }
//    MPMEntityUtil.Copy(this.mc.thePlayer, player);

    EntityLivingBase player = this.player;

    int l = this.width / 2 - 180;
    int i1 = this.height / 2 - 90;
    GL11.glEnable(32826);
    GL11.glEnable(2903);
    GL11.glPushMatrix();
    GL11.glTranslatef(l + 233, i1 + 231, 50.0F);
    float scale = 1.0F;
    if (player.height > 2.4D)
      scale = 2.0F / player.height;
    scale = 1.0F;
    GL11.glScalef(-50.0F * scale, 50.0F * scale, 50.0F * scale);
    GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
    float f2 = player.renderYawOffset;
    float f3 = player.rotationYaw;
    float f4 = player.rotationPitch;
    float f7 = player.rotationYawHead;
    float f5 = l + 233 - i;
    float f6 = i1 + 231 - 50 - j;
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
    try {
      RenderManager.instance.renderEntityWithPosYaw(player, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F);
    }
    catch (Exception e) {
      this.playerdata.setEntityClass(null);
    }
    player.renderYawOffset = f2;
    player.rotationYaw = f3;
    player.rotationPitch = f4;
    player.rotationYawHead = f7;
    GL11.glPopMatrix();
    RenderHelper.disableStandardItemLighting();
    GL11.glDisable(32826);
    
    OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
    GL11.glDisable(3553);
    OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);
    this.slot.drawScreen(i, j, f);
    super.drawScreen(i, j, f);
  }
  
  public void elementClicked() { this.playerdata.setEntityClass((Class)this.parent.data.get(this.slot.selected)); }
  
  public void doubleClicked() {
    close();
  }
  
  protected void keyTyped(char par1, int par2)
  {
    if (par2 == 1)
    {
      close(); }
  }
  
  private void close() {
    this.mc.displayGuiScreen(this.parent);
  }
  



  protected void actionPerformed(GuiButton guibutton)
  {
    close();
  }
}
