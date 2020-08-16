package com.mzimu.zmvoidrelic.VoidRelic.data;

import com.mzimu.zmvoidrelic.VoidRelic.data.VoidRelicFileName;
import org.bukkit.entity.Player;

public class VoidRelicPlayData {
    private Player player;
    private VoidRelicFileName voidRelicFileName;
    private String quality;

    public VoidRelicPlayData(Player player, VoidRelicFileName voidRelicFileName, String quality) {
        this.player = player;
        this.voidRelicFileName = voidRelicFileName;
        this.quality = quality;
    }

    public VoidRelicFileName getVoidRelicFileName() {
        return voidRelicFileName;
    }

    public Player getPlayer() {
        return player;
    }

    public String getQuality() {
        return quality;
    }
}
