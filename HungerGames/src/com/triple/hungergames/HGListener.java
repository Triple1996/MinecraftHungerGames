package com.triple.hungergames;

import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
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
    	if (!Main.getGameStarted()) {
	        hgu.welcomePlayer(console, player);;
	        scoreboard.getObjective("kills").getScore(player.getName()).setScore(0);
	        player.addPotionEffects(Main.starterEffects);
	        Main.addPlayerToGame(player.getName());
	        
    	} else if (!Main.playerInGame(player.getName())){
    		
    		player.setGameMode(GameMode.SPECTATOR);
    		scoreboard.getTeam("Red").addEntry(player.getName());
    		hgu.displayTitle(console,  player.getName(), "Game is in progress", "gold", "You are now spectating.", "gold");
    	}
        
    }
    
    @EventHandler
    public void onDeath(EntityDeathEvent event) {
    	
    	if (event.getEntity() instanceof Player) {
    		Player player = (Player) event.getEntity();
    		player.setGameMode(GameMode.SPECTATOR);
    		scoreboard.getTeam("Red").addEntry(player.getName());
    		
    		hgu.displayTitle(console,  player.getName(), "Wasted", "dark_red", "You are now spectating.", "gold");
    		
    		Main.removePlayerFromGame(player.getName());
    		
    		// TODO This should work, needs to be tested
    		if (Main.playersInGame() == 1) {
    			String winner = Main.getPlayerFromList(0);
    			hgu.displayTitle(console, winner, "Congratulations", "green", "You are the champion!", "green");
    		}
    		
    	}
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
    	
    	Bukkit.dispatchCommand(console, "gamerule sendCommandFeedback false");
    	world.setSpawnLocation(0, hgu.getYValOfSurface(world, 0, 0), 0);
    	hgu.initWorldBorder(console, border);
    	hgu.initSpawnArea(console, world);

    	if (scoreboard.getTeam("Red") == null) {
    		hgu.initTeams(console, scoreboard);
    	}

    	if (scoreboard.getObjective("kills") == null) {
    		hgu.initScoreBoard(console, scoreboard);
    	}

    	Bukkit.dispatchCommand(console, "spreadplayers 0 0 0 1 true @a");
    	
    	Player player;
    	List<Player> players = Bukkit.getServer().getWorld("world").getPlayers();
    	for (int i = 0; i < players.size(); i++) {
    		player = players.get(i);
    		hgu.welcomePlayer(console, player);
    		player.addPotionEffects(Main.starterEffects);
    		player.setGameMode(GameMode.ADVENTURE);
    	}
    	
    	Main.setGameStarted(false);
    	
    }
    
    @EventHandler
    public void onWorldInit(WorldInitEvent event) {
    	
    	world.setSpawnLocation(0, hgu.getYValOfSurface(world, 0, 0), 0);
    	hgu.initWorldBorder(console, border);
    }
}


