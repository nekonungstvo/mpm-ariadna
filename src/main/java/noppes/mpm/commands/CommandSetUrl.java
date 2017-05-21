package noppes.mpm.commands;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentTranslation;
import noppes.mpm.ModelData;
import noppes.mpm.PlayerDataController;
import noppes.mpm.Server;
import noppes.mpm.constants.EnumPackets;

public class CommandSetUrl
  extends MpmCommandInterface
{
  public String getCommandName()
  {
    return "seturl";
  }
  
  public void processCommand(ICommandSender icommandsender, String[] var2)
  {
    if (var2.length < 1) {
      icommandsender.addChatMessage(new ChatComponentTranslation("Not enough arguments given", new Object[0]));
      return;
    }
    EntityPlayer player = null;
    int i = 0;
    if (var2.length == 1) {
      if ((icommandsender instanceof EntityPlayer)) {
        player = (EntityPlayer)icommandsender;
      }
    } else {
      player = getPlayer(icommandsender, var2[0]);
      i = 1;
    }
    String url = var2[i];
    i++;
    for (; i < var2.length; i++) {
      url = url + " " + var2[i];
    }
    if (url.equalsIgnoreCase("clear")) {
      url = "";
    }
    ModelData data = PlayerDataController.instance.getPlayerData(player);
    data.url = url;
    Server.sendAssociatedData(player, EnumPackets.SEND_PLAYER_DATA, new Object[] { player.getCommandSenderName(), data.writeToNBT() });
  }
  
  public String getCommandUsage(ICommandSender icommandsender)
  {
    return "/seturl [@p] url (to go back to default /seturl clear)";
  }
}
