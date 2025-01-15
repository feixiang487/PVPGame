package com.xiaolin233.Game;

import com.xiaolin233.Arena.Arena;
import com.xiaolin233.Manager.Manager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class GamePvp {
    private Arena arena;
    private HashMap<Player, Integer> playerDeaths;
    public GamePvp(Arena arena){
        this.arena = arena;
        this.playerDeaths = new HashMap<>();
    }

    public void start(){
        for (UUID player : arena.getPlayers()) {
            if(arena.getTeam().get(Bukkit.getPlayer(player)) == null){
                arena.aisetTeam(Bukkit.getPlayer(player));
            }
            playerDeaths.put(Bukkit.getPlayer(player), 0);
            arena.getCareer(player).start(Bukkit.getPlayer(player));
        }
    }
    public void addDeath(Player player){
        playerDeaths.put(player, playerDeaths.get(player) + 1);
        int p = playerDeaths.get(player);
        if (p == 3){
            player.sendMessage("你已经阵亡，正在传送到观察台");
            playerDeaths.remove(player);
            //TODO 传送到观察台，这个部分暂时不开发
        }
        if(playerDeaths.size() == 1){
            for (Player player1 : playerDeaths.keySet()) {
                Manager.isPlayerArena(player1).sendMessage("恭喜" + player1.getName() + "获得胜利");
                playerDeaths.clear();
                arena.reset();
            }
        }
    }
}
