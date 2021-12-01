# Last updated for Spigot 1.17.1

# Spigot Development Setup

1. Install Minecraft   
  https://www.minecraft.net/en-us/download  
2. Install Spigot Server  
  https://www.spigotmc.org/wiki/spigot-installation/#linux  
3. Install BuildTools and run to build `spigot-X.XX.X.jar` (where `X.XX.X` is the latest version, for instance `spigot-1.17.1.jar`)
  https://www.spigotmc.org/wiki/buildtools/ 
  
# Linux Install Instructions

1. Clone this repository    
2. Create a new Java workspace and open the `HungerGames` root folder as an existing project.    
3. You need to configure the build path to use `spigot-X.XX.X.jar` as an external archive.    
    a) The exact file name may be different depending on the latest version, but it will always be something like "spigot-X.XX.X.jar"    

# To Run The Server  
1. Create a new directory for your server. Inside it, you must have a COPY of the `spigot-X.XX.X.jar` JAR file      
2. Create a file `start.sh` to start your server with the command `java -Xms#G -Xmx#G -XX:+UseG1GC -jar spigot-X.XX.X.jar nogui`    
    a) Change the `#` to however much memory you wish to allocate to the server   
    b) Change the name of `spigot-X.XX.X.jar` to the latest version of spigot 
  
3. Inside the server directory you must create another directory named `plugins`    
4. Export from your workspace the project's `plugin.yml` as a .JAR file into the `plugins` directory
5. Start your server with `./start.sh`
    
##### To get started developing on Spigot. check out the official Spigot API Docs: https://www.spigotmc.org/wiki/how-to-learn-the-spigot-api/
##### For a more hands-on tutorial, check out Daemon Gaming TV's youtube series: https://www.youtube.com/playlist?list=PLHUkvT20xZZN-cUIw4pkRg02uZ-RPEb7W

