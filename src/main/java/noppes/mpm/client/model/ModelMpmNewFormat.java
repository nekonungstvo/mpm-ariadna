package noppes.mpm.client.model;

import net.minecraft.client.model.ModelRenderer;
import noppes.mpm.client.model.part.ModelHead;

public class ModelMpmNewFormat extends ModelMPM {
    private ModelScaleRenderer bipedLeftArmwear;
    private ModelScaleRenderer bipedRightArmwear;
    private ModelScaleRenderer bipedLeftLegwear;
    private ModelScaleRenderer bipedRightLegwear;
    private ModelScaleRenderer bipedBodyWear;

    public ModelMpmNewFormat() {
        super(0, 64);
    }

    @Override
    public void reloadBoxes() {
        bipedCloak = new ModelRenderer(this, 0, 0);
        bipedCloak.addBox(-5.0F, 0.0F, -1.0F, 10, 16, 1, 0);

        bipedEars = new ModelRenderer(this, 24, 0);
        bipedEars.addBox(-3.0F, -6.0F, -1.0F, 6, 6, 1, 0);

        this.bipedHead = new ModelHead(this, 0);

        bipedHeadwear = new ModelScaleRenderer(this, 32, 0);
        bipedHeadwear.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.25F);
        bipedHeadwear.setRotationPoint(0.0F, 0.0F, 0.0F);

        bipedBody = new ModelScaleRenderer(this, 16, 16);
        bipedBody.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0);
        bipedBody.setRotationPoint(0.0F, 0.0F, 0.0F);

        bipedBodyWear = new ModelScaleRenderer(this, 16, 32);
        bipedBodyWear.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.25F);
        bipedBody.addChild(this.bipedBodyWear);

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

        reloadExtraPartBoxes();
    }
}
