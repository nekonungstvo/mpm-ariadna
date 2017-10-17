package noppes.mpm;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

import java.util.HashMap;

public class ModelDataShared {
    public Class<? extends EntityLivingBase> entityClass;
    public ModelPartConfig head = new ModelPartConfig();
    public ModelPartConfig arms = new ModelPartConfig();
    public ModelPartConfig body = new ModelPartConfig();
    public ModelPartConfig legs = new ModelPartConfig();
    public ModelPartData legParts = new ModelPartData();
    public NBTTagCompound extra = new NBTTagCompound();
    public byte breasts = 0;
    public byte headwear = 2;
    public boolean newSkinFormat = false;
    public byte armsAmputee = 0;
    public boolean doubleHead = false;
    protected EntityLivingBase entity;
    protected HashMap<String, ModelPartData> parts = new HashMap<>();

    public NBTTagCompound writeToNBT() {
        NBTTagCompound compound = new NBTTagCompound();

        if (this.entityClass != null) {
            compound.setString("EntityClass", this.entityClass.getCanonicalName());
        }
        compound.setTag("ArmsConfig", this.arms.writeToNBT());
        compound.setTag("BodyConfig", this.body.writeToNBT());
        compound.setTag("LegsConfig", this.legs.writeToNBT());
        compound.setTag("HeadConfig", this.head.writeToNBT());
        compound.setTag("LegParts", this.legParts.writeToNBT());

        compound.setByte("Headwear", this.headwear);
        compound.setByte("Breasts", this.breasts);
        compound.setTag("ExtraData", this.extra);

        NBTTagList list = new NBTTagList();
        for (String name : this.parts.keySet()) {
            NBTTagCompound item = ((ModelPartData) this.parts.get(name)).writeToNBT();
            item.setString("PartName", name);
            list.appendTag(item);
        }
        compound.setTag("Parts", list);

        compound.setBoolean("NewSkinFormat", this.newSkinFormat);
        compound.setByte("ArmsAmputee", this.armsAmputee);
        compound.setBoolean("DoubleHead", this.doubleHead);

        return compound;
    }

    public void readFromNBT(NBTTagCompound compound) {
        setEntityClass(compound.getString("EntityClass"));
        this.arms.readFromNBT(compound.getCompoundTag("ArmsConfig"));
        this.body.readFromNBT(compound.getCompoundTag("BodyConfig"));
        this.legs.readFromNBT(compound.getCompoundTag("LegsConfig"));
        this.head.readFromNBT(compound.getCompoundTag("HeadConfig"));
        this.legParts.readFromNBT(compound.getCompoundTag("LegParts"));

        this.headwear = compound.getByte("Headwear");
        this.breasts = compound.getByte("Breasts");
        this.extra = compound.getCompoundTag("ExtraData");

        HashMap<String, ModelPartData> parts = new HashMap();
        NBTTagList list = compound.getTagList("Parts", 10);
        for (int i = 0; i < list.tagCount(); i++) {
            NBTTagCompound item = list.getCompoundTagAt(i);
            ModelPartData part = new ModelPartData();
            part.readFromNBT(item);
            parts.put(item.getString("PartName"), part);
        }
        this.parts = parts;

        this.newSkinFormat = compound.getBoolean("NewSkinFormat");
        this.armsAmputee = compound.getByte("ArmsAmputee");
        this.doubleHead = compound.getBoolean("DoubleHead");
    }

    public Class<? extends EntityLivingBase> getEntityClass() {
        return this.entityClass;
    }

    public void setEntityClass(Class<? extends EntityLivingBase> entityClass) {
        this.entityClass = entityClass;
        this.entity = null;
        this.extra = new NBTTagCompound();
    }

    private void setEntityClass(String string) {
        this.entityClass = null;
        this.entity = null;
        try {
            Class<?> cls = Class.forName(string);
            if (cls.isAssignableFrom(EntityLivingBase.class)) {
                setEntityClass(cls.asSubclass(EntityLivingBase.class));
            }
        } catch (ClassNotFoundException e) {
        }
    }

    public void clearEntity() {
        this.entity = null;
    }

    public float offsetY() {
        if (this.entity == null)
            return -getBodyY();
        return this.entity.height - 1.8F;
    }

    public ModelPartData getPartData(String type) {
        return this.parts.get(type);
    }

    public void removePart(String type) {
        this.parts.remove(type);
    }

    public ModelPartData getOrCreatePart(String type) {
        ModelPartData part = this.parts.get(type);
        if (part == null)
            this.parts.put(type, part = new ModelPartData());
        return part;
    }

    public float getBodyY() {
        if (this.legParts.type == 3)
            return (0.9F - this.body.scaleY) * 0.75F + getLegsY();
        if (this.legParts.type == 3)
            return (0.5F - this.body.scaleY) * 0.75F + getLegsY();
        return (1.0F - this.body.scaleY) * 0.75F + getLegsY();
    }

    public float getLegsY() {
        if (this.legParts.type == 3)
            return (0.87F - this.legs.scaleY) * 1.0F;
        return (1.0F - this.legs.scaleY) * 0.75F;
    }
}
