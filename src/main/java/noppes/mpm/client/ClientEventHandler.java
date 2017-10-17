package noppes.mpm.client;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.Phase;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.RenderTickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import noppes.mpm.ModelData;
import noppes.mpm.ModelPartData;
import noppes.mpm.MorePlayerModels;
import noppes.mpm.PlayerDataController;
import noppes.mpm.client.fx.EntityEnderFX;
import noppes.mpm.client.gui.GuiCreationScreen;
import noppes.mpm.constants.EnumAnimation;
import noppes.mpm.constants.EnumPackets;

import java.util.List;
import java.util.Random;

public class ClientEventHandler {
    public static float partialTicks = 0.0F;
    private World prevWorld;
    private List<EntityPlayer> playerlist;
    private EntityRendererAlt alt;
    private net.minecraft.client.renderer.EntityRenderer prevAlt;

    @SubscribeEvent
    public void onKey(KeyInputEvent event) {
        Minecraft mc = Minecraft.getMinecraft();
        if ((mc == null) || (mc.thePlayer == null))
            return;
        if (ClientProxy.Screen.isPressed()) {
            ModelData data = PlayerDataController.instance.getPlayerData(mc.thePlayer);
            data.animation = EnumAnimation.NONE;
            if (mc.currentScreen == null) {
                mc.displayGuiScreen(new GuiCreationScreen());
            } else if ((mc.currentScreen instanceof GuiCreationScreen))
                mc.setIngameFocus();
        }
        if (!mc.inGameHasFocus)
            return;
        if (ClientProxy.Sleep.isPressed()) {
            processAnimation(MorePlayerModels.button1);
        }
        if (ClientProxy.Sit.isPressed()) {
            processAnimation(MorePlayerModels.button2);
        }
        if (ClientProxy.Dance.isPressed()) {
            processAnimation(MorePlayerModels.button3);
        }
        if (ClientProxy.Hug.isPressed()) {
            processAnimation(MorePlayerModels.button4);
        }
        if (ClientProxy.Crawl.isPressed()) {
            processAnimation(MorePlayerModels.button5);
        }
    }

    private void processAnimation(int type) {
        if (type <= 0)
            return;
        if (MorePlayerModels.HasServerSide) {
            Client.sendData(EnumPackets.ANIMATION, new Object[]{Integer.valueOf(type)});
        } else {
            EntityPlayer player = Minecraft.getMinecraft().thePlayer;
            EnumAnimation animation = EnumAnimation.values()[type];
            if (animation == EnumAnimation.SLEEPING_SOUTH) {
                float rotation = player.rotationYaw;
                while (rotation < 0.0F)
                    rotation += 360.0F;
                while (rotation > 360.0F)
                    rotation -= 360.0F;
                int rotate = (int) ((rotation + 45.0F) / 90.0F);
                if (rotate == 1)
                    animation = EnumAnimation.SLEEPING_WEST;
                if (rotate == 2)
                    animation = EnumAnimation.SLEEPING_NORTH;
                if (rotate == 3)
                    animation = EnumAnimation.SLEEPING_EAST;
            }
            ModelData data = PlayerDataController.instance.getPlayerData(player);
            if (data.animationEquals(animation))
                animation = EnumAnimation.NONE;
            data.setAnimation(animation.ordinal());
        }
    }

    @SubscribeEvent
    public void onRenderTick(RenderTickEvent event) {
        partialTicks = event.renderTickTime;
        Minecraft mc = Minecraft.getMinecraft();
        if (MorePlayerModels.EnablePOV) {
            if (this.alt == null)
                this.alt = new EntityRendererAlt(mc);
            if (mc.entityRenderer != this.alt) {
                this.prevAlt = mc.entityRenderer;
                mc.entityRenderer = this.alt;
            }
        } else if ((this.prevAlt != null) && (mc.entityRenderer != this.prevAlt)) {
            mc.entityRenderer = this.prevAlt;
        }
    }

    @SubscribeEvent
    public void onClientTick(ClientTickEvent event) {
        Minecraft mc = Minecraft.getMinecraft();
        World world = mc.theWorld;
        if ((world != null) && (this.prevWorld != world)) {
            MorePlayerModels.HasServerSide = false;
            ModelData data = PlayerDataController.instance.getPlayerData(mc.thePlayer);
            Client.sendData(EnumPackets.PING, new Object[]{data.writeToNBT()});
            this.prevWorld = world;
        }
        if ((MorePlayerModels.HasServerSide) && (mc.thePlayer != null) && (world != null) && (world.getWorldTime() % 20L == 0L)) {
            List<EntityPlayer> list = world.getEntitiesWithinAABB(EntityPlayer.class, mc.thePlayer.boundingBox.expand(64.0D, 64.0D, 64.0D));
            for (EntityPlayer player : list)
                if ((player != mc.thePlayer) && (

                        (this.playerlist == null) || (!this.playerlist.contains(player)))) {
                    ModelData data = PlayerDataController.instance.getPlayerData(player);
                    Client.sendData(EnumPackets.REQUEST_PLAYER_DATA, new Object[]{player.getCommandSenderName(), data.getHash()});
                }
            this.playerlist = list;
        }
    }

    @SubscribeEvent
    public void onPlayerTick(PlayerTickEvent event) {
        if ((event.side == cpw.mods.fml.relauncher.Side.SERVER) || (event.phase == Phase.START))
            return;
        EntityPlayer player = event.player;
        ModelData data = PlayerDataController.instance.getPlayerData(player);
        EntityLivingBase entity = data.getEntity(player.worldObj, player);
        if (entity != null) {
            entity.posY -= player.yOffset;
            net.minecraft.entity.MPMEntityUtil.Copy(player, entity);
            entity.onUpdate();
        }
        if (data.inLove > 0) {
            data.inLove -= 1;
            if (player.getRNG().nextBoolean()) {
                String s = "heart";
                double d0 = player.getRNG().nextGaussian() * 0.02D;
                double d1 = player.getRNG().nextGaussian() * 0.02D;
                double d2 = player.getRNG().nextGaussian() * 0.02D;
                player.worldObj.spawnParticle(s, player.posX + player.getRNG().nextFloat() * player.width * 2.0F - player.width, player.posY + 0.5D + player.getRNG().nextFloat() * player.height - player.yOffset, player.posZ + player.getRNG().nextFloat() * player.width * 2.0F - player.width, d0, d1, d2);
            }
        }

        if (data.animation == EnumAnimation.CRY) {
            float f1 = player.rotationYaw * 3.1415927F / 180.0F;
            float dx = -MathHelper.sin(f1);
            float dz = MathHelper.cos(f1);
            for (int i = 0; i < 10.0F; i++) {
                float f2 = (player.getRNG().nextFloat() - 0.5F) * player.width * 0.5F + dx * 0.15F;
                float f3 = (player.getRNG().nextFloat() - 0.5F) * player.width * 0.5F + dz * 0.15F;
                player.worldObj.spawnParticle("splash", player.posX + f2, player.posY - data.getBodyY() + 1.100000023841858D - player.getYOffset(), player.posZ + f3, 1.0000000195414814E-25D, 0.0D, 1.0000000195414814E-25D);
            }
        }
        if (data.animation != EnumAnimation.NONE) {
            noppes.mpm.ServerEventHandler.checkAnimation(player, data);
        }
        ModelPartData particles = data.getPartData("particles");
        if (particles != null)
            spawnParticles(player, data, particles);
    }

    private void spawnParticles(EntityPlayer player, ModelData data, ModelPartData particles) {
        Minecraft minecraft = Minecraft.getMinecraft();
        double height = player.getYOffset() + data.getBodyY();
        Random rand = player.getRNG();
        if (particles.type == 0) {
            for (int i = 0; i < 2; i++) {
                EntityEnderFX fx = new EntityEnderFX((AbstractClientPlayer) player, (rand.nextDouble() - 0.5D) * player.width, rand.nextDouble() * player.height - height - 0.25D, (rand.nextDouble() - 0.5D) * player.width, (rand.nextDouble() - 0.5D) * 2.0D, -rand.nextDouble(), (rand.nextDouble() - 0.5D) * 2.0D, particles);
                minecraft.effectRenderer.addEffect(fx);
            }

        } else if (particles.type == 1) {
            for (int i = 0; i < 2; i++) {
                double x = player.posX + (rand.nextDouble() - 0.5D) * 0.9D;
                double y = player.posY + rand.nextDouble() * 1.9D - 0.25D - height;
                double z = player.posZ + (rand.nextDouble() - 0.5D) * 0.9D;


                double f = (rand.nextDouble() - 0.5D) * 2.0D;
                double f1 = -rand.nextDouble();
                double f2 = (rand.nextDouble() - 0.5D) * 2.0D;

                minecraft.effectRenderer.addEffect(new noppes.mpm.client.fx.EntityRainbowFX(player.worldObj, x, y, z, f, f1, f2));
            }
        }
    }
}
