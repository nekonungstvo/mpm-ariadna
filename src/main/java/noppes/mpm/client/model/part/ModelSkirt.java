package noppes.mpm.client.model.part;

import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import noppes.mpm.ModelData;
import noppes.mpm.ModelPartData;
import noppes.mpm.client.model.ModelMPM;
import noppes.mpm.client.model.ModelPartInterface;
import noppes.mpm.client.model.ModelPlaneRenderer;
import org.lwjgl.opengl.GL11;


public class ModelSkirt extends ModelPartInterface {
    private ModelPlaneRenderer Shape1;

    public ModelSkirt(ModelMPM base) {
        super(base);
        float pi = 0.62831855F;

        this.Shape1 = new ModelPlaneRenderer(base, 62, 0);
        this.Shape1.addSidePlane(0.0F, 0.0F, 0.0F, 8, 2);

        ModelPlaneRenderer part1 = new ModelPlaneRenderer(base, 62, 0);
        part1.addSidePlane(2.0F, 0.0F, 0.0F, 8, 2);
        part1.rotateAngleY = -1.5707964F;
        this.Shape1.addChild(part1);

        this.Shape1.setRotationPoint(2.4F, 8.8F, 0.0F);
        setRotation(this.Shape1, 0.3F, -0.2F, -0.2F);
    }

    public void render(float par1) {
        if ((this.isHidden) || (!this.showModel))
            return;
        GL11.glPushMatrix();
        GL11.glScalef(1.7F, 1.04F, 1.6F);
        super.render(par1);
        GL11.glPopMatrix();
    }

    public void renderParts(float par1) {
        for (int i = 0; i < 10; i++) {
            GL11.glRotatef(36.0F, 0.0F, 1.0F, 0.0F);
            this.Shape1.render(par1);
        }
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity) {
        setRotation(this.Shape1, 0.3F, -0.2F, -0.2F);
        this.Shape1.rotateAngleX += this.base.bipedLeftArm.rotateAngleX * 0.04F;
        this.Shape1.rotateAngleZ += this.base.bipedLeftArm.rotateAngleX * 0.06F;

        this.Shape1.rotateAngleZ -= MathHelper.cos(par3 * 0.09F) * 0.04F - 0.05F;
    }

    public void initData(ModelData data) {
        ModelPartData config = data.getPartData("skirt");
        if (config == null) {
            this.isHidden = true;
            return;
        }
        this.color = config.color;
        this.isHidden = false;
        this.location = config.getResource();
    }
}
