package best.boba.bobabasics;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public class CommandTpAll implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player) && (args.length < 1)) {
            sender.sendMessage(Messages.needsToProvidePlayer);
            return false;
        }

        if (args.length > 1) {
            sender.sendMessage(Messages.tooManyArguments);
            return false;
        }

        Player target;
        Server server = sender.getServer();
        if (args.length == 0) {
            target = (Player) sender;
        } else {
            target = server.getPlayer(args[0]);
            if (target == null) {
                sender.sendMessage(Messages.playerIsOffline);
                return false;
            }
        }

        Collection<? extends Player> allPlayers = server.getOnlinePlayers();
        for (Player p : allPlayers) {
            p.teleport(target);
        }
        sender.sendMessage(ChatColor.GREEN +
                           String.format("Teleported %d players to %s", allPlayers.size(), target.getName()));
        return true;
    }
}
