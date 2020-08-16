package com.mzimu.zmvoidrelic.trigger;

import com.mzimu.zmvoidrelic.Main;
import com.mzimu.zmvoidrelic.VoidRelic.data.VoidRelicFileName;
import io.lumine.xikage.mythicmobs.MythicMobs;
import io.lumine.xikage.mythicmobs.items.ItemManager;
import io.lumine.xikage.mythicmobs.mobs.MythicMob;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginEnableEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.*;

public class OnPluginTrigger implements Listener {



    public OnPluginTrigger(Plugin p) {
        p.getServer().getPluginManager().registerEvents(this,p);
    }

    @EventHandler
    public void main(PluginEnableEvent e){
        if(e.getPlugin().getName().equals(Main.getPlugin(MythicMobs.class).getName())){
            ItemManager im = MythicMobs.inst().getItemManager();
            for(VoidRelicFileName v :VoidRelicFileName.values()){
                try{
                    ItemStack item = im.getItemStack(v.getFileName());
                    Main.voidRelicItem.add(item);
                }catch (NullPointerException nulle){
                    System.out.println("当出现这个报错的时候 很有可能是你在MM中添加了插件未注册的虚空遗物 请注意!!!!!!!!!!!");
                    nulle.printStackTrace();
                }


            }


        }
    }
}
