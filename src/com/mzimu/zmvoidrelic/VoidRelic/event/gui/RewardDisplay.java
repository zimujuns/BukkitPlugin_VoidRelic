package com.mzimu.zmvoidrelic.VoidRelic.event.gui;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class RewardDisplay {
    public Map<String, List<ItemStack>> rewardDisplay;

    public RewardDisplay() {
        this.rewardDisplay = new HashMap<>();
    }

    public void putItemStack(String world, ItemStack itemStack){
        //刚创建缓存的时候
        if(rewardDisplay.get(world)==null){
            List<ItemStack> itemStackList = new ArrayList<>();
            itemStackList.add(itemStack);
            rewardDisplay.put(world,itemStackList);
        }else{
            //已经拥有缓存后
            rewardDisplay.get(world).add(itemStack);
        }
    }

    public void remove(String world){
        rewardDisplay.remove(world);
    }

    public void main(String world,Player player){
        if(rewardDisplay.containsKey(world)){
            Inventory inventory = Bukkit.createInventory(null,9,"当前奖励");
            ItemStack itemStac[] = new ItemStack[9];
            rewardDisplay.get(world).toArray(itemStac);
            inventory.setContents(itemStac);
            player.closeInventory();
            player.openInventory(inventory);
        }
    }
}
