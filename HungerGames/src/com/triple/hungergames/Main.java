package com.triple.hungergames;


import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Difficulty;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scoreboard.Scoreboard;

public class Main extends JavaPlugin{

	public final static List<PotionEffect> starterEffects = 
			Arrays.asList(
					new PotionEffect(PotionEffectType.REGENERATION, 12000, 9), 
					new PotionEffect(PotionEffectType.SATURATION, 12000, 9));

	public final static int MAX_BUILD_HEIGHT = 256;
	private static HGUtils hgu = new HGUtils();
	private static Boolean gameStarted = false;
	
	@Override
    public void onEnable() {

        getLogger().info("HungerGames has been enabled.");
        PluginManager pm = getServer().getPluginManager();
        HGListener listener = new HGListener(this);
        pm.registerEvents(listener, this);
    }

    @Override
    public void onDisable() {
        getLogger().info("HungerGames has been disabled.");
    }    
    
    public static void setGameStarted(Boolean bool) {
    	gameStarted = bool;
    }
    
    public  static Boolean getGameStarted() {
    	return gameStarted;
    }
    
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        Player playerSender = (Player) sender;

        if (sender instanceof Player) {

            String lowerCmd = cmd.getName().toLowerCase();

            switch (lowerCmd) {
            
            case "start-2000":
            	setGameStarted(true);
            	
                ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
            	World world = Bukkit.getServer().getWorld("world");
            	

            	int xCenter = (int) ((Math.random() * 401) - 200);
            	int zCenter = (int) ((Math.random() * 401) - 200);
            	
            	world.getWorldBorder().setCenter(xCenter, zCenter);
            	world.getWorldBorder().setSize(2000);		
            	world.getWorldBorder().setWarningDistance(100);
            	
            	prepGame(world, xCenter, zCenter);
            	startGame();
            	// TODO when teams are set up, change option to "true", so teams stay together
            	Bukkit.dispatchCommand(console, "spreadplayers " + xCenter + " " + zCenter + " 300 900 false @a");

            	return true;

            case "welcome":
            	playerSender.sendMessage("Welcome " + playerSender.getName());
            	return true;

            default:

            	playerSender.sendMessage("Your command was not recognized.");
                return true;
            }
        }
        playerSender.sendMessage("Somehow you aren't a player.");
        return true;
    }

    private void prepGame(World world, int xCenter, int zCenter) {
    	// clears effects and invents, gives everyone a compass, sets gamemode, reset kils/deaths

    	Scoreboard scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
    	ItemStack compass = new ItemStack(Material.COMPASS, 1);
    	Player player;
    	
    	world.setDifficulty(Difficulty.HARD);
        world.setTime(1000);

    	List<Player> players = world.getPlayers();
    	for (int i = 0; i < players.size(); i++) {
    		player = players.get(i);
    		scoreboard.getObjective("kills").getScore(player.getName()).setScore(0); 
    		player.setGameMode(GameMode.SURVIVAL);
    		hgu.clearEffects(player);
    		player.getInventory().clear();
    		player.getInventory().setItem(8, compass);
    		player.setCompassTarget(new Location(world, xCenter, hgu.getYValOfSurface(world, xCenter, zCenter), zCenter));
    	}
    }
    
    private void startGame() {
    	
    	// TODO polish
    	ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
    	WorldBorder border = Bukkit.getServer().getWorld("world").getWorldBorder();
    	BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
    	
    	scheduler.scheduleSyncDelayedTask(this, new Runnable() {
    		@Override
    		public void run() {
    			border.setSize(1750, 180); // run for 3 mins (3600 ticks)
    			Bukkit.dispatchCommand(console, "title @a title {\"text\":\"Ring closing\",\"color\":\"gold\"}");
    		}
    	}, 2400);	// after 2 mins (2400 ticks)
    	// 6000 total
    	
    	scheduler.scheduleSyncDelayedTask(this, new Runnable() {
    		@Override
    		public void run() {
    			border.setSize(1000, 360);// run for 6 minutes (7200 ticks)
    			Bukkit.dispatchCommand(console, "title @a title {\"text\":\"Ring closing\",\"color\":\"gold\"}");
    		}
    	}, 6000+7200);	// after 6 mins (7200 ticks)
    	// 14,400 total
    	
    	scheduler.scheduleSyncDelayedTask(this, new Runnable() {
    		@Override
    		public void run() {
    			border.setSize(15, 300);// run for 5 mins(6000 ticks)
    			Bukkit.dispatchCommand(console, "title @a title {\"text\":\"Ring closing\",\"color\":\"gold\"}");
    		}
    	}, 6000+14400+6000); // after 5 mins(6000 ticks)
    	// 12,000 total
    	
    	scheduler.scheduleSyncDelayedTask(this, new Runnable() {
    		@Override
    		public void run() {
    			repeatedlyMoveCenter();
    		}
    	}, 6000+14400+12000);
    }
    
    private void repeatedlyMoveCenter() {
    	BukkitScheduler scheduler = getServer().getScheduler();
    	World world = Bukkit.getWorld("world");
        scheduler.scheduleSyncRepeatingTask(this, new Runnable() {
            @Override
            public void run() {
            	int direction = (int)(Math.random()*4);
            	Location newCenter = world.getWorldBorder().getCenter();
            	
            	switch (direction){
            	case 0:
            		newCenter.add(1, 0, 1);
            		break;
            	case 1:
            		newCenter.add(1, 0, -1);
            		break;
            	case 2:
            		newCenter.add(-1, 0, 1);
            		break;
            	case 3:
            		newCenter.add(-1, 0, -1);
            		break;
            	default:
            		break;
            	}
            	world.getWorldBorder().setCenter(newCenter);
            	List<Player> players = world.getPlayers();
            	for (int i = 0; i < players.size(); i++) {
            		players.get(i).setCompassTarget(newCenter);
            	}          	
            }
        }, 0L, 100L);  // repeat every 100 ticks (5s), starting when called
    }
    
}
