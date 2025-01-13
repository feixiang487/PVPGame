package com.xiaolin233.Game;

import com.xiaolin233.Arena.Arena;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class Game {
    private int point;
    private static HashMap<Player, Integer> playerPoint;
    private static Arena arena;
    public Game(Arena arena){
        this.arena = arena;
        playerPoint = new HashMap<>();
    }
    public static void start(){
        for (UUID player : arena.getPlayers()) {
            playerPoint.put(Bukkit.getPlayer(player), 0);
        }
    }

    public void addpoint(Player player){
        playerPoint.put(player, playerPoint.get(player) + 1);
        int i = playerPoint.get(player);
        if (i == 10){
            arena.sendMessage("玩家" + player.getName() + "取得胜利");
            //arena.reset();
        }
    }
}
