package noppes.mpm.client.model.extrapart.flufftail;

import noppes.mpm.client.MCALibrary.animation.Channel;
import noppes.mpm.client.MCALibrary.animation.KeyFrame;
import noppes.mpm.client.MCALibrary.math.Quaternion;
import noppes.mpm.client.MCALibrary.math.Vector3f;

public class ChannelFluffySit extends Channel {
    public static final String ANIM_NAME = "fluffytail-sit";
    public ChannelFluffySit() {
        super(ANIM_NAME, 1, 2, Mode.LOOP);
    }

    @Override
    protected void initializeAllFrames() {
//        KeyFrame frame0 = new KeyFrame();
//        frame0.modelRenderersRotations.put("Tail 3", new Quaternion(0.18223552F, 0.0F, 0.0F, 0.9832549F));
//        frame0.modelRenderersRotations.put("Tail 1 start", new Quaternion(0.9099613F, 0.0F, 0.0F, 0.41469327F));
//        frame0.modelRenderersRotations.put("Tail 4", new Quaternion(0.18085381F, 0.22793263F, 0.05673214F, 0.9550498F));
//        frame0.modelRenderersRotations.put("Tail 2", new Quaternion(-0.14712891F, 0.09479297F, 0.014166903F, 0.9844626F));
//        frame0.modelRenderersRotations.put("Tail 7 end", new Quaternion(0.18139654F, 0.09424081F, -0.0174665F, 0.9787282F));
//        frame0.modelRenderersRotations.put("Tail 6", new Quaternion(0.264303F, 0.14243364F, -0.039500345F, 0.9530458F));
//        frame0.modelRenderersTranslations.put("Tail 3", new Vector3f(0.0F, 0.0F, 3.5F));
//        frame0.modelRenderersTranslations.put("Tail 1 start", new Vector3f(0.0F, 1.0F, -0.5F));
//        frame0.modelRenderersTranslations.put("Tail 4", new Vector3f(0.0F, 0.0F, 2.5F));
//        frame0.modelRenderersTranslations.put("Tail 2", new Vector3f(0.0F, 0.0F, 1.5F));
//        frame0.modelRenderersTranslations.put("Tail 7 end", new Vector3f(0.0F, 0.0F, 3.5F));
//        frame0.modelRenderersTranslations.put("Tail 6", new Vector3f(0.0F, 0.0F, 3.5F));
//        keyFrames.put(0, frame0);

        KeyFrame frame1 = new KeyFrame();
        frame1.modelRenderersRotations.put("Tail 3", new Quaternion(0.049374502F, 0.21786387F, -0.011036509F, 0.9746669F));
        frame1.modelRenderersRotations.put("Tail 1 start", new Quaternion(0.99306846F, 0.0F, 0.0F, 0.1175374F));
        frame1.modelRenderersRotations.put("Tail 4", new Quaternion(-0.047760636F, 0.32098582F, 0.117992625F, 0.9384907F));
        frame1.modelRenderersRotations.put("Tail 2", new Quaternion(-0.1452633F, 0.18277907F, 0.027316514F, 0.9719795F));
        frame1.modelRenderersRotations.put("Tail 7 end", new Quaternion(0.015447318F, 0.3632013F, -0.0060226573F, 0.9315632F));
        frame1.modelRenderersRotations.put("Tail 6", new Quaternion(0.08022501F, 0.31451884F, -0.02668731F, 0.9454785F));
        frame1.modelRenderersTranslations.put("Tail 3", new Vector3f(0.0F, 0.0F, 3.5F));
        frame1.modelRenderersTranslations.put("Tail 1 start", new Vector3f(0.0F, 1.0F, -0.5F));
        frame1.modelRenderersTranslations.put("Tail 4", new Vector3f(0.0F, 0.0F, 2.5F));
        frame1.modelRenderersTranslations.put("Tail 2", new Vector3f(0.0F, 0.0F, 1.5F));
        frame1.modelRenderersTranslations.put("Tail 7 end", new Vector3f(0.0F, 0.0F, 3.5F));
        frame1.modelRenderersTranslations.put("Tail 6", new Vector3f(0.0F, 0.0F, 3.5F));
        keyFrames.put(0, frame1);

        KeyFrame frame2 = new KeyFrame();
        frame2.modelRenderersRotations.put("Tail 3", new Quaternion(0.049374502F, 0.21786387F, -0.011036509F, 0.9746669F));
        frame2.modelRenderersRotations.put("Tail 1 start", new Quaternion(0.99306846F, 0.0F, 0.0F, 0.1175374F));
        frame2.modelRenderersRotations.put("Tail 4", new Quaternion(-0.047760636F, 0.32098582F, 0.117992625F, 0.9384907F));
        frame2.modelRenderersRotations.put("Tail 2", new Quaternion(-0.1452633F, 0.18277907F, 0.027316514F, 0.9719795F));
        frame2.modelRenderersRotations.put("Tail 7 end", new Quaternion(0.015447318F, 0.3632013F, -0.0060226573F, 0.9315632F));
        frame2.modelRenderersRotations.put("Tail 6", new Quaternion(0.08022501F, 0.31451884F, -0.02668731F, 0.9454785F));
        frame2.modelRenderersTranslations.put("Tail 3", new Vector3f(0.0F, 0.0F, 3.5F));
        frame2.modelRenderersTranslations.put("Tail 1 start", new Vector3f(0.0F, 1.0F, -0.5F));
        frame2.modelRenderersTranslations.put("Tail 4", new Vector3f(0.0F, 0.0F, 2.5F));
        frame2.modelRenderersTranslations.put("Tail 2", new Vector3f(0.0F, 0.0F, 1.5F));
        frame2.modelRenderersTranslations.put("Tail 7 end", new Vector3f(0.0F, 0.0F, 3.5F));
        frame2.modelRenderersTranslations.put("Tail 6", new Vector3f(0.0F, 0.0F, 3.5F));
        keyFrames.put(1, frame2);

    }
}