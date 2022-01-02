package best.boba.bobabasics;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class CommandEcho implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length <= 0) {
            sender.sendMessage(ChatColor.RED + "You didn't give any arguments, dummy.");
            return false;
        }

        sender.sendMessage("ECHO!!");
        for (int i = 0; i < args.length; i++) {
            sender.sendMessage(String.format("args[%d] = %s", i, args[i]));
        }
        return true;
    }
}
