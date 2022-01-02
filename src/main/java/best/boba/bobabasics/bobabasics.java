package best.boba.bobabasics;

import org.bukkit.plugin.java.JavaPlugin;

public class bobabasics extends JavaPlugin {
    @Override
    public void onEnable() {
        this.getCommand("echo").setExecutor(new CommandEcho());
        this.getCommand("echo").setTabCompleter(new BlankCompletion());

        this.getCommand("editsign").setExecutor(new CommandEditsign());
        this.getCommand("editsign").setTabCompleter(new BlankCompletion());

        this.getCommand("enderchest").setExecutor(new CommandEnderchest());
        this.getCommand("enderchest").setTabCompleter(new OnlyPlayerCompletion());

        this.getCommand("extinguish").setExecutor(new CommandExtinguish());
        this.getCommand("extinguish").setTabCompleter(new OnlyPlayerCompletion());

        this.getCommand("fly").setExecutor(new CommandFly());
        this.getCommand("fly").setTabCompleter(new OnlyPlayerCompletion());

        this.getCommand("gm").setExecutor(new CommandGamemode());
        this.getCommand("gm").setTabCompleter(new OnlyPlayerCompletion());

        this.getCommand("hat").setExecutor(new CommandHat());
        this.getCommand("hat").setTabCompleter(new BlankCompletion());

        this.getCommand("invsee").setExecutor(new CommandInvSee());
        this.getCommand("invsee").setTabCompleter(new OnlyPlayerCompletion());

        this.getCommand("repair").setExecutor(new CommandRepair());
        this.getCommand("repair").setTabCompleter(new BlankCompletion());

        this.getCommand("speed").setExecutor(new CommandSpeed());
        this.getCommand("speed").setTabCompleter(new CommandSpeedCompletion());

        this.getCommand("tpall").setExecutor(new CommandTpAll());
        this.getCommand("tpall").setTabCompleter(new OnlyPlayerCompletion());

        this.getCommand("workbench").setExecutor(new CommandWorkbench());
        this.getCommand("workbench").setTabCompleter(new CommandWorkbenchCompletion());
    }

    @Override
    public void onDisable() {
    }
}
