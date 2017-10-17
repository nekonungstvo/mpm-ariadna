package noppes.mpm.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;

public abstract class MpmCommandInterface extends CommandBase {
    public boolean canCommandSenderUseCommand(ICommandSender par1ICommandSender) {
        return true;
    }

    public int getRequiredPermissionLevel() {
        return 0;
    }

    public int compareTo(Object o) {
        return 0;
    }
}
