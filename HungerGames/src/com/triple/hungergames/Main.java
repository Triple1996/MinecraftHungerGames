package com.triple.hungergames;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	@Override
	public void onEnable() {
		
		getLogger().info("HungerGames has been enabled.");
	}
	
	@Override
	public void onDisable() {
		// test comment
		getLogger().info("HungerGames has been disabled.");
	}
}
