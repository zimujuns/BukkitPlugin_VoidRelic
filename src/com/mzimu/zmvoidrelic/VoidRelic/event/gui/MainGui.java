package com.mzimu.zmvoidrelic.VoidRelic.event.gui;

import com.mzimu.zmvoidrelic.Main;
import com.mzimu.zmvoidrelic.util.displaydetails.VoidRelicLoreItem;
import com.mzimu.zmvoidrelic.VoidRelic.event.updata.VoidRelicUp;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class MainGui {

    public static void openMain(Player player){
        Inventory inventory = Bukkit.createInventory(null,9,"操作界面");
        ItemStack itemStack = new ItemStack(Material.BOOK);
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName("精炼遗物");
        itemStack.setItemMeta(itemMeta);
        inventory.setItem(0,itemStack);
        itemMeta.setDisplayName("查看详情");
        itemStack.setItemMeta(itemMeta);
        inventory.setItem(8,itemStack);
        player.closeInventory();
        player.openInventory(inventory);
    }

    public static void openUp(Player player){
        Inventory inventory = Bukkit.createInventory(null,9,"精炼遗物");
        ItemStack itemStack = new ItemStack(Material.BOOK);
        ItemMeta itemMeta = itemStack.getItemMeta();
        for(int i=0;i<4;i++){
            itemMeta.setDisplayName(VoidRelicUp.Builder.pArray[i]);
            itemStack.setItemMeta(itemMeta);
            inventory.setItem(i,itemStack);
        }

        itemMeta.setDisplayName("虚无元素");
        List<String> lore = new ArrayList<>();
        lore.add("您当前虚无元素拥有 [ " + Main.voidNumber.get(player.getName()) +" ]");
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        inventory.setItem(8,itemStack);
        player.closeInventory();
        player.openInventory(inventory);
    }

    public static void openDetails(Player player,String item){
        Inventory inventory = Bukkit.createInventory(null,9,"虚空遗物:详情");
        inventory.setContents(
                VoidRelicLoreItem.getItemStacks(item)
        );
        player.closeInventory();
        player.openInventory(inventory);
    }
}
