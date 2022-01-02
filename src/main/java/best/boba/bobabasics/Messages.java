package best.boba.bobabasics;

import org.bukkit.ChatColor;

public class Messages {
    public static final String senderIsNotPlayer = ChatColor.RED + "This command can only be used by a player.";
    public static final String needsToProvidePlayer = ChatColor.RED + "This command requires any non-players to provide a target player.";
    public static final String playerIsOffline = ChatColor.RED + "The target player is offline.";
    public static final String playerIsNotCached = ChatColor.RED + "Could not find a player named %s; have they logged on before?";
    public static final String notEnoughArguments = ChatColor.RED + "Not enough arguments.";
    public static final String tooManyArguments = ChatColor.RED + "Too many arguments.";
    public static final String invalidArgumentType = ChatColor.RED + "Invalid %s for argument named %s: %s";
    public static final String noPermission = ChatColor.RED + "You do not have permission to run this command.";
}
