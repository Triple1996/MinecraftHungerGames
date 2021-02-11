package com.triple.hungergames;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

public class HGHelper {

	public int getYValOfSurface(int x, int z) {
		World world = Bukkit.getWorld("world");
		Location y = new Location(world, x, 0, z);
		int safe = 0;
		while ( safe < 300 &&
				!(
					!(y.getBlock().getType().equals(Material.AIR)) &&
					(y.clone().add(0, 1, 0).getBlock().getType().equals(Material.AIR)) &&
					(y.clone().add(0, 2, 0).getBlock().getType().equals(Material.AIR))
				)) {
			safe ++;
			y.add(0,1,0);
		}

		return (int) y.add(0,1,0).getY();
	}

}
