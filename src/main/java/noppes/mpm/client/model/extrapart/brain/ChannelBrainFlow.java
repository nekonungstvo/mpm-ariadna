package noppes.mpm.client.model.extrapart.brain;

import noppes.mpm.client.MCALibrary.animation.Channel;
import noppes.mpm.client.MCALibrary.animation.KeyFrame;
import noppes.mpm.client.MCALibrary.math.Quaternion;
import noppes.mpm.client.MCALibrary.math.Vector3f;

public class ChannelBrainFlow extends Channel {
    public static final String ANIM_NAME = "brain-flow";

    public ChannelBrainFlow() {
        super(ANIM_NAME, 0.5f, 4, Mode.LOOP);
    }

    @Override
    protected void initializeAllFrames() {
        KeyFrame frame0 = new KeyFrame();
        frame0.modelRenderersRotations.put("Brain", new Quaternion(0.0F, 0.0F, 0.0F, 1.0F));
        frame0.modelRenderersTranslations.put("Brain", new Vector3f(-2.5F, 1.0F, -2.5F));
        keyFrames.put(0, frame0);

        KeyFrame frame1 = new KeyFrame();
        frame1.modelRenderersRotations.put("Brain", new Quaternion(0.0F, 0.0F, 0.0F, 1.0F));
        frame1.modelRenderersTranslations.put("Brain", new Vector3f(-2.5F, 1.0F, -2.5F));
        keyFrames.put(1, frame1);

        KeyFrame frame2 = new KeyFrame();
        frame2.modelRenderersRotations.put("Brain", new Quaternion(0.0F, 0.0F, 0.0F, 1.0F));
        frame2.modelRenderersTranslations.put("Brain", new Vector3f(-2.5F, 3.0F, -2.5F));
        keyFrames.put(2, frame2);

        KeyFrame frame3 = new KeyFrame();
        frame3.modelRenderersRotations.put("Brain", new Quaternion(0.0F, 0.0F, 0.0F, 1.0F));
        frame3.modelRenderersTranslations.put("Brain", new Vector3f(-2.5F, 1.0F, -2.5F));
        keyFrames.put(3, frame3);
    }
}