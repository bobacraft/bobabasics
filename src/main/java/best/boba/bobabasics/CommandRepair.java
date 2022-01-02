package best.boba.bobabasics;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

public class CommandRepair implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(Messages.senderIsNotPlayer);
            return false;
        }

        ItemStack item = player.getInventory().getItemInMainHand();
        if (item.getType() == Material.AIR) {
            sender.sendMessage(ChatColor.RED + "You aren't holding any item in your main hand.");
            return false;
        }

        Damageable damage = (Damageable) item.getItemMeta();
        if (!damage.hasDamage()) {
            sender.sendMessage(ChatColor.RED + "The item you're holding is not damaged.");
            return false;
        }

        damage.setDamage(0);
        item.setItemMeta(damage);
        sender.sendMessage(ChatColor.GREEN + "Repaired.");
        return true;
    }
}
