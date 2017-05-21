package net.minecraft.client.renderer.entity;

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import noppes.mpm.client.model.ModelMPM;

public class MPMRendererHelper
{
  public static net.minecraft.client.model.ModelBase getMainModel(RendererLivingEntity renderer)
  {
    return renderer.mainModel;
  }
  
  public static void setMainModel(RenderPlayer renderer, ModelMPM modelBipedMain) {
    renderer.mainModel = modelBipedMain;
  }
  
  public static String getTexture(RendererLivingEntity render, Entity entity) {
    ResourceLocation location = render.getEntityTexture(entity);
    return location.toString();
  }
  
  public static ResourceLocation getResource(AbstractClientPlayer player, RendererLivingEntity render, Entity entity) {
    if (render != null) {
      try {
        return render.getEntityTexture(entity);
      }
      catch (Exception ex) {}
    }
    

    return player.getLocationSkin();
  }
  
  public static int shouldRenderPass(EntityLivingBase entity, int par2, float par3, RendererLivingEntity renderEntity) {
    return renderEntity.shouldRenderPass(entity, par2, par3);
  }
  
  public static void preRenderCallback(EntityLivingBase entity, float f, RendererLivingEntity renderEntity)
  {
    renderEntity.preRenderCallback(entity, f);
  }
  
  public static net.minecraft.client.model.ModelBase getPassModel(RendererLivingEntity render) {
    return render.renderPassModel;
  }
  
  public static float handleRotationFloat(EntityLivingBase entity, float par2, RendererLivingEntity renderEntity)
  {
    return renderEntity.handleRotationFloat(entity, par2);
  }
  
  public static void renderEquippedItems(EntityLivingBase entity, float f, RendererLivingEntity renderEntity)
  {
    renderEntity.renderEquippedItems(entity, f);
  }
}
