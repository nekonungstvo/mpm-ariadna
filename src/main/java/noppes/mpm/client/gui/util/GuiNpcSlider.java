package noppes.mpm.client.gui.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public class GuiNpcSlider extends GuiSlider {
    private ISliderListener listener;

    public GuiNpcSlider(GuiScreen parent, int id, int xPos, int yPos, String displayString, float sliderValue) {
        super(id, xPos, yPos, null, displayString, sliderValue);

        if ((parent instanceof ISliderListener))
            this.listener = ((ISliderListener) parent);
    }

    public GuiNpcSlider(GuiScreen parent, int id, int xPos, int yPos, float sliderValue) {
        this(parent, id, xPos, yPos, "", sliderValue);
        if (this.listener != null) {
            this.listener.mouseDragged(this);
        }
    }

    @Override
    public void mouseDragged(Minecraft par1Minecraft, int par2, int par3) {
        if (this.visible) {
            FontRenderer fontrenderer = par1Minecraft.fontRenderer;
            int l = 14737632;

            if (this.packedFGColour != 0) {
                l = this.packedFGColour;
            } else if (!this.enabled) {
                l = 10526880;
            } else if (this.field_146123_n) {
                l = 16777120;
            }

            par1Minecraft.getTextureManager().bindTexture(buttonTextures);
            if (this.dragging) {
                this.sliderValue = (float) (par2 - (this.xPosition + 4)) / (float) (this.width - 8);

                if (this.sliderValue < 0.0F) {
                    this.sliderValue = 0.0F;
                }

                if (this.sliderValue > 1.0F) {
                    this.sliderValue = 1.0F;
                }

                if (this.listener != null) {
                    this.listener.mouseDragged(this);
                }
                if (!Mouse.isButtonDown(0)) {
                    func_146111_b(0, 0);
                }
            }

            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            drawTexturedModalRect(this.xPosition + (int) (this.sliderValue * (this.width - 8)), this.yPosition, 0, 66, 4, 20);
            drawTexturedModalRect(this.xPosition + (int) (this.sliderValue * (this.width - 8)) + 4, this.yPosition, 196, 66, 4, 20);

            drawCenteredString(fontrenderer, this.displayString, this.xPosition + this.width / 2, this.yPosition + (this.height - 8) / 2, l);
        }
    }

    public String getDisplayString() {
        return this.displayString;
    }

    public void setString(String str) {
        this.displayString = str;
    }


    public boolean mousePressed(Minecraft par1Minecraft, int par2, int par3) {
        if ((this.enabled) && (this.visible) && (par2 >= this.xPosition) && (par3 >= this.yPosition) && (par2 < this.xPosition + this.width) && (par3 < this.yPosition + this.height)) {
            this.sliderValue = ((par2 - (this.xPosition + 4)) / (this.width - 8));

            if (this.sliderValue < 0.0F) {
                this.sliderValue = 0.0F;
            }

            if (this.sliderValue > 1.0F) {
                this.sliderValue = 1.0F;
            }

            if (this.listener != null) {
                this.listener.mousePressed(this);
            }
            this.dragging = true;
            return true;
        }


        return false;
    }


    public void func_146111_b(int par1, int par2) {
        this.dragging = false;

        if (this.listener != null) {
            this.listener.mouseReleased(this);
        }
    }
}
