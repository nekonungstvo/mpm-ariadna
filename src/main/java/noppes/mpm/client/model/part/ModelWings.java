package noppes.mpm.client.model.part;

import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import noppes.mpm.ModelData;
import noppes.mpm.ModelPartData;
import noppes.mpm.client.model.Model2DRenderer;
import noppes.mpm.client.model.ModelMPM;
import noppes.mpm.client.model.ModelPartInterface;

public class ModelWings extends ModelPartInterface {
    private Model2DRenderer lWing;
    private Model2DRenderer rWing;

    public ModelWings(ModelMPM base) {
        super(base);

        this.lWing = new Model2DRenderer(base, 56.0F, 16.0F, 8, 16, 64.0F, 32.0F);
        this.lWing.mirror = true;
        this.lWing.setRotationPoint(2.0F, 4.0F, 2.0F);
        this.lWing.setRotationOffset(-16.0F, -12.0F);
        setRotation(this.lWing, 0.7141593F, -0.5235988F, -0.5090659F);
        addChild(this.lWing);

        this.rWing = new Model2DRenderer(base, 56.0F, 16.0F, 8, 16, 64.0F, 32.0F);
        this.rWing.setRotationPoint(-2.0F, 4.0F, 2.0F);
        this.rWing.setRotationOffset(-16.0F, -12.0F);
        setRotation(this.rWing, 0.7141593F, 0.5235988F, 0.5090659F);
        addChild(this.rWing);
    }


    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity) {
        this.rWing.rotateAngleX = 0.7141593F;
        this.rWing.rotateAngleZ = 0.5090659F;

        this.lWing.rotateAngleX = 0.7141593F;
        this.lWing.rotateAngleZ = -0.5090659F;

        float motion = Math.abs(MathHelper.sin(par1 * 0.033F + 3.1415927F) * 0.4F) * par2;
        if ((!entity.onGround) || (motion > 0.01D)) {
            float speed = 0.55F + 0.5F * motion;
            float y = MathHelper.sin(par3 * 0.55F);

            this.rWing.rotateAngleZ += y * 0.5F * speed;
            this.rWing.rotateAngleX += y * 0.5F * speed;

            this.lWing.rotateAngleZ -= y * 0.5F * speed;
            this.lWing.rotateAngleX += y * 0.5F * speed;
        } else {
            this.lWing.rotateAngleZ += MathHelper.cos(par3 * 0.09F) * 0.05F + 0.05F;
            this.rWing.rotateAngleZ -= MathHelper.cos(par3 * 0.09F) * 0.05F + 0.05F;
            this.lWing.rotateAngleX += MathHelper.sin(par3 * 0.067F) * 0.05F;
            this.rWing.rotateAngleX += MathHelper.sin(par3 * 0.067F) * 0.05F;
        }
    }

    public void initData(ModelData data) {
        ModelPartData config = data.getPartData("wings");
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
