package noppes.mpm.client.model.part;

import net.minecraft.entity.EntityLivingBase;
import noppes.mpm.ModelData;
import noppes.mpm.client.MCALibrary.animation.AnimationHandler;
import noppes.mpm.client.model.ModelMPM;
import noppes.mpm.client.model.ModelScaleRenderer;
import noppes.mpm.client.model.extrapart.brain.ChannelBrainFlow;
import noppes.mpm.client.model.extrapart.brain.ModelBrain;

public class ModelHead extends ModelScaleRenderer {
    private ModelMPM base;
    private ModelData data;

    private ModelBrain brain;

    public ModelHead(ModelMPM base, float headZ) {
        super(base);
        this.base = base;

        addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, headZ);
        setRotationPoint(0.0F, 0.0F, 0.0F);

        brain = new ModelBrain(base);
        addChild(brain);
    }

    public void setData(ModelData data, EntityLivingBase entity) {
        this.data = data;

        brain.isHidden = !data.brainHead;

//        if (brain.isHidden) {
//            data.animationHandler.stopAnimation(ChannelBrainFlow.ANIM_NAME);
//        } else {
//            data.animationHandler.keepAnimation(ChannelBrainFlow.ANIM_NAME, 0);
//        }
    }

    @Override
    public void render(float par1) {
//        if (!brain.isHidden) {
//            AnimationHandler.performAnimationInModel(brain.parts, data.animationHandler);
//        }

        base.loadPlayerTexture();
        super.render(par1);

    }
}
