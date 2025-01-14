package com.xiaolin233.Caneer;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class Swordsman extends Kits{
    public Swordsman(UUID player){
        super(player, Career.Swordsman);
    }
    @Override
    public void start(Player player) {
        player.getInventory().addItem(new ItemStack(Material.DIAMOND_SWORD));
        //获得16个金苹果
        player.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE,16));
    }
}
