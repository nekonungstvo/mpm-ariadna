package noppes.mpm.client;

import net.minecraft.nbt.NBTTagCompound;
import noppes.mpm.ModelData;
import noppes.mpm.ModelPartData;

import java.util.HashMap;

public class Preset {
    public ModelData data = new ModelData();
    public String name;

    public static void FillDefault(HashMap<String, Preset> presets) {
        ModelData data = new ModelData();
        Preset preset = new Preset();
        preset.name = "Human Male";
        preset.data = data;
        presets.put("human male", preset);

        data = new ModelData();
        preset = new Preset();
        preset.name = "Human Female";
        preset.data = data;
        data.breasts = 2;
        data.head.setScale(0.95F, 0.95F);
        data.legs.setScale(0.92F, 0.92F);
        data.arms.setScale(0.8F, 0.92F);
        data.body.setScale(0.92F, 0.92F);
        presets.put("human female", preset);

        data = new ModelData();
        preset = new Preset();
        preset.name = "Elf Male";
        preset.data = data;
        data.legs.setScale(0.85F, 1.15F);
        data.arms.setScale(0.85F, 1.15F);
        data.body.setScale(0.85F, 1.15F);
        data.head.setScale(0.85F, 0.95F);
        presets.put("elf male", preset);

        data = new ModelData();
        preset = new Preset();
        preset.name = "Elf Female";
        preset.data = data;
        data.breasts = 2;
        data.legs.setScale(0.8F, 1.05F);
        data.arms.setScale(0.8F, 1.05F);
        data.body.setScale(0.8F, 1.05F);
        data.head.setScale(0.8F, 0.85F);
        presets.put("elf female", preset);

        data = new ModelData();
        preset = new Preset();
        preset.name = "Dwarf Male";
        preset.data = data;
        data.legs.setScale(1.1F, 0.7F, 0.9F);
        data.arms.setScale(0.9F, 0.7F);
        data.body.setScale(1.2F, 0.7F, 1.5F);
        data.head.setScale(0.85F, 0.85F);
        presets.put("dwarf male", preset);

        data = new ModelData();
        preset = new Preset();
        preset.name = "Dwarf Female";
        preset.data = data;
        data.breasts = 2;
        data.legs.setScale(0.9F, 0.65F);
        data.arms.setScale(0.9F, 0.65F);
        data.body.setScale(1.0F, 0.65F, 1.1F);
        data.head.setScale(0.85F, 0.85F);
        presets.put("dwarf female", preset);

        data = new ModelData();
        preset = new Preset();
        preset.name = "Orc Male";
        preset.data = data;
        data.legs.setScale(1.2F, 1.05F);
        data.arms.setScale(1.2F, 1.05F);
        data.body.setScale(1.4F, 1.1F, 1.5F);
        data.head.setScale(1.2F, 1.1F);
        presets.put("orc male", preset);

        data = new ModelData();
        preset = new Preset();
        preset.name = "Orc Female";
        preset.data = data;
        data.breasts = 2;
        data.legs.setScale(1.1F, 1.0F);
        data.arms.setScale(1.1F, 1.0F);
        data.body.setScale(1.1F, 1.0F, 1.25F);
        presets.put("orc female", preset);

        data = new ModelData();
        preset = new Preset();
        preset.name = "Cat Male";
        preset.data = data;
        ModelPartData ears = data.getOrCreatePart("ears");
        ears.setTexture("ears/type1", 0);
        ears.color = 14263886;
        ModelPartData snout = data.getOrCreatePart("snout");
        snout.setTexture("snout/small1", 0);
        snout.color = 14263886;
        ModelPartData tail = data.getOrCreatePart("tail");
        tail.setTexture("tail/tail1", 0);
        tail.color = 14263886;
        presets.put("cat male", preset);

        data = new ModelData();
        preset = new Preset();
        preset.name = "Cat Female";
        preset.data = data;
        ears = data.getOrCreatePart("ears");
        ears.setTexture("ears/type1", 0);
        ears.color = 14263886;
        snout = data.getOrCreatePart("snout");
        snout.setTexture("snout/small1", 0);
        snout.color = 14263886;
        tail = data.getOrCreatePart("tail");
        tail.setTexture("tail/tail1", 0);
        tail.color = 14263886;
        data.breasts = 2;
        data.head.setScale(0.95F, 0.95F);
        data.legs.setScale(0.92F, 0.92F);
        data.arms.setScale(0.8F, 0.92F);
        data.body.setScale(0.92F, 0.92F);
        presets.put("cat female", preset);

        data = new ModelData();
        preset = new Preset();
        preset.name = "Wolf Male";
        preset.data = data;
        ears = data.getOrCreatePart("ears");
        ears.setTexture("ears/type1", 0);
        ears.color = 6182997;
        snout = data.getOrCreatePart("snout");
        snout.setTexture("snout/large1", 2);
        snout.color = 6182997;
        tail = data.getOrCreatePart("tail");
        tail.setTexture("tail/tail2", 0);
        tail.color = 6182997;
        presets.put("wolf male", preset);

        data = new ModelData();
        preset = new Preset();
        preset.name = "Wolf Female";
        preset.data = data;
        ears = data.getOrCreatePart("ears");
        ears.setTexture("ears/type1", 0);
        ears.color = 6182997;
        snout = data.getOrCreatePart("snout");
        snout.setTexture("snout/large1", 2);
        snout.color = 6182997;
        tail = data.getOrCreatePart("tail");
        tail.setTexture("tail/tail2", 0);
        tail.color = 6182997;
        data.breasts = 2;
        data.head.setScale(0.95F, 0.95F);
        data.legs.setScale(0.92F, 0.92F);
        data.arms.setScale(0.8F, 0.92F);
        data.body.setScale(0.92F, 0.92F);
        presets.put("wolf female", preset);

        data = new ModelData();
        preset = new Preset();
        preset.name = "Enderchibi";
        preset.data = data;
        data.legs.setScale(0.65F, 0.75F);
        data.arms.setScale(0.5F, 1.45F);
//        ModelPartData part = data.getOrCreatePart("particles");
//        part.setTexture("particle/type1", 1);
//        part.color = 16711680;
        presets.put("enderchibi", preset);
    }

    public NBTTagCompound writeToNBT() {
        NBTTagCompound compound = new NBTTagCompound();
        compound.setString("PresetName", this.name);
        compound.setTag("PresetData", this.data.writeToNBT());
        return compound;
    }

    public void readFromNBT(NBTTagCompound compound) {
        this.name = compound.getString("PresetName");
        this.data.readFromNBT(compound.getCompoundTag("PresetData"));
    }
}
