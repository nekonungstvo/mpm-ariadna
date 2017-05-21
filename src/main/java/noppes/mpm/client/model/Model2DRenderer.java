package noppes.mpm.client.model;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import org.lwjgl.opengl.GL11;










public class Model2DRenderer
  extends ModelRenderer
{
  private float thickness = 1.0F; private float scaleY = 1.0F; private float scaleX = 1.0F;
  private float rotationOffsetY;
  private float rotationOffsetX;
  public Model2DRenderer(ModelBase par1ModelBase, float x, float y, int width, int height, float textureWidth, float textureHeight) { super(par1ModelBase);
    this.width = width;
    this.height = height;
    this.textureWidth = textureWidth;
    this.textureHeight = textureHeight;
    
    this.x1 = (x / textureWidth);
    this.y1 = (y / textureHeight);
    
    this.x2 = ((x + width) / textureWidth);
    this.y2 = ((y + height) / textureHeight); }
  
  private int height;
  private int width;
  public Model2DRenderer(ModelBase base, int x, int y, int width, int height) { this(base, x, y, width, height, width, height); }
  
  private float y2;
  private float y1;
  public void render(float par1) { if ((!this.showModel) || (this.isHidden))
      return;
    if (!this.compiled) {
      compileDisplayList(par1);
    }
    GL11.glPushMatrix();
    postRender(par1);
    
    GL11.glCallList(this.displayList);
    GL11.glPopMatrix(); }
  
  private float x2;
  private float x1;
  public void setRotationOffset(float x, float y) { this.rotationOffsetX = x;
    this.rotationOffsetY = y; }
  
  private int displayList;
  
  public void setScale(float scale) { this.scaleX = scale;
    this.scaleY = scale;
  }
  
  public void setScale(float x, float y) { this.scaleX = x;
    this.scaleY = y;
  }
  
  private boolean compiled;
  public void setThickness(float thickness) { this.thickness = thickness; }
  

  @SideOnly(Side.CLIENT)
  private void compileDisplayList(float par1) {
    this.displayList = GLAllocation.generateDisplayLists(1);
    GL11.glNewList(this.displayList, 4864);
    
    GL11.glScalef(this.scaleX * this.width / this.height, this.scaleY, this.thickness);
    GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
    if (this.mirror) {
      GL11.glTranslatef(0.0F, 0.0F, -1.0F * par1);
      GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
    }
    
    GL11.glTranslated(this.rotationOffsetX * par1, this.rotationOffsetY * par1, 0.0D);
    ItemRenderer.renderItemIn2D(Tessellator.instance, this.x1, this.y1, this.x2, this.y2, this.width, this.height, par1);
    
    GL11.glEndList();
    this.compiled = true;
  }
}
