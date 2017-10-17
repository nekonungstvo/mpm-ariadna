package noppes.mpm.client.MCALibrary.animation;

import noppes.mpm.client.MCALibrary.math.Quaternion;
import noppes.mpm.client.MCALibrary.math.Vector3f;

import java.util.HashMap;

public class KeyFrame {
    public HashMap<String, Quaternion> modelRenderersRotations = new HashMap<String, Quaternion>();
    public HashMap<String, Vector3f> modelRenderersTranslations = new HashMap<String, Vector3f>();

    public boolean useBoxInRotations(String boxName) {
        return modelRenderersRotations.get(boxName) != null;
    }

    public boolean useBoxInTranslations(String boxName) {
        return modelRenderersTranslations.get(boxName) != null;
    }

    public KeyFrame copy() {
        KeyFrame c = new KeyFrame();
        c.modelRenderersRotations.putAll(modelRenderersRotations);
        c.modelRenderersTranslations.putAll(modelRenderersTranslations);
        return c;
    }
}