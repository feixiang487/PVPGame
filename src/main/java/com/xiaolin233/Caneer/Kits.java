package com.xiaolin233.Caneer;

import com.xiaolin233.PVPGame;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;

import java.util.UUID;

public abstract class Kits implements Listener {
    private UUID player;
    private Career career;
    public Kits(UUID player,Career career){
        this.player = player;
        this.career = career;
        Bukkit.getPluginManager().registerEvents(this, PVPGame.getInstance());
    }
    public abstract void start(Player player);

    public void remove(){
        HandlerList.unregisterAll();
    }


}
