package com.triple.hungergames;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.RenderType;
import org.bukkit.scoreboard.Scoreboard;

public class HGUtils {
	final static int MAX_BUILD_HEIGHT = 256;
	private static boolean teamsInit = false;
	private boolean scoreBoardInit = false;
	private World world = Bukkit.getWorld("world");


	public void initSpawnArea() {

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
    	for (int i = 3; i < MAX_BUILD_HEIGHT; i++) {
    		if (!(pillarOrigin.getType().equals(Material.AIR))) {
    			pillarOrigin.setType(Material.GLASS);
    		}
    		pillarOrigin = pillarOrigin.getRelative(0,1,0);
    	}
	}

	public void initScoreBoard(Scoreboard scoreboard) {
		
		if (scoreBoardInit) {
			return;
		}
		else {
			Bukkit.getServer().getPlayer("Triple96").sendMessage("init scoreboard");
			scoreboard.registerNewObjective("kills", "playerKillCount", "Kills", RenderType.INTEGER);
	        scoreboard.getObjective("kills").setDisplaySlot(DisplaySlot.SIDEBAR);
	        scoreBoardInit = true;	// TODO This doesn't work
		}
	}
	
	public void initTeams(Scoreboard scoreboard) {
		
		if (teamsInit) {
			return;
		}
		else {
			String[] teams = {"Red", "Blue", "Cyan", "Purple", "Green", "White",
			                  	"Orange", "Lime", "Black", "Pink"};
			ChatColor[] colors = {ChatColor.DARK_RED, ChatColor.BLUE, ChatColor.AQUA,
									ChatColor.DARK_PURPLE, ChatColor.DARK_GREEN, 
									ChatColor.WHITE, ChatColor.GOLD, ChatColor.GREEN,
									ChatColor.DARK_GRAY, ChatColor.LIGHT_PURPLE};
			for (int i = 0; i < teams.length; i++) {
				scoreboard.registerNewTeam(teams[i]);
				scoreboard.getTeam(teams[i]).setColor(colors[i]);
			}
			teamsInit = true;	// TODO This doesn't work
		}
	}
	
	public int getYValOfSurface(int x, int z) {
		World world = Bukkit.getWorld("world");
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
