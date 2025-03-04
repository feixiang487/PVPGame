package com.xiaolin233.GameListener;

import com.xiaolin233.Caneer.Career;
import com.xiaolin233.Gamestatus.GameStatus;
import com.xiaolin233.Manager.Manager;
import com.xiaolin233.Team.Team;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;

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
    //点击这个界面
    @EventHandler
    public void onPlayerInventoryClick(InventoryClickEvent event){
        Player whoClicked = (Player) event.getWhoClicked();
        if(event.getInventory().getName().equals(ChatColor.RED + "职业选择")) {
            if (Manager.isPlayer(whoClicked) && Manager.isPlayerArena(whoClicked).getArenaStatus() == GameStatus.waiting) {
                Career career = Career.valueOf(event.getCurrentItem().getItemMeta().getLocalizedName());
                Manager.isPlayerArena(whoClicked).setCareer(whoClicked.getUniqueId(), career);
                whoClicked.sendMessage("你选择了" + career.getName() + "职业");
                event.getWhoClicked().closeInventory();
                event.setCancelled(true);
            }
        }
        if(event.getInventory().getName().equals(ChatColor.RED + "队伍选择")) {
            if (Manager.isPlayer(whoClicked) && Manager.isPlayerArena(whoClicked).getArenaStatus() == GameStatus.waiting) {
                Team team = Team.valueOf(event.getCurrentItem().getItemMeta().getLocalizedName());
                Manager.isPlayerArena(whoClicked).setTeam(whoClicked, team);
                whoClicked.sendMessage("你选择了" + team.getName() + "队伍");
                event.getWhoClicked().closeInventory();
                event.setCancelled(true);
            }
        }
    }
}
