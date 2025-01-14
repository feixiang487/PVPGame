package com.xiaolin233.GameGui;

import com.xiaolin233.Caneer.Career;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class GameCareerGui {
    public GameCareerGui(Player player){
        Inventory inventory = Bukkit.createInventory(null,9, ChatColor.RED + "职业选择");
        for (Career career : Career.values()){
            ItemStack itemStack = new ItemStack(career.getMaterial());
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName(career.getName());
            itemMeta.setLore(Arrays.asList(career.getDesprition()));
            itemMeta.setLocalizedName(career.getName());
            itemStack.setItemMeta(itemMeta);
            inventory.addItem(itemStack);
        }
        player.openInventory(inventory);
    }
}
