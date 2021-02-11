package com.triple.hungergames;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

public class HGHelper {

	public Location getYValOfSurface(int x, int z) {
		World world = Bukkit.getWorld("world");
		Location y = new Location(world, x, 128, z);
		while ( !(
				!(y.getBlock().getType().equals(Material.AIR)) &&
				y.add(0, 1, 0).getBlock().getType().equals(Material.AIR) &&
				y.add(0, 2, 0).getBlock().getType().equals(Material.AIR)
				)) {
			y.setY(y.getY()-1);
		}
		
		return y;
	}
	
	
}
