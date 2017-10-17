package noppes.mpm.client.gui.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

public class GuiNpcButton extends GuiButton {
    public boolean shown = true;
    public int id;
    private String[] display;
    private int displayValue = 0;

    public GuiNpcButton(int i, int j, int k, String s) {
        super(i, j, k, net.minecraft.util.StatCollector.translateToLocal(s));
        this.id = i;
    }

    public GuiNpcButton(int i, int j, int k, String[] display, int val) {
        this(i, j, k, display[val]);
        this.display = display;
        this.displayValue = val;
    }

    public GuiNpcButton(int i, int j, int k, int l, int m, String string) {
        super(i, j, k, l, m, net.minecraft.util.StatCollector.translateToLocal(string));
        this.id = i;
    }

    public GuiNpcButton(int i, int j, int k, int l, int m, String[] display, int val) {
        this(i, j, k, l, m, display[(val % display.length)]);
        this.display = display;
        this.displayValue = (val % display.length);
    }

    public void setDisplayText(String text) {
        this.displayString = net.minecraft.util.StatCollector.translateToLocal(text);
    }

    public int getValue() {
        return this.displayValue;
    }


    public void drawButton(Minecraft minecraft, int i, int j) {
        if (!this.shown)
            return;
        super.drawButton(minecraft, i, j);
    }

    public boolean mousePressed(Minecraft minecraft, int i, int j) {
        boolean bo = super.mousePressed(minecraft, i, j);
        if ((bo) && (this.display != null)) {
            this.displayValue = ((this.displayValue + 1) % this.display.length);
            setDisplayText(this.display[this.displayValue]);
        }
        return bo;
    }

    public void setDisplay(int value) {
        this.displayValue = value;
        setDisplayText(this.display[value]);
    }
}
