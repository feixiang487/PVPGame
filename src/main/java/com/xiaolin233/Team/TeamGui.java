package com.xiaolin233.Team;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class TeamGui {
    //设置玩家gui面板
    public TeamGui(Player player){
        Inventory inventory = Bukkit.createInventory(null,9, ChatColor.RED + "队伍选择");
        for (Team team : Team.values()) {
            ItemStack itemStack = new ItemStack(team.getMaterial());
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName(team.getName());
            itemMeta.setLocalizedName(team.name());
            itemStack.setItemMeta(itemMeta);
            inventory.addItem(itemStack);
        }
        player.openInventory(inventory);
    }
}
