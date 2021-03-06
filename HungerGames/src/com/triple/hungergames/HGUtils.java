package com.triple.hungergames;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.block.Block;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.RenderType;
import org.bukkit.scoreboard.Scoreboard;

import net.md_5.bungee.api.chat.ComponentBuilder;

public class HGUtils {
	
	public void clearEffects(Player player) {
		List<PotionEffect> activeEffects = (List<PotionEffect>) player.getActivePotionEffects();
		for (PotionEffect effect : activeEffects) {
			player.removePotionEffect(effect.getType());
		}
	}
	
	public void displayTitle(ConsoleCommandSender console, String playerName, String title, String tColor, String subtitle, String sColor) {
		Bukkit.dispatchCommand(console, "title " + playerName + " subtitle {\"text\":\"" + subtitle + "\",\"color\":\"" + sColor + "\"}");
		Bukkit.dispatchCommand(console, "title "+ playerName + " title {\"text\":\"" + title + "\",\"color\":\"" + tColor + "\"}");
	}
	
	public void displayTitle(ConsoleCommandSender console, String playerName, String title, String tColor) {
		Bukkit.dispatchCommand(console, "title "+ playerName + " title {\"text\":\"" + title + "\",\"color\":\"" + tColor + "\"}");
	}
	
	public void welcomePlayer(ConsoleCommandSender console, Player player) {

		player.spigot().sendMessage(
				new ComponentBuilder("Welcome to Minecraft Hunger Games!")
				.color(net.md_5.bungee.api.ChatColor.YELLOW)
				.create());
		player.spigot().sendMessage(
				new ComponentBuilder("Your objective is simply to eliminate the other teams.")
				.color(net.md_5.bungee.api.ChatColor.YELLOW)
				.create());
		player.spigot().sendMessage(
				new ComponentBuilder("Please be patient while the game is about to begin.")
				.color(net.md_5.bungee.api.ChatColor.YELLOW)
				.create());
	}

	public void initSpawnArea(ConsoleCommandSender console, World world) {

		Location spawnPlatform;
		for (int x = -2; x <= 2; x++){
			for (int z = -2; z <= 2; z++){
				spawnPlatform = new Location(world, x, 62, z);
				spawnPlatform.getBlock().setType(Material.DIRT);
			}
		}

		Location beaconBase = new Location(world,0,1,0);
		for (int x = -1; x <= 1; x++) {
			for (int z = -1; z <= 1; z++) {
				beaconBase = new Location(world, x, 1, z);
				beaconBase.getBlock().setType(Material.IRON_BLOCK);
			}
		}

		Block beacon = new Location(world, 0,2,0).getBlock();
		beacon.setType(Material.BEACON);

		Block pillarOrigin = new Location(world, 0,3,0).getBlock();
    	for (int i = 3; i < Main.MAX_BUILD_HEIGHT; i++) {
    		if (!(pillarOrigin.getType().equals(Material.AIR))) {
    			pillarOrigin.setType(Material.GLASS);
    		}
    		pillarOrigin = pillarOrigin.getRelative(0,1,0);
    	}
    	tellConsole(console, "initialized spawn area");
	}

	public void initScoreBoard(ConsoleCommandSender console, Scoreboard scoreboard) {

		scoreboard.registerNewObjective("kills", "playerKillCount", "Kills", RenderType.INTEGER);
	    scoreboard.getObjective("kills").setDisplaySlot(DisplaySlot.SIDEBAR);
		tellConsole(console, "initialized scoreboard");
	}

	public void initTeams(ConsoleCommandSender console, Scoreboard scoreboard) {
		
		String[] teams = {"Red"};
		ChatColor[] colors = {ChatColor.DARK_RED};
		for (int i = 0; i < teams.length; i++) {
			scoreboard.registerNewTeam(teams[i]);
			scoreboard.getTeam(teams[i]).setColor(colors[i]);
		}
    	tellConsole(console, "initialized teams");
	}
	
	public void initWorldBorder(ConsoleCommandSender console, WorldBorder border) {

		border.setCenter(0, 0);
    	border.setSize(150);
    	border.setWarningDistance(0);
    	tellConsole(console, "initialized world border");
	}
	
	public int getYValOfSurface(World world, int x, int z) {
		Location y = new Location(world, x, 0, z);
		int safe = 0;
		while ( safe < 300 &&
			!(
				!(y.getBlock().getType().equals(Material.AIR)) &&
				(y.getBlock().getRelative(0, 1, 0).getType().equals(Material.AIR)) &&
			    (y.getBlock().getRelative(0, 2, 0).getType().equals(Material.AIR)) )
			
			) {
			safe ++;
			y.add(0,1,0);
		}

		return (int) y.add(0,1,0).getY();
	}

	public void tellConsole(ConsoleCommandSender console, String message){
	    console.sendMessage(message);
	}
	
}
