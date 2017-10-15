package noppes.mpm.client.model.extraparts.ctenotail;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import noppes.mpm.client.model.MCALibrary.MCAModelRenderer;
import noppes.mpm.client.model.MCALibrary.animation.AnimationHandler;
import noppes.mpm.client.model.MCALibrary.math.Matrix4f;
import noppes.mpm.client.model.MCALibrary.math.Quaternion;
import noppes.mpm.client.model.ModelMPM;
import noppes.mpm.client.model.ModelScaleRenderer;

import java.util.HashMap;

public class ModelCtenoTail extends ModelScaleRenderer {
    public HashMap<String, MCAModelRenderer> parts = new HashMap<>();

    MCAModelRenderer segment1;
    MCAModelRenderer segment2;
    MCAModelRenderer segment3;
    MCAModelRenderer segment4;
    MCAModelRenderer segment5;
    MCAModelRenderer segment6;
    MCAModelRenderer thorn11;
    MCAModelRenderer thorn12;
    MCAModelRenderer thorn13;
    MCAModelRenderer thorn14;
    MCAModelRenderer thorn15;
    MCAModelRenderer thorn21;
    MCAModelRenderer thorn22;
    MCAModelRenderer thorn23;
    MCAModelRenderer thorn24;
    MCAModelRenderer thorn31;
    MCAModelRenderer thorn32;
    MCAModelRenderer thorn33;
    MCAModelRenderer thorn41;
    MCAModelRenderer thorn42;
    MCAModelRenderer thorn43;
    MCAModelRenderer thorn44;
    MCAModelRenderer thorn51;
    MCAModelRenderer thorn52;
    MCAModelRenderer thorn53;
    MCAModelRenderer thorn61;
    MCAModelRenderer thorn62;
    MCAModelRenderer topBulb;

    public ModelCtenoTail(ModelMPM base) {
        super(base);
        textureWidth = 64;
        textureHeight = 64;

        segment1 = new MCAModelRenderer(base, "segment1", 0, 32);
        segment1.mirror = false;
        segment1.addBox(-2.5F, -1.0F, -2.0F, 5, 4, 3);
        segment1.setInitialRotationPoint(0.0F, -3.0F, -2.0F);
        segment1.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(-0.0784591F, 0.0F, 0.0F, 0.9969173F)).transpose());
        segment1.setTextureSize(64, 64);
        parts.put(segment1.boxName, segment1);

        segment2 = new MCAModelRenderer(base, "segment2", 0, 39);
        segment2.mirror = false;
        segment2.addBox(-4.0F, -3.5F, -7.0F, 8, 6, 7);
        segment2.setInitialRotationPoint(0.0F, 1.5F, -2.0F);
        segment2.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.008726535F, 0.0F, 0.0F, 0.9999619F)).transpose());
        segment2.setTextureSize(64, 64);
        parts.put(segment2.boxName, segment2);
        segment1.addChild(segment2);

        segment3 = new MCAModelRenderer(base, "segment3", 0, 39);
        segment3.mirror = false;
        segment3.addBox(-4.0F, -4.0F, -6.0F, 8, 7, 7);
        segment3.setInitialRotationPoint(0.0F, 0.0F, -6.0F);
        segment3.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.309017F, 0.0F, 0.0F, 0.95105654F)).transpose());
        segment3.setTextureSize(64, 64);
        parts.put(segment3.boxName, segment3);
        segment2.addChild(segment3);

        segment4 = new MCAModelRenderer(base, "segment4", 0, 39);
        segment4.mirror = false;
        segment4.addBox(-3.5F, -3.0F, -5.0F, 7, 7, 7);
        segment4.setInitialRotationPoint(0.0F, 0.0F, -6.0F);
        segment4.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(0.309017F, 0.0F, 0.0F, 0.95105654F)).transpose());
        segment4.setTextureSize(64, 64);
        parts.put(segment4.boxName, segment4);
        segment3.addChild(segment4);

        segment5 = new MCAModelRenderer(base, "segment5", 0, 39);
        segment5.mirror = false;
        segment5.addBox(-2.5F, -3.0F, -5.0F, 5, 6, 6);
        segment5.setInitialRotationPoint(0.0F, 1.0F, -5.0F);
        segment5.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(-0.06104854F, 0.0F, 0.0F, 0.9981348F)).transpose());
        segment5.setTextureSize(64, 64);
        parts.put(segment5.boxName, segment5);
        segment4.addChild(segment5);

        thorn13 = new MCAModelRenderer(base, "thorn13", 16, 32);
        thorn13.mirror = false;
        thorn13.addBox(-0.5F, 0.0F, -0.5F, 1, 3, 1);
        thorn13.setInitialRotationPoint(0.0F, 3.0F, 0.5F);
        thorn13.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(-0.25881904F, 0.0F, 0.0F, 0.9659258F)).transpose());
        thorn13.setTextureSize(64, 64);
        parts.put(thorn13.boxName, thorn13);
        segment1.addChild(thorn13);

        thorn14 = new MCAModelRenderer(base, "thorn14", 16, 32);
        thorn14.mirror = false;
        thorn14.addBox(-0.5F, 0.0F, -0.5F, 1, 3, 1);
        thorn14.setInitialRotationPoint(2.0F, 2.5F, 0.5F);
        thorn14.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(-0.25488698F, 0.044943456F, -0.16773126F, 0.9512512F)).transpose());
        thorn14.setTextureSize(64, 64);
        parts.put(thorn14.boxName, thorn14);
        segment1.addChild(thorn14);

        thorn12 = new MCAModelRenderer(base, "thorn12", 16, 32);
        thorn12.mirror = false;
        thorn12.addBox(-0.5F, 0.0F, -0.5F, 1, 3, 1);
        thorn12.setInitialRotationPoint(-2.0F, 2.5F, 0.5F);
        thorn12.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(-0.25488698F, -0.044943456F, 0.16773126F, 0.9512512F)).transpose());
        thorn12.setTextureSize(64, 64);
        parts.put(thorn12.boxName, thorn12);
        segment1.addChild(thorn12);

        thorn15 = new MCAModelRenderer(base, "thorn15", 16, 32);
        thorn15.mirror = false;
        thorn15.addBox(-0.5F, 0.0F, -0.5F, 1, 2, 1);
        thorn15.setInitialRotationPoint(2.5F, 1.5F, 0.5F);
        thorn15.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(-0.17729697F, 0.124144666F, -0.5599804F, 0.7997349F)).transpose());
        thorn15.setTextureSize(64, 64);
        parts.put(thorn15.boxName, thorn15);
        segment1.addChild(thorn15);

        thorn11 = new MCAModelRenderer(base, "thorn11", 16, 32);
        thorn11.mirror = false;
        thorn11.addBox(-0.5F, 0.0F, -0.5F, 1, 2, 1);
        thorn11.setInitialRotationPoint(-2.5F, 1.5F, 0.5F);
        thorn11.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(-0.17729697F, -0.124144666F, 0.5599804F, 0.7997349F)).transpose());
        thorn11.setTextureSize(64, 64);
        parts.put(thorn11.boxName, thorn11);
        segment1.addChild(thorn11);

        thorn23 = new MCAModelRenderer(base, "thorn23", 16, 32);
        thorn23.mirror = false;
        thorn23.addBox(-0.5F, 0.0F, -0.5F, 1, 3, 1);
        thorn23.setInitialRotationPoint(2.0F, 2.0F, -2.0F);
        thorn23.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(-0.41619775F, 0.07338689F, -0.15737869F, 0.8925389F)).transpose());
        thorn23.setTextureSize(64, 64);
        parts.put(thorn23.boxName, thorn23);
        segment2.addChild(thorn23);

        thorn22 = new MCAModelRenderer(base, "thorn22", 16, 32);
        thorn22.mirror = false;
        thorn22.addBox(-0.5F, 0.0F, -0.5F, 1, 3, 1);
        thorn22.setInitialRotationPoint(-2.0F, 2.0F, -2.0F);
        thorn22.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(-0.41619775F, -0.07338689F, 0.15737869F, 0.8925389F)).transpose());
        thorn22.setTextureSize(64, 64);
        parts.put(thorn22.boxName, thorn22);
        segment2.addChild(thorn22);

        thorn24 = new MCAModelRenderer(base, "thorn24", 16, 32);
        thorn24.mirror = false;
        thorn24.addBox(-0.5F, 0.0F, -0.5F, 1, 3, 1);
        thorn24.setInitialRotationPoint(3.0F, -1.0F, -2.5F);
        thorn24.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(-0.22957529F, 0.11950933F, -0.44601488F, 0.85678667F)).transpose());
        thorn24.setTextureSize(64, 64);
        parts.put(thorn24.boxName, thorn24);
        segment2.addChild(thorn24);

        thorn21 = new MCAModelRenderer(base, "thorn21", 16, 32);
        thorn21.mirror = false;
        thorn21.addBox(-0.5F, 0.0F, -0.5F, 1, 3, 1);
        thorn21.setInitialRotationPoint(-3.0F, -1.0F, -2.5F);
        thorn21.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(-0.22957529F, -0.11950933F, 0.44601488F, 0.85678667F)).transpose());
        thorn21.setTextureSize(64, 64);
        parts.put(thorn21.boxName, thorn21);
        segment2.addChild(thorn21);

        thorn32 = new MCAModelRenderer(base, "thorn32", 16, 32);
        thorn32.mirror = false;
        thorn32.addBox(-0.5F, 0.0F, -0.5F, 1, 3, 1);
        thorn32.setInitialRotationPoint(-0.5F, 2.0F, -1.0F);
        thorn32.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(-0.42261827F, 0.0F, 0.0F, 0.90630776F)).transpose());
        thorn32.setTextureSize(64, 64);
        parts.put(thorn32.boxName, thorn32);
        segment3.addChild(thorn32);

        thorn33 = new MCAModelRenderer(base, "thorn33", 16, 32);
        thorn33.mirror = false;
        thorn33.addBox(-0.5F, 0.0F, -0.5F, 1, 3, 1);
        thorn33.setInitialRotationPoint(3.5F, 0.0F, -1.0F);
        thorn33.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(-0.4330127F, 0.25F, -0.4330127F, 0.75F)).transpose());
        thorn33.setTextureSize(64, 64);
        parts.put(thorn33.boxName, thorn33);
        segment3.addChild(thorn33);

        thorn31 = new MCAModelRenderer(base, "thorn31", 16, 32);
        thorn31.mirror = false;
        thorn31.addBox(-0.5F, 0.0F, -0.5F, 1, 3, 1);
        thorn31.setInitialRotationPoint(-3.5F, 0.0F, -1.0F);
        thorn31.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(-0.4330127F, -0.25F, 0.4330127F, 0.75F)).transpose());
        thorn31.setTextureSize(64, 64);
        parts.put(thorn31.boxName, thorn31);
        segment3.addChild(thorn31);

        thorn42 = new MCAModelRenderer(base, "thorn42", 16, 32);
        thorn42.mirror = false;
        thorn42.addBox(-0.5F, 0.0F, -0.5F, 1, 3, 1);
        thorn42.setInitialRotationPoint(-2.5F, 3.5F, -1.0F);
        thorn42.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(-0.5180285F, 0.11129188F, 0.06819975F, 0.84534574F)).transpose());
        thorn42.setTextureSize(64, 64);
        parts.put(thorn42.boxName, thorn42);
        segment4.addChild(thorn42);

        thorn43 = new MCAModelRenderer(base, "thorn43", 16, 32);
        thorn43.mirror = false;
        thorn43.addBox(-0.5F, 0.0F, -0.5F, 1, 3, 1);
        thorn43.setInitialRotationPoint(2.5F, 3.5F, -1.0F);
        thorn43.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(-0.5180285F, -0.11129188F, -0.06819975F, 0.84534574F)).transpose());
        thorn43.setTextureSize(64, 64);
        parts.put(thorn43.boxName, thorn43);
        segment4.addChild(thorn43);

        thorn44 = new MCAModelRenderer(base, "thorn44", 16, 32);
        thorn44.mirror = false;
        thorn44.addBox(-0.5F, 0.0F, -0.5F, 1, 3, 1);
        thorn44.setInitialRotationPoint(2.5F, 0.0F, 0.0F);
        thorn44.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(-0.3822041F, 0.27262354F, -0.51273024F, 0.71882135F)).transpose());
        thorn44.setTextureSize(64, 64);
        parts.put(thorn44.boxName, thorn44);
        segment4.addChild(thorn44);

        thorn41 = new MCAModelRenderer(base, "thorn41", 16, 32);
        thorn41.mirror = false;
        thorn41.addBox(-0.5F, 0.0F, -0.5F, 1, 3, 1);
        thorn41.setInitialRotationPoint(-2.5F, 0.0F, 0.0F);
        thorn41.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(-0.3822041F, -0.27262354F, 0.51273024F, 0.71882135F)).transpose());
        thorn41.setTextureSize(64, 64);
        parts.put(thorn41.boxName, thorn41);
        segment4.addChild(thorn41);

        segment6 = new MCAModelRenderer(base, "segment6", 0, 39);
        segment6.mirror = false;
        segment6.addBox(-2.0F, -2.0F, -4.0F, 4, 3, 5);
        segment6.setInitialRotationPoint(0.0F, 1.0F, -5.0F);
        segment6.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(-0.35836795F, 0.0F, 0.0F, 0.9335804F)).transpose());
        segment6.setTextureSize(64, 64);
        parts.put(segment6.boxName, segment6);
        segment5.addChild(segment6);

        thorn52 = new MCAModelRenderer(base, "thorn52", 16, 32);
        thorn52.mirror = false;
        thorn52.addBox(-0.5F, 0.0F, -0.5F, 1, 3, 1);
        thorn52.setInitialRotationPoint(0.0F, 2.5F, -1.5F);
        thorn52.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(-0.52249855F, 0.0F, 0.0F, 0.85264015F)).transpose());
        thorn52.setTextureSize(64, 64);
        parts.put(thorn52.boxName, thorn52);
        segment5.addChild(thorn52);

        thorn53 = new MCAModelRenderer(base, "thorn53", 16, 32);
        thorn53.mirror = false;
        thorn53.addBox(-0.5F, 0.0F, -0.5F, 1, 3, 1);
        thorn53.setInitialRotationPoint(2.0F, -0.5F, -1.5F);
        thorn53.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(-0.29513338F, 0.38462532F, -0.53243476F, 0.69388247F)).transpose());
        thorn53.setTextureSize(64, 64);
        parts.put(thorn53.boxName, thorn53);
        segment5.addChild(thorn53);

        thorn51 = new MCAModelRenderer(base, "thorn51", 16, 32);
        thorn51.mirror = false;
        thorn51.addBox(-0.5F, 0.0F, -0.5F, 1, 3, 1);
        thorn51.setInitialRotationPoint(-2.0F, -0.5F, -1.5F);
        thorn51.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(-0.29513338F, -0.38462532F, 0.53243476F, 0.69388247F)).transpose());
        thorn51.setTextureSize(64, 64);
        parts.put(thorn51.boxName, thorn51);
        segment5.addChild(thorn51);

        thorn61 = new MCAModelRenderer(base, "thorn61", 16, 32);
        thorn61.mirror = false;
        thorn61.addBox(-0.5F, 0.0F, -0.5F, 1, 3, 1);
        thorn61.setInitialRotationPoint(-1.0F, 0.0F, -0.5F);
        thorn61.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(-0.4109413F, -0.09865828F, 0.21157336F, 0.8812664F)).transpose());
        thorn61.setTextureSize(64, 64);
        parts.put(thorn61.boxName, thorn61);
        segment6.addChild(thorn61);

        thorn62 = new MCAModelRenderer(base, "thorn62", 16, 32);
        thorn62.mirror = false;
        thorn62.addBox(-0.5F, 0.0F, -0.5F, 1, 3, 1);
        thorn62.setInitialRotationPoint(1.0F, 0.0F, -0.5F);
        thorn62.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(-0.4109413F, 0.09865828F, -0.21157336F, 0.8812664F)).transpose());
        thorn62.setTextureSize(64, 64);
        parts.put(thorn62.boxName, thorn62);
        segment6.addChild(thorn62);

        topBulb = new MCAModelRenderer(base, "topBulb", 16, 32);
        topBulb.mirror = false;
        topBulb.addBox(-1.0F, -1.0F, -1.5F, 2, 2, 2);
        topBulb.setInitialRotationPoint(0.0F, -0.5F, -4.0F);
        topBulb.setInitialRotationMatrix(new Matrix4f().set(new Quaternion(-0.09584575F, 0.0F, 0.0F, 0.9953962F)).transpose());
        topBulb.setTextureSize(64, 64);
        parts.put(topBulb.boxName, topBulb);
        segment6.addChild(topBulb);

    }

    public void animate(float par1, float par2, float par3, float par4, float par5, float par6, Entity par7Entity) {

    }
}