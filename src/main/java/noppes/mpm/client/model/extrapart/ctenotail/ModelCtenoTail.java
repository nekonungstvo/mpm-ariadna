package noppes.mpm.client.model.extrapart.ctenotail;

import noppes.mpm.client.MCALibrary.MCAModelRenderer;
import noppes.mpm.client.MCALibrary.MCAToMPMModelRenderer;
import noppes.mpm.client.MCALibrary.math.Matrix4f;
import noppes.mpm.client.MCALibrary.math.Quaternion;
import noppes.mpm.client.model.ModelMPM;
import noppes.mpm.constants.EnumAnimation;

import java.util.HashMap;

public class ModelCtenoTail extends MCAToMPMModelRenderer {
    public HashMap<String, MCAModelRenderer> parts = new HashMap<>();

    MCAModelRenderer segment1;
    MCAModelRenderer thorn14;
    MCAModelRenderer thorn15;
    MCAModelRenderer segment2;
    MCAModelRenderer thorn11;
    MCAModelRenderer thorn12;
    MCAModelRenderer thorn13;
    MCAModelRenderer thorn16;
    MCAModelRenderer segment3;
    MCAModelRenderer thorn23;
    MCAModelRenderer thorn24;
    MCAModelRenderer thorn21;
    MCAModelRenderer thorn22;
    MCAModelRenderer segment4;
    MCAModelRenderer thorn32;
    MCAModelRenderer thorn33;
    MCAModelRenderer thorn31;
    MCAModelRenderer segment5;
    MCAModelRenderer thorn42;
    MCAModelRenderer thorn43;
    MCAModelRenderer thorn44;
    MCAModelRenderer thorn41;
    MCAModelRenderer segment6;
    MCAModelRenderer thorn52;
    MCAModelRenderer thorn53;
    MCAModelRenderer thorn51;
    MCAModelRenderer thorn61;
    MCAModelRenderer thorn62;
    MCAModelRenderer topBulb;

    public ModelCtenoTail(ModelMPM base) {
        super(base);
        textureWidth = 64;
        textureHeight = 64;

        segment1 = new MCAModelRenderer(base, "segment1", 0, 0);
        segment1.mirror = false;
        segment1.addBox(-3.0F, -2.0F, -4.0F, 6, 4, 4);
        segment1.setInitialRotationPoint(0.0F, 3.0F, -3.0F);
        segment1.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(-0.25881904F, 0.0F, 0.0F, 0.9659258F)).transpose());
        segment1.setTextureSize(64, 64);
        parts.put(segment1.boxName, segment1);
        addChild(segment1);

        thorn14 = new MCAModelRenderer(base, "thorn14", 0, 9);
        thorn14.mirror = false;
        thorn14.addBox(-1.0F, 0.0F, -0.5F, 2, 4, 1);
        thorn14.setInitialRotationPoint(2.0F, 0.5F, 0.5F);
        thorn14.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(-0.21315141F, 0.037584346F, -0.16953203F, 0.96146387F)).transpose());
        thorn14.setTextureSize(64, 64);
        parts.put(thorn14.boxName, thorn14);
        segment1.addChild(thorn14);

        thorn15 = new MCAModelRenderer(base, "thorn15", 0, 9);
        thorn15.mirror = false;
        thorn15.addBox(-0.5F, 0.0F, -0.5F, 1, 3, 1);
        thorn15.setInitialRotationPoint(2.5F, -0.5F, 0.5F);
        thorn15.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(-0.1874422F, 0.10821981F, -0.488148F, 0.84549713F)).transpose());
        thorn15.setTextureSize(64, 64);
        parts.put(thorn15.boxName, thorn15);
        segment1.addChild(thorn15);

        segment2 = new MCAModelRenderer(base, "segment2", 0, 8);
        segment2.mirror = false;
        segment2.addBox(-4.0F, -3.5F, -6.0F, 8, 7, 7);
        segment2.setInitialRotationPoint(0.0F, 0.0F, -4.0F);
        segment2.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(-0.18223552F, 0.0F, 0.0F, 0.9832549F)).transpose());
        segment2.setTextureSize(64, 64);
        parts.put(segment2.boxName, segment2);
        segment1.addChild(segment2);

        thorn11 = new MCAModelRenderer(base, "thorn11", 0, 9);
        thorn11.mirror = false;
        thorn11.addBox(-0.5F, 0.0F, -0.5F, 1, 3, 1);
        thorn11.setInitialRotationPoint(-2.5F, -0.5F, 0.5F);
        thorn11.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(-0.1874422F, -0.10821981F, 0.488148F, 0.84549713F)).transpose());
        thorn11.setTextureSize(64, 64);
        parts.put(thorn11.boxName, thorn11);
        segment1.addChild(thorn11);

        thorn12 = new MCAModelRenderer(base, "thorn12", 0, 9);
        thorn12.mirror = false;
        thorn12.addBox(-1.0F, 0.0F, -0.5F, 2, 4, 1);
        thorn12.setInitialRotationPoint(-2.0F, 0.5F, 0.5F);
        thorn12.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(-0.21315141F, -0.037584346F, 0.16953203F, 0.96146387F)).transpose());
        thorn12.setTextureSize(64, 64);
        parts.put(thorn12.boxName, thorn12);
        segment1.addChild(thorn12);

        thorn13 = new MCAModelRenderer(base, "thorn13", 0, 9);
        thorn13.mirror = false;
        thorn13.addBox(-0.5F, 0.0F, -0.5F, 1, 4, 1);
        thorn13.setInitialRotationPoint(-0.8F, 1.0F, 0.5F);
        thorn13.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(-0.17348291F, -0.007574427F, 0.04295671F, 0.98387045F)).transpose());
        thorn13.setTextureSize(64, 64);
        parts.put(thorn13.boxName, thorn13);
        segment1.addChild(thorn13);

        thorn16 = new MCAModelRenderer(base, "thorn16", 0, 9);
        thorn16.mirror = false;
        thorn16.addBox(-0.5F, 0.0F, -0.5F, 1, 4, 1);
        thorn16.setInitialRotationPoint(0.8F, 1.0F, 0.5F);
        thorn16.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(-0.17348291F, 0.007574427F, -0.04295671F, 0.98387045F)).transpose());
        thorn16.setTextureSize(64, 64);
        parts.put(thorn16.boxName, thorn16);
        segment1.addChild(thorn16);

        segment3 = new MCAModelRenderer(base, "segment3", 30, 8);
        segment3.mirror = false;
        segment3.addBox(-4.5F, -3.5F, -6.0F, 9, 7, 7);
        segment3.setInitialRotationPoint(0.0F, 0.0F, -5.0F);
        segment3.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.23344538F, 0.0F, 0.0F, 0.9723699F)).transpose());
        segment3.setTextureSize(64, 64);
        parts.put(segment3.boxName, segment3);
        segment2.addChild(segment3);

        thorn23 = new MCAModelRenderer(base, "thorn23", 0, 10);
        thorn23.mirror = false;
        thorn23.addBox(-0.5F, 0.0F, 0.5F, 1, 3, 1);
        thorn23.setInitialRotationPoint(2.0F, 2.0F, -1.0F);
        thorn23.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(-0.41619775F, 0.07338689F, -0.15737869F, 0.8925389F)).transpose());
        thorn23.setTextureSize(64, 64);
        parts.put(thorn23.boxName, thorn23);
        segment2.addChild(thorn23);

        thorn24 = new MCAModelRenderer(base, "thorn24", 0, 10);
        thorn24.mirror = false;
        thorn24.addBox(-0.5F, 0.0F, -0.5F, 1, 3, 1);
        thorn24.setInitialRotationPoint(3.5F, -1.0F, -1.5F);
        thorn24.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(-0.22957529F, 0.11950933F, -0.44601488F, 0.85678667F)).transpose());
        thorn24.setTextureSize(64, 64);
        parts.put(thorn24.boxName, thorn24);
        segment2.addChild(thorn24);

        thorn21 = new MCAModelRenderer(base, "thorn21", 0, 10);
        thorn21.mirror = false;
        thorn21.addBox(-0.5F, 0.0F, -0.5F, 1, 3, 1);
        thorn21.setInitialRotationPoint(-3.5F, -1.0F, -1.5F);
        thorn21.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(-0.22957529F, -0.11950933F, 0.44601488F, 0.85678667F)).transpose());
        thorn21.setTextureSize(64, 64);
        parts.put(thorn21.boxName, thorn21);
        segment2.addChild(thorn21);

        thorn22 = new MCAModelRenderer(base, "thorn22", 0, 10);
        thorn22.mirror = false;
        thorn22.addBox(-0.5F, 0.0F, 0.5F, 1, 3, 1);
        thorn22.setInitialRotationPoint(-2.0F, 2.0F, -1.0F);
        thorn22.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(-0.41619775F, -0.07338689F, 0.15737869F, 0.8925389F)).transpose());
        thorn22.setTextureSize(64, 64);
        parts.put(thorn22.boxName, thorn22);
        segment2.addChild(thorn22);

        segment4 = new MCAModelRenderer(base, "segment4", 0, 22);
        segment4.mirror = false;
        segment4.addBox(-4.0F, -3.5F, -6.0F, 8, 7, 7);
        segment4.setInitialRotationPoint(0.0F, 0.0F, -5.0F);
        segment4.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.26723838F, 0.0F, 0.0F, 0.96363044F)).transpose());
        segment4.setTextureSize(64, 64);
        parts.put(segment4.boxName, segment4);
        segment3.addChild(segment4);

        thorn32 = new MCAModelRenderer(base, "thorn32", 0, 10);
        thorn32.mirror = false;
        thorn32.addBox(-0.5F, 0.0F, -0.5F, 1, 3, 1);
        thorn32.setInitialRotationPoint(0.0F, 3.0F, -1.0F);
        thorn32.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(-0.42261827F, 0.0F, 0.0F, 0.90630776F)).transpose());
        thorn32.setTextureSize(64, 64);
        parts.put(thorn32.boxName, thorn32);
        segment3.addChild(thorn32);

        thorn33 = new MCAModelRenderer(base, "thorn33", 0, 10);
        thorn33.mirror = false;
        thorn33.addBox(-0.5F, 0.0F, -0.5F, 1, 3, 1);
        thorn33.setInitialRotationPoint(4.0F, 1.0F, -1.0F);
        thorn33.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(-0.4330127F, 0.25F, -0.4330127F, 0.75F)).transpose());
        thorn33.setTextureSize(64, 64);
        parts.put(thorn33.boxName, thorn33);
        segment3.addChild(thorn33);

        thorn31 = new MCAModelRenderer(base, "thorn31", 0, 10);
        thorn31.mirror = false;
        thorn31.addBox(-0.5F, 0.0F, -0.5F, 1, 3, 1);
        thorn31.setInitialRotationPoint(-4.0F, 1.0F, -1.0F);
        thorn31.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(-0.4330127F, -0.25F, 0.4330127F, 0.75F)).transpose());
        thorn31.setTextureSize(64, 64);
        parts.put(thorn31.boxName, thorn31);
        segment3.addChild(thorn31);

        segment5 = new MCAModelRenderer(base, "segment5", 30, 22);
        segment5.mirror = false;
        segment5.addBox(-3.0F, -3.0F, -5.0F, 6, 6, 6);
        segment5.setInitialRotationPoint(0.0F, 0.0F, -5.0F);
        segment5.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.23344538F, 0.0F, 0.0F, 0.9723699F)).transpose());
        segment5.setTextureSize(64, 64);
        parts.put(segment5.boxName, segment5);
        segment4.addChild(segment5);

        thorn42 = new MCAModelRenderer(base, "thorn42", 0, 10);
        thorn42.mirror = false;
        thorn42.addBox(-0.5F, 0.0F, -0.5F, 1, 3, 1);
        thorn42.setInitialRotationPoint(-2.5F, 3.5F, -1.0F);
        thorn42.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(-0.5180285F, 0.11129188F, 0.06819975F, 0.84534574F)).transpose());
        thorn42.setTextureSize(64, 64);
        parts.put(thorn42.boxName, thorn42);
        segment4.addChild(thorn42);

        thorn43 = new MCAModelRenderer(base, "thorn43", 0, 10);
        thorn43.mirror = false;
        thorn43.addBox(-0.5F, 0.0F, -0.5F, 1, 3, 1);
        thorn43.setInitialRotationPoint(2.5F, 3.5F, -1.0F);
        thorn43.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(-0.5180285F, -0.11129188F, -0.06819975F, 0.84534574F)).transpose());
        thorn43.setTextureSize(64, 64);
        parts.put(thorn43.boxName, thorn43);
        segment4.addChild(thorn43);

        thorn44 = new MCAModelRenderer(base, "thorn44", 0, 10);
        thorn44.mirror = false;
        thorn44.addBox(-0.5F, 0.0F, -0.5F, 1, 3, 1);
        thorn44.setInitialRotationPoint(3.5F, 0.0F, -1.0F);
        thorn44.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(-0.40957603F, 0.28678823F, -0.49673176F, 0.7094065F)).transpose());
        thorn44.setTextureSize(64, 64);
        parts.put(thorn44.boxName, thorn44);
        segment4.addChild(thorn44);

        thorn41 = new MCAModelRenderer(base, "thorn41", 0, 10);
        thorn41.mirror = false;
        thorn41.addBox(-0.5F, 0.0F, -0.5F, 1, 3, 1);
        thorn41.setInitialRotationPoint(-3.5F, 0.0F, -1.0F);
        thorn41.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(-0.40957603F, -0.28678823F, 0.49673176F, 0.7094065F)).transpose());
        thorn41.setTextureSize(64, 64);
        parts.put(thorn41.boxName, thorn41);
        segment4.addChild(thorn41);

        segment6 = new MCAModelRenderer(base, "segment6", 20, 0);
        segment6.mirror = false;
        segment6.addBox(-2.0F, -1.5F, -4.0F, 4, 3, 5);
        segment6.setInitialRotationPoint(0.0F, 0.0F, -4.0F);
        segment6.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.11320321F, 0.0F, 0.0F, 0.9935719F)).transpose());
        segment6.setTextureSize(64, 64);
        parts.put(segment6.boxName, segment6);
        segment5.addChild(segment6);

        thorn52 = new MCAModelRenderer(base, "thorn52", 0, 10);
        thorn52.mirror = false;
        thorn52.addBox(-0.5F, 0.0F, -0.5F, 1, 3, 1);
        thorn52.setInitialRotationPoint(0.0F, 2.5F, -1.0F);
        thorn52.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(-0.52249855F, 0.0F, 0.0F, 0.85264015F)).transpose());
        thorn52.setTextureSize(64, 64);
        parts.put(thorn52.boxName, thorn52);
        segment5.addChild(thorn52);

        thorn53 = new MCAModelRenderer(base, "thorn53", 0, 10);
        thorn53.mirror = false;
        thorn53.addBox(-0.5F, 0.0F, -0.5F, 1, 3, 1);
        thorn53.setInitialRotationPoint(2.5F, -0.5F, -2.0F);
        thorn53.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(-0.29513338F, 0.38462532F, -0.53243476F, 0.69388247F)).transpose());
        thorn53.setTextureSize(64, 64);
        parts.put(thorn53.boxName, thorn53);
        segment5.addChild(thorn53);

        thorn51 = new MCAModelRenderer(base, "thorn51", 0, 10);
        thorn51.mirror = false;
        thorn51.addBox(-0.5F, 0.0F, -0.5F, 1, 3, 1);
        thorn51.setInitialRotationPoint(-2.5F, -0.5F, -2.0F);
        thorn51.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(-0.29513338F, -0.38462532F, 0.53243476F, 0.69388247F)).transpose());
        thorn51.setTextureSize(64, 64);
        parts.put(thorn51.boxName, thorn51);
        segment5.addChild(thorn51);

        thorn61 = new MCAModelRenderer(base, "thorn61", 0, 10);
        thorn61.mirror = false;
        thorn61.addBox(-0.5F, 0.0F, -0.5F, 1, 3, 1);
        thorn61.setInitialRotationPoint(-1.5F, 0.0F, -1.5F);
        thorn61.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(-0.38302222F, -0.3213938F, 0.55667037F, 0.66341394F)).transpose());
        thorn61.setTextureSize(64, 64);
        parts.put(thorn61.boxName, thorn61);
        segment6.addChild(thorn61);

        thorn62 = new MCAModelRenderer(base, "thorn62", 0, 10);
        thorn62.mirror = false;
        thorn62.addBox(-0.5F, 0.0F, -0.5F, 1, 3, 1);
        thorn62.setInitialRotationPoint(1.5F, 0.0F, -1.5F);
        thorn62.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(-0.38302222F, 0.3213938F, -0.55667037F, 0.66341394F)).transpose());
        thorn62.setTextureSize(64, 64);
        parts.put(thorn62.boxName, thorn62);
        segment6.addChild(thorn62);

        topBulb = new MCAModelRenderer(base, "topBulb", 39, 0);
        topBulb.mirror = false;
        topBulb.addBox(-1.0F, -1.0F, -2.5F, 2, 2, 3);
        topBulb.setInitialRotationPoint(0.0F, 0.0F, -4.0F);
        topBulb.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.1478094F, 0.0F, 0.0F, 0.9890159F)).transpose());
        topBulb.setTextureSize(64, 64);
        parts.put(topBulb.boxName, topBulb);
        segment6.addChild(topBulb);

    }

    public static String getAnimation(EnumAnimation animation) {
        if (animation == EnumAnimation.CRAWLING) return ChannelCtenoLowered.ANIM_NAME;
        else if (animation == EnumAnimation.SITTING) return ChannelCtenoSit.ANIM_NAME;
        else return ChannelCtenoIdle.ANIM_NAME;
    }
}