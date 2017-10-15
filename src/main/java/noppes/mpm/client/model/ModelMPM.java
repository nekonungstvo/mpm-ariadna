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
    public ModelBase entityModel;
    public EntityLivingBase entity;
    public boolean currentlyPlayerTexture;
    public boolean isArmor;

    protected ModelPartInterface wings;
    protected ModelPartInterface mohawk;
    protected ModelPartInterface hair;
    protected ModelPartInterface beard;
    protected ModelPartInterface breasts;
    protected ModelPartInterface snout;
    protected ModelPartInterface ears;
    protected ModelPartInterface fin;
    protected ModelPartInterface skirt;
    protected ModelPartInterface horns;
    protected ModelPartInterface clawsR;
    protected ModelPartInterface clawsL;
    protected ModelLegs legs;
    protected ModelScaleRenderer headwear;
    protected ModelTail tail;

    private float z;

    public ModelMPM(float z) {
        this(z, 32);
    }

    public ModelMPM(float z, int textureHeight) {
        super(z, 0, 64, textureHeight);
        this.z = z;
        this.isArmor = (z > 0.0F);
        reloadBoxes();
    }

    public void reloadBoxes() {
        this.bipedCloak = new ModelRenderer(this, 0, 0);
        this.bipedCloak.addBox(-5.0F, 0.0F, -1.0F, 10, 16, 1, z);

        this.bipedEars = new ModelRenderer(this, 24, 0);
        this.bipedEars.addBox(-3.0F, -6.0F, -1.0F, 6, 6, 1, z);

        this.bipedHead = new ModelScaleRenderer(this, 0, 0);
        this.bipedHead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, z);
        this.bipedHead.setRotationPoint(0.0F, 0.0F, 0.0F);

        this.bipedHeadwear = new ModelScaleRenderer(this, 32, 0);
        this.bipedHeadwear.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, z + 0.5F);
        this.bipedHeadwear.setRotationPoint(0.0F, 0.0F, 0.0F);

        this.bipedBody = new ModelScaleRenderer(this, 16, 16);
        this.bipedBody.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, z);
        this.bipedBody.setRotationPoint(0.0F, 0.0F, 0.0F);

        this.bipedRightArm = new ModelScaleRenderer(this, 40, 16);
        this.bipedRightArm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, z);
        this.bipedRightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);

        this.bipedLeftArm = new ModelScaleRenderer(this, 40, 16);
        this.bipedLeftArm.mirror = true;
        this.bipedLeftArm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, z);
        this.bipedLeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);

        this.bipedRightLeg = new ModelScaleRenderer(this, 0, 16);
        this.bipedRightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, z);
        this.bipedRightLeg.setRotationPoint(-1.9F, 12.0F, 0.0F);

        this.bipedLeftLeg = new ModelScaleRenderer(this, 0, 16);
        this.bipedLeftLeg.mirror = true;
        this.bipedLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, z);
        this.bipedLeftLeg.setRotationPoint(1.9F, 12.0F, 0.0F);

        reloadExtraPartBoxes();
    }

    protected void reloadExtraPartBoxes() {
        legs = new ModelLegs(this, (ModelScaleRenderer) bipedRightLeg, (ModelScaleRenderer) bipedLeftLeg);
        headwear = new ModelHeadwear(this);

        bipedBody.addChild(breasts = new ModelBreasts(this));

        if (!this.isArmor) {
            bipedHead.addChild(ears = new ModelEars(this));
            bipedHead.addChild(mohawk = new ModelMohawk(this));
            bipedHead.addChild(hair = new ModelHair(this));
            bipedHead.addChild(beard = new ModelBeard(this));
            bipedHead.addChild(snout = new ModelSnout(this));
            bipedHead.addChild(horns = new ModelHorns(this));

            bipedBody.addChild(tail = new ModelTail(this));
            bipedBody.addChild(wings = new ModelWings(this));
            bipedBody.addChild(skirt = new ModelSkirt(this));
            bipedBody.addChild(fin = new ModelFin(this));

            bipedLeftArm.addChild(clawsL = new ModelClaws(this, false));
            bipedRightArm.addChild(clawsR = new ModelClaws(this, true));
        }
    }

    public void setPlayerData(ModelData newdata, EntityLivingBase entity) {
        boolean justLoaded = this.data == null;
        this.data = newdata;
        if (justLoaded) reloadBoxes();

        if (!this.isArmor) {
            this.mohawk.setData(data, entity);
            this.beard.setData(data, entity);
            this.hair.setData(data, entity);
            this.snout.setData(data, entity);
            this.tail.setData(data, entity);
            this.fin.setData(data, entity);
            this.wings.setData(data, entity);
            this.ears.setData(data, entity);
            this.clawsL.setData(data, entity);
            this.clawsR.setData(data, entity);
            this.skirt.setData(data, entity);
            this.horns.setData(data, entity);
        }
        this.breasts.setData(data, entity);
        this.legs.setData(data, entity);
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

        float doubleHeadOffset = data.doubleHead ? data.head.scaleX * 0.3f : 0;

        GL11.glPushMatrix();
        if (this.data.animation == EnumAnimation.DANCING) {
            float dancing = entity.ticksExisted / 4.0F;
            GL11.glTranslatef((float) Math.sin(dancing) * 0.075F, (float) Math.abs(Math.cos(dancing)) * 0.125F - 0.02F, (float) -Math.abs(Math.cos(dancing)) * 0.075F);
        }
        ModelPartConfig head = this.data.head;
        if ((this.bipedHeadwear.showModel) && (!this.bipedHeadwear.isHidden)) {
            if ((this.data.headwear == 1) || (this.isArmor)) {
                ((ModelScaleRenderer) this.bipedHeadwear).setConfig(head, x - doubleHeadOffset, y, z);
                ((ModelScaleRenderer) this.bipedHeadwear).render(f);
                if (data.doubleHead) {
                    ((ModelScaleRenderer) this.bipedHeadwear).setConfig(head, x + doubleHeadOffset, y, z);
                    ((ModelScaleRenderer) this.bipedHeadwear).render(f);
                }
            } else if (this.data.headwear == 2) {
                this.headwear.rotateAngleX = this.bipedHeadwear.rotateAngleX;
                this.headwear.rotateAngleY = this.bipedHeadwear.rotateAngleY;
                this.headwear.rotateAngleZ = this.bipedHeadwear.rotateAngleZ;
                this.headwear.rotationPointX = this.bipedHeadwear.rotationPointX;
                this.headwear.rotationPointY = this.bipedHeadwear.rotationPointY;
                this.headwear.rotationPointZ = this.bipedHeadwear.rotationPointZ;
                this.headwear.setConfig(head, x - doubleHeadOffset, y, z);
                this.headwear.render(f);
                if (data.doubleHead) {
                    this.headwear.setConfig(head, x + doubleHeadOffset, y, z);
                    this.headwear.render(f);
                }
            }
        }
        ((ModelScaleRenderer) this.bipedHead).setConfig(head, x - doubleHeadOffset, y, z);
        ((ModelScaleRenderer) this.bipedHead).render(f);
        if (data.doubleHead) {
            ((ModelScaleRenderer) this.bipedHead).setConfig(head, x + doubleHeadOffset, y, z);
            ((ModelScaleRenderer) this.bipedHead).render(f);
        }

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
        ((ModelScaleRenderer) this.bipedBody).setConfig(body, x, y, z);
        ((ModelScaleRenderer) this.bipedBody).render(f);
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
            ((ModelScaleRenderer) this.bipedRightArm).setConfig(arms, 0.0F, 0.0F, 0.0F);
            ((ModelScaleRenderer) this.bipedRightArm).render(f);
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
//        if (!this.isArmor) {
//            this.tail.setConfig(legs, 0.0F, y, z);
//            this.tail.render(f);
//        }
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
        return ((entity instanceof EntityPlayer)) && (((EntityPlayer) entity).isPlayerSleeping()) || this.data.isSleeping();
    }
}
