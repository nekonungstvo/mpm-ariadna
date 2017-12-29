package noppes.mpm.client.model.extrapart.ctenotail;

import noppes.mpm.client.MCALibrary.animation.Channel;
import noppes.mpm.client.MCALibrary.animation.KeyFrame;
import noppes.mpm.client.MCALibrary.math.Quaternion;
import noppes.mpm.client.MCALibrary.math.Vector3f;

public class ChannelCtenoIdle extends Channel {
    public static final String ANIM_NAME = "ctenotail-idle";

    public ChannelCtenoIdle(float _fps, int _totalFrames, Mode _mode) {
        super(ANIM_NAME, _fps, _totalFrames, _mode);
    }

    @Override
    protected void initializeAllFrames() {
        KeyFrame frame0 = new KeyFrame();
        frame0.modelRenderersRotations.put("topBulb", new Quaternion(0.1478094F, 0.0F, 0.0F, 0.9890159F));
        frame0.modelRenderersRotations.put("segment2", new Quaternion(-0.18223552F, 0.0F, 0.0F, 0.9832549F));
        frame0.modelRenderersRotations.put("segment5", new Quaternion(0.23344538F, 0.0F, 0.0F, 0.9723699F));
        frame0.modelRenderersRotations.put("segment6", new Quaternion(0.11320321F, 0.0F, 0.0F, 0.9935719F));
        frame0.modelRenderersRotations.put("segment4", new Quaternion(0.26723838F, 0.0F, 0.0F, 0.96363044F));
        frame0.modelRenderersRotations.put("segment3", new Quaternion(0.23344538F, 0.0F, 0.0F, 0.9723699F));
        frame0.modelRenderersTranslations.put("topBulb", new Vector3f(0.0F, 0.0F, -4.0F));
        frame0.modelRenderersTranslations.put("segment2", new Vector3f(0.0F, 0.0F, -4.0F));
        frame0.modelRenderersTranslations.put("segment5", new Vector3f(0.0F, 0.0F, -5.0F));
        frame0.modelRenderersTranslations.put("segment6", new Vector3f(0.0F, 0.0F, -4.0F));
        frame0.modelRenderersTranslations.put("segment4", new Vector3f(0.0F, 0.0F, -5.0F));
        frame0.modelRenderersTranslations.put("segment3", new Vector3f(0.0F, 0.0F, -5.0F));
        keyFrames.put(0, frame0);

        KeyFrame frame2 = new KeyFrame();
        frame2.modelRenderersRotations.put("topBulb", new Quaternion(0.17364818F, 0.0F, 0.0F, 0.9848077F));
        frame2.modelRenderersRotations.put("segment2", new Quaternion(-0.15643448F, 0.0F, 0.0F, 0.98768836F));
        frame2.modelRenderersRotations.put("segment5", new Quaternion(0.25038F, 0.0F, 0.0F, 0.96814764F));
        frame2.modelRenderersRotations.put("segment6", new Quaternion(0.1305262F, 0.0F, 0.0F, 0.9914449F));
        frame2.modelRenderersRotations.put("segment4", new Quaternion(0.27563736F, 0.0F, 0.0F, 0.9612617F));
        frame2.modelRenderersRotations.put("segment3", new Quaternion(0.2419219F, 0.0F, 0.0F, 0.9702957F));
        frame2.modelRenderersTranslations.put("topBulb", new Vector3f(0.0F, 0.0F, -4.0F));
        frame2.modelRenderersTranslations.put("segment2", new Vector3f(0.0F, 0.0F, -4.0F));
        frame2.modelRenderersTranslations.put("segment5", new Vector3f(0.0F, 0.0F, -5.0F));
        frame2.modelRenderersTranslations.put("segment6", new Vector3f(0.0F, 0.0F, -4.0F));
        frame2.modelRenderersTranslations.put("segment4", new Vector3f(0.0F, 0.0F, -5.0F));
        frame2.modelRenderersTranslations.put("segment3", new Vector3f(0.0F, 0.0F, -5.0F));
        keyFrames.put(1, frame2);

        KeyFrame frame4 = new KeyFrame();
        frame4.modelRenderersRotations.put("topBulb", new Quaternion(0.1478094F, 0.0F, 0.0F, 0.9890159F));
        frame4.modelRenderersRotations.put("segment2", new Quaternion(-0.18223552F, 0.0F, 0.0F, 0.9832549F));
        frame4.modelRenderersRotations.put("segment5", new Quaternion(0.23344538F, 0.0F, 0.0F, 0.9723699F));
        frame4.modelRenderersRotations.put("segment6", new Quaternion(0.11320321F, 0.0F, 0.0F, 0.9935719F));
        frame4.modelRenderersRotations.put("segment4", new Quaternion(0.26723838F, 0.0F, 0.0F, 0.96363044F));
        frame4.modelRenderersRotations.put("segment3", new Quaternion(0.23344538F, 0.0F, 0.0F, 0.9723699F));
        frame4.modelRenderersTranslations.put("topBulb", new Vector3f(0.0F, 0.0F, -4.0F));
        frame4.modelRenderersTranslations.put("segment2", new Vector3f(0.0F, 0.0F, -4.0F));
        frame4.modelRenderersTranslations.put("segment5", new Vector3f(0.0F, 0.0F, -5.0F));
        frame4.modelRenderersTranslations.put("segment6", new Vector3f(0.0F, 0.0F, -4.0F));
        frame4.modelRenderersTranslations.put("segment4", new Vector3f(0.0F, 0.0F, -5.0F));
        frame4.modelRenderersTranslations.put("segment3", new Vector3f(0.0F, 0.0F, -5.0F));
        keyFrames.put(2, frame4);
    }
}