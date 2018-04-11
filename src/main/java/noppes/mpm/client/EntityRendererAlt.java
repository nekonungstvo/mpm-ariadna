package noppes.mpm.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;
import noppes.mpm.ModelData;
import noppes.mpm.PlayerDataController;
import noppes.mpm.constants.EnumAnimation;

public class EntityRendererAlt extends EntityRenderer {
    public EntityRendererAlt(Minecraft par1Minecraft) {
        super(par1Minecraft, par1Minecraft.getResourceManager());
    }

    public void updateCameraAndRender(float par1) {
        EntityPlayer player = Minecraft.getMinecraft().thePlayer;

        if ((player == null) || (player.isPlayerSleeping())) {
            super.updateCameraAndRender(par1);
            return;
        }
        ModelData data = PlayerDataController.instance.getPlayerData(player);
        player.yOffset -= data.offsetY();

        if (data.animation == EnumAnimation.SITTING)
            player.yOffset += 0.5F - data.getLegsY();

        if (data.animation == EnumAnimation.CRAWLING)
            player.yOffset = 2.8F;

        if (data.isSleeping())
            player.yOffset = 2.8F;

        if ((player.yOffset < 1.4F) && (isBlocked(player)))
            player.yOffset = 1.4F;

        super.updateCameraAndRender(par1);
        player.yOffset = 1.62F;
    }

    private boolean isBlocked(EntityPlayer player) {
        int x = MathHelper.floor_double(player.posX);
        int y = MathHelper.floor_double(player.posY) + 1;
        int z = MathHelper.floor_double(player.posZ);
        return !player.worldObj.isAirBlock(x, y, z);
    }

    public void getMouseOver(float par1) {
        EntityPlayer player = Minecraft.getMinecraft().thePlayer;
        if ((player == null) || (player.isPlayerSleeping())) {
            super.getMouseOver(par1);
            return;
        }
        ModelData data = PlayerDataController.instance.getPlayerData(player);

        float offset = -data.offsetY();
        if (data.animation == EnumAnimation.SITTING) {
            offset += 0.5F - data.getLegsY();
        }
        if (data.isSleeping())
            offset = 1.18F;
        if ((offset < -0.2F) && (isBlocked(player)))
            offset = -0.2F;
        player.posY += -offset;
        player.prevPosY += -offset;
        player.lastTickPosY += -offset;
        super.getMouseOver(par1);
        player.posY -= -offset;
        player.prevPosY -= -offset;
        player.lastTickPosY -= -offset;
    }
}
