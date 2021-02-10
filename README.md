For use with Minecraft Spigot Server 1.16.5

# Linux Install Instructions

1. Install Minecraft   
  https://www.minecraft.net/en-us/download  
2. Install Spigot Server  
  https://www.spigotmc.org/wiki/spigot-installation/#linux  
3. Install BuildTools and run to build spigot.jar  
  https://www.spigotmc.org/wiki/buildtools/ 
  
4. Create a new Java workspace and open the `HungerGames` root folder as an existing project.    
    a) You need to configure the build path to use the built `spigot.jar` file as an external archive. 

# To Run The Server  
1. Create a new directory for your server. Inside it, you must have a COPY of the built `spigot.jar`    
2. Create a file `start.sh` to start your server with the command `java -Xms#G -Xmx#G -XX:+UseG1GC -jar spigot.jar nogui`    
    a) Change the `#` to however much memory you wish to allocate to the server    
  
3. Inside the server directory you must create another directory named `plugins`    
4. Export the project's `plugin.yml` as a .JAR file into this new directory
5. Start your server with `./start.sh`
    
##### To get started developing on Spigot. check out the official Spigot API Docs: https://www.spigotmc.org/wiki/how-to-learn-the-spigot-api/
##### For a more hands-on tutorial, check out Daemon Gaming TV's youtube series: https://www.youtube.com/playlist?list=PLHUkvT20xZZN-cUIw4pkRg02uZ-RPEb7W
