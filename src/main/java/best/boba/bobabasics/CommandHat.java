package best.boba.bobabasics;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.jetbrains.annotations.NotNull;

public class CommandHat implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(Messages.senderIsNotPlayer);
            return false;
        }

        if (args.length > 0) {
            sender.sendMessage(Messages.tooManyArguments);
            return false;
        }

        PlayerInventory inventory = player.getInventory();
        ItemStack originalHat = inventory.getHelmet();
        ItemStack newHat = inventory.getItemInMainHand();

        // swap the main hand and helmet slot
        inventory.setHelmet(newHat);
        inventory.setItemInMainHand(originalHat);
        sender.sendMessage(ChatColor.GREEN + "Hat updated.");
        return true;
    }
}
