package noppes.mpm.client.model.extrapart.ctenotail;

import noppes.mpm.client.MCALibrary.animation.Channel;
import noppes.mpm.client.MCALibrary.animation.KeyFrame;
import noppes.mpm.client.MCALibrary.math.Quaternion;
import noppes.mpm.client.MCALibrary.math.Vector3f;

public class ChannelCtenoIdle extends Channel {
    public static final String ANIM_IDLE = "cteno-tail-idle";

    public ChannelCtenoIdle(String _name, float _fps, int _totalFrames, byte _mode) {
        super(_name, _fps, _totalFrames, _mode);
    }

    @Override
    protected void initializeAllFrames() {
        KeyFrame frame0 = new KeyFrame();
        frame0.modelRenderersRotations.put("segment2", new Quaternion(-0.06104854F, 0.0F, 0.0F, 0.9981348F));
        frame0.modelRenderersRotations.put("segment3", new Quaternion(0.40673664F, 0.0F, 0.0F, 0.9135454F));
        frame0.modelRenderersRotations.put("segment1", new Quaternion(-0.0784591F, 0.0F, 0.0F, 0.9969173F));
        frame0.modelRenderersRotations.put("segment6", new Quaternion(-0.43837115F, 0.0F, 0.0F, 0.89879405F));
        frame0.modelRenderersRotations.put("segment4", new Quaternion(0.34202012F, 0.0F, 0.0F, 0.9396926F));
        frame0.modelRenderersRotations.put("segment5", new Quaternion(-0.26723838F, 0.0F, 0.0F, 0.96363044F));
        frame0.modelRenderersTranslations.put("segment2", new Vector3f(0.0F, 0.5F, -2.0F));
        frame0.modelRenderersTranslations.put("segment3", new Vector3f(0.0F, 1.0F, -6.0F));
        frame0.modelRenderersTranslations.put("segment1", new Vector3f(0.0F, -2.0F, -2.0F));
        frame0.modelRenderersTranslations.put("segment6", new Vector3f(0.0F, 1.0F, -5.0F));
        frame0.modelRenderersTranslations.put("segment4", new Vector3f(0.0F, 0.0F, -5.0F));
        frame0.modelRenderersTranslations.put("segment5", new Vector3f(0.0F, 0.0F, -4.0F));
        keyFrames.put(0, frame0);

        KeyFrame frame2 = new KeyFrame();
        frame2.modelRenderersRotations.put("segment2", new Quaternion(-0.043619387F, 0.0F, 0.0F, 0.99904823F));
        frame2.modelRenderersRotations.put("segment3", new Quaternion(0.42261827F, 0.0F, 0.0F, 0.90630776F));
        frame2.modelRenderersRotations.put("segment1", new Quaternion(-0.06975647F, 0.0F, 0.0F, 0.9975641F));
        frame2.modelRenderersRotations.put("segment6", new Quaternion(-0.39874908F, 0.0F, 0.0F, 0.9170601F));
        frame2.modelRenderersRotations.put("segment4", new Quaternion(0.27563736F, 0.0F, 0.0F, 0.9612617F));
        frame2.modelRenderersRotations.put("segment5", new Quaternion(-0.25881904F, 0.0F, 0.0F, 0.9659258F));
        frame2.modelRenderersTranslations.put("segment2", new Vector3f(0.0F, 0.5F, -2.0F));
        frame2.modelRenderersTranslations.put("segment3", new Vector3f(0.0F, 1.0F, -6.0F));
        frame2.modelRenderersTranslations.put("segment1", new Vector3f(0.0F, -2.0F, -2.0F));
        frame2.modelRenderersTranslations.put("segment6", new Vector3f(0.0F, 1.0F, -5.0F));
        frame2.modelRenderersTranslations.put("segment4", new Vector3f(0.0F, 0.0F, -5.0F));
        frame2.modelRenderersTranslations.put("segment5", new Vector3f(0.0F, 0.0F, -4.0F));
        keyFrames.put(1, frame2);

        keyFrames.put(3, frame0.copy());

//        KeyFrame frame4 = new KeyFrame();
//        frame4.modelRenderersRotations.put("segment2", new Quaternion(-0.06104854F, 0.0F, 0.0F, 0.9981348F));
//        frame4.modelRenderersRotations.put("segment3", new Quaternion(0.40673664F, 0.0F, 0.0F, 0.9135454F));
//        frame4.modelRenderersRotations.put("segment1", new Quaternion(-0.0784591F, 0.0F, 0.0F, 0.9969173F));
//        frame4.modelRenderersRotations.put("segment6", new Quaternion(-0.43837115F, 0.0F, 0.0F, 0.89879405F));
//        frame4.modelRenderersRotations.put("segment4", new Quaternion(0.34202012F, 0.0F, 0.0F, 0.9396926F));
//        frame4.modelRenderersRotations.put("segment5", new Quaternion(-0.26723838F, 0.0F, 0.0F, 0.96363044F));
//        frame4.modelRenderersTranslations.put("segment2", new Vector3f(0.0F, 0.5F, -2.0F));
//        frame4.modelRenderersTranslations.put("segment3", new Vector3f(0.0F, 1.0F, -6.0F));
//        frame4.modelRenderersTranslations.put("segment1", new Vector3f(0.0F, -2.0F, -2.0F));
//        frame4.modelRenderersTranslations.put("segment6", new Vector3f(0.0F, 1.0F, -5.0F));
//        frame4.modelRenderersTranslations.put("segment4", new Vector3f(0.0F, 0.0F, -5.0F));
//        frame4.modelRenderersTranslations.put("segment5", new Vector3f(0.0F, 0.0F, -4.0F));
//        keyFrames.put(4, frame4);
    }
}