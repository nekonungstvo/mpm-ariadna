package noppes.mpm.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import noppes.mpm.ModelData;
import noppes.mpm.ModelPartConfig;
import noppes.mpm.ModelPartData;
import noppes.mpm.client.ClientProxy;
import noppes.mpm.client.model.animation.AniCrawling;
import noppes.mpm.client.model.animation.AniHug;
import noppes.mpm.client.model.part.*;
import noppes.mpm.constants.EnumAnimation;
import org.lwjgl.opengl.GL11;

import java.util.Random;

public class ModelMPM extends ModelBiped {
    public ModelData data;
    private ModelPartInterface wings;
    private ModelPartInterface mohawk;
    private ModelPartInterface hair;
    private ModelPartInterface beard;
    private ModelPartInterface breasts;
    private ModelPartInterface snout;
    private ModelPartInterface ears;
    private ModelPartInterface fin;
    private ModelPartInterface skirt;
    private ModelPartInterface horns;
    private ModelPartInterface clawsR;
    private ModelPartInterface clawsL;
    private ModelLegs legs;
    private ModelScaleRenderer headwear;
    private ModelTail tail;
    public ModelBase entityModel;
    public EntityLivingBase entity;
    public boolean currentlyPlayerTexture;
    public boolean isArmor;

    public ModelScaleRenderer bipedLeftArmwear;
    public ModelScaleRenderer bipedRightArmwear;
    public ModelScaleRenderer bipedLeftLegwear;
    public ModelScaleRenderer bipedRightLegwear;
    public ModelScaleRenderer bipedBodyWear;

    public ModelMPM(float z) {
        super(z, 0, 64, 64);
        this.isArmor = (z > 0.0F);

        bipedCloak = new ModelRenderer(this, 0, 0);
        bipedCloak.setTextureSize(64, 32);
        bipedCloak.addBox(-5.0F, 0.0F, -1.0F, 10, 16, 1, z);

        bipedEars = new ModelRenderer(this, 24, 0);
        bipedEars.addBox(-3.0F, -6.0F, -1.0F, 6, 6, 1, z);

        bipedHead = new ModelScaleRenderer(this, 0, 0);
        bipedHead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, z);
        bipedHead.setRotationPoint(0.0F, 0.0F, 0.0F);

        bipedHeadwear = new ModelScaleRenderer(this, 32, 0);
        bipedHeadwear.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, z + 0.5F);
        bipedHeadwear.setRotationPoint(0.0F, 0.0F, 0.0F);

        bipedBody = new ModelScaleRenderer(this, 16, 16);
        bipedBody.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, z);
        bipedBody.setRotationPoint(0.0F, 0.0F, 0.0F);

        bipedBodyWear = new ModelScaleRenderer(this, 16, 32);
        bipedBodyWear.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, z + 0.25F);
        bipedBody.addChild(bipedBodyWear);

        bipedRightArm = new ModelScaleRenderer(this, 40, 16);
        bipedRightArm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, z);
        bipedRightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);

        bipedRightArmwear = new ModelScaleRenderer(this, 40, 32);
        bipedRightArmwear.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, z + 0.25F);
        bipedRightArm.addChild(bipedRightArmwear);

        bipedLeftArm = new ModelScaleRenderer(this, 32, 48);
        bipedLeftArm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, z);
        bipedLeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);

        bipedLeftArmwear = new ModelScaleRenderer(this, 48, 48);
        bipedLeftArmwear.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, z + 0.25F);
        bipedLeftArm.addChild(bipedLeftArmwear);

        bipedRightLeg = new ModelScaleRenderer(this, 0, 16);
        bipedRightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, z);
        bipedRightLeg.setRotationPoint(-1.9F, 12.0F, 0.0F);

        bipedRightLegwear = new ModelScaleRenderer(this, 0, 32);
        bipedRightLegwear.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, z + 0.25F);
        bipedRightLeg.addChild(bipedRightLegwear);

        bipedLeftLeg = new ModelScaleRenderer(this, 16, 48);
        bipedLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, z);
        bipedLeftLeg.setRotationPoint(1.9F, 12.0F, 0.0F);

        bipedLeftLegwear = new ModelScaleRenderer(this, 0, 48);
        bipedLeftLegwear.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, z + 0.25F);
        bipedLeftLeg.addChild(bipedLeftLegwear);

        headwear = new ModelHeadwear(this);
        legs = new ModelLegs(this, (ModelScaleRenderer) bipedRightLeg, (ModelScaleRenderer) bipedLeftLeg);
        breasts = new ModelBreasts(this);
        bipedBody.addChild(breasts);

        if (!this.isArmor) {
            bipedHead.addChild(this.ears = new ModelEars(this));
            bipedHead.addChild(this.mohawk = new ModelMohawk(this));
            bipedHead.addChild(this.hair = new ModelHair(this));
            bipedHead.addChild(this.beard = new ModelBeard(this));
            bipedHead.addChild(this.snout = new ModelSnout(this));
            bipedHead.addChild(this.horns = new ModelHorns(this));

            tail = new ModelTail(this);

            bipedBody.addChild(this.wings = new ModelWings(this));
            bipedBody.addChild(this.skirt = new ModelSkirt(this));
            bipedBody.addChild(this.fin = new ModelFin(this));

            bipedLeftArm.addChild(this.clawsL = new ModelClaws(this, false));
            bipedRightArm.addChild(this.clawsR = new ModelClaws(this, true));
        }
    }

//    private void setSlimLimbs(float z) {
//        bipedLeftArm = new ModelScaleRenderer(this, 32, 48);
//        bipedLeftArm.addBox(-1.0F, -2.0F, -2.0F, 3, 12, 4, z);
//        bipedLeftArm.setRotationPoint(5.0F, 2.5F, 0.0F);
//
//        bipedRightArm = new ModelScaleRenderer(this, 40, 16);
//        bipedRightArm.addBox(-2.0F, -2.0F, -2.0F, 3, 12, 4, z);
//        bipedRightArm.setRotationPoint(-5.0F, 2.5F, 0.0F);
//
//        bipedLeftArmwear = new ModelScaleRenderer(this, 48, 48);
//        bipedLeftArmwear.addBox(-1.0F, -2.0F, -2.0F, 3, 12, 4, z + 0.25F);
//        bipedLeftArm.addChild(bipedLeftArmwear);
//
//        bipedRightArmwear = new ModelScaleRenderer(this, 40, 32);
//        bipedRightArmwear.addBox(-2.0F, -2.0F, -2.0F, 3, 12, 4, z + 0.25F);
//        bipedRightArm.addChild(bipedRightArmwear);
//    }

    public void setPlayerData(ModelData data, EntityLivingBase entity) {
        this.data = data;
        if (!this.isArmor) {
            mohawk.setData(data, entity);
            beard.setData(data, entity);
            hair.setData(data, entity);
            snout.setData(data, entity);
            tail.setData(data, entity);
            fin.setData(data, entity);
            wings.setData(data, entity);
            ears.setData(data, entity);
            clawsL.setData(data, entity);
            clawsR.setData(data, entity);
            skirt.setData(data, entity);
            horns.setData(data, entity);
        }
        breasts.setData(data, entity);
        legs.setData(data, entity);
    }


    public void render(Entity par1Entity, float par2, float par3, float par4, float par5, float par6, float par7) {
        if (this.entityModel != null) {
            if (!this.isArmor) {
                this.entityModel.isChild = this.entity.isChild();
                this.entityModel.onGround = this.onGround;
                this.entityModel.isRiding = this.isRiding;
                if ((this.entityModel instanceof ModelBiped)) {
                    ModelBiped biped = (ModelBiped) this.entityModel;
                    biped.aimedBow = this.aimedBow;
                    biped.heldItemLeft = this.heldItemLeft;
                    biped.heldItemRight = this.heldItemRight;
                    biped.isSneak = this.isSneak;
                }
                this.entityModel.render(this.entity, par2, par3, par4, par5, par6, par7);
            }
            return;
        }
        this.currentlyPlayerTexture = true;
        setRotationAngles(par2, par3, par4, par5, par6, par7, par1Entity);


        if (this.data.animation == EnumAnimation.BOW) {
            GL11.glPushMatrix();
            float ticks = (par1Entity.ticksExisted - this.data.animationStart) / 10.0F;
            if (ticks > 1.0F)
                ticks = 1.0F;
            float scale = 2.0F - this.data.body.scaleY + this.data.getLegsY();
            GL11.glTranslatef(0.0F, 12.0F * scale * par7, 0.0F);
            GL11.glRotatef(60.0F * ticks, 1.0F, 0.0F, 0.0F);
            GL11.glTranslatef(0.0F, -12.0F * scale * par7, 0.0F);
        }
        renderHead(par1Entity, par7);
        renderArms(par1Entity, par7, false);
        renderBody(par1Entity, par7);
        if (this.data.animation == EnumAnimation.BOW) {
            GL11.glPopMatrix();
        }
        renderLegs(par7);
    }


    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity) {
        if (!this.isRiding) {
            this.isRiding = (this.data.animation == EnumAnimation.SITTING);
        }
        if ((this.isSneak) && ((this.data.animation == EnumAnimation.CRAWLING) || (this.data.isSleeping())))
            this.isSneak = false;
        this.bipedBody.rotationPointZ = 0.0F;
        this.bipedBody.rotationPointY = 0.0F;
        this.bipedHead.rotateAngleZ = 0.0F;
        this.bipedHeadwear.rotateAngleZ = 0.0F;
        this.bipedLeftLeg.rotateAngleX = 0.0F;
        this.bipedLeftLeg.rotateAngleY = 0.0F;
        this.bipedLeftLeg.rotateAngleZ = 0.0F;
        this.bipedRightLeg.rotateAngleX = 0.0F;
        this.bipedRightLeg.rotateAngleY = 0.0F;
        this.bipedRightLeg.rotateAngleZ = 0.0F;
        this.bipedLeftArm.rotationPointY = 2.0F;
        this.bipedLeftArm.rotationPointZ = 0.0F;
        this.bipedRightArm.rotationPointY = 2.0F;
        this.bipedRightArm.rotationPointZ = 0.0F;

        super.setRotationAngles(par1, par2, par3, par4, par5, par6, entity);

        if (!this.isArmor) {
            this.hair.setRotationAngles(par1, par2, par3, par4, par5, par6, entity);
            this.beard.setRotationAngles(par1, par2, par3, par4, par5, par6, entity);
            this.wings.setRotationAngles(par1, par2, par3, par4, par5, par6, entity);
            this.tail.setRotationAngles(par1, par2, par3, par4, par5, par6, entity);
            this.skirt.setRotationAngles(par1, par2, par3, par4, par5, par6, entity);
        }
        this.legs.setRotationAngles(par1, par2, par3, par4, par5, par6, entity);

        if (isSleeping(entity)) {
            if (this.bipedHead.rotateAngleX < 0.0F) {
                this.bipedHead.rotateAngleX = 0.0F;
                this.bipedHeadwear.rotateAngleX = 0.0F;
            }
        } else if (this.data.animation == EnumAnimation.CRY) {
            this.bipedHeadwear.rotateAngleX = (this.bipedHead.rotateAngleX = 0.7F);
        } else if (this.data.animation == EnumAnimation.HUG) {
            AniHug.setRotationAngles(par1, par2, par3, par4, par5, par6, entity, this);
        } else if (this.data.animation == EnumAnimation.CRAWLING) {
            AniCrawling.setRotationAngles(par1, par2, par3, par4, par5, par6, entity, this);
        } else if (this.data.animation == EnumAnimation.WAVING) {
            this.bipedRightArm.rotateAngleX = -0.1F;
            this.bipedRightArm.rotateAngleY = 0.0F;
            this.bipedRightArm.rotateAngleZ = ((float) (2.141592653589793D - Math.sin(entity.ticksExisted * 0.27F) * 0.5D));
        } else if (this.isSneak) {
            this.bipedBody.rotateAngleX = (0.5F / this.data.body.scaleY);
        }
    }


    public void setLivingAnimations(EntityLivingBase par1EntityLivingBase, float par2, float par3, float par4) {
        if (this.entityModel != null) {
            this.entityModel.setLivingAnimations(this.entity, par2, par3, par4);
        } else if (!this.isArmor) {
            ModelPartData partData = this.data.getPartData("tail");
            if (partData != null)
                this.tail.setLivingAnimations(partData, par1EntityLivingBase, par2, par3, par4);
        }
    }

    public void loadPlayerTexture() {
        if ((!this.isArmor) && (!this.currentlyPlayerTexture)) {
            ClientProxy.bindTexture(this.data.playerResource);
            this.currentlyPlayerTexture = true;
        }
    }

    private void renderHead(Entity entity, float f) {
        loadPlayerTexture();

        float x = 0.0F;
        float y = this.data.getBodyY();
        float z = 0.0F;

        GL11.glPushMatrix();
        if (this.data.animation == EnumAnimation.DANCING) {
            float dancing = entity.ticksExisted / 4.0F;
            GL11.glTranslatef((float) Math.sin(dancing) * 0.075F, (float) Math.abs(Math.cos(dancing)) * 0.125F - 0.02F, (float) -Math.abs(Math.cos(dancing)) * 0.075F);
        }
        ModelPartConfig head = this.data.head;
        if ((this.bipedHeadwear.showModel) && (!this.bipedHeadwear.isHidden)) {
            if ((this.data.headwear == 1) || (this.isArmor)) {
                ((ModelScaleRenderer) this.bipedHeadwear).setConfig(head, x, y, z);
                ((ModelScaleRenderer) this.bipedHeadwear).render(f);
            } else if (this.data.headwear == 2) {
                this.headwear.rotateAngleX = this.bipedHeadwear.rotateAngleX;
                this.headwear.rotateAngleY = this.bipedHeadwear.rotateAngleY;
                this.headwear.rotateAngleZ = this.bipedHeadwear.rotateAngleZ;
                this.headwear.rotationPointX = this.bipedHeadwear.rotationPointX;
                this.headwear.rotationPointY = this.bipedHeadwear.rotationPointY;
                this.headwear.rotationPointZ = this.bipedHeadwear.rotationPointZ;
                this.headwear.setConfig(head, x, y, z);
                this.headwear.render(f);
            }
        }
        ((ModelScaleRenderer) this.bipedHead).setConfig(head, x, y, z);
        ((ModelScaleRenderer) this.bipedHead).render(f);

        GL11.glPopMatrix();
    }

    private void renderBody(Entity entity, float f) {
        loadPlayerTexture();
        float x = 0.0F;
        float y = this.data.getBodyY();
        float z = 0.0F;
        GL11.glPushMatrix();

        if (this.data.animation == EnumAnimation.DANCING) {
            float dancing = entity.ticksExisted / 4.0F;
            GL11.glTranslatef((float) Math.sin(dancing) * 0.015F, 0.0F, 0.0F);
        }

        ModelPartConfig body = this.data.body;
        ((ModelScaleRenderer) bipedBody).setConfig(body, x, y, z);
        ((ModelScaleRenderer) bipedBody).render(f);
        GL11.glPopMatrix();
    }

    public void renderArms(Entity entity, float f, boolean firstPerson) {
        loadPlayerTexture();
        ModelPartConfig arms = this.data.arms;

        float x = (1.0F - this.data.body.scaleX) * 0.25F + (1.0F - arms.scaleX) * 0.075F;
        float y = this.data.getBodyY() + (1.0F - arms.scaleY) * -0.1F;
        float z = 0.0F;

        GL11.glPushMatrix();

        if (this.data.animation == EnumAnimation.DANCING) {
            float dancing = entity.ticksExisted / 4.0F;
            GL11.glTranslatef((float) Math.sin(dancing) * 0.025F, (float) Math.abs(Math.cos(dancing)) * 0.125F - 0.02F, 0.0F);
        }

        if (!firstPerson) {
            byte amputee = data.armsAmputee;
            if (amputee == 0 || amputee == 3) {
                ((ModelScaleRenderer) bipedLeftArm).setConfig(arms, -x, y, z);
                ((ModelScaleRenderer) bipedLeftArm).render(f);
            }
            if (amputee == 0 || amputee == 2) {
                ((ModelScaleRenderer) bipedRightArm).setConfig(arms, x, y, z);
                ((ModelScaleRenderer) bipedRightArm).render(f);
            }
        } else {
            ((ModelScaleRenderer) bipedRightArm).setConfig(arms, 0.0F, 0.0F, 0.0F);
            ((ModelScaleRenderer) bipedRightArm).render(f);
        }

        GL11.glPopMatrix();
    }

    private void renderLegs(float f) {
        loadPlayerTexture();
        ModelPartConfig legs = this.data.legs;

        float x = (1.0F - legs.scaleX) * 0.125F;
        float y = this.data.getLegsY();
        float z = 0.0F;

        GL11.glPushMatrix();
        this.legs.setConfig(legs, x, y, z);
        this.legs.render(f);

        if (!this.isArmor) {
            this.tail.setConfig(legs, 0.0F, y, z);
            this.tail.render(f);
        }
        GL11.glPopMatrix();
    }

    public ModelRenderer getRandomModelBox(Random par1Random) {
        int random = par1Random.nextInt(5);
        switch (random) {
            case 0:
                return this.bipedRightLeg;
            case 1:
                return this.bipedHead;
            case 2:
                return this.bipedLeftArm;
            case 3:
                return this.bipedRightArm;
            case 4:
                return this.bipedLeftLeg;
        }

        return this.bipedBody;
    }

    public boolean isSleeping(Entity entity) {
        if (((entity instanceof EntityPlayer)) && (((EntityPlayer) entity).isPlayerSleeping()))
            return true;
        return this.data.isSleeping();
    }
}
