package noppes.mpm.client.model.extrapart.ctenotail;

import noppes.mpm.client.model.MCALibrary.animation.*;
import noppes.mpm.client.model.MCALibrary.math.*;

public class ChannelTailIdle extends Channel {
    public ChannelTailIdle(String _name, float _fps, int _totalFrames, byte _mode) {
        super(_name, _fps, _totalFrames, _mode);
    }

    @Override
    protected void initializeAllFrames() {
        KeyFrame frame0 = new KeyFrame();
        frame0.modelRenderersRotations.put("segment3", new Quaternion(0.309017F, 0.0F, 0.0F, 0.95105654F));
        frame0.modelRenderersRotations.put("segment2", new Quaternion(0.008726535F, 0.0F, 0.0F, 0.9999619F));
        frame0.modelRenderersRotations.put("segment6", new Quaternion(-0.35836795F, 0.0F, 0.0F, 0.9335804F));
        frame0.modelRenderersRotations.put("segment4", new Quaternion(0.309017F, 0.0F, 0.0F, 0.95105654F));
        frame0.modelRenderersRotations.put("segment5", new Quaternion(-0.06104854F, 0.0F, 0.0F, 0.9981348F));
        frame0.modelRenderersTranslations.put("segment3", new Vector3f(0.0F, 0.0F, -6.0F));
        frame0.modelRenderersTranslations.put("segment2", new Vector3f(0.0F, 1.5F, -2.0F));
        frame0.modelRenderersTranslations.put("segment6", new Vector3f(0.0F, 1.0F, -5.0F));
        frame0.modelRenderersTranslations.put("segment4", new Vector3f(0.0F, 0.0F, -6.0F));
        frame0.modelRenderersTranslations.put("segment5", new Vector3f(0.0F, 1.0F, -5.0F));
        keyFrames.put(0, frame0);

        KeyFrame frame1 = new KeyFrame();
        frame1.modelRenderersRotations.put("segment3", new Quaternion(0.32556814F, 0.0F, 0.0F, 0.94551855F));
        frame1.modelRenderersRotations.put("segment2", new Quaternion(0.017452406F, 0.0F, 0.0F, 0.9998477F));
        frame1.modelRenderersRotations.put("segment6", new Quaternion(-0.31730464F, 0.0F, 0.0F, 0.94832367F));
        frame1.modelRenderersRotations.put("segment4", new Quaternion(0.32556814F, 0.0F, 0.0F, 0.94551855F));
        frame1.modelRenderersRotations.put("segment5", new Quaternion(-0.017452406F, 0.0F, 0.0F, 0.9998477F));
        frame1.modelRenderersTranslations.put("segment3", new Vector3f(0.0F, 0.0F, -6.0F));
        frame1.modelRenderersTranslations.put("segment2", new Vector3f(0.0F, 1.5F, -2.0F));
        frame1.modelRenderersTranslations.put("segment6", new Vector3f(0.0F, 1.0F, -5.0F));
        frame1.modelRenderersTranslations.put("segment4", new Vector3f(0.0F, 0.0F, -6.0F));
        frame1.modelRenderersTranslations.put("segment5", new Vector3f(0.0F, 1.0F, -5.0F));
        keyFrames.put(1, frame1);

//        KeyFrame frame4 = new KeyFrame();
//        frame4.modelRenderersRotations.put("segment2", new Quaternion(0.008726535F, 0.0F, 0.0F, 0.9999619F));
//        frame4.modelRenderersRotations.put("segment3", new Quaternion(0.309017F, 0.0F, 0.0F, 0.95105654F));
//        frame4.modelRenderersRotations.put("segment6", new Quaternion(-0.35836795F, 0.0F, 0.0F, 0.9335804F));
//        frame4.modelRenderersRotations.put("segment4", new Quaternion(0.309017F, 0.0F, 0.0F, 0.95105654F));
//        frame4.modelRenderersRotations.put("segment5", new Quaternion(-0.06104854F, 0.0F, 0.0F, 0.9981348F));
//        frame4.modelRenderersTranslations.put("segment2", new Vector3f(0.0F, 1.5F, -2.0F));
//        frame4.modelRenderersTranslations.put("segment3", new Vector3f(0.0F, 0.0F, -6.0F));
//        frame4.modelRenderersTranslations.put("segment6", new Vector3f(0.0F, 1.0F, -5.0F));
//        frame4.modelRenderersTranslations.put("segment4", new Vector3f(0.0F, 0.0F, -6.0F));
//        frame4.modelRenderersTranslations.put("segment5", new Vector3f(0.0F, 1.0F, -5.0F));
        keyFrames.put(2, frame0);

    }
}