package com.triple.hungergames;

import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.server.ServerLoadEvent;
import org.bukkit.event.world.WorldInitEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.Scoreboard;


public class HGListener implements Listener{

	private HGUtils hgu = new HGUtils();
	private ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
	private World world = Bukkit.getWorld("world");
	private WorldBorder border = world.getWorldBorder();
	private Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();

	Plugin plugin;
	
    // Constructor
    public HGListener(Main plugin) {
    	
    	this.plugin = plugin;
    }

    @EventHandler
    public void OnPlayerJoin(PlayerJoinEvent event) {

        Player player = (Player) event.getPlayer();
        hgu.welcomePlayer(console, player);;
        scoreboard.getObjective("kills").getScore(player.getName()).setScore(0); 
        scoreboard.getObjective("deaths").getScore(player.getName()).setScore(0); 
        player.addPotionEffects(Main.starterEffects);
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
    	
    	if (!Main.getGameStarted()) {
	        Location playerLoc = event.getPlayer().getLocation();
	        if ( (Math.abs( playerLoc.getX() ) > 16) || (Math.abs( playerLoc.getZ() ) > 16) ) {
	        	Location spawn = new Location(Bukkit.getWorld("world"), 0, hgu.getYValOfSurface(world, 0,0), 0);
	        	event.getPlayer().teleport(spawn);
	        }
    	}
    }
    
    @EventHandler
    public void onLoad(ServerLoadEvent event) {

    	hgu.initWorldBorder(console, border);
    	hgu.initSpawnArea(console, world);
    	
    	    	
    	// If you wanted to create a new world, need to clear the config file
    	if (!plugin.getConfig().getBoolean("teamsInit")){
    		hgu.initTeams(console, scoreboard);
    		hgu.setAndSaveConfig(plugin, "teamsInit", true);
    	}
    	if (!plugin.getConfig().getBoolean("scoreBoardInit")) {
    		hgu.initScoreBoard(console, scoreboard);
    		hgu.setAndSaveConfig(plugin, "scoreBoardInit", true);
    	}
    	if (!plugin.getConfig().getBoolean("deathCountInit")) {
    		hgu.initDeathCount(console, scoreboard);
    		hgu.setAndSaveConfig(plugin, "deathCountInit", true);
    	}
    	
    	Bukkit.dispatchCommand(console, "spreadplayers 0 0 0 1 true @a");
    	
    	Player player;
    	List<Player> players = Bukkit.getServer().getWorld("world").getPlayers();
    	for (int i = 0; i < players.size(); i++) {
    		player = players.get(i);
    		hgu.welcomePlayer(console, player);
    		player.addPotionEffects(Main.starterEffects);
    	}
    	
    	Main.setGameStarted(false);
    	
    }
    
    @EventHandler
    public void onWorldInit(WorldInitEvent event) {
    	
       
    	world.setSpawnLocation(0, hgu.getYValOfSurface(world, 0, 0), 0);
    	hgu.initWorldBorder(console, border);
    }
}


