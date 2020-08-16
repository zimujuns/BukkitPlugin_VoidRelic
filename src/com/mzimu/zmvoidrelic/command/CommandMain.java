package com.mzimu.zmvoidrelic.command;

import com.mzimu.zmvoidrelic.VoidRelic.event.GetVoidRelicEvent;
import com.mzimu.zmvoidrelic.VoidRelic.event.RollGiftEvent;
import io.lumine.xikage.mythicmobs.MythicMobs;
import io.lumine.xikage.mythicmobs.items.MythicItem;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.Iterator;

public class CommandMain implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(commandSender instanceof Player){
            Player player = (Player) commandSender;
            if(strings.length == 0){
                playHelpMsg(player);
                return false;
            }
            switch (strings[0]){
                case "getItem":
                    if(strings.length !=3){
                        return false;
                    }
                    if(player.getInventory().getItemInMainHand().getType() == Material.AIR){
                        ItemStack itemStack = GetVoidRelicEvent.getGiftItem(Integer.valueOf(strings[1].charAt(0)),Integer.parseInt(strings[2]));
                        player.getInventory().setItemInMainHand(itemStack);
                    }else{
                        player.sendMessage("您的主手有物品无法设置");
                        return false;
                    }
                    return true;
                case "list":
                    player.sendMessage("§f§l========================当前列表========================");
                    Iterator<MythicItem> iterator = MythicMobs.inst().getItemManager().getItems().iterator();
                    String content = "";
                    while (iterator.hasNext()){
                        MythicItem a = iterator.next();
                        if(a.getDisplayName().matches("虚空遗物\\S*")){
                            content += " §f§l| " + a.getDisplayName() + " §f§l> §b§lMythicMob §f§l> §a§l" + a.getFile() + " §f§l|\n";
                        }

                    }
                    player.sendMessage(content);
                    return true;
                case "bate":

                    break;
//
            }
            return false;
        }

        return false;
    }

    public void playHelpMsg(Player player){
        player.sendMessage("/zmvr getItem [品质] [第几] eg:想要获取A1 就输入/zmvr getItem A(大小写区分) 1");
        player.sendMessage("/zmvr list 列表");
    }
}
