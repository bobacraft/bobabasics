package best.boba.bobabasics;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class CommandWorkbenchCompletion implements TabCompleter {
    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String alias, @NotNull String[] args) {
        List<String> completion = new ArrayList<>();

        switch (args.length) {
            case 1:
                completion.add("anvil");
                completion.add("cartography_table");
                completion.add("enchanting_table");
                completion.add("grindstone");
                completion.add("loom");
                completion.add("crafting_table");
                break;
            case 2:
                return null;
        }

        return completion;
    }
}
