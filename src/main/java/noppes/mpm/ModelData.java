package noppes.mpm;

import com.mojang.authlib.GameProfile;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import noppes.mpm.constants.EnumAnimation;

import java.security.MessageDigest;


public class ModelData
        extends ModelDataShared
        implements IExtendedEntityProperties {
    public String username;
    public boolean loaded = false;

    public ResourceLocation playerResource;
    public int rev = MorePlayerModels.Revision;

    public ItemStack backItem;

    public int inLove = 0;
    public int animationTime = 0;

    public EnumAnimation animation = EnumAnimation.NONE;
    public int animationStart = 0;

    public short soundType = 0;

    public String url = "";

    public NBTTagCompound writeToNBT() {
        NBTTagCompound compound = super.writeToNBT();
        compound.setInteger("Revision", this.rev);

        compound.setInteger("Animation", this.animation.ordinal());

        compound.setShort("SoundType", this.soundType);

        compound.setString("CustomSkinUrl", this.url);

        return compound;
    }

    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.rev = compound.getInteger("Revision");

        this.soundType = compound.getShort("SoundType");

        this.url = compound.getString("CustomSkinUrl");

        setAnimation(compound.getInteger("Animation"));

        this.loaded = false;
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
                this.entity = ((EntityLivingBase) this.entityClass.getConstructor(World.class).newInstance(world));

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
            MessageDigest digest = MessageDigest.getInstance("MD5");
            String toHash = this.arms.toString() + this.legs.toString() + this.body.toString() + this.head.toString();

            if (this.entityClass != null) {
                toHash = toHash + this.entityClass.getCanonicalName();
            }
            toHash = toHash + this.legParts.toString() + this.headwear + this.breasts + this.soundType + this.url;

            for (String name : this.parts.keySet()) {
                toHash = toHash + name + ":" + ((ModelPartData) this.parts.get(name)).toString();
            }
            byte[] hash = digest.digest(toHash.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder(2 * hash.length);
            for (byte b : hash) {
                sb.append(String.format("%02x", new Object[]{Integer.valueOf(b & 0xFF)}));
            }

            return sb.toString();
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
