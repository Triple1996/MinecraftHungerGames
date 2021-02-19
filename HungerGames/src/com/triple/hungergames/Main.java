package com.triple.hungergames;


import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Main extends JavaPlugin{

	public static List<PotionEffect> starterEffects = 
			Arrays.asList(
					new PotionEffect(PotionEffectType.REGENERATION, 12000, 9), 
					new PotionEffect(PotionEffectType.SATURATION, 12000, 9));

	public final static int MAX_BUILD_HEIGHT = 256;
	private static HGUtils hgu = new HGUtils();
	
	@Override
    public void onEnable() {
    	this.getConfig().addDefault("teamsInit", false);
    	this.getConfig().addDefault("scoreBoardInit", false);
    	this.getConfig().addDefault("deathCountInit", false);
    	
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

        Player playerSender = (Player) sender;

        if (sender instanceof Player) {

            String lowerCmd = cmd.getName().toLowerCase();

            switch (lowerCmd) {
            
            case "start":
            	playerSender.sendMessage("That's the start command");
            	// TODO set world border, set world center, set border warning, 
            	// TODO spread players, clear effects, clear invents
            	// TODO set world border to shrink, set up scheduler
            	Player player;
            	List<Player> players = Bukkit.getServer().getWorld("world").getPlayers();
            	for (int i = 0; i < players.size(); i++) {
            		player = players.get(i);
            		hgu.clearEffects(player);
            		player.getInventory().clear();
            	}
            	
            	
            	return true;
            	
            case "welcome":
            	playerSender.sendMessage("Welcome " + playerSender.getName());
            	return true;

            default:

            	playerSender.sendMessage("Your command was not recognized.");
                return true;
            }
        }
        playerSender.sendMessage("Somehow you aren't a player.");
        return true;
    }

}
