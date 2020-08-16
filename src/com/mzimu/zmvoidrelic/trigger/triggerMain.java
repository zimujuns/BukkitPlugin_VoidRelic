package com.mzimu.zmvoidrelic.trigger;

import org.bukkit.plugin.*;

public class triggerMain {
    public triggerMain(Plugin plugin) {
        new OnPlayInventoryTrigger(plugin);
//        new OnPlayLeaveEvent(plugin);
        new OnDungeonEvent(plugin);
        new OnPlayJoinEvent(plugin);
    }
}
