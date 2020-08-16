package com.mzimu.zmvoidrelic.trigger;

import com.mzimu.zmvoidrelic.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;

public class OnPlayJoinEvent implements Listener {
    public OnPlayJoinEvent(Plugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(this,plugin);
    }

    @EventHandler
    public void main(PlayerJoinEvent playerJoinEvent){
        String name = playerJoinEvent.getPlayer().getName();
        Main.voidNumber.put(name,Main.configData.getVoidNumber(name));
    }
}
