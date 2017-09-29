package noppes.mpm.client.model;

import net.minecraft.client.model.ModelRenderer;
import noppes.mpm.client.model.part.*;

public class ModelMpmNewFormat extends ModelMPM {
    private ModelScaleRenderer bipedLeftArmwear;
    private ModelScaleRenderer bipedRightArmwear;
    private ModelScaleRenderer bipedLeftLegwear;
    private ModelScaleRenderer bipedRightLegwear;
    private ModelScaleRenderer bipedBodyWear;

    public ModelMpmNewFormat() {
        super(0, 64);
    }

    public void reloadBoxes() {
        this.textureHeight = 32;
        super.reloadBoxes();
        this.textureHeight = 64;

        bipedCloak = new ModelRenderer(this, 0, 0);
        bipedCloak.addBox(-5.0F, 0.0F, -1.0F, 10, 16, 1, 0);

        bipedEars = new ModelRenderer(this, 24, 0);
        bipedEars.addBox(-3.0F, -6.0F, -1.0F, 6, 6, 1, 0);

        bipedHead = new ModelScaleRenderer(this, 0, 0);
        bipedHead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0);
        bipedHead.setRotationPoint(0.0F, 0.0F, 0.0F);

        bipedHeadwear = new ModelScaleRenderer(this, 32, 0);
        bipedHeadwear.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.5F);
        bipedHeadwear.setRotationPoint(0.0F, 0.0F, 0.0F);

        bipedBody = new ModelScaleRenderer(this, 16, 16);
        bipedBody.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0);
        bipedBody.setRotationPoint(0.0F, 0.0F, 0.0F);

        bipedBodyWear = new ModelScaleRenderer(this, 16, 32);
        bipedBodyWear.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.25F);
        bipedBody.addChild(this.bipedBodyWear);

        if (data != null && data.slim) {
            bipedRightArm = new ModelScaleRenderer(this, 40, 16);
            bipedRightArm.addBox(-2.0F, -2.0F, -2.0F, 3, 12, 4, 0);
            bipedRightArm.setRotationPoint(-5.0F, 2.5F, 0.0F);

            bipedLeftArm = new ModelScaleRenderer(this, 32, 48);
            bipedLeftArm.addBox(-1.0F, -2.0F, -2.0F, 3, 12, 4, 0);
            bipedLeftArm.setRotationPoint(5.0F, 2.5F, 0.0F);

            bipedRightArmwear = new ModelScaleRenderer(this, 40, 32);
            bipedRightArmwear.addBox(-2.0F, -2.0F, -2.0F, 3, 12, 4, 0.25F);
            bipedRightArm.addChild(bipedRightArmwear);

            bipedLeftArmwear = new ModelScaleRenderer(this, 48, 48);
            bipedLeftArmwear.addBox(-1.0F, -2.0F, -2.0F, 3, 12, 4, 0.25F);
            bipedLeftArm.addChild(bipedLeftArmwear);
        } else {
            bipedRightArm = new ModelScaleRenderer(this, 40, 16);
            bipedRightArm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0);
            bipedRightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);

            bipedLeftArm = new ModelScaleRenderer(this, 32, 48);
            bipedLeftArm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0);
            bipedLeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);

            bipedRightArmwear = new ModelScaleRenderer(this, 40, 32);
            bipedRightArmwear.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.25F);
            bipedRightArm.addChild(bipedRightArmwear);

            bipedLeftArmwear = new ModelScaleRenderer(this, 48, 48);
            bipedLeftArmwear.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.25F);
            bipedLeftArm.addChild(bipedLeftArmwear);
        }

        bipedRightLeg = new ModelScaleRenderer(this, 0, 16);
        bipedRightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0);
        bipedRightLeg.setRotationPoint(-1.9F, 12.0F, 0.0F);

        bipedLeftLeg = new ModelScaleRenderer(this, 16, 48);
        bipedLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0);
        bipedLeftLeg.setRotationPoint(1.9F, 12.0F, 0.0F);

        bipedRightLegwear = new ModelScaleRenderer(this, 0, 32);
        bipedRightLegwear.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.25F);
        bipedRightLeg.addChild(bipedRightLegwear);

        bipedLeftLegwear = new ModelScaleRenderer(this, 0, 48);
        bipedLeftLegwear.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.25F);
        bipedLeftLeg.addChild(bipedLeftLegwear);

        legs = new ModelLegs(this, (ModelScaleRenderer) bipedRightLeg, (ModelScaleRenderer) bipedLeftLeg);

        headwear = new ModelHeadwear(this);
        bipedHead.addChild(ears = new ModelEars(this));
        bipedHead.addChild(mohawk = new ModelMohawk(this));
        bipedHead.addChild(hair = new ModelHair(this));
        bipedHead.addChild(beard = new ModelBeard(this));
        bipedHead.addChild(snout = new ModelSnout(this));
        bipedHead.addChild(horns = new ModelHorns(this));

        bipedBody.addChild(breasts = new ModelBreasts(this));
        bipedBody.addChild(tail = new ModelTail(this));
        bipedBody.addChild(wings = new ModelWings(this));
        bipedBody.addChild(skirt = new ModelSkirt(this));

        bipedLeftArm.addChild(clawsL = new ModelClaws(this, false));
        bipedRightArm.addChild(clawsR = new ModelClaws(this, true));
    }
}
