package best.boba.bobabasics;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

public class CommandInvSee implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(Messages.senderIsNotPlayer);
            return false;
        }

        if (args.length > 1) {
            sender.sendMessage(Messages.tooManyArguments);
            return false;
        }
        if (args.length < 1) {
            sender.sendMessage(Messages.notEnoughArguments);
            return false;
        }

        boolean isOnline = true;
        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            isOnline = false;
            OfflinePlayer offlineTarget = Bukkit.getOfflinePlayerIfCached(args[0]);
            if (offlineTarget == null) {
                sender.sendMessage(ChatColor.RED + String.format(Messages.playerIsNotCached, args[0]));
                return false;
            }
            target = offlineTarget.getPlayer();
            assert target != null;
        }

        Inventory inv = target.getInventory();
        player.openInventory(inv);
        if (isOnline) {
            sender.sendMessage(ChatColor.GREEN + String.format("Opened %s's inventory.", player.getName()));
        } else {
            sender.sendMessage(ChatColor.GREEN + String.format("Opened %s's offline inventory.", player.getName()));
        }
        return true;
    }
}
