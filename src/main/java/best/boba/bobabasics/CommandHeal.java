package best.boba.bobabasics;

import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandHeal implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length > 2) {
            sender.sendMessage(Messages.tooManyArguments);
            return false;
        }

        if (args.length < 1) {
            sender.sendMessage(Messages.notEnoughArguments);
            return false;
        }

        boolean setHealth = false;
        boolean setHunger = false;
        switch (args[0]) {
            case "health" -> setHealth = true;
            case "hunger" -> setHunger = true;
            case "*" -> {
                setHealth = true;
                setHunger = true;
            }
            default -> {
                sender.sendMessage(String.format(Messages.invalidArgument, "type", args[0]));
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

        if (setHealth) {
            double previousHealth = player.getHealth();
            player.setHealth(20);
            sender.sendMessage(ChatColor.GREEN +
                               String.format("Set %s's health from %s to %s.", player.getName(), previousHealth, 20));
        }
        if (setHunger) {
            int previousHunger = player.getFoodLevel();
            player.setFoodLevel(20);
            sender.sendMessage(ChatColor.GREEN +
                    String.format("Set %s's hunger from %s to %s.", player.getName(), previousHunger, 20));
        }

        return true;
    }
}
