package noppes.mpm.client.model.part;

import noppes.mpm.ModelData;
import noppes.mpm.ModelPartData;
import noppes.mpm.client.model.Model2DRenderer;
import noppes.mpm.client.model.ModelMPM;

public class ModelMohawk extends noppes.mpm.client.model.ModelPartInterface {
    private Model2DRenderer model;

    public ModelMohawk(ModelMPM base) {
        super(base);

        this.model = new Model2DRenderer(base, 0, 0, 13, 13);
        this.model.setRotationPoint(-0.5F, 0.0F, 9.0F);
        setRotation(this.model, 0.0F, 1.5707964F, 0.0F);
        this.model.setScale(0.825F);
        addChild(this.model);
    }


    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, net.minecraft.entity.Entity entity) {
    }


    public void initData(ModelData data) {
        ModelPartData config = data.getPartData("mohawk");
        if (config == null) {
            this.isHidden = true;
            return;
        }
        this.color = config.color;
        this.isHidden = false;
        this.location = config.getResource();
    }
}
