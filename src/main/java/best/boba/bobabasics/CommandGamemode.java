package best.boba.bobabasics;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.jetbrains.annotations.NotNull;

public class CommandGamemode implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length > 1) {
            sender.sendMessage(Messages.tooManyArguments);
            return false;
        }

        GameMode gamemode = null;
        Permission permission = null;
        switch (label) {
            case "gmsp":
                gamemode = GameMode.SPECTATOR;
                permission = new Permission("bobabasics.gamemode.spectator");
                break;

            case "gmc":
                gamemode = GameMode.CREATIVE;
                permission = new Permission("bobabasics.gamemode.creative");
                break;

            case "gma":
                gamemode = GameMode.ADVENTURE;
                permission = new Permission("bobabasics.gamemode.adventure");
                break;

            case "gms":
                gamemode = GameMode.SURVIVAL;
                permission = new Permission("bobabasics.gamemode.survival");
                break;

            case "gm":
                sender.sendMessage(ChatColor.RED + "Please use one of the gamemode specific commands:");
                sender.sendMessage(ChatColor.RED + "/(gmc|gms|gma|gmsp)");
                return false;
        }

        Server server = sender.getServer();
        Player target;
        if (args.length >= 1) {
            target = server.getPlayer(args[0]);
            if (target == null) {
                sender.sendMessage(Messages.playerIsOffline);
                return false;
            }
        } else if (sender instanceof Player player) {
            target = player;
        } else {
            sender.sendMessage(Messages.needsToProvidePlayer);
            return false;
        }

        if (!sender.hasPermission(permission)) {
            sender.sendMessage(Messages.noPermission);
            return false;
        }

        target.setGameMode(gamemode);
        sender.sendMessage(ChatColor.GREEN +
                           String.format("Changed %s's gamemode to %s", target.getName(), gamemode));
        return true;
    }
}
