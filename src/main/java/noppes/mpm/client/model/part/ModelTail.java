package noppes.mpm.client.model.part;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import noppes.mpm.ModelData;
import noppes.mpm.ModelPartData;
import noppes.mpm.client.ClientProxy;
import noppes.mpm.client.MCALibrary.animation.AnimationHandler;
import noppes.mpm.client.model.ModelMPM;
import noppes.mpm.client.model.ModelScaleRenderer;
import noppes.mpm.client.model.extrapart.ctenotail.ChannelCtenoIdle;
import noppes.mpm.client.model.extrapart.ctenotail.ChannelCtenoLowered;
import noppes.mpm.client.model.extrapart.ctenotail.ChannelCtenoSit;
import noppes.mpm.client.model.extrapart.ctenotail.ModelCtenoTail;
import noppes.mpm.client.model.extrapart.flufftail.ChannelFluffyLie;
import noppes.mpm.client.model.extrapart.flufftail.ChannelFluffySit;
import noppes.mpm.client.model.extrapart.flufftail.ModelFluffyTail;
import noppes.mpm.client.model.part.tails.ModelDragonTail;
import noppes.mpm.client.model.part.tails.ModelRodentTail;
import noppes.mpm.client.model.part.tails.ModelSquirrelTail;
import noppes.mpm.client.model.part.tails.ModelTailFin;
import noppes.mpm.constants.EnumAnimation;
import org.lwjgl.opengl.GL11;

import java.util.stream.Stream;

public class ModelTail extends ModelScaleRenderer {
    public ModelData data;
    private EntityLivingBase entity;
    private ModelMPM base;

    private ModelRenderer tail;
    private ModelRenderer dragon;
    private ModelRenderer squirrel;
    private ModelRenderer horse;
    private ModelRenderer fin;
    private ModelRenderer rodent;

    private ModelCtenoTail cteno;
    private ModelFluffyTail fluffy;

    private int color = 16777215;

    private ResourceLocation location = null;

    public ModelTail(ModelMPM base) {
        super(base);
        this.base = base;
        this.rotationPointY = 11.0F;

        this.tail = new ModelRenderer(base, 56, 21);
        this.tail.addBox(-1.0F, 0.0F, 0.0F, 2, 9, 2);
        this.tail.setRotationPoint(0.0F, 0.0F, 1.0F);
        setRotation(this.tail, 0.8714253F, 0.0F, 0.0F);
        addChild(this.tail);

        this.horse = new ModelRenderer(base);
        this.horse.setRotationPoint(0.0F, -1.0F, 1.0F);
        addChild(this.horse);

        ModelRenderer tailBase = new ModelRenderer(base, 0, 26);
        tailBase.addBox(-1.0F, -1.0F, 0.0F, 2, 2, 3);
        setRotation(tailBase, -1.134464F, 0.0F, 0.0F);
        this.horse.addChild(tailBase);

        ModelRenderer tailMiddle = new ModelRenderer(base, 0, 13);
        tailMiddle.addBox(-1.5F, -2.0F, 3.0F, 3, 4, 7);
        setRotation(tailMiddle, -1.134464F, 0.0F, 0.0F);
        this.horse.addChild(tailMiddle);

        ModelRenderer tailTip = new ModelRenderer(base, 0, 0);
        tailTip.addBox(-1.5F, -4.5F, 9.0F, 3, 4, 7);
        setRotation(tailTip, -1.40215F, 0.0F, 0.0F);
        this.horse.addChild(tailTip);
        this.horse.rotateAngleX = 0.5F;

        addChild(this.dragon = new ModelDragonTail(base));
        addChild(this.squirrel = new ModelSquirrelTail(base));
        addChild(this.fin = new ModelTailFin(base));
        addChild(this.rodent = new ModelRodentTail(base));
        addChild(this.cteno = new ModelCtenoTail(base));
        addChild(this.fluffy = new ModelFluffyTail(base));
    }

    public void setData(ModelData data, EntityLivingBase entity) {
        this.data = data;
        this.entity = entity;
        initData(data);
    }

    public void setRotationAngles(float par1, float par2, float par3, float par4, float par5, float par6, Entity entity) {
        // Special Cteno idle wiggle
        if (!cteno.isHidden) {
            AnimationHandler.performAnimationInModel(cteno.parts, data.animationHandler);
        } else if (!fluffy.isHidden) {
            AnimationHandler.performAnimationInModel(fluffy.parts, data.animationHandler);
        } else {
            this.rotateAngleX = (MathHelper.sin(par3 * 0.067F) * 0.05F); // Idle vert wiggle
        }
        this.rotateAngleY = (MathHelper.cos(par1 * 0.6662F) * 0.2F * par2); // Walking wiggle

        if (this.data.animation == EnumAnimation.WAG) {
            this.rotateAngleY = ((float) (Math.sin(entity.ticksExisted * 0.5F) * 0.3f));
        }
        if (this.data.legParts.type == 2) { // Horsie
            this.rotationPointY = 13.0F;
            this.rotationPointZ = (14.0F * this.data.legs.scaleZ);

            if ((this.base.isSleeping(entity)) || (this.data.animation == EnumAnimation.CRAWLING)) {
                this.rotationPointY = (12.0F + 16.0F * this.data.legs.scaleZ);
                this.rotationPointZ = (1.0F * this.data.legs.scaleY);

                this.rotateAngleX = -0.7853982F;
            }
        } else if (this.data.legParts.type == 3) { // Squirrel
            this.rotationPointY = 8.6F;
            this.rotationPointZ = (19.0F * this.data.legs.scaleZ);
        } else {
            this.rotationPointY = 11.0F;
            this.rotationPointZ = -1.0F;
        }
//        this.rotationPointZ += this.base.bipedRightLeg.rotationPointZ + 0.5F;

//        if (base.isSneak)
//            this.rotationPointZ = 0;
    }

    public void setLivingAnimations(ModelPartData data, EntityLivingBase entity, float par2, float par3, float par4) {
    }

    public void initData(ModelData data) {
        ModelPartData config = data.getPartData("tail");
        if (config == null) {
            this.isHidden = true;
            return;
        }

        color = config.color;
        isHidden = false;
        tail.isHidden = (config.type != 0);
        dragon.isHidden = (config.type != 1);
        horse.isHidden = (config.type != 2);
        squirrel.isHidden = (config.type != 3);
        fin.isHidden = (config.type != 4);
        rodent.isHidden = (config.type != 5);
        cteno.isHidden = (config.type != 6);
        fluffy.isHidden = (config.type != 7);

        if (config.type > 5) { // Premium extra parts gets extra texture
            this.location = data.playerExtraTexture;
        } else if (!config.playerTexture) {
            this.location = config.getResource();
        } else {
            this.location = null;
        }

        if (!cteno.isHidden) {
            final String anim = ModelCtenoTail.getAnimation(data.animation);
            Stream.of(ChannelCtenoIdle.ANIM_NAME, ChannelCtenoLowered.ANIM_NAME, ChannelCtenoSit.ANIM_NAME)
                .filter(s -> !s.equals(anim))
                .forEach(data.animationHandler::stopAnimation);

            data.animationHandler.keepAnimation(anim, 0);
        }
        if (!fluffy.isHidden) {
            final String anim = ModelFluffyTail.getAnimation(data.animation);
            Stream.of(ChannelFluffySit.ANIM_NAME, ChannelFluffyLie.ANIM_NAME)
                    .filter(s -> !s.equals(anim))
                    .forEach(data.animationHandler::stopAnimation);
            if (anim != null)
                data.animationHandler.keepAnimation(anim, 0);
        }
    }

    public void render(float par1) {
        if ((this.isHidden) || (!this.showModel))
            return;
        if (!this.base.isArmor) {
            if (this.location != null) {
                ClientProxy.bindTexture(this.location);
                this.base.currentlyPlayerTexture = false;
            } else if (!this.base.currentlyPlayerTexture) {
                ClientProxy.bindTexture(this.data.playerResource);
                this.base.currentlyPlayerTexture = true;
            }
        }
        boolean bo = (this.entity.hurtTime <= 0) && (this.entity.deathTime <= 0);
        if (bo) {
            float red = (this.color >> 16 & 0xFF) / 255.0F;
            float green = (this.color >> 8 & 0xFF) / 255.0F;
            float blue = (this.color & 0xFF) / 255.0F;
            GL11.glColor4f(red, green, blue, 1.0F);
        }

        if (!cteno.isHidden)
            AnimationHandler.performAnimationInModel(cteno.parts, data.animationHandler);
        if (!fluffy.isHidden)
            AnimationHandler.performAnimationInModel(fluffy.parts, data.animationHandler);

        super.render(par1);

        if (bo) {
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        }
    }
}
