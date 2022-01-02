package best.boba.bobabasics;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.Statistic;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandRest implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length > 2) {
            sender.sendMessage(Messages.tooManyArguments);
            return false;
        }

        int newRest = 0;
        if (args.length >= 1) {
            try {
                newRest = Integer.parseInt(args[0]);
                if (newRest < 0) {
                    sender.sendMessage(ChatColor.RED + "Time since last rest cannot be negative.");
                    return false;
                }
            }
            catch (NumberFormatException e) {
                sender.sendMessage(String.format(Messages.invalidArgumentType, "int", "time", args[0]));
                return false;
            }
        }

        Server server = sender.getServer();
        Player player = (Player) sender;
        if (args.length >= 2) {
            player = server.getPlayer(args[1]);
            if (player == null) {
                sender.sendMessage(Messages.playerIsOffline);
                return false;
            }
        }
        int previousRest = player.getStatistic(Statistic.TIME_SINCE_REST);

        player.setStatistic(Statistic.TIME_SINCE_REST, newRest);
        sender.sendMessage(ChatColor.GREEN +
                           String.format("Set %s's time since rest statistic from %s to %s", player.getName(), previousRest, newRest));
        return true;
    }
}
