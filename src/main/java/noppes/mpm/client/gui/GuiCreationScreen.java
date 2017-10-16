package noppes.mpm.client.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import noppes.mpm.ModelData;
import noppes.mpm.MorePlayerModels;
import noppes.mpm.PlayerDataController;
import noppes.mpm.client.EntityFakeLiving;
import noppes.mpm.client.gui.util.*;
import noppes.mpm.constants.EnumPackets;
import org.lwjgl.input.Keyboard;

import java.lang.reflect.Modifier;
import java.util.*;

public class GuiCreationScreen extends GuiModelInterface implements ITextfieldListener {
    public HashMap<String, Class<? extends EntityLivingBase>> data = new HashMap<>();
    private List<String> list;
    private final String[] ignoredTags = {"CanBreakDoors", "Bred", "PlayerCreated", "Tame", "HasReproduced"};

    private GuiNpcButton prev;

    private GuiNpcButton next;
    public String hash;
    private HashMap<Integer, String> mapped = new HashMap<>();

    public GuiCreationScreen() {
        this.hash = this.playerdata.getHash();
        Map<?, ?> mapping = EntityList.stringToClassMapping;
        for (Object name : mapping.keySet()) {
            Class<?> c = (Class) mapping.get(name);
            try {
                if (EntityLiving.class.isAssignableFrom(c))
                    if ((c.getConstructor(World.class) != null) && (!Modifier.isAbstract(c.getModifiers())) &&
                            ((RenderManager.instance.getEntityClassRenderObject(c) instanceof RendererLivingEntity))) {
                        this.data.put(name.toString(), c.asSubclass(EntityLivingBase.class));
                    }
            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
            }
        }
        this.list = new ArrayList(this.data.keySet());
        Collections.sort(this.list, String.CASE_INSENSITIVE_ORDER);
    }


    public void initGui() {
        super.initGui();
        Keyboard.enableRepeatEvents(true);
        String title = "Player";

        EntityLivingBase entity = this.playerdata.getEntity(this.mc.theWorld, this.mc.thePlayer);
        this.xOffset = (entity == null ? 0 : 50);
        if (entity != null)
            title = (String) EntityList.classToStringMapping.get(this.playerdata.getEntityClass());
        addButton(new GuiNpcButton(1, this.guiLeft + 140, this.guiTop + 4, 100, 20, title));

        addButton(this.prev = new GuiNpcButton(0, this.guiLeft + 118, this.guiTop + 4, 20, 20, "<"));
        addButton(this.next = new GuiNpcButton(2, this.guiLeft + 242, this.guiTop + 4, 20, 20, ">"));
        this.prev.enabled = (getCurrentEntityIndex() >= 0);
        this.next.enabled = (getCurrentEntityIndex() < this.list.size() - 1);

        addButton(new GuiNpcButton(46, this.guiLeft + 310, this.guiTop + 58, 80, 20, "Reload Skins"));
        addButton(new GuiNpcButton(51, this.guiLeft + 310, this.guiTop + 80, 80, 20, "Edit Buttons"));

        addLabel(new GuiNpcLabel(47, "Point of View", this.guiLeft + 270, this.guiTop + 139, 16777215));
        addButton(new GuiNpcButton(47, this.guiLeft + 350, this.guiTop + 134, 50, 20, new String[]{"gui.no", "gui.yes"}, MorePlayerModels.EnablePOV ? 1 : 0));

        addLabel(new GuiNpcLabel(48, "Chatbubbles", this.guiLeft + 270, this.guiTop + 161, 16777215));
        addButton(new GuiNpcButton(48, this.guiLeft + 350, this.guiTop + 156, 50, 20, new String[]{"gui.no", "gui.yes"}, MorePlayerModels.EnableChatBubbles ? 1 : 0));

        addLabel(new GuiNpcLabel(49, "BackItem", this.guiLeft + 270, this.guiTop + 183, 16777215));
        addButton(new GuiNpcButton(49, this.guiLeft + 350, this.guiTop + 178, 50, 20, new String[]{"gui.no", "gui.yes"}, MorePlayerModels.EnableBackItem ? 1 : 0));

        addLabel(new GuiNpcLabel(50, "Tooltip", this.guiLeft + 270, this.guiTop + 205, 16777215));
        addButton(new GuiNpcButton(50, this.guiLeft + 350, this.guiTop + 200, 50, 20, new String[]{"gui.no", "1", "2", "3", "4"}, MorePlayerModels.Tooltips));

        if (entity == null) {
            showPlayerButtons();
        } else {
            showEntityButtons(entity);
        }

        addButton(new GuiNpcButton(44, this.guiLeft + 310, this.guiTop + 14, 80, 20, "Save Settings"));
        addButton(new GuiNpcButton(45, this.guiLeft + 310, this.guiTop + 36, 80, 20, "Load Settings"));
        addLabel(new GuiNpcLabel(52, "Skin Url", this.guiLeft, this.guiTop + 163, 16777215));
        addTextField(new GuiNpcTextField(52, this, this.guiLeft, this.guiTop + 173, 160, 20, this.playerdata.url));
    }

    private void showPlayerButtons() {
        int y = this.guiTop;

        y += 22;
        addButton(new GuiNpcButton(8, this.guiLeft + 4, y, 96, 20, "Scale"));

        y += 22;
        addButton(new GuiNpcButton(4, this.guiLeft + 50, y, 50, 20, "selectServer.edit"));
        addLabel(new GuiNpcLabel(1, "Head", this.guiLeft, y + 5, 16777215));

        y += 22;
        addButton(new GuiNpcButton(5, this.guiLeft + 50, y, 50, 20, "selectServer.edit"));
        addLabel(new GuiNpcLabel(2, "Body", this.guiLeft, y + 5, 16777215));

        y += 22;
        addButton(new GuiNpcButton(6, this.guiLeft + 50, y, 50, 20, "selectServer.edit"));
        addLabel(new GuiNpcLabel(3, "Arms", this.guiLeft, y + 5, 16777215));

        y += 22;
        addButton(new GuiNpcButton(7, this.guiLeft + 50, y, 50, 20, "selectServer.edit"));
        addLabel(new GuiNpcLabel(4, "Legs", this.guiLeft, y + 5, 16777215));

        y += 22;
        addButton(new GuiNpcButton(9, this.guiLeft + 50, y, 50, 20, new String[]{"Old", "New"}, this.playerdata.newSkinFormat ? 1 : 0));
        addLabel(new GuiNpcLabel(5, "Format", this.guiLeft, y + 5, 16777215));

        y += 22;
        addButton(new GuiNpcButton(10, this.guiLeft + 50, y, 50, 20, new String[]{"Default", "Female", "Male", "Goblin Male"}, this.playerdata.soundType));
        addLabel(new GuiNpcLabel(6, "Sounds", this.guiLeft, y + 5, 16777215));
    }

    private void showEntityButtons(EntityLivingBase entity) {
        this.mapped.clear();
        int y = this.guiTop + 20;

        NBTTagCompound compound = getExtras(entity);
        Set<String> keys = compound.func_150296_c();
        int i = 0;
        for (String name : keys)
            if (!isIgnored(name)) {
                NBTBase base = compound.getTag(name);
                if (name.equals("Age")) {
                    i++;
                    addLabel(new GuiNpcLabel(0, "Child", this.guiLeft, y + 5 + i * 22, 16777215));
                    addButton(new GuiNpcButton(30, this.guiLeft + 80, y + i * 22, 50, 20, new String[]{"gui.no", "gui.yes"}, entity.isChild() ? 1 : 0));
                } else if (base.getId() == 1) {
                    byte b = ((NBTTagByte) base).func_150290_f();
                    if ((b == 0) || (b == 1)) {
                        if (this.playerdata.extra.hasKey(name))
                            b = this.playerdata.extra.getByte(name);
                        i++;
                        addLabel(new GuiNpcLabel(100 + i, name, this.guiLeft, y + 5 + i * 22, 16777215));
                        addButton(new GuiNpcButton(100 + i, this.guiLeft + 80, y + i * 22, 50, 20, new String[]{"gui.no", "gui.yes"}, b));

                        this.mapped.put(Integer.valueOf(i), name);
                    }
                }
            }
    }

    private boolean isIgnored(String tag) {
        for (String s : this.ignoredTags)
            if (s.equals(tag))
                return true;
        return false;
    }

    private NBTTagCompound getExtras(EntityLivingBase entity) {
        NBTTagCompound fake = new NBTTagCompound();
        new EntityFakeLiving(entity.worldObj).writeEntityToNBT(fake);

        NBTTagCompound compound = new NBTTagCompound();
        entity.writeEntityToNBT(compound);
        Set<String> keys = fake.func_150296_c();
        for (String name : keys) {
            compound.removeTag(name);
        }
        return compound;
    }

    private int getCurrentEntityIndex() {
        return this.list.indexOf(EntityList.classToStringMapping.get(this.playerdata.getEntityClass()));
    }

    protected void actionPerformed(GuiButton btn) {
        super.actionPerformed(btn);
        GuiNpcButton button = (GuiNpcButton) btn;
        if (button.id == 0) {
            int index = getCurrentEntityIndex();
            if (!this.prev.enabled)
                return;
            index--;
            if (index < 0) {
                this.playerdata.setEntityClass(null);
            } else
                this.playerdata.setEntityClass(this.data.get(this.list.get(index)));

            initGui();
        }
        if (button.id == 2) {
            int index = getCurrentEntityIndex();
            if (!this.next.enabled)
                return;
            index++;
            this.playerdata.setEntityClass(this.data.get(this.list.get(index)));

            initGui();
        }

        if (button.id == 1) {
            this.mc.displayGuiScreen(new GuiEntitySelection(this, this.playerdata));
        }

        if (button.id == 4) {
            this.mc.displayGuiScreen(new GuiModelHead(this));
        }
        if (button.id == 5) {
            this.mc.displayGuiScreen(new GuiModelBody(this));
        }
        if (button.id == 6) {
            this.mc.displayGuiScreen(new GuiModelArms(this));
        }
        if (button.id == 7) {
            this.mc.displayGuiScreen(new GuiModelLegs(this));
        }
        if (button.id == 8) {
            this.mc.displayGuiScreen(new GuiModelScale(this, this.playerdata));
        }
        if (button.id == 9) {
            this.playerdata.newSkinFormat = !playerdata.newSkinFormat;
            this.playerdata.reloadBoxes = true;
        }
        if (button.id == 10) {
            this.playerdata.soundType = ((short) button.getValue());
        }
        if (button.id == 30) {
            this.playerdata.extra.setInteger("Age", button.getValue() == 1 ? 41536 : 0);
            this.playerdata.clearEntity();
        }

        if (button.id == 44) {
            this.mc.displayGuiScreen(new GuiPresetSave(this, this.playerdata));
        }

        if (button.id == 45) {
            this.mc.displayGuiScreen(new GuiPresetSelection(this, this.playerdata));
        }
        if (button.id == 46) {
            List<EntityPlayer> players = this.mc.theWorld.playerEntities;
            for (EntityPlayer player : players) {
                ModelData data = PlayerDataController.instance.getPlayerData(player);
                data.loaded = false;
            }
        }

        if (button.id == 47) {
            MorePlayerModels.EnablePOV = button.getValue() == 1;
            MorePlayerModels.instance.configLoader.updateConfig();
        }
        if (button.id == 48) {
            MorePlayerModels.EnableChatBubbles = button.getValue() == 1;
            MorePlayerModels.instance.configLoader.updateConfig();
        }
        if (button.id == 49) {
            MorePlayerModels.EnableBackItem = button.getValue() == 1;
            MorePlayerModels.instance.configLoader.updateConfig();
        }
        if (button.id == 50) {
            MorePlayerModels.Tooltips = button.getValue();
            MorePlayerModels.instance.configLoader.updateConfig();
        }
        if (button.id == 51) {
            this.mc.displayGuiScreen(new GuiEditButtons(this));
        }
        if (button.id >= 100) {
            String name = this.mapped.get(button.id - 100);
            if (name != null) {
                this.playerdata.extra.setBoolean(name, button.getValue() == 1);
                this.playerdata.clearEntity();
            }
        }
    }


    public void onGuiClosed() {
        Keyboard.enableRepeatEvents(false);
    }

    public void close() {
        super.close();
        if (!this.hash.equals(this.playerdata.getHash())) {
            PlayerDataController.instance.savePlayerData(this.mc.thePlayer, this.playerdata);
            noppes.mpm.client.Client.sendData(EnumPackets.UPDATE_PLAYER_DATA, this.playerdata.writeToNBT());
        }
    }

    public void unFocused(GuiNpcTextField guiNpcTextField) {
        this.playerdata.url = guiNpcTextField.getText();
        this.playerdata.loaded = false;
    }
}
