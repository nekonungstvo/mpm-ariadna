package noppes.mpm;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import noppes.mpm.client.animation.MPMAnimationHandler;
import noppes.mpm.constants.EnumAnimation;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;

public class ModelData extends ModelDataShared implements IExtendedEntityProperties {
    public int rev = MorePlayerModels.Revision;

    public boolean loaded = false;
    public boolean extraLoaded = false;
    public boolean reloadBoxes = false;

    public MPMAnimationHandler animationHandler = new MPMAnimationHandler(this);
    public ResourceLocation playerResource;
    public ResourceLocation playerExtraTexture;

    public ItemStack backItem;

    public EnumAnimation animation = EnumAnimation.NONE;
    public int animationStart = 0;
    public int animationTime = 0;

    public int inLove = 0;
    public short soundType = 0;

    public String url = "";
    public String extraUrl = "";

    public NBTTagCompound writeToNBT() {
        NBTTagCompound compound = super.writeToNBT();
        compound.setInteger("Revision", this.rev);
        compound.setInteger("Animation", this.animation.ordinal());
        compound.setShort("SoundType", this.soundType);
        compound.setString("CustomSkinUrl", this.url);
        compound.setString("ExtraSkinUrl", this.extraUrl);
        return compound;
    }

    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.rev = compound.getInteger("Revision");
        this.soundType = compound.getShort("SoundType");
        this.url = compound.getString("CustomSkinUrl");
        this.extraUrl = compound.getString("ExtraSkinUrl");
        setAnimation(compound.getInteger("Animation"));
        this.loaded = false;
        this.extraLoaded = false;
    }

    public void setAnimation(int i) {
        if (i < EnumAnimation.values().length) {
            this.animation = EnumAnimation.values()[i];
        } else {
            this.animation = EnumAnimation.NONE;
        }
        if (this.animation == EnumAnimation.WAVING)
            this.animationTime = 80;
    }

    public EntityLivingBase getEntity(World world, EntityPlayer player) {
        if (this.entityClass == null)
            return null;
        if (this.entity == null) {
            try {
                this.entity = this.entityClass.getConstructor(World.class).newInstance(world);
                this.entity.readEntityFromNBT(this.extra);

                if ((this.entity instanceof EntityLiving)) {
                    EntityLiving living = (EntityLiving) this.entity;
                    living.setCurrentItemOrArmor(0, player.getHeldItem());
                    living.setCurrentItemOrArmor(1, player.inventory.armorItemInSlot(3));
                    living.setCurrentItemOrArmor(2, player.inventory.armorItemInSlot(2));
                    living.setCurrentItemOrArmor(3, player.inventory.armorItemInSlot(1));
                    living.setCurrentItemOrArmor(4, player.inventory.armorItemInSlot(0));
                }
            } catch (Exception e) {
            }
        }
        return this.entity;
    }

    public String getHash() {
        try {
            final MessageDigest digest = MessageDigest.getInstance("MD5");
            final StringBuilder toHash = new StringBuilder()
                    .append(this.head.toString())
                    .append(this.body.toString())
                    .append(this.arms.toString())
                    .append(this.legs.toString())
                    // Parts, url
                    .append(this.legParts.toString())
                    .append(this.headwear)
                    .append(this.breasts)
                    .append(this.soundType)
                    .append(this.url)
                    .append(this.extraUrl)
                    // Ari extra
                    .append(armsAmputee)
                    .append(doubleHead)
                    .append(brainHead);

            for (String name : this.parts.keySet()) {
                toHash.append(name)
                        .append(":")
                        .append(this.parts.get(name).toString());
            }

            if (this.entityClass != null) {
                toHash.append(this.entityClass.getCanonicalName());
            }

            byte[] hash = digest.digest(toHash.toString().getBytes("UTF-8"));
            return DatatypeConverter.printHexBinary(hash);
        } catch (Exception e) {
        }

        return "";
    }

    public ModelData copy() {
        ModelData data = new ModelData();
        data.readFromNBT(writeToNBT());
        return data;
    }

    public boolean isSleeping() {
        return isSleeping(this.animation);
    }

    private boolean isSleeping(EnumAnimation animation) {
        return (animation == EnumAnimation.SLEEPING_EAST) || (animation == EnumAnimation.SLEEPING_NORTH) || (animation == EnumAnimation.SLEEPING_SOUTH) || (animation == EnumAnimation.SLEEPING_WEST);
    }


    public boolean animationEquals(EnumAnimation animation2) {
        return (animation2 == this.animation) || ((isSleeping()) && (isSleeping(animation2)));
    }

    public void saveNBTData(NBTTagCompound compound) {
    }

    public void loadNBTData(NBTTagCompound compound) {
    }

    public void init(Entity entity, World world) {
    }
}
