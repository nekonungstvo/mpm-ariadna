package noppes.mpm.client.gui;

import net.minecraft.client.gui.GuiScreen;
import noppes.mpm.ModelData;
import noppes.mpm.client.PresetController;
import noppes.mpm.client.gui.util.GuiInterface;
import noppes.mpm.client.gui.util.GuiNpcButton;
import noppes.mpm.client.gui.util.GuiNpcTextField;

public class GuiPresetSave extends GuiInterface {
    private ModelData data;
    private GuiScreen parent;

    public GuiPresetSave(GuiScreen parent, ModelData data) {
        this.data = data;
        this.parent = parent;
        this.xSize = 200;
        this.drawDefaultBackground = true;
    }

    public void initGui() {
        super.initGui();
        addTextField(new GuiNpcTextField(0, this, this.guiLeft, this.guiTop + 70, 200, 20, ""));
        addButton(new GuiNpcButton(0, this.guiLeft, this.guiTop + 100, 98, 20, "Save"));
        addButton(new GuiNpcButton(1, this.guiLeft + 100, this.guiTop + 100, 98, 20, "Cancel"));
    }

    protected void actionPerformed(net.minecraft.client.gui.GuiButton btn) {
        super.actionPerformed(btn);
        GuiNpcButton button = (GuiNpcButton) btn;
        if (button.id == 0) {
            String name = getTextField(0).getText().trim();
            if (name.isEmpty())
                return;
            noppes.mpm.client.Preset preset = new noppes.mpm.client.Preset();
            preset.name = name;
            preset.data = this.data.copy();
            PresetController.instance.addPreset(preset);
        }
        this.mc.displayGuiScreen(this.parent);
    }
}
