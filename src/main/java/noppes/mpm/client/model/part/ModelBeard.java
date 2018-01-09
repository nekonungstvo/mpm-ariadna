package noppes.mpm.client.model.part;

import noppes.mpm.ModelData;
import noppes.mpm.ModelPartData;
import noppes.mpm.client.model.Model2DRenderer;
import noppes.mpm.client.model.ModelMPM;
import noppes.mpm.client.model.ModelPartInterface;

public class ModelBeard extends ModelPartInterface {
    private Model2DRenderer model;

    public ModelBeard(ModelMPM base) {
        super(base);
        this.model = new Model2DRenderer(base, 56.0F, 20.0F, 8, 12, 64, 64);
        this.model.setRotationPoint(-3.99F, 11.9F, -4.0F);
        this.model.setScale(0.74F);
        addChild(this.model);
    }


    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, net.minecraft.entity.Entity entity) {
        if (this.base.bipedHead.rotateAngleX > 0.0F) {
            this.rotateAngleX = (-this.base.bipedHead.rotateAngleX);
        } else {
            this.rotateAngleX = 0.0F;
        }
    }

    public void initData(ModelData data) {
        ModelPartData config = data.getPartData("beard");
        if (config == null) {
            this.isHidden = true;
            return;
        }
        this.color = config.color;
        this.isHidden = false;

        if (!config.playerTexture) {
            this.location = config.getResource();
        } else {
            this.location = null;
        }
    }
}
