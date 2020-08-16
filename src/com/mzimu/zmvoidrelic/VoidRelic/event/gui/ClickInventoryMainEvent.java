package com.mzimu.zmvoidrelic.VoidRelic.event.gui;

import com.mzimu.zmvoidrelic.Main;
import com.mzimu.zmvoidrelic.VoidRelic.data.VoidRelicFileName;
import com.mzimu.zmvoidrelic.VoidRelic.event.updata.VoidRelicUp;
import com.mzimu.zmvoidrelic.util.IsVoidRelic;
import de.erethon.dungeonsxl.player.DGamePlayer;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public class ClickInventoryMainEvent {
    public ClickInventoryMainEvent(InventoryClickEvent inventoryClickEvent) {
        Inventory inventory = inventoryClickEvent.getClickedInventory();
        if (inventory != null && inventoryClickEvent.getCurrentItem().getType() != Material.AIR) {
            Player player = (Player) inventoryClickEvent.getWhoClicked();
            //判断点击的类型
            //CREATIVE 是中间
            if (inventoryClickEvent.getClick().isCreativeAction()) {
                if (player.getInventory().getItemInMainHand().isSimilar(inventoryClickEvent.getCurrentItem())) {
                    if (IsVoidRelic.is(inventoryClickEvent.getCurrentItem())) {
                        MainGui.openMain(player);
                        inventoryClickEvent.setCancelled(true);
                    }
                }
            } else {
                //这里写的是别的背包的触发
                //先写if是因为暂时只有一个
                switch (inventory.getTitle()) {
                    case "虚空遗物:详情":
                        inventoryClickEvent.setCancelled(true);
                        break;
                    case "当前奖励":
                        inventoryClickEvent.setCancelled(true);
                        Inventory ender = player.getEnderChest();
                        int slot = ender.firstEmpty();
                        if (slot != -1) {
                            ender.setItem(ender.firstEmpty(), inventory.getItem(inventoryClickEvent.getSlot()));
                            //每局添加5点
                            int a = Main.voidNumber.getOrDefault(player.getName(), 0) + 5;
                            Main.voidNumber.put(player.getName(), a);
                            Main.configData.setVoidNumber(player.getName(), a);
                            player.closeInventory();
                            DGamePlayer.getByPlayer(player).leave(false);
                        } else {
                            player.sendMessage("§a[虚空遗物]§7提醒: 您的末影箱已满，无法给予你选择的虚空遗物奖励，但增加10点虚空点数");
                            int a = Main.voidNumber.getOrDefault(player.getName(), 0) + 10;
                            Main.voidNumber.put(player.getName(), a);
                            Main.configData.setVoidNumber(player.getName(), a);
                            player.closeInventory();
                        }
                        break;
                    case "操作界面":
                        inventoryClickEvent.setCancelled(true);
                        switch (inventoryClickEvent.getSlot()) {
                            case 0:
                                if (player.getInventory().getItemInMainHand().getAmount() > 1) {
                                    player.sendMessage("§a[虚空遗物]§7提醒: 您手上的物品不能为复数!(不能手持两个或两个以上的虚空遗物进行精炼!)");
                                    player.closeInventory();
                                    return;
                                }
                                for (int i = 0; i < 4; i++) {
                                    if (player.getInventory().getItemInMainHand().getItemMeta().getLore().contains(VoidRelicUp.Builder.pArray[i])) {
                                        player.sendMessage("§a[虚空遗物]§7提醒: 您当前想精炼的物品已经精炼过了!");
                                        player.closeInventory();
                                        return;
                                    }
                                }
                                MainGui.openUp(player);
                                break;
                            case 8:
                                String itemName = player.getInventory().getItemInMainHand().getItemMeta().getDisplayName();
                                MainGui.openDetails(player, itemName);
                                break;
                        }

                        break;
                    case "精炼遗物":
                        int a = Main.voidNumber.getOrDefault(player.getName(), 0) - (25 * (inventoryClickEvent.getSlot() + 1));
                        if (a >= 0) {
                            VoidRelicUp.builder()
                                    .setI(inventoryClickEvent.getSlot())
                                    .setItemStack(player.getInventory().getItemInMainHand())
                                    .setPlayer(player)
                                    .stack();
                            Main.voidNumber.put(player.getName(), a);
                            Main.configData.setVoidNumber(player.getName(), a);
                        } else {
                            player.sendMessage("§a[虚空遗物]§7提醒: 您没有虚空点数了!");
                        }

                        inventoryClickEvent.setCancelled(true);
                        break;
                }
            }

        }
    }

}
