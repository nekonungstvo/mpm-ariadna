package noppes.mpm.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.Tessellator;
import noppes.mpm.ModelPartConfig;
import org.lwjgl.opengl.GL11;

public class ModelScaleRenderer extends ModelRenderer {
    public boolean compiled;
    public int displayList;
    public float x;
    public float y;
    public float z;
    protected ModelPartConfig config;

    public ModelScaleRenderer(ModelBase par1ModelBase) {
        super(par1ModelBase);
    }

    public ModelScaleRenderer(ModelBase par1ModelBase, int xTextureOffset, int yTextureOffset) {
        this(par1ModelBase);
        setTextureOffset(xTextureOffset, yTextureOffset);
    }

    public ModelScaleRenderer(ModelBase modelBase, String boxName, int xTextureOffset, int yTextureOffset) {
        super(modelBase, boxName);
        setTextureOffset(xTextureOffset, yTextureOffset);
    }

    public void setConfig(ModelPartConfig config, float x, float y, float z) {
        this.config = config;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = x;
        model.rotateAngleY = y;
        model.rotateAngleZ = z;
    }

    public void renderChilderen(float par1) {
        if ((!this.showModel) || (this.isHidden))
            return;
        if (!this.compiled)
            compileDisplayList(par1);
        GL11.glPushMatrix();
        GL11.glTranslatef(this.x, this.y, this.z);
        if (this.config != null)
            GL11.glTranslatef(this.config.transX, this.config.transY, this.config.transZ);
        postRender(par1);
        if (this.config != null)
            GL11.glScalef(this.config.scaleX, this.config.scaleY, this.config.scaleZ);
        GL11.glCallList(this.displayList);
        if (this.childModels != null) {
            for (int i = 0; i < this.childModels.size(); i++) {
                ((ModelRenderer) this.childModels.get(i)).render(par1);
            }
        }
        GL11.glPopMatrix();
    }

    public void renderChild(float par1, ModelRenderer model) {
        if ((!this.showModel) || (this.isHidden))
            return;
        GL11.glPushMatrix();
        GL11.glTranslatef(this.x, this.y, this.z);
        if (this.config != null)
            GL11.glTranslatef(this.config.transX, this.config.transY, this.config.transZ);
        postRender(par1);
        if (this.config != null)
            GL11.glScalef(this.config.scaleX, this.config.scaleY, this.config.scaleZ);
        model.render(par1);
        GL11.glPopMatrix();
    }


    public void render(float par1) {
        if ((!this.showModel) || (this.isHidden))
            return;
        if (!this.compiled)
            compileDisplayList(par1);
        GL11.glPushMatrix();
        GL11.glTranslatef(this.x, this.y, this.z);
        if (this.config != null)
            GL11.glTranslatef(this.config.transX, this.config.transY, this.config.transZ);
        postRender(par1);
        if (this.config != null)
            GL11.glScalef(this.config.scaleX, this.config.scaleY, this.config.scaleZ);
        GL11.glCallList(this.displayList);
        if (this.childModels != null) {
            for (int i = 0; i < this.childModels.size(); i++) {
                ((ModelRenderer) this.childModels.get(i)).render(par1);
            }
        }
        GL11.glPopMatrix();
    }

    public void parentRender(float par1) {
        super.render(par1);
    }

    public void compileDisplayList(float par1) {
        this.displayList = GLAllocation.generateDisplayLists(1);
        GL11.glNewList(this.displayList, 4864);
        Tessellator tessellator = Tessellator.instance;

        for (int i = 0; i < this.cubeList.size(); i++) {
            ((ModelBox) this.cubeList.get(i)).render(tessellator, par1);
        }

        GL11.glEndList();
        this.compiled = true;
    }
}
