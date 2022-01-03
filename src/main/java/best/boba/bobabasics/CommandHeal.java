package best.boba.bobabasics;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
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

        Player target;
        if (args.length >= 2) {
            target = Bukkit.getPlayer(args[1]);
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

        if (setHealth) {
            double previousHealth = target.getHealth();
            target.setHealth(20d);
            sender.sendMessage(ChatColor.GREEN +
                               String.format("Set %s's health from %s to %s.", target.getName(), previousHealth, 20d));
        }
        if (setHunger) {
            int previousHunger = target.getFoodLevel();
            target.setFoodLevel(20);
            sender.sendMessage(ChatColor.GREEN +
                    String.format("Set %s's hunger from %s to %s.", target.getName(), previousHunger, 20));
        }

        return true;
    }
}
