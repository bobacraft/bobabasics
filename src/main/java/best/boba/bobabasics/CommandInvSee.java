package best.boba.bobabasics;

import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.Server;
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

        Server server = sender.getServer();
        Player target = server.getPlayer(args[0]);
        boolean isOnline = true;
        if (target == null) {
            isOnline = false;
            OfflinePlayer offlineTarget = server.getOfflinePlayerIfCached(args[0]);
            if (offlineTarget == null) {
                sender.sendMessage(String.format(Messages.playerIsNotCached, args[0]));
                return false;
            }
            target = offlineTarget.getPlayer();
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
