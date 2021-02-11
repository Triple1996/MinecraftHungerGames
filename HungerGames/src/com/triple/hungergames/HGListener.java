package com.triple.hungergames;

import java.lang.Math;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.WorldBorder;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.server.ServerLoadEvent;

public class HGListener implements Listener{

    // Constructor
    public HGListener(Main plugin) {

    }

    @EventHandler
    public void OnPlayerJoin(PlayerJoinEvent event) {

        Player player = (Player) event.getPlayer();

        player.sendMessage("Welcome to the server " + player.getName());

    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {

        Location location = event.getPlayer().getLocation();
        HGHelper hgh = new HGHelper();
        if ( (Math.abs( location.getX() ) > 16) || (Math.abs( location.getZ() ) > 16) ) {
        	Location spawn = new Location(Bukkit.getWorld("world"), 0, hgh.getYValOfSurface(0,0), 0);
        	event.getPlayer().teleport(spawn);
        }
    }
    
    @EventHandler
    public void onLoad(ServerLoadEvent event) {

    	WorldBorder border = Bukkit.getWorld("world").getWorldBorder();
    	ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
    	border.setCenter(0, 0);
    	border.setSize(150);

    	Bukkit.dispatchCommand(console, "spreadplayers 0 0 0 1 true @a");

    	
    }
}


