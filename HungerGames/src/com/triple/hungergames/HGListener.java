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
	final static int MAX_BUILD_HEIGHT = 256;
	public static boolean teamsInit = false;
	public boolean scoreBoardInit = false;
	Plugin plugin;
    // Constructor
    public HGListener(Main plugin) {
    	this.plugin = plugin;
    }

    @EventHandler
    public void OnPlayerJoin(PlayerJoinEvent event) {

        Player player = (Player) event.getPlayer();
        player.sendMessage("Welcome to Minecraft Hunger Games!");
        
        // TODO Initialize player's death count and kills to 0
        //scoreboard.getObjective("Kills").getScore(player).setScore(0); // This is deprecated. Also it doesn't work
        //scoreboard.getObjective("Kills").getScore("kills").setScore(0); 
        //scoreboard.getObjective("Kills").getScore(player.getName()).setScore(0); 
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {

        Location location = event.getPlayer().getLocation();
        if ( (Math.abs( location.getX() ) > 16) || (Math.abs( location.getZ() ) > 16) ) {
        	Location spawn = new Location(Bukkit.getWorld("world"), 0, hgu.getYValOfSurface(0,0), 0);
        	event.getPlayer().teleport(spawn);
        }
    }
    
    @EventHandler
    public void onLoad(ServerLoadEvent event) {
    	
    	border.setCenter(0, 0);
    	border.setSize(150);
    	border.setWarningDistance(0);
    	Bukkit.dispatchCommand(console, "spreadplayers 0 0 0 1 true @a");
    	hgu.initSpawnArea();
    	hgu.tellConsole(console, "initialized spawn area");
    	List<Player> players = Bukkit.getServer().getWorld("world").getPlayers();
    	for (int i = 0; i < players.size(); i++) {
    		Player player = players.get(i);
    		player.sendMessage("Welcome to Minecraft Hunger Games!");
    	}
    	
    	// TODO Reset values to false when world is remade
    	if (!plugin.getConfig().getBoolean("teamsInit")){
    		hgu.initTeams(scoreboard);
    		hgu.tellConsole(console, "initialized teams");
    		plugin.getConfig().set("teamsInit", true);
    		plugin.getConfig().options().copyDefaults(true);
    		plugin.saveConfig();
    	}
    	if (!plugin.getConfig().getBoolean("scoreBoardInit")) {
    		hgu.initScoreBoard(scoreboard);
    		hgu.tellConsole(console, "initialized scoreboard");
    		plugin.getConfig().set("scoreBoardInit", true);
    		plugin.getConfig().options().copyDefaults(true);
    		plugin.saveConfig();
    	}
    }
    
    @EventHandler
    public void onWorldInit(WorldInitEvent event) {
    	// TODO initdeathcount,

    	border.setCenter(0, 0);
    	world.setSpawnLocation(0, hgu.getYValOfSurface(0, 0), 0);
    	border.setCenter(0, 0);
    	border.setSize(150);
    	border.setWarningDistance(0);
    	hgu.tellConsole(console, "initialized world border");
    	
    	
    	
    }
}


