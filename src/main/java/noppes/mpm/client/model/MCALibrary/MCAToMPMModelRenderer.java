package noppes.mpm.client.model.MCALibrary;

import noppes.mpm.client.model.ModelMPM;
import noppes.mpm.client.model.ModelScaleRenderer;
import org.lwjgl.opengl.GL11;

public abstract class MCAToMPMModelRenderer extends ModelScaleRenderer {
    public MCAToMPMModelRenderer(ModelMPM base) {
        super(base);
    }

    @Override
    public void render(float par1) {
        GL11.glPushMatrix();

        GL11.glRotatef(180F, 0, 1F, 0F);
        GL11.glRotatef(180F, 0, 0, 1F);
        GL11.glDisable(GL11.GL_CULL_FACE);

        super.render(par1);

        GL11.glEnable(GL11.GL_CULL_FACE);

        GL11.glPopMatrix();
    }
}
