package com.triple.hungergames;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Main extends JavaPlugin{
	
	public static List<PotionEffect> starterEffects = new ArrayList<PotionEffect>();
	public final static int MAX_BUILD_HEIGHT = 256;
    @Override
    public void onEnable() {
    	this.getConfig().addDefault("teamsInit", false);
    	this.getConfig().addDefault("scoreBoardInit", false);
    	this.getConfig().addDefault("deathCountInit", false);
    	
    	starterEffects.add(new PotionEffect(PotionEffectType.REGENERATION, 10000, 10));
        starterEffects.add(new PotionEffect(PotionEffectType.SATURATION, 10000, 10));
        
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
