For use with Minecraft Spigot Server 1.16.5

# Install Instructions

1. Install Minecraft 
  https://www.minecraft.net/en-us/download  
2. Install Spigot Server  
  https://www.spigotmc.org/wiki/spigot-installation/#linux  
3. Install Eclipse (Or your favorite Java IDE)  
  https://www.eclipse.org/downloads/  
4. Install BuildTools and drun to build spigot.jar  
  https://www.spigotmc.org/wiki/buildtools/ 
  
5. Create an eclipse workspace and open the `HungerGames` root folder as an existing project.    
    a) You need to configure the build path to use the built `spigot.jar` file as an external archive. 

# To Run The Server  
1. Create a new directory for your server. Inside it, you must have a COPY of the built `spigot.jar`    
Create a file `start.sh` to start your server with the command `java -Xms#G -Xmx#G -XX:+UseG1GC -jar spigot.jar nogui`    
    a) Change the `#` to however much memory you wish to allocate to the server!
  
Inside the server directory you must create another directory named `plugins`    
Now, inside Eclipse:     
    a) right click on project    
    b) `export...`    
    c) ensure JAR file is selected    
    d) `Next`    
    e) uncheck `.classpath` and `.project` (Ensure `plugin.yml` is selected!!)    
    f) navigate to your server/plugins folder    
    g) `Finish`    
    
    
