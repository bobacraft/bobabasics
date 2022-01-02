package best.boba.bobabasics;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class CommandSpeedCompletion implements TabCompleter {
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        List<String> completion = new ArrayList<>();

        switch (args.length) {
            case 1:
                completion.add("<speed>");
                completion.add("reset");
                break;
            case 2:
                completion.add("fly");
                completion.add("walk");
                completion.add("*");
                break;
            case 3:
                return null; // use player names for completion
        }

        return completion;
    }
}
