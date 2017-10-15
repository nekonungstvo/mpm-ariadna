package noppes.mpm.client.model.MCALibrary;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.Tessellator;
import noppes.mpm.client.model.MCALibrary.math.Matrix4f;
import noppes.mpm.client.model.MCALibrary.math.Quaternion;
import noppes.mpm.client.model.MCALibrary.math.Vector3f;
import noppes.mpm.client.model.ModelScaleRenderer;
import org.lwjgl.opengl.GL11;

import java.nio.FloatBuffer;

public class MCAModelRenderer extends ModelScaleRenderer {
    /**
     * Custom version, as parent variable is PRIVATE
     */
    private int DDStextureOffsetX;
    private int DDStextureOffsetY;
    private boolean DDScompiled;
    private int DDSdisplayList;

    private Matrix4f rotationMatrix = new Matrix4f();
    /**
     * Previous value of the matrix
     */
    private Matrix4f prevRotationMatrix = new Matrix4f();

    private float defaultRotationPointX;
    private float defaultRotationPointY;
    private float defaultRotationPointZ;

    private Matrix4f defaultRotationMatrix = new Matrix4f();
    private Quaternion defaultRotationAsQuaternion;

    public MCAModelRenderer(ModelBase par1ModelBase, String par2Str, int xTextureOffset, int yTextureOffset) {
        super(par1ModelBase, par2Str, xTextureOffset, yTextureOffset);
//        this.setTextureSize(par1ModelBase.textureWidth, par1ModelBase.textureHeight);
    }

    @Override
    public ModelRenderer setTextureOffset(int par1, int par2) {
        this.DDStextureOffsetX = par1;
        this.DDStextureOffsetY = par2;
        return this;
    }

    @Override
    public ModelRenderer addBox(String par1Str, float par2, float par3, float par4, int par5, int par6, int par7) {
        par1Str = this.boxName + "." + par1Str;
        this.cubeList.add((new MCAModelBox(this, this.DDStextureOffsetX, this.DDStextureOffsetY, par2, par3, par4, par5, par6, par7, 0.0F)).func_78244_a(par1Str));
        return this;
    }

    @Override
    public ModelRenderer addBox(float offX, float offY, float offZ, int sizeX, int sizeY, int sizeZ) {
        this.cubeList.add(new MCAModelBox(this, this.DDStextureOffsetX, this.DDStextureOffsetY, offX, offY, offZ, sizeX, sizeY, sizeZ, 0.0F));
        return this;
    }

    @Override
    public void addBox(float par1, float par2, float par3, int par4, int par5, int par6, float par7) {
        this.cubeList.add(new MCAModelBox(this, this.DDStextureOffsetX, this.DDStextureOffsetY, par1, par2, par3, par4, par5, par6, par7));
    }

    @Override
    public void render(float par1) {
        if (!this.isHidden) {
            if (this.showModel) {
                if (!this.DDScompiled) {
                    this.DDScompileDisplayList(par1);
                }

                //GL11.glPushMatrix();
                GL11.glTranslatef(this.offsetX, this.offsetY, this.offsetZ);
                int i;

                if (this.rotationMatrix.isEmptyRotationMatrix()) {
                    if (this.rotationPointX == 0.0F && this.rotationPointY == 0.0F && this.rotationPointZ == 0.0F) {
                        GL11.glCallList(this.DDSdisplayList);

                        if (this.childModels != null) {
                            for (i = 0; i < this.childModels.size(); ++i) {
                                ((ModelRenderer) this.childModels.get(i)).render(par1);
                            }
                        }
                    } else {
                        //GL11.glPushMatrix();
                        GL11.glTranslatef(this.rotationPointX * par1, this.rotationPointY * par1, this.rotationPointZ * par1);
                        GL11.glCallList(this.DDSdisplayList);

                        if (this.childModels != null) {
                            for (i = 0; i < this.childModels.size(); ++i) {
                                ((ModelRenderer) this.childModels.get(i)).render(par1);
                            }
                        }

                        GL11.glTranslatef(-this.rotationPointX * par1, -this.rotationPointY * par1, -this.rotationPointZ * par1);
                        //GL11.glPopMatrix();
                    }
                } else {
                    GL11.glPushMatrix();
                    GL11.glTranslatef(this.rotationPointX * par1, this.rotationPointY * par1, this.rotationPointZ * par1);
                    FloatBuffer buf = Utils.makeFloatBuffer(this.rotationMatrix.intoArray());
                    GL11.glMultMatrix(buf);

                    GL11.glCallList(this.DDSdisplayList);

                    if (this.childModels != null) {
                        for (i = 0; i < this.childModels.size(); ++i) {
                            ((ModelRenderer) this.childModels.get(i)).render(par1);
                        }
                    }

                    GL11.glPopMatrix();
                }

                GL11.glTranslatef(-this.offsetX, -this.offsetY, -this.offsetZ);
                //GL11.glPopMatrix();

                this.prevRotationMatrix = this.rotationMatrix;
            }
        }
    }

    @Override
    public void renderWithRotation(float par1) {
        //NOTHING AS WE MUSTN'T USE GL ROTATIONS METHODS
    }

    @Override
    public void postRender(float par1) {
        if (!this.isHidden) {
            if (this.showModel) {
                if (!this.DDScompiled) {
                    this.DDScompileDisplayList(par1);
                }

                if (this.rotationMatrix.equals(prevRotationMatrix)) {
                    if (this.rotationPointX != 0.0F || this.rotationPointY != 0.0F || this.rotationPointZ != 0.0F) {
                        GL11.glTranslatef(this.rotationPointX * par1, this.rotationPointY * par1, this.rotationPointZ * par1);
                    }
                } else {
                    GL11.glTranslatef(this.rotationPointX * par1, this.rotationPointY * par1, this.rotationPointZ * par1);

                    GL11.glMultMatrix(FloatBuffer.wrap(this.rotationMatrix.intoArray()));
                }
            }
        }
    }

    /**
     * Set default rotation point (model with no animations) and set the current rotation point.
     */
    public void setInitialRotationPoint(float par1, float par2, float par3) {
        this.defaultRotationPointX = par1;
        this.defaultRotationPointY = par2;
        this.defaultRotationPointZ = par3;
        this.setRotationPoint(par1, par2, par3);
    }

    /**
     * Set the rotation point
     */
    public void setRotationPoint(float par1, float par2, float par3) {
        this.rotationPointX = par1;
        this.rotationPointY = par2;
        this.rotationPointZ = par3;
    }

    /**
     * Reset the rotation point to the default values.
     */
    public void resetRotationPoint() {
        this.rotationPointX = this.defaultRotationPointX;
        this.rotationPointY = this.defaultRotationPointY;
        this.rotationPointZ = this.defaultRotationPointZ;
    }

    public Vector3f getPositionAsVector() {
        return new Vector3f(this.rotationPointX, this.rotationPointY, this.rotationPointZ);
    }

    /**
     * Set rotation matrix setting also an initial default value (model with no animations).
     */
    public void setInitialRotationMatrix(Matrix4f matrix) {
        defaultRotationMatrix = matrix;
        setRotationMatrix(matrix);
        this.defaultRotationAsQuaternion = Utils.getQuaternionFromMatrix(rotationMatrix);
    }

    /**
     * Set the rotation matrix values based on the given matrix.
     */
    public void setRotationMatrix(Matrix4f matrix) {
        rotationMatrix.set(matrix);
    }

    /**
     * Reset the rotation matrix to the default one.
     */
    public void resetRotationMatrix() {
        setRotationMatrix(this.defaultRotationMatrix);
    }

    public Matrix4f getRotationMatrix() {
        return this.rotationMatrix;
    }

    public Quaternion getDefaultRotationAsQuaternion() {
        return defaultRotationAsQuaternion;
    }

    /**
     * Compiles a GL display list for this model.
     * EDITED VERSION BECAUSE OF THE PRIVATE FIELDS
     */
    public void DDScompileDisplayList(float par1) {
        this.DDSdisplayList = GLAllocation.generateDisplayLists(1);
        GL11.glNewList(this.DDSdisplayList, GL11.GL_COMPILE);
        Tessellator tessellator = Tessellator.instance;

        for (int i = 0; i < this.cubeList.size(); ++i) {
            ((ModelBox) this.cubeList.get(i)).render(tessellator, par1);
        }

        GL11.glEndList();
        this.DDScompiled = true;
    }
}