package com.xiaolin233.GameListener;

import com.xiaolin233.Gamestatus.GameStatus;
import com.xiaolin233.Manager.Manager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

public class GameListener implements Listener {
    @EventHandler
    public void onBreak(BlockBreakEvent e){
        Player player = e.getPlayer();
        if(Manager.isPlayer(player) && Manager.isPlayerArena(player).getArenaStatus() == GameStatus.started){

        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player entity = event.getEntity();
        if(Manager.isPlayer(entity) && Manager.isPlayerArena(entity).getArenaStatus() == GameStatus.started){
            Manager.isPlayerArena(entity).getGame().addDeath(entity);
            entity.teleport(Manager.isPlayerArena(entity).getArenaLocation());
        }
    }
}
