package noppes.mpm.client.model.part;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import noppes.mpm.ModelData;
import noppes.mpm.client.ClientProxy;
import noppes.mpm.client.model.ModelMPM;
import noppes.mpm.client.model.ModelScaleRenderer;

public class ModelBrain extends ModelScaleRenderer {
    private static final ResourceLocation texture = new ResourceLocation("moreplayermodels:textures/head/brain.png");
    private ModelMPM base;

    public ModelBrain(ModelMPM base) {
        super(base, "Brain", 0, 0);
        this.base = base;

        addBox(-2.5F, -6.0F, -2.5F, 5, 4, 5);
        setRotationPoint(0.0F, 0.0F, 0.0F);
    }

    public void setData(ModelData data, EntityLivingBase entity) {
        this.isHidden = !data.brainHead;
    }

    @Override
    public void render(float par1) {
        ClientProxy.bindTexture(texture);
        base.currentlyPlayerTexture = false;
        super.render(par1);
    }
}
