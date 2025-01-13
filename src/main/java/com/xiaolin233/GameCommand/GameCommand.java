package com.xiaolin233.GameCommand;

import com.xiaolin233.Config.Config;
import com.xiaolin233.Gamestatus.GameStatus;
import com.xiaolin233.Manager.Manager;
import com.xiaolin233.PVPGame;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GameCommand implements CommandExecutor {
    private PVPGame main;
    public GameCommand(PVPGame main) {
        this.main = main;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] strings) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            if(strings.length == 1 && strings[0].equals("list")){
                for (int i = 0; i < Config.getArenaCount() - 1; i++){
                    if(Manager.getArena(i).getArenaStatus() == GameStatus.online){
                        player.sendMessage("竞技场" + i + "号在线空闲");
                    }
                }
            }
            if(strings.length == 2 && strings[0].equals("join")){
                if(Manager.getArena(Integer.parseInt(strings[1])).getPlayers().contains(player.getUniqueId())){
                    player.sendMessage("你已经加入竞技场" + strings[1] + "号");
                }else if(Manager.getArena(Integer.parseInt(strings[1])).getArenaStatus() == GameStatus.started &&
                    Manager.getArena(Integer.parseInt(strings[1])).getArenaStatus() == GameStatus.waiting
                ){
                    player.sendMessage("竞技场" + strings[1] + "号正在游戏");
                }else{
                    Manager.getArena(Integer.parseInt(strings[1])).addPlayer(player.getUniqueId());
                }
            }
            if(strings.length == 1 && strings[0].equals("leave")){
                if(Manager.isPlayer(player)){
                    Manager.isPlayerArena(player).removePlayer(player.getUniqueId());
                }else{
                    player.sendMessage("你没有加入任何竞技场");
                }
            }
        }
        return false;
    }
}
