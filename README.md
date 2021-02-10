For use with Minecraft Spigot Server 1.16.5 

# Spigot Development Setup

1. Install Minecraft   
  https://www.minecraft.net/en-us/download  
2. Install Spigot Server  
  https://www.spigotmc.org/wiki/spigot-installation/#linux  
3. Install BuildTools and run to build spigot.jar  
  https://www.spigotmc.org/wiki/buildtools/ 
  
# Linux Install Instructions

1. Clone this repository    
2. Create a new Java workspace and open the `HungerGames` root folder as an existing project.    
    a) You need to configure the build path to use the built `spigot-1.16.5.jar` file as an external archive. 
        i. The exact file name may be different depending on the latest version, but it will always be something like `spigot.jar`

# To Run The Server  
1. Create a new directory for your server. Inside it, you must have a COPY of the built `spigot-1.16.5.jar`    
2. Create a file `start.sh` to start your server with the command `java -Xms#G -Xmx#G -XX:+UseG1GC -jar spigot-1.16.5.jar nogui`    
    a) Change the `#` to however much memory you wish to allocate to the server 
    b) Check the name of the `spigot-1.16.6.jar` file - it may be different depending on the latest version
  
3. Inside the server directory you must create another directory named `plugins`    
4. Export the project's `plugin.yml` as a .JAR file into this new directory
5. Start your server with `./start.sh`
    
##### To get started developing on Spigot. check out the official Spigot API Docs: https://www.spigotmc.org/wiki/how-to-learn-the-spigot-api/
##### For a more hands-on tutorial, check out Daemon Gaming TV's youtube series: https://www.youtube.com/playlist?list=PLHUkvT20xZZN-cUIw4pkRg02uZ-RPEb7W
