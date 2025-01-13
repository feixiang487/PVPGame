package com.xiaolin233.Config;

import com.xiaolin233.PVPGame;
import org.bukkit.Bukkit;
import org.bukkit.Location;

public class Config {
    private static PVPGame main;
    public Config(PVPGame main){
        this.main = main;
        main.getConfig().options().copyDefaults(true);
        main.saveDefaultConfig();
    }
    public static int getPlayers(){
        return main.getConfig().getInt("required-players");
    }
    public static int getTime(){
        return main.getConfig().getInt("countdown-seconds");
    }
    public static Location getLobby(){
        return new Location(
                Bukkit.getWorld(main.getConfig().getString("lobby-spawn.world")),
                main.getConfig().getDouble("lobby-spawn.x"),
                main.getConfig().getDouble("lobby-spawn.y"),
                main.getConfig().getDouble("lobby-spawn.z"),
                main.getConfig().getInt("lobby-spawn.pitch"),
                main.getConfig().getInt("lobby-spawn.yaw")
        );
    }
    public static Location getArena(int id){
    return new Location(
            Bukkit.getWorld(main.getConfig().getString("arenas." + id + ".world")),
            main.getConfig().getDouble("arenas." + id + ".x"),
            main.getConfig().getDouble("arenas." + id + ".y"),
            main.getConfig().getDouble("arenas." + id + ".z"),
            main.getConfig().getInt("arenas." + id + ".pitch"),
            main.getConfig().getInt("arenas." + id + ".yaw")
    );
    }

    public static int getArenaCount(){
        return main.getConfig().getConfigurationSection("arenas").getKeys(false).size();
    }
}
