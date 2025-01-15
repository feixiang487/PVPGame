package com.xiaolin233.Arena;

import com.google.common.collect.TreeMultimap;
import com.xiaolin233.Caneer.Career;
import com.xiaolin233.Caneer.Fisherman;
import com.xiaolin233.Caneer.Kits;
import com.xiaolin233.Caneer.Swordsman;
import com.xiaolin233.Config.Config;
import com.xiaolin233.CountDown.CountDown;
import com.xiaolin233.Game.GamePvp;
import com.xiaolin233.Gamestatus.GameStatus;
import com.xiaolin233.Team.Team;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.*;

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
    //配置职业
    private static HashMap<UUID, Kits> career;
    //配置团队
    private static HashMap<UUID, Team> team;
    public Arena(int id){
        this.id = id;
        this.location = Config.getArena(id);
        this.status = GameStatus.online;
        this.players = new ArrayList<>();
        this.countDown = new CountDown();
        this.game = new GamePvp(this);
        this.career = new HashMap<>();
        this.team = new HashMap<>();
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
            if(players.size() != 0){
            status = GameStatus.waiting;
            }
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
            //删除职业
            career.remove(player);
            //删除玩家团队
            removeTeam(Bukkit.getPlayer(player));
            if(players.size() == 0){
                status = GameStatus.online;
            }
            Bukkit.getPlayer(player).teleport(Config.getLobby());
            Bukkit.getPlayer(player).sendMessage(ChatColor.RED + "你成功退出" + getArenaId() + "号竞技场");
        }else {
            Bukkit.getPlayer(player).sendMessage(ChatColor.RED + "你并没有加入" + getArenaId() + "号竞技场");
        }
    }
    //向全部竞技场发送消息
    public void sendMessage(String message){
        for(UUID player : players){
            Bukkit.getPlayer(player).sendMessage(message);
        }
    }
    //恢复场地
    public void reset() {
        for(UUID playerId : players){
            Player player = Bukkit.getPlayer(playerId);
            player.teleport(Config.getLobby());
            removeTeam(player);
        }
        removeCareer();
        players.clear();
        this.status = GameStatus.online;
        this.players = new ArrayList<>();
        this.countDown = new CountDown();
        this.game = new GamePvp(this);
    }

    //玩家获得职业
    public void setCareer(UUID player, Career career1){
        if(career1.getName().equals("Swordsman")){
            career.put(player, new Swordsman(player));
        }else if(career1.getName().equals("Fisherman")){
            career.put(player, new Fisherman(player));
        }
    }
    //玩家设置团队
    public void setTeam(Player player, Team team1){
        //必须先删除团队才能重新添加
        removeTeam(player);
        team.put(player.getUniqueId(), team1);
    }


    //玩家删除职业
    public void removeTeam(Player player){
        team.remove(player);
    }

    //计算团队中人数的数量来进行遍历
    public int getSum(Team team1){
        int sum = 0;
        //遍历值
        for (Team value : team.values()) {
            if(team1.equals(value)){
                sum++;
            }
        }
        return sum;
    }

    //玩家结束游戏清除职业
    public void removeCareer(){
        career.clear();
    }
    //如果玩家没有选择团队，就需要系统自己去安排团队
    public void aisetTeam(Player player){
        TreeMultimap<Integer, Team> map = TreeMultimap.create();
        for (Team value : Team.values()) {
            int a = getSum(value);
            map.put(a, value);
        }
        Team o = (Team) map.values().toArray()[0];
        setTeam(player, o);
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
    //获取团队
    public HashMap<UUID, Team> getTeam(){
        return team;
    }    //获取职业
    public Kits getCareer(UUID player){
        return career.get(player);
    }
}