package noppes.mpm.client.gui;

import net.minecraft.client.gui.GuiScreen;
import noppes.mpm.ModelPartData;
import noppes.mpm.client.gui.util.GuiModelInterface;
import noppes.mpm.client.gui.util.GuiNpcButton;
import noppes.mpm.client.gui.util.GuiNpcLabel;

public class GuiModelArms extends GuiModelInterface {
    private final String[] arrParticles = {"gui.no", "Both", "Left", "Right"};
    private final String[] arrAmputee = {"gui.no", "Both", "Left", "Right"};
    private final String[] arrSlim = {"gui.no", "gui.yes"};
    private GuiScreen parent;

    public GuiModelArms(GuiScreen parent) {
        this.parent = parent;
        this.xOffset = 60;
    }

    public void initGui() {
        super.initGui();
        int y = this.guiTop + 20;

        ModelPartData claws = this.playerdata.getPartData("claws");
        y += 22;
        addButton(new GuiNpcButton(0, this.guiLeft + 50, y, 70, 20, this.arrParticles, claws == null ? 0 : claws.type + 1));
        addLabel(new noppes.mpm.client.gui.util.GuiNpcLabel(0, "Claws", this.guiLeft, y + 5, 16777215));
        if (claws != null) {
            addButton(new GuiNpcButton(10, this.guiLeft + 122, y, 40, 20, claws.getColor()));
        }

        y += 22;
        byte amputee = this.playerdata.armsAmputee;
        addButton(new GuiNpcButton(1, this.guiLeft + 50, y, 70, 20, this.arrAmputee, amputee));
        addLabel(new GuiNpcLabel(1, "Amputee", this.guiLeft, y + 5, 16777215));

//        y += 22;
//        boolean slim = this.playerdata.slim;
//        addButton(new GuiNpcButton(2, this.guiLeft + 50, y, 70, 20, this.arrSlim, slim ? 1 : 0));
//        addLabel(new GuiNpcLabel(2, "Slim", this.guiLeft, y + 5, 16777215));
    }

    protected void actionPerformed(net.minecraft.client.gui.GuiButton btn) {
        super.actionPerformed(btn);
        GuiNpcButton button = (GuiNpcButton) btn;

        if (button.id == 0) {
            if (button.getValue() == 0) {
                this.playerdata.removePart("claws");
            } else {
                ModelPartData data = this.playerdata.getOrCreatePart("claws");
                data.type = ((byte) (button.getValue() - 1));
            }
            initGui();
        }
        if (button.id == 1) {
            byte nextAmputee = this.playerdata.armsAmputee;
            nextAmputee++;
            if (nextAmputee > 3) nextAmputee = 0;
            this.playerdata.armsAmputee = nextAmputee;
            initGui();
        }
        if (button.id == 2) {
            this.playerdata.slim = !this.playerdata.slim;
            this.playerdata.reloadBoxes = true;
            initGui();
        }

        if (button.id == 10) {
            this.mc.displayGuiScreen(new GuiModelColor(this, this.playerdata.getPartData("claws")));
        }
    }

    public void close() {
        this.mc.displayGuiScreen(this.parent);
    }
}
