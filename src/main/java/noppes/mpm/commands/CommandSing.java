package noppes.mpm.commands;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import noppes.mpm.constants.EnumPackets;

public class CommandSing extends MpmCommandInterface {
    public String getCommandName() {
        return "sing";
    }

    public void processCommand(ICommandSender icommandsender, String[] var2) {
        if (!(icommandsender instanceof EntityPlayerMP))
            return;
        EntityPlayerMP player = (EntityPlayerMP) icommandsender;

        int note = player.getRNG().nextInt(25);
        if (var2.length > 0) {
            try {
                int n = Integer.parseInt(var2[0]);
                if ((n >= 0) && (n < 25)) {
                    note = n;
                }
            } catch (NumberFormatException ex) {
            }
        }
        float var7 = (float) Math.pow(2.0D, (note - 12) / 12.0D);
        player.worldObj.playSoundEffect(player.posX, player.posY, player.posZ, "note.harp", 3.0F, var7);

        noppes.mpm.Server.sendAssociatedData(player, EnumPackets.PARTICLE, new Object[]{Integer.valueOf(1), Double.valueOf(player.posX), Double.valueOf(player.posY + 2.0D), Double.valueOf(player.posZ), Double.valueOf(note / 24.0D)});
    }


    public String getCommandUsage(ICommandSender icommandsender) {
        return "/sing [0-24] to sing";
    }
}
