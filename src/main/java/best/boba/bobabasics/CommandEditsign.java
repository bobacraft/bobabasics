package best.boba.bobabasics;

import org.bukkit.ChatColor;
import org.bukkit.FluidCollisionMode;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.RayTraceResult;
import org.jetbrains.annotations.NotNull;

public class CommandEditsign implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(Messages.senderIsNotPlayer);
            return false;
        }

        RayTraceResult trace = player.rayTraceBlocks(16, FluidCollisionMode.NEVER);
        if (trace == null) {
            sender.sendMessage(ChatColor.RED + "You aren't looking at a block.");
            return false;
        }
        Block traceBlock = trace.getHitBlock();
        assert traceBlock != null;
        BlockState traceBlockState = traceBlock.getState();

        if (!(traceBlockState instanceof Sign sign)) {
            sender.sendMessage(ChatColor.RED + "You aren't looking at a sign.");
            return false;
        }

        player.openSign(sign);
        sender.sendMessage(ChatColor.GREEN + "Editing the sign you were looking at.");
        return true;
    }
}
