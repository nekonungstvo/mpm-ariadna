package noppes.mpm.client.model.extrapart.flufftail;

import noppes.mpm.client.MCALibrary.MCAModelRenderer;
import noppes.mpm.client.MCALibrary.MCAToMPMModelRenderer;
import noppes.mpm.client.MCALibrary.math.Matrix4f;
import noppes.mpm.client.MCALibrary.math.Quaternion;
import noppes.mpm.client.model.ModelMPM;
import noppes.mpm.constants.EnumAnimation;

import java.util.HashMap;

public class ModelFluffyTail extends MCAToMPMModelRenderer {
    public HashMap<String, MCAModelRenderer> parts = new HashMap<>();

    MCAModelRenderer tail;
    MCAModelRenderer hat;
    MCAModelRenderer tail1Start;
    MCAModelRenderer tail2;
    MCAModelRenderer tail3;
    MCAModelRenderer tail4;
    MCAModelRenderer tail6;
    MCAModelRenderer tail7End;
    MCAModelRenderer tail8End;

    public ModelFluffyTail(ModelMPM base) {
        super(base);
        textureWidth = 64;
        textureHeight = 64;

        tail = new MCAModelRenderer(base, "tail", 0, 0);
        tail.mirror = false;
        tail.addBox(0.0F, 0.0F, 0.0F, 1, 1, 1);
        tail.setInitialRotationPoint(0.0F, 0.0F, 0.0F);
        tail.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.0F, 0.0F, 0.0F, 1.0F)).transpose());
        tail.setTextureSize(64, 64);
        parts.put(tail.boxName, tail);
        addChild(tail);

        tail1Start = new MCAModelRenderer(base, "Tail 1 start", 4, 0);
        tail1Start.mirror = false;
        tail1Start.addBox(-1.5F, -1.5F, 0.0F, 3, 3, 2);
        tail1Start.setInitialRotationPoint(0.0F, 1.0F, -0.5F);
        tail1Start.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.9099613F, 0.0F, 0.0F, 0.41469327F)).transpose());
        tail1Start.setTextureSize(64, 64);
        parts.put(tail1Start.boxName, tail1Start);
        tail.addChild(tail1Start);

        tail2 = new MCAModelRenderer(base, "Tail 2", 0, 5);
        tail2.mirror = false;
        tail2.addBox(-2.0F, -2.0F, 0.0F, 4, 4, 3);
        tail2.setInitialRotationPoint(0.0F, 0.0F, 1.5F);
        tail2.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(-0.14712891F, 0.09479297F, 0.014166903F, 0.9844626F)).transpose());
        tail2.setTextureSize(64, 64);
        parts.put(tail2.boxName, tail2);
        tail1Start.addChild(tail2);

        tail3 = new MCAModelRenderer(base, "Tail 3", 28, 4);
        tail3.mirror = false;
        tail3.addBox(-2.0F, -2.0F, -1.0F, 4, 4, 4);
        tail3.setInitialRotationPoint(0.0F, 0.0F, 3.5F);
        tail3.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.18223552F, 0.0F, 0.0F, 0.9832549F)).transpose());
        tail3.setTextureSize(64, 64);
        parts.put(tail3.boxName, tail3);
        tail2.addChild(tail3);

        tail4 = new MCAModelRenderer(base, "Tail 4", 18, 12);
        tail4.mirror = false;
        tail4.addBox(-2.5F, -2.5F, 0.0F, 5, 5, 4);
        tail4.setInitialRotationPoint(0.0F, 0.0F, 2.5F);
        tail4.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.18085381F, 0.22793263F, 0.05673214F, 0.9550498F)).transpose());
        tail4.setTextureSize(64, 64);
        parts.put(tail4.boxName, tail4);
        tail3.addChild(tail4);

        tail6 = new MCAModelRenderer(base, "Tail 6", 0, 12);
        tail6.mirror = false;
        tail6.addBox(-2.5F, -2.5F, 0.0F, 5, 5, 4);
        tail6.setInitialRotationPoint(0.0F, 0.0F, 3.5F);
        tail6.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.264303F, 0.14243364F, -0.039500345F, 0.9530458F)).transpose());
        tail6.setTextureSize(64, 64);
        parts.put(tail6.boxName, tail6);
        tail4.addChild(tail6);

        tail7End = new MCAModelRenderer(base, "Tail 7 end", 14, 5);
        tail7End.mirror = false;
        tail7End.addBox(-2.0F, -2.0F, 0.0F, 4, 4, 3);
        tail7End.setInitialRotationPoint(0.0F, 0.0F, 3.5F);
        tail7End.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.18139654F, 0.09424081F, -0.0174665F, 0.9787282F)).transpose());
        tail7End.setTextureSize(64, 64);
        parts.put(tail7End.boxName, tail7End);
        tail6.addChild(tail7End);

        tail8End = new MCAModelRenderer(base, "Tail 8 end", 14, 0);
        tail8End.mirror = false;
        tail8End.addBox(-1.5F, -1.5F, 0.0F, 3, 3, 2);
        tail8End.setInitialRotationPoint(0.0F, 0.0F, 2.5F);
        tail8End.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(-0.12992528F, 0.09502578F, 0.012510382F, 0.9868805F)).transpose());
        tail8End.setTextureSize(64, 64);
        parts.put(tail8End.boxName, tail8End);
        tail7End.addChild(tail8End);
    }

    public static String getAnimation(EnumAnimation animation) {
        if (animation == EnumAnimation.SITTING) return ChannelFluffySit.ANIM_NAME;
        else if (animation == EnumAnimation.CRAWLING) return ChannelFluffyLie.ANIM_NAME;
        else return null;
    }
}