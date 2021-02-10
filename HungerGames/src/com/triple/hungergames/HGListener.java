package com.triple.hungergames;

import java.lang.Math;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;

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
    public void onPlayerDamage(PlayerMoveEvent event) {
        
        Location location = event.getPlayer().getLocation();
        event.getPlayer().sendMessage("You are moving");
                
        if ( (Math.abs( location.getX() ) > 16) || (Math.abs( location.getZ() ) > 16) ) {
        	Location spawn = new Location(Bukkit.getServer().getWorld("world"), 0, 62, 0);
        	event.getPlayer().teleport(spawn);
        	// TODO Remove hard-coded y=62. This code is for demonstration purposes only
        	
        }
        
    }
}
