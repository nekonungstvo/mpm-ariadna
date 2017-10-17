package noppes.mpm.client.gui.util;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

import java.util.HashMap;

public class GuiInterface extends GuiScreen {
    public boolean drawDefaultBackground = false;
    public int guiLeft;
    public int guiTop;
    public int xSize = 256;
    public int ySize = 216;
    private HashMap<Integer, GuiNpcButton> buttons = new HashMap();
    private HashMap<Integer, GuiNpcLabel> labels = new HashMap();
    private HashMap<Integer, GuiNpcSlider> sliders = new HashMap();
    private HashMap<Integer, GuiNpcTextField> textfields = new HashMap();

    public void initGui() {
        super.initGui();
        this.guiLeft = ((this.width - this.xSize) / 2);
        this.guiTop = ((this.height - this.ySize) / 2);

        this.buttonList.clear();
        this.labels.clear();
        this.buttons.clear();
        this.textfields.clear();
    }


    protected void actionPerformed(GuiButton btn) {
    }


    public void addButton(GuiNpcButton button) {
        this.buttons.put(Integer.valueOf(button.id), button);
        this.buttonList.add(button);
    }

    public GuiNpcButton getButton(int i) {
        return (GuiNpcButton) this.buttons.get(Integer.valueOf(i));
    }

    public void addLabel(GuiNpcLabel label) {
        this.labels.put(Integer.valueOf(label.id), label);
    }

    public GuiNpcLabel getLabel(int i) {
        return (GuiNpcLabel) this.labels.get(Integer.valueOf(i));
    }

    public void addSlider(GuiNpcSlider slider) {
        this.sliders.put(Integer.valueOf(slider.id), slider);
        this.buttonList.add(slider);
    }

    public GuiNpcSlider getSlider(int i) {
        return (GuiNpcSlider) this.sliders.get(Integer.valueOf(i));
    }

    public void addTextField(GuiNpcTextField tf) {
        this.textfields.put(Integer.valueOf(tf.id), tf);
    }

    public GuiNpcTextField getTextField(int i) {
        return (GuiNpcTextField) this.textfields.get(Integer.valueOf(i));
    }

    public void updateScreen() {
        for (GuiNpcTextField tf : this.textfields.values())
            if (tf.enabled)
                tf.updateCursorCounter();
        super.updateScreen();
    }

    public void drawScreen(int par1, int par2, float par3) {
        if (this.drawDefaultBackground)
            drawBackground(0);
        for (GuiNpcLabel label : this.labels.values())
            label.drawLabel(this, this.fontRendererObj);
        for (GuiNpcTextField tf : this.textfields.values()) {
            tf.drawTextBox();
        }
        super.drawScreen(par1, par2, par3);
    }


    public void keyTyped(char c, int i) {
        for (GuiNpcTextField tf : this.textfields.values()) {
            tf.textboxKeyTyped(c, i);
        }
    }

    public void mouseClicked(int i, int j, int k) {
        for (GuiNpcTextField tf : (GuiNpcTextField[]) this.textfields.values().toArray(new GuiNpcTextField[this.textfields.size()]))
            if (tf.enabled)
                tf.mouseClicked(i, j, k);
        super.mouseClicked(i, j, k);
    }
}
