package com.xiaolin233.Arena;

import com.xiaolin233.Config.Config;
import com.xiaolin233.CountDown.CountDown;
import com.xiaolin233.Game.GamePvp;
import com.xiaolin233.Gamestatus.GameStatus;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Arena {
    //竞技场编号
    private static int id;
    //竞技场位置
    private static Location location;
    //竞技场状态
    private static GameStatus status;
    //添加玩家
    private static List<UUID> players;
    //倒计时
    private static CountDown countDown;
    //游戏配置
    private static GamePvp game;
    public Arena(int id){
        this.id = id;
        this.location = Config.getArena(id);
        this.status = GameStatus.online;
        this.players = new ArrayList<>();
        this.countDown = new CountDown();
        this.game = new GamePvp(this);
    }

    public static void start() {
        status = GameStatus.started;
        game.start();

    }


    public void addPlayer(UUID player){
        if(players.contains(player)){
            Bukkit.getPlayer(player).sendMessage(ChatColor.RED + "你已经在" + getArenaId() + "号竞技场里面");
        }else{
            players.add(player);
            //传送玩家到竞技场
            Bukkit.getPlayer(player).teleport(Config.getArena(id));
            Bukkit.getPlayer(player).sendMessage(ChatColor.RED + "你成功加入" + getArenaId() + "号竞技场");
            if(players.size() >= Config.getPlayers()){
                countDown.begin(Bukkit.getPlayer(player));
            }
        }
    }

    public void removePlayer(UUID player){
        if(players.contains(player)){
            players.remove(player);
            Bukkit.getPlayer(player).teleport(Config.getLobby());
            Bukkit.getPlayer(player).sendMessage(ChatColor.RED + "你成功退出" + getArenaId() + "号竞技场");
        }else {
            Bukkit.getPlayer(player).sendMessage(ChatColor.RED + "你并没有加入" + getArenaId() + "号竞技场");
        }
    }

    public void sendMessage(String message){
        for(UUID player : players){
            Bukkit.getPlayer(player).sendMessage(message);
        }
    }


    public static int getArenaId(){
        return id;
    }
    public Location getArenaLocation(){
        return location;
    }
    public GameStatus getArenaStatus(){
        return status;
    }
    public List<UUID> getPlayers(){
        return players;
    }
    //获取游戏
    public GamePvp getGame(){
        return game;
    }

    public void reset() {
        for(UUID playerId : players){
            Player player = Bukkit.getPlayer(playerId);
            player.teleport(Config.getLobby());
        }
        players.clear();
        this.status = GameStatus.online;
        this.players = new ArrayList<>();
        this.countDown = new CountDown();
        this.game = new GamePvp(this);
    }
}