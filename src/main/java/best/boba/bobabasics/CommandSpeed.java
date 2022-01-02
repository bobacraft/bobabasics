package best.boba.bobabasics;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CommandSpeed implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player) && (args.length < 3)) {
            sender.sendMessage(Messages.needsToProvidePlayer);
            return false;
        }

        // Immediately error if there's too many arguments
        if (args.length > 3) {
            sender.sendMessage(Messages.tooManyArguments);
            return false;
        }

        // The player needs to provide a speed value (first argument)
        if (args.length < 1) {
            sender.sendMessage(Messages.notEnoughArguments);
            return false;
        }

        float speed = 0;
        boolean defaultSpeed = false;
        if (args[0].equals("reset")) {
            defaultSpeed = true;
        } else {
            try {
                speed = Float.parseFloat(args[0]);
            }
            catch (NumberFormatException e) {
                sender.sendMessage(String.format(Messages.invalidArgumentType, "float", "speed", args[0]));
                return false;
            }

            if ((speed < -1) || (speed > 1)) { // if it's out of the range -1 to 1
                sender.sendMessage(ChatColor.RED + "Speed must be between -1 and 1.");
                return false;
            }
        }

        // Figure out the target player
        Player player = (Player) sender;
        if (args.length >= 3) {
            Player targetPlayer = sender.getServer().getPlayer(args[2]);
            if (targetPlayer == null) {
                sender.sendMessage(Messages.playerIsOffline);
                return false;
            }
            player = targetPlayer;
        }

        // Figure out whether to set flight or walking speed
        // Use 2nd argument, or fallback to if the player is actively flying or not
        // This is processed after the target player since it depends on it
        boolean setFly = false;
        boolean setWalk = false;
        if (args.length >= 2) {
            switch (args[1]) {
                case "fly" -> setFly = true;
                case "walk" -> setWalk = true;
                case "*" -> {
                    setFly = true;
                    setWalk = true;
                }
                default -> {
                    sender.sendMessage(ChatColor.RED + "You must choose between fly or walk speed.");
                    return false;
                }
            }
        } else {
            if (player.isFlying()) {
                setFly = true;
            } else {
                setWalk = true;
            }
        }

        // Actually set the speed
        if (setFly) {
            float previousSpeed = player.getFlySpeed();
            String speedName = "flight";
            if (defaultSpeed) {
                speed = 0.1f;
            }
            player.setFlySpeed(speed);
            sender.sendMessage(ChatColor.GREEN +
                    String.format("Set %s's %s speed from %s to %s",
                            player.getName(), speedName, previousSpeed, speed));
        }
        if (setWalk) {
            float previousSpeed = player.getWalkSpeed();
            String speedName = "walking";
            if (defaultSpeed) {
                speed = 0.2f;
            }
            player.setWalkSpeed(speed);
            sender.sendMessage(ChatColor.GREEN +
                    String.format("Set %s's %s speed from %s to %s",
                            player.getName(), speedName, previousSpeed, speed));
        }
        return true;

    }
}
