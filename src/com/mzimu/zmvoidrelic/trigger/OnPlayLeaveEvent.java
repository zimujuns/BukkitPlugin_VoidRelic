package com.mzimu.zmvoidrelic.trigger;

import com.mzimu.zmvoidrelic.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.*;

public class OnPlayLeaveEvent implements Listener {
    public OnPlayLeaveEvent(Plugin plugin) {
        plugin.getServer().getPluginManager().registerEvents(this,plugin);
    }

    @EventHandler
    public void main(PlayerQuitEvent playerQuitEvent){
        String name = playerQuitEvent.getPlayer().getName();
    }

}
