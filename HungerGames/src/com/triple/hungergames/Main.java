package com.triple.hungergames;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{

    @Override
    public void onEnable() {
        getLogger().info("HungerGames has been enabled.");
        PluginManager pm = getServer().getPluginManager();
        HGListener listener = new HGListener(this);
        pm.registerEvents(listener, this);
    }

    @Override
    public void onDisable() {
        getLogger().info("HungerGames has been disabled.");
    }    

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        Player player = (Player) sender;

        if (sender instanceof Player) {

            String lowerCmd = cmd.getName().toLowerCase();

            switch (lowerCmd) {

            case "welcome":
            	player.sendMessage("Welcome " + player.getName());
            	return true;

            default:

                player.sendMessage("Your command was not recognized.");
                return true;
            }
        }
        player.sendMessage("Somehow you aren't a player.");
        return true;
    }

}
