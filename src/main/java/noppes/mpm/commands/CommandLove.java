package noppes.mpm.commands;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import noppes.mpm.Server;
import noppes.mpm.constants.EnumPackets;

public class CommandLove extends MpmCommandInterface {
    public String getCommandName() {
        return "love";
    }

    public void processCommand(ICommandSender icommandsender, String[] var2) {
        if (!(icommandsender instanceof EntityPlayerMP))
            return;
        EntityPlayerMP player = (EntityPlayerMP) icommandsender;
        Server.sendAssociatedData(player, EnumPackets.PARTICLE, new Object[]{Integer.valueOf(0), player.getCommandSenderName()});
    }

    public String getCommandUsage(ICommandSender icommandsender) {
        return "/love to show your love";
    }
}
