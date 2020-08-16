package com.mzimu.zmvoidrelic.VoidRelic.event;

import com.mzimu.zmvoidrelic.Main;
import com.mzimu.zmvoidrelic.VoidRelic.data.VoidRelicPlayData;
import de.erethon.dungeonsxl.world.DGameWorld;

import java.util.List;
import java.util.Map;

public class DungeonCacheInitialization {
    public DungeonCacheInitialization(String world, List<String> isRollWorld, Map<String, List<VoidRelicPlayData>> dungeonsVpodRelic) {
        //简单的初始化操作
        isRollWorld.remove(world);
        dungeonsVpodRelic.remove(world);
        Main.rewardDisplay.remove(world);
    }
}
