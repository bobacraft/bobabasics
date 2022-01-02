package best.boba.bobabasics;

import org.bukkit.ChatColor;
import org.bukkit.Server;
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

        Server server = sender.getServer();
        Player player;
        if (args.length >= 1) {
            player = server.getPlayer(args[0]);
            if (player == null) {
                sender.sendMessage(Messages.playerIsOffline);
                return false;
            }
        } else {
            player = (Player) sender;
        }

        boolean shouldFly = !player.getAllowFlight();
        player.setAllowFlight(shouldFly);
        sender.sendMessage(ChatColor.GREEN +
                           String.format("Set %s's ability to fly to %s", player.getName(), shouldFly));
        return true;
    }
}
