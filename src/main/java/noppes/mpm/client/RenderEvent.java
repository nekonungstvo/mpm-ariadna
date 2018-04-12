package noppes.mpm.client;

import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.ReflectionHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.entity.MPMRendererHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import noppes.mpm.ModelData;
import noppes.mpm.MorePlayerModels;
import noppes.mpm.PlayerDataController;
import noppes.mpm.client.model.ModelMPM;
import noppes.mpm.constants.EnumAnimation;
import org.lwjgl.opengl.GL11;

public class RenderEvent {
    public static RenderMPM renderer = new RenderMPM();

    private ModelData data;

    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void pre(RenderPlayerEvent.Pre event) {
        EntityPlayer player = event.entityPlayer;
        this.data = PlayerDataController.instance.getPlayerData(player);
        renderer.setModelData(this.data, player);
        setModels(event.renderer);
        if (!this.data.loaded) {
            if (player.ticksExisted > 20) {
                this.data.playerResource = renderer.loadResource((AbstractClientPlayer) player);
            } else
                this.data.playerResource = ((AbstractClientPlayer) player).getLocationSkin();
        }
        if (!data.extraUrl.isEmpty() && !this.data.extraLoaded && data.loaded) {
            this.data.playerExtraTexture = renderer.loadExtraTexture((AbstractClientPlayer) player);
        }

        if (!(event.renderer instanceof RenderMPM)) {
            RenderManager.instance.entityRenderMap.put(EntityPlayer.class, renderer);
            RenderManager.instance.entityRenderMap.put(EntityPlayerSP.class, renderer);
            RenderManager.instance.entityRenderMap.put(EntityPlayerMP.class, renderer);
            RenderManager.instance.entityRenderMap.put(EntityOtherPlayerMP.class, renderer);
            RenderManager.instance.entityRenderMap.put(EntityClientPlayerMP.class, renderer);
            RenderManager.instance.entityRenderMap.put(AbstractClientPlayer.class, renderer);
        }

        EntityLivingBase entity = this.data.getEntity(player.worldObj, player);
        renderer.setEntity(entity);
        if (player == Minecraft.getMinecraft().thePlayer) {
            player.yOffset = 1.62F;
            this.data.backItem = player.inventory.mainInventory[0];
        }
    }

    private void setModels(RenderPlayer render) {
        ModelMPM playerModel = renderer.modelBipedMainNewFormat;
        if (MPMRendererHelper.getMainModel(render) == playerModel)
            return;
        ReflectionHelper.setPrivateValue(RenderPlayer.class, render, playerModel, 1);
        ReflectionHelper.setPrivateValue(RenderPlayer.class, render, renderer.modelArmorChestplate, 2);
        ReflectionHelper.setPrivateValue(RenderPlayer.class, render, renderer.modelArmor, 3);
        MPMRendererHelper.setMainModel(render, playerModel);
    }

    @SubscribeEvent
    public void special(RenderPlayerEvent.Specials.Pre event) {
        if (this.data.animation == EnumAnimation.BOW) {
            float ticks = (event.entityPlayer.ticksExisted - this.data.animationStart) / 10.0F;
            if (ticks > 1.0F)
                ticks = 1.0F;
            float scale = 2.0F - this.data.body.scaleY;
            GL11.glTranslatef(0.0F, 12.0F * scale * 0.065F, 0.0F);
            GL11.glRotatef(60.0F * ticks, 1.0F, 0.0F, 0.0F);
            GL11.glTranslatef(0.0F, -12.0F * scale * 0.065F, 0.0F);
        }
        event.renderItem = false;
        event.renderHelmet = false;
        renderer.renderItem(event.entityPlayer);
        renderer.renderHelmet(event.entityPlayer);
        if (MorePlayerModels.EnableBackItem)
            renderer.renderBackitem(event.entityPlayer);
        GL11.glTranslatef(0.0F, this.data.getBodyY(), 0.0F);
    }

    @SubscribeEvent
    public void chat(ClientChatReceivedEvent event) {
        if (MorePlayerModels.HasServerSide)
            return;
        try {
            ChatMessages.parseMessage(event.message.getFormattedText());
        } catch (Exception ex) {
            System.out.println("Cant handle chatmessage: " + event.message + ":" + ex.getMessage());
        }
    }

    @SubscribeEvent
    public void overlay(RenderGameOverlayEvent event) {
        if (event.type != RenderGameOverlayEvent.ElementType.ALL) {
            return;
        }
        Minecraft mc = Minecraft.getMinecraft();
        if ((mc.currentScreen != null) || (MorePlayerModels.Tooltips == 0))
            return;
        ItemStack item = mc.thePlayer.getCurrentEquippedItem();
        if (item == null) {
            return;
        }
        String name = item.getDisplayName();
        int x = event.resolution.getScaledWidth() - mc.fontRenderer.getStringWidth(name);

        int posX = 4;
        int posY = 4;
        if (MorePlayerModels.Tooltips % 2 == 0) {
            posX = x - 4;
        }
        if (MorePlayerModels.Tooltips > 2) {
            posY = event.resolution.getScaledHeight() - 24;
        }
        mc.fontRenderer.drawStringWithShadow(name, posX, posY, 16777215);
        if (item.isItemStackDamageable()) {
            int max = item.getMaxDamage();

            String dam = max - item.getItemDamage() + "/" + max;

            x = event.resolution.getScaledWidth() - mc.fontRenderer.getStringWidth(dam);

            if ((MorePlayerModels.Tooltips == 2) || (MorePlayerModels.Tooltips == 4)) {
                posX = x - 4;
            }
            mc.fontRenderer.drawStringWithShadow(dam, posX, posY + 12, 16777215);
        }
    }
}
