package com.xiaolin233;

import com.xiaolin233.Config.Config;
import com.xiaolin233.GameCommand.GameCommand;
import com.xiaolin233.GameListener.GameListener;
import com.xiaolin233.Manager.Manager;
import org.bukkit.plugin.java.JavaPlugin;

public class PVPGame extends JavaPlugin {
    private static PVPGame instance;

    public void onEnable(){
        getLogger().info("PVPGame加载成功");
        instance = this;
        new Config(this);
        new Manager(this);
        getCommand("pvpgame").setExecutor(new GameCommand(this));
        getServer().getPluginManager().registerEvents(new GameListener(),this);
    }
    public void onDisable(){
        getLogger().info("PVPGame卸载成功");
    }

    public static PVPGame getInstance(){
        return instance;
    }


}