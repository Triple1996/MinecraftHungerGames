package com.triple.hungergames;

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


public class HGListener implements Listener{

	private HGHelper hgh = new HGHelper();
	private ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
	private World world = Bukkit.getWorld("world");
	private WorldBorder border = world.getWorldBorder();

	final static int MAX_BUILD_HEIGHT = 256;
	
    // Constructor
    public HGListener(Main plugin) {

    }

    @EventHandler
    public void OnPlayerJoin(PlayerJoinEvent event) {

        Player player = (Player) event.getPlayer();
        player.sendMessage("Welcome to the server " + player.getName());
        // TODO Initialize player's death count and kills to 0

    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {

        Location location = event.getPlayer().getLocation();
        if ( (Math.abs( location.getX() ) > 16) || (Math.abs( location.getZ() ) > 16) ) {
        	Location spawn = new Location(Bukkit.getWorld("world"), 0, hgh.getYValOfSurface(0,0), 0);
        	event.getPlayer().teleport(spawn);
        }
    }
    
    @EventHandler
    public void onLoad(ServerLoadEvent event) {
    	
    	border.setCenter(0, 0);
    	border.setSize(150);
    	border.setWarningDistance(0);
    	Bukkit.dispatchCommand(console, "spreadplayers 0 0 0 1 true @a");
    	hgh.initSpawnArea();
    	hgh.tellConsole(console, "initialized spawn area");
    }
    
    @EventHandler
    public void onWorldInit(WorldInitEvent event) {
    	// TODO initteams, initscoreboard, initdeathcount, initspawnplatform

    	border.setCenter(0, 0);
    	world.setSpawnLocation(0, (int)world.getSpawnLocation().getY(), 0);
    	border.setCenter(0, 0);
    	border.setSize(150);
    	border.setWarningDistance(0);
    	hgh.tellConsole(console, "initialized world border");
    	
    }
}


