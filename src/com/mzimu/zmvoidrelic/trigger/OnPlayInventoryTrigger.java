package com.mzimu.zmvoidrelic.trigger;

import com.mzimu.zmvoidrelic.VoidRelic.event.gui.ClickInventoryMainEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;

public class OnPlayInventoryTrigger implements Listener {
    public OnPlayInventoryTrigger(Plugin plugin) {
        new OnDrop(plugin);
        new OnClick(plugin);
    }

    public class OnDrop implements Listener {
        public OnDrop(Plugin plugin) {
            plugin.getServer().getPluginManager().registerEvents(this,plugin);
        }
        @EventHandler
        public void main(InventoryDragEvent inventoryDragEvent){
            Inventory inventory = inventoryDragEvent.getInventory();
            switch (inventory.getType()){
                case CREATIVE:
                case CHEST:
                    if(inventory.getTitle().equals("当前奖励") || inventory.getTitle().equals("虚空遗物:详情")){
                        inventoryDragEvent.setCancelled(true);
                    }
                    break;
            }
        }
    }



    //点击背包获取物品
    public class OnClick implements Listener {
        public OnClick(Plugin plugin) {
            plugin.getServer().getPluginManager().registerEvents(this, plugin);
        }
        @EventHandler
        public void main(InventoryClickEvent inventoryClickEvent) {
            new ClickInventoryMainEvent(inventoryClickEvent);
        }
    }
}
