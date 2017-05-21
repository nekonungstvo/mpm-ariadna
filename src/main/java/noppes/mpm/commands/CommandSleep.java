package noppes.mpm.commands;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import noppes.mpm.ModelData;
import noppes.mpm.PlayerDataController;
import noppes.mpm.Server;
import noppes.mpm.constants.EnumAnimation;
import noppes.mpm.constants.EnumPackets;

public class CommandSleep extends MpmCommandInterface
{
  public String getCommandName()
  {
    return "sleep";
  }
  
  public void processCommand(ICommandSender icommandsender, String[] var2)
  {
    if (!(icommandsender instanceof EntityPlayerMP))
      return;
    EntityPlayerMP player = (EntityPlayerMP)icommandsender;
    ModelData data = PlayerDataController.instance.getPlayerData(player);
    
    float rotation = player.rotationYaw;
    while (rotation < 0.0F)
      rotation += 360.0F;
    while (rotation > 360.0F) {
      rotation -= 360.0F;
    }
    int rotate = (int)((rotation + 45.0F) / 90.0F);
    EnumAnimation animation = EnumAnimation.SLEEPING_SOUTH;
    if (rotate == 1)
      animation = EnumAnimation.SLEEPING_WEST;
    if (rotate == 2)
      animation = EnumAnimation.SLEEPING_NORTH;
    if (rotate == 3) {
      animation = EnumAnimation.SLEEPING_EAST;
    }
    if ((data.animation == EnumAnimation.SLEEPING_EAST) || (data.animation == EnumAnimation.SLEEPING_NORTH) || (data.animation == EnumAnimation.SLEEPING_WEST) || (data.animation == EnumAnimation.SLEEPING_SOUTH)) {
      animation = EnumAnimation.NONE;
    }
    Server.sendAssociatedData(player, EnumPackets.ANIMATION, new Object[] { player.getCommandSenderName(), animation });
    data.animation = animation;
  }
  
  public String getCommandUsage(ICommandSender icommandsender)
  {
    return "/sleep to lie down";
  }
}
