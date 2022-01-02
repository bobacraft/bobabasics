package best.boba.bobabasics;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandWorkbench implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(Messages.senderIsNotPlayer);
            return false;
        }

        if (args.length > 2) {
            sender.sendMessage(Messages.tooManyArguments);
            return false;
        }

        if (args.length < 1) {
            sender.sendMessage(Messages.notEnoughArguments);
            return false;
        }

        Player target = player;
        if (args.length >= 2) {
            target = Bukkit.getPlayer(args[1]);
            if (target == null) {
                sender.sendMessage(Messages.playerIsOffline);
                return false;
            }
        }

        switch (args[0]) {
            case "anvil":
                target.openAnvil(null, true);
                sender.sendMessage(ChatColor.GREEN +
                                   String.format("Opened an anvil for %s.", target.getName()));
                break;

            case "cartography_table":
                target.openCartographyTable(null, true);
                sender.sendMessage(ChatColor.GREEN +
                        String.format("Opened a cartography table for %s.", target.getName()));
                break;

            case "enchanting_table":
                target.openEnchanting(null, true);
                sender.sendMessage(ChatColor.GREEN +
                        String.format("Opened an enchanting table for %s.", target.getName()));
                break;

            case "grindstone":
                target.openGrindstone(null, true);
                sender.sendMessage(ChatColor.GREEN +
                        String.format("Opened a grindstone for %s.", target.getName()));
                break;

            case "loom":
                target.openLoom(null, true);
                sender.sendMessage(ChatColor.GREEN +
                        String.format("Opened a loom for %s.", target.getName()));
                break;

            case "crafting_table":
                target.openWorkbench(null, true);
                sender.sendMessage(ChatColor.GREEN +
                        String.format("Opened a crafting table for %s.", target.getName()));
                break;

            default:
                sender.sendMessage(ChatColor.RED + "Pick a valid menu.");
                return false;
        }

        return true;
    }
}
