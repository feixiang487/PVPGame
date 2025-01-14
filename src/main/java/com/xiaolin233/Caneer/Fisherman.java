package com.xiaolin233.Caneer;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class Fisherman extends Kits{
    public Fisherman(UUID player){
        super(player, Career.Fisherman);
    }
    @Override
    public void start(Player player) {
        player.getInventory().addItem(new ItemStack(Material.FISHING_ROD, 1));
        //添加铁剑
        player.getInventory().addItem(new ItemStack(Material.IRON_SWORD, 1));
        player.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE, 16));
    }
}
