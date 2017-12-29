package noppes.mpm.client.gui;

import net.minecraft.client.gui.GuiScreen;
import noppes.mpm.ModelPartData;
import noppes.mpm.client.gui.util.GuiModelInterface;
import noppes.mpm.client.gui.util.GuiNpcButton;
import noppes.mpm.client.gui.util.GuiNpcLabel;
import noppes.mpm.client.gui.util.GuiNpcTextField;
import org.lwjgl.input.Keyboard;

public class GuiModelLegs extends GuiModelInterface {
    private final String[] arrLegs = {"gui.no", "Player", "Player Naga", "Spider", "Horse", "Naga", "Mermaid", "Digitigrade"};
    private final String[] arrTail = {"gui.no", "Player", "Player Dragon", "Cat", "Wolf", "Horse", "Dragon", "Squirrel", "Fin", "Rodent", "Cteno"};
    private GuiScreen parent;

    public GuiModelLegs(GuiScreen parent) {
        this.parent = parent;
        this.xOffset = 60;
    }

    public void initGui() {
        super.initGui();
        int y = this.guiTop + 20;

        y += 22;
        addButton(new GuiNpcButton(1, this.guiLeft + 50, y, 70, 20, this.arrLegs, getNagaIndex(this.playerdata.legParts)));
        addLabel(new noppes.mpm.client.gui.util.GuiNpcLabel(1, "Legs", this.guiLeft, y + 5, 16777215));
        if (this.playerdata.legParts.type > 0) {
            addButton(new GuiNpcButton(11, this.guiLeft + 122, y, 40, 20, this.playerdata.legParts.getColor()));
        }

        ModelPartData tail = this.playerdata.getPartData("tail");
        y += 22;
        addButton(new GuiNpcButton(2, this.guiLeft + 50, y, 70, 20, this.arrTail, getTailIndex(tail)));
        addLabel(new noppes.mpm.client.gui.util.GuiNpcLabel(2, "Tail", this.guiLeft, y + 5, 16777215));
        if (tail != null) {
            addButton(new GuiNpcButton(12, this.guiLeft + 122, y, 40, 20, tail.getColor()));
        }
    }

    private int getNagaIndex(ModelPartData data) {
        if ((!data.playerTexture) && (data.type == 1))
            return 5;
        if (data.type == 4)
            return 6;
        if (data.type == 5)
            return 7;
        return data.type + 1;
    }

    private int getTailIndex(ModelPartData data) {
        if (data == null)
            return 0;
        if ((data.playerTexture) && (data.type == 0))
            return 1;
        if ((data.type == 0) && (data.texture.contains("tail1")))
            return 3;
        if ((data.type == 0) && (data.texture.contains("tail2")))
            return 4;
        if ((data.playerTexture) && (data.type == 1))
            return 2;
        if (data.type == 1)
            return 6;
        if (data.type == 2)
            return 5; // Horse
        if (data.type == 3)
            return 7; // Squirrel
        if (data.type == 4)
            return 8;
        if (data.type == 5)
            return 9;
        if (data.type == 6)
            return 10; // Cteno

        return 0;
    }

    protected void actionPerformed(net.minecraft.client.gui.GuiButton btn) {
        super.actionPerformed(btn);
        GuiNpcButton button = (GuiNpcButton) btn;

        if (button.id == 1) {
            ModelPartData data = this.playerdata.legParts;
            int value = button.getValue() - 1;
            if (value < 1)
                data.color = 16777215;
            if (value < 2) {
                data.setTexture("", value);
            }
            if (value == 2)
                data.setTexture("legs/spider1", 2);
            if (value == 3)
                data.setTexture("legs/horse1", 3);
            if (value == 4)
                data.setTexture("legs/naga1", 1);
            if (value == 5)
                data.setTexture("legs/mermaid1", 4);
            if (value == 6) {
                data.setTexture("", 5);
            }
            initGui();
        }


        if (button.id == 2) {
            int value = button.getValue();
            if (value == 0) {
                this.playerdata.removePart("tail");
                initGui();
            } else {
                ModelPartData data = this.playerdata.getOrCreatePart("tail");
                if (value == 1)
                    data.setTexture("", 0);
                if (value == 2) // Dragon player
                    data.setTexture("", 1);
                if (value == 3)
                    data.setTexture("tail/tail1", 0);
                if (value == 4)
                    data.setTexture("tail/tail2", 0);
                if (value == 5)
                    data.setTexture("tail/horse1", 2);
                if (value == 6) // Dragon
                    data.setTexture("tail/dragon1", 1);
                if (value == 7)
                    data.setTexture("tail/squirrel1", 3);
                if (value == 8)
                    data.setTexture("tail/fin1", 4);
                if (value == 9)
                    data.setTexture("tail/rodent1", 5);

                // Premium parts
                data.extraTexture = false;
                if (value > 9) {
                    if (Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)) {
                        if (value == 10) {
                            data.setTexture("", 6);
                            data.extraTexture = true;
                        }
                    } else {
                        button.setValue(0);
                        data.setTexture("", 0);
                    }
                }

                initGui();
            }
        }

        if (button.id == 11) { // Legs color
            this.mc.displayGuiScreen(new GuiModelColor(this, this.playerdata.legParts));
        }

        if (button.id == 12) { // Tail color
            this.mc.displayGuiScreen(new GuiModelColor(this, this.playerdata.getPartData("tail")));
        }
    }

    public void close() {
        this.mc.displayGuiScreen(this.parent);
    }
}
