package noppes.mpm.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.MPMRendererHelper;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

public class ModelRenderPassHelper
        extends ModelBase {
    public RendererLivingEntity renderer;
    public EntityLivingBase entity;

    public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
        ModelBase model = MPMRendererHelper.getPassModel(this.renderer);
        model.isChild = this.isChild;
        model.render(this.entity, par2, par3, par4, par5, par6, par7);
    }

    public void setLivingAnimations(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4) {
        ModelBase model = MPMRendererHelper.getPassModel(this.renderer);
        model.isChild = this.isChild;
        model.setLivingAnimations(this.entity, par2, par3, par4);
    }
}
