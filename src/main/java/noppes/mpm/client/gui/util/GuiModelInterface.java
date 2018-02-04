package noppes.mpm.client.gui.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import noppes.mpm.ModelData;
import noppes.mpm.PlayerDataController;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;


public class GuiModelInterface
        extends GuiInterface {
    private static int rotation = 0;
    private static float zoomed = 60.0F;
    public ModelData playerdata;
    public int xOffset = 0;
    private GuiNpcButton left;
    private GuiNpcButton right;
    private GuiNpcButton zoom;
    private GuiNpcButton unzoom;
    private AbstractClientPlayer player;

    public GuiModelInterface() {
        this.xSize = 380;
        this.player = Minecraft.getMinecraft().thePlayer;
        this.playerdata = PlayerDataController.instance.getPlayerData(Minecraft.getMinecraft().thePlayer);
    }

    public void initGui() {
        super.initGui();

        addButton(this.unzoom = new GuiNpcButton(666, this.guiLeft + 148 + this.xOffset, this.guiTop + 210, 20, 20, "-"));
        addButton(this.zoom = new GuiNpcButton(667, this.guiLeft + 214 + this.xOffset, this.guiTop + 210, 20, 20, "+"));
        addButton(this.left = new GuiNpcButton(668, this.guiLeft + 170 + this.xOffset, this.guiTop + 210, 20, 20, "<"));
        addButton(this.right = new GuiNpcButton(669, this.guiLeft + 192 + this.xOffset, this.guiTop + 210, 20, 20, ">"));

        addButton(new GuiNpcButton(66, this.width - 22, 2, 20, 20, "X"));
    }

    protected void actionPerformed(GuiButton btn) {
        if (btn.id == 66) {
            close();
        }
    }

    public boolean doesGuiPauseGame() {
        return false;
    }


    public void drawScreen(int par1, int par2, float par3) {
        if (Mouse.isButtonDown(0)) {
            if (this.left.mousePressed(this.mc, par1, par2)) {
                rotation += 2;
            } else if (this.right.mousePressed(this.mc, par1, par2)) {
                rotation -= 2;
            } else if (this.zoom.mousePressed(this.mc, par1, par2)) {
                zoomed += 2.0F;
            } else if ((this.unzoom.mousePressed(this.mc, par1, par2)) && (zoomed > 10.0F))
                zoomed -= 2.0F;
        }
        drawDefaultBackground();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

//        EntityLivingBase player = this.playerdata.getEntity(Minecraft.getMinecraft().theWorld, this.mc.player);
//        if (player == null) {
//            player = this.player;
//        } else {
//            MPMEntityUtil.Copy(this.mc.player, player);
//        }

        EntityLivingBase player = this.player;

        int l = this.guiLeft + 190 + this.xOffset;
        int i1 = this.guiTop + 180;
        GL11.glEnable(2903);
        GL11.glPushMatrix();
        GL11.glTranslatef(l, i1, 60.0F);
        GL11.glScalef(-zoomed, zoomed, zoomed);
        GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
        float f5 = l - par1;
        float f6 = i1 - 50 - par2;
        GL11.glRotatef(135.0F, 0.0F, 1.0F, 0.0F);
        RenderHelper.enableStandardItemLighting();
        GL11.glRotatef(-135.0F, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-(float) Math.atan(f6 / 80.0F) * 20.0F, 1.0F, 0.0F, 0.0F);

        float f = player.renderYawOffset;
        float f2 = player.rotationYaw;
        float f3 = player.rotationPitch;
        float f4 = player.prevRotationYawHead;
        player.renderYawOffset = rotation;
        player.rotationYaw = ((float) Math.atan(f5 / 80.0F) * 40.0F + rotation);
        player.rotationPitch = (-(float) Math.atan(f6 / 80.0F) * 20.0F);
        player.prevRotationYawHead = (player.rotationYawHead = player.rotationYaw);
        GL11.glTranslatef(0.0F, player.yOffset, 0.0F);
        RenderManager.instance.playerViewY = 180.0F;
        try {
            RenderManager.instance.renderEntityWithPosYaw(player, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F);
        } catch (Exception e) {
            this.playerdata.setEntityClass(null);
        }

        player.renderYawOffset = f;
        player.rotationYaw = f2;
        player.rotationPitch = f3;
        player.prevRotationYawHead = f4;

        GL11.glPopMatrix();
        RenderHelper.disableStandardItemLighting();
        GL11.glDisable(32826);
        OpenGlHelper.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GL11.glDisable(3553);
        OpenGlHelper.setActiveTexture(OpenGlHelper.defaultTexUnit);

        GL11.glPushMatrix();
        GL11.glTranslatef(0.0F, 0.0F, 500.065F);
        super.drawScreen(par1, par2, par3);
        GL11.glPopMatrix();
    }


    public void keyTyped(char par1, int par2) {
        super.keyTyped(par1, par2);
        if (par2 == 1) {
            close();
        }
    }

    public void close() {
        this.mc.displayGuiScreen((GuiScreen) null);
        this.mc.setIngameFocus();
    }
}
