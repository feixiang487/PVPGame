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
            Arena.start();
            cancel();
        }
        if(countdown % 30 == 0 || countdown <= 10){
            player.sendMessage("游戏开始还有：" + countdown+"秒");
        }
        if(!Manager.isPlayer(player)){
            player.sendMessage("玩家不在竞技场，游戏暂停");
            cancel();
        }
        countdown--;
    }
}
