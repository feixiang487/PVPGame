package com.xiaolin233.Manager;

import com.xiaolin233.Arena.Arena;
import com.xiaolin233.Config.Config;
import com.xiaolin233.PVPGame;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class Manager {
    private PVPGame main;
    private static List<Arena> arenas;
    public Manager(PVPGame main){
        this.main = main;
        this.arenas = new ArrayList<>();
        for (int i = 0; i < Config.getArenaCount() - 1; i++) {
            arenas.add(new Arena(i));
        }
    }

    //根据竞技场id获取竞技场
    public static Arena getArena(int id){
        for (Arena arena : arenas) {
            if (arena.getArenaId() == id) {
                return arena;
            }
        }
        return null;
    }
    //判断玩家是否在竞技场里面
    public static boolean isPlayer(Player player){
        for (Arena arena : arenas) {
            if(arena.getPlayers().contains(player.getUniqueId())){
                return true;
            }
        }
        return false;
    }
    //获取玩家在哪个竞技场,是否在竞技场
    public static Arena isPlayerArena(Player player){
        for (Arena arena : arenas) {
            if(arena.getPlayers().contains(player.getUniqueId())){
                return arena;
            }
        }
        return null;
    }
}
