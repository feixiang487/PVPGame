package com.xiaolin233.CountDown;

import com.xiaolin233.Arena.Arena;
import com.xiaolin233.Config.Config;
import com.xiaolin233.Manager.Manager;
import com.xiaolin233.PVPGame;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class CountDown extends BukkitRunnable {
    private int countdown;
    private static Player player;
    public CountDown() {
        this.countdown = Config.getTime();
    }
    public void begin(Player player){
        this.player = player;
        runTaskTimer(PVPGame.getInstance(), 0, 20);
    }

    @Override
    public void run() {
        if(countdown == 0){
            //游戏开始
            cancel();
            Arena.start();
            return;
        }
        if(countdown % 30 == 0 || countdown <= 10){
            Manager.isPlayerArena(player).sendMessage("游戏开始倒计时: " + countdown);
        }
        if(!Manager.isPlayer(player)){
            Manager.isPlayerArena(player).sendMessage("玩家: " + player.getName() + " 退出了游戏");
            cancel();
            return;
        }
        countdown--;
    }
}
