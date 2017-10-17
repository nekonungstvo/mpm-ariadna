package noppes.mpm.client.gui.util;

import net.minecraft.client.gui.FontRenderer;

public class GuiNpcLabel {
    public String label;
    public boolean enabled = true;
    public int id;
    private int x;
    private int y;
    private int color = 4210752;

    public GuiNpcLabel(int id, String label, int x, int y, int color) {
        this.id = id;
        this.label = net.minecraft.util.StatCollector.translateToLocal(label);
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public void drawLabel(net.minecraft.client.gui.GuiScreen gui, FontRenderer fontRenderer) {
        if (this.enabled)
            fontRenderer.drawString(this.label, this.x, this.y, this.color);
    }

    public void center(int width) {
        int size = net.minecraft.client.Minecraft.getMinecraft().fontRenderer.getStringWidth(this.label);
        this.x += (width - size) / 2;
    }
}
