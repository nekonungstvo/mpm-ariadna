package noppes.mpm.client.model.extrapart.flufftail;

import noppes.mpm.client.MCALibrary.animation.Channel;
import noppes.mpm.client.MCALibrary.animation.KeyFrame;
import noppes.mpm.client.MCALibrary.math.Quaternion;
import noppes.mpm.client.MCALibrary.math.Vector3f;

public class ChannelFluffyLie extends Channel {
    public static final String ANIM_NAME = "fluffytail-lie";

    public ChannelFluffyLie() {
        super(ANIM_NAME, 1, 2, Mode.LOOP);
    }

    @Override
    protected void initializeAllFrames() {
        KeyFrame frame0 = new KeyFrame();
        frame0.modelRenderersRotations.put("Tail 3", new Quaternion(-0.28216457F, 0.08108666F, 0.02394201F, 0.9556332F));
        frame0.modelRenderersRotations.put("Tail 1 start", new Quaternion(0.7587031F, 0.0F, 0.0F, 0.65143657F));
        frame0.modelRenderersRotations.put("Tail 4", new Quaternion(-0.13580234F, 0.10234384F, -0.002718662F, 0.9854319F));
        frame0.modelRenderersRotations.put("Tail 2", new Quaternion(0.034022674F, 0.016570266F, -5.6416896E-4F, 0.9992835F));
        frame0.modelRenderersRotations.put("tail", new Quaternion(0.2831785F, 0.0F, 0.0F, 0.9590672F));
        frame0.modelRenderersRotations.put("Tail 7 end", new Quaternion(-0.3298125F, 0.09043163F, 0.03175733F, 0.9391684F));
        frame0.modelRenderersRotations.put("Tail 6", new Quaternion(0.09994628F, 0.14705272F, -0.014937071F, 0.9839528F));
        frame0.modelRenderersTranslations.put("Tail 3", new Vector3f(0.0F, 0.0F, 3.0F));
        frame0.modelRenderersTranslations.put("Tail 1 start", new Vector3f(0.0F, 1.0F, -1.5F));
        frame0.modelRenderersTranslations.put("Tail 4", new Vector3f(0.0F, 0.0F, 2.5F));
        frame0.modelRenderersTranslations.put("Tail 2", new Vector3f(0.0F, 0.0F, 1.5F));
        frame0.modelRenderersTranslations.put("tail", new Vector3f(0.0F, 0.0F, 0.0F));
        frame0.modelRenderersTranslations.put("Tail 7 end", new Vector3f(0.0F, 0.0F, 3.5F));
        frame0.modelRenderersTranslations.put("Tail 6", new Vector3f(0.0F, 0.0F, 3.5F));
        keyFrames.put(0, frame0);

        KeyFrame frame1 = new KeyFrame();
        frame1.modelRenderersRotations.put("Tail 3", new Quaternion(-0.28216457F, 0.08108666F, 0.02394201F, 0.9556332F));
        frame1.modelRenderersRotations.put("Tail 1 start", new Quaternion(0.7587031F, 0.0F, 0.0F, 0.65143657F));
        frame1.modelRenderersRotations.put("Tail 4", new Quaternion(-0.13580234F, 0.10234384F, -0.002718662F, 0.9854319F));
        frame1.modelRenderersRotations.put("Tail 2", new Quaternion(0.034022674F, 0.016570266F, -5.6416896E-4F, 0.9992835F));
        frame1.modelRenderersRotations.put("tail", new Quaternion(0.2831785F, 0.0F, 0.0F, 0.9590672F));
        frame1.modelRenderersRotations.put("Tail 7 end", new Quaternion(-0.3298125F, 0.09043163F, 0.03175733F, 0.9391684F));
        frame1.modelRenderersRotations.put("Tail 6", new Quaternion(0.09994628F, 0.14705272F, -0.014937071F, 0.9839528F));
        frame1.modelRenderersTranslations.put("Tail 3", new Vector3f(0.0F, 0.0F, 3.0F));
        frame1.modelRenderersTranslations.put("Tail 1 start", new Vector3f(0.0F, 1.0F, -1.5F));
        frame1.modelRenderersTranslations.put("Tail 4", new Vector3f(0.0F, 0.0F, 2.5F));
        frame1.modelRenderersTranslations.put("Tail 2", new Vector3f(0.0F, 0.0F, 1.5F));
        frame1.modelRenderersTranslations.put("tail", new Vector3f(0.0F, 0.0F, 0.0F));
        frame1.modelRenderersTranslations.put("Tail 7 end", new Vector3f(0.0F, 0.0F, 3.5F));
        frame1.modelRenderersTranslations.put("Tail 6", new Vector3f(0.0F, 0.0F, 3.5F));
        keyFrames.put(1, frame1);
    }
}