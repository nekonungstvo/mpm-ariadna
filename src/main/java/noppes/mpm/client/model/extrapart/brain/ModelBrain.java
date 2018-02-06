package noppes.mpm.client.model.extrapart.brain;

import net.minecraft.util.ResourceLocation;
import noppes.mpm.client.ClientProxy;
import noppes.mpm.client.MCALibrary.MCAModelRenderer;
import noppes.mpm.client.MCALibrary.MCAToMPMModelRenderer;
import noppes.mpm.client.MCALibrary.math.Matrix4f;
import noppes.mpm.client.MCALibrary.math.Quaternion;
import noppes.mpm.client.model.ModelMPM;

import java.util.HashMap;

public class ModelBrain extends MCAToMPMModelRenderer {
    private static final ResourceLocation texture = new ResourceLocation("moreplayermodels:textures/head/brain.png");
    public HashMap<String, MCAModelRenderer> parts = new HashMap<>();

    private ModelMPM base;

    public ModelBrain(ModelMPM base) {
        super(base);
        this.base = base;

        final MCAModelRenderer mind = new MCAModelRenderer(base, "Brain", 0, 0);
        mind.mirror = false;
        mind.addBox(0.0F, 0.0F, 0.0F, 5, 4, 5);
        mind.setInitialRotationPoint(-2.5F, 2.0F, -2.5F);
        mind.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, 0.0F, 1.0F)).transpose());
        mind.setTextureSize(64, 64);
        parts.put(mind.boxName, mind);

        addChild(mind);
    }

    @Override
    public void render(float par1) {
        ClientProxy.bindTexture(texture);
        base.currentlyPlayerTexture = false;

        super.render(par1);
    }
}