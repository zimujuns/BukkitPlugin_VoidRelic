package com.mzimu.zmvoidrelic.util;

import com.mzimu.zmvoidrelic.Main;
import com.mzimu.zmvoidrelic.VoidRelic.data.VoidRelicFileName;
import org.bukkit.inventory.ItemStack;

public class IsVoidRelic {
    public static boolean is(ItemStack i){
        for(ItemStack item : Main.voidRelicItem)
            if(item.isSimilar(i))
                return true;
        return false;
    }
}
