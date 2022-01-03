package best.boba.bobabasics;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandFly implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length > 1) {
            sender.sendMessage(Messages.tooManyArguments);
            return false;
        }

        Player target;
        if (args.length >= 1) {
            target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                sender.sendMessage(Messages.playerIsOffline);
                return false;
            }
        } else {
            if (sender instanceof Player) {
                target = (Player) sender;
            } else {
                sender.sendMessage(Messages.needsToProvidePlayer);
                return false;
            }
        }

        boolean shouldFly = !target.getAllowFlight();
        target.setAllowFlight(shouldFly);
        sender.sendMessage(ChatColor.GREEN +
                           String.format("Set %s's ability to fly to %s", target.getName(), shouldFly));
        return true;
    }
}
