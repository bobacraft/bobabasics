package best.boba.bobabasics;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandExtinguish implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length > 1) {
            sender.sendMessage(Messages.tooManyArguments);
            return false;
        }

        Server server = sender.getServer();
        Player target = null;
        if (sender instanceof Player player) {
            if (args.length < 1) {
                target = player;
            } else {
                target = server.getPlayer(args[0]);
                if (target == null) {
                    sender.sendMessage(Messages.playerIsOffline);
                    return false;
                }
            }
        } else {
            if (args.length < 1) {
                sender.sendMessage(Messages.needsToProvidePlayer);
                return false;
            }
        }

        assert target != null;
        target.setFireTicks(0);
        sender.sendMessage(ChatColor.GREEN + String.format("Extinguished %s", target.getName()));
        return true;
    }
}
