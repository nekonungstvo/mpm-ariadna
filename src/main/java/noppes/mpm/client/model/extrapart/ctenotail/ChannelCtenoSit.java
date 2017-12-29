package noppes.mpm.client.model.extrapart.ctenotail;

import noppes.mpm.client.MCALibrary.animation.Channel;
import noppes.mpm.client.MCALibrary.animation.KeyFrame;
import noppes.mpm.client.MCALibrary.math.Quaternion;
import noppes.mpm.client.MCALibrary.math.Vector3f;

public class ChannelCtenoSit extends Channel {
    public static final String ANIM_NAME = "ctenotail-sit";

    public ChannelCtenoSit(float _fps, int _totalFrames, Mode _mode) {
        super(ANIM_NAME, _fps, _totalFrames, _mode);
    }

    @Override
    protected void initializeAllFrames() {
        KeyFrame frame0 = new KeyFrame();
        frame0.modelRenderersRotations.put("segment2", new Quaternion(0.050592944F, 0.0F, 0.0F, 0.99871933F));
        frame0.modelRenderersRotations.put("segment5", new Quaternion(-0.016579868F, 0.0F, 0.0F, 0.99986255F));
        frame0.modelRenderersRotations.put("segment4", new Quaternion(0.050592944F, 0.0F, 0.0F, 0.99871933F));
        frame0.modelRenderersTranslations.put("segment2", new Vector3f(0.0F, 0.0F, -4.0F));
        frame0.modelRenderersTranslations.put("segment5", new Vector3f(0.0F, 0.0F, -5.0F));
        frame0.modelRenderersTranslations.put("segment4", new Vector3f(0.0F, 0.0F, -5.0F));
        keyFrames.put(0, frame0);

        KeyFrame frame1 = new KeyFrame();
        frame1.modelRenderersRotations.put("segment2", new Quaternion(0.050592944F, 0.0F, 0.0F, 0.99871933F));
        frame1.modelRenderersRotations.put("segment5", new Quaternion(-0.016579868F, 0.0F, 0.0F, 0.99986255F));
        frame1.modelRenderersRotations.put("segment4", new Quaternion(0.050592944F, 0.0F, 0.0F, 0.99871933F));
        frame1.modelRenderersTranslations.put("segment2", new Vector3f(0.0F, 0.0F, -4.0F));
        frame1.modelRenderersTranslations.put("segment5", new Vector3f(0.0F, 0.0F, -5.0F));
        frame1.modelRenderersTranslations.put("segment4", new Vector3f(0.0F, 0.0F, -5.0F));
        keyFrames.put(1, frame1);
    }
}