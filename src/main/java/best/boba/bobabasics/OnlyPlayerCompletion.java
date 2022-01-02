package best.boba.bobabasics;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class OnlyPlayerCompletion implements TabCompleter {
    /**
     * TabCompleter which just does a single argument with a list of online
     * players. Meant for commands that take a single argument which is a
     * player name.
     */
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        if (args.length == 1) {
            return null;
        }

        return new ArrayList<>();
    }
}
