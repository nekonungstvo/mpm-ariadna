package noppes.mpm.client.model.extrapart.ctenotail;

import noppes.mpm.client.MCALibrary.animation.Channel;
import noppes.mpm.client.MCALibrary.animation.KeyFrame;
import noppes.mpm.client.MCALibrary.math.Quaternion;
import noppes.mpm.client.MCALibrary.math.Vector3f;

public class ChannelCtenoLowered extends Channel {
    public static final String ANIM_NAME = "ctenotail-lowered";

    public ChannelCtenoLowered(float _fps, int _totalFrames, Mode _mode) {
        super(ANIM_NAME, _fps, _totalFrames, _mode);
    }

    @Override
    protected void initializeAllFrames() {
        KeyFrame frame0 = new KeyFrame();
        frame0.modelRenderersRotations.put("segment1", new Quaternion(-0.2831785F, 0.0F, 0.0F, 0.9590672F));
        frame0.modelRenderersRotations.put("segment2", new Quaternion(-0.31564903F, 0.0F, 0.0F, 0.948876F));
        frame0.modelRenderersRotations.put("segment5", new Quaternion(-0.16848938F, 0.0F, 0.0F, 0.98570347F));
        frame0.modelRenderersRotations.put("segment6", new Quaternion(0.34693563F, 0.0F, 0.0F, 0.9378889F));
        frame0.modelRenderersRotations.put("segment4", new Quaternion(-0.1175374F, 0.0F, 0.0F, 0.99306846F));
        frame0.modelRenderersRotations.put("segment3", new Quaternion(-0.15126081F, 0.0F, 0.0F, 0.98849386F));
        frame0.modelRenderersTranslations.put("segment1", new Vector3f(0.0F, 3.0F, -1.0F));
        frame0.modelRenderersTranslations.put("segment2", new Vector3f(0.0F, 0.0F, -4.0F));
        frame0.modelRenderersTranslations.put("segment5", new Vector3f(0.0F, 0.0F, -5.0F));
        frame0.modelRenderersTranslations.put("segment6", new Vector3f(0.0F, 0.0F, -4.0F));
        frame0.modelRenderersTranslations.put("segment4", new Vector3f(0.0F, 0.0F, -5.0F));
        frame0.modelRenderersTranslations.put("segment3", new Vector3f(0.0F, 0.0F, -5.0F));
        keyFrames.put(0, frame0);

        KeyFrame frame1 = new KeyFrame();
        frame1.modelRenderersRotations.put("segment1", new Quaternion(-0.2831785F, 0.0F, 0.0F, 0.9590672F));
        frame1.modelRenderersRotations.put("segment2", new Quaternion(-0.31564903F, 0.0F, 0.0F, 0.948876F));
        frame1.modelRenderersRotations.put("segment5", new Quaternion(-0.16848938F, 0.0F, 0.0F, 0.98570347F));
        frame1.modelRenderersRotations.put("segment6", new Quaternion(0.34693563F, 0.0F, 0.0F, 0.9378889F));
        frame1.modelRenderersRotations.put("segment4", new Quaternion(-0.1175374F, 0.0F, 0.0F, 0.99306846F));
        frame1.modelRenderersRotations.put("segment3", new Quaternion(-0.15126081F, 0.0F, 0.0F, 0.98849386F));
        frame1.modelRenderersTranslations.put("segment1", new Vector3f(0.0F, 3.0F, -1.0F));
        frame1.modelRenderersTranslations.put("segment2", new Vector3f(0.0F, 0.0F, -4.0F));
        frame1.modelRenderersTranslations.put("segment5", new Vector3f(0.0F, 0.0F, -5.0F));
        frame1.modelRenderersTranslations.put("segment6", new Vector3f(0.0F, 0.0F, -4.0F));
        frame1.modelRenderersTranslations.put("segment4", new Vector3f(0.0F, 0.0F, -5.0F));
        frame1.modelRenderersTranslations.put("segment3", new Vector3f(0.0F, 0.0F, -5.0F));
        keyFrames.put(1, frame1);
    }
}