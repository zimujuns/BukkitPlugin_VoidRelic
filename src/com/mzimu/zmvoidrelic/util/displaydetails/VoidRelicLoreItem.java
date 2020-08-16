package com.mzimu.zmvoidrelic.util.displaydetails;

import com.mzimu.zmvoidrelic.VoidRelic.data.VoidRelicFileName;
import io.lumine.xikage.mythicmobs.MythicMobs;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 目的是用来显示 虚空遗物中
 * lore的物品详情
 * 即 存放在MythicMob内的物品
 * 显示在gui上
 */
public class VoidRelicLoreItem {
    public static ItemStack[] getItemStacks(String item){
        List<ItemStack> stackList = new ArrayList<>();
        ItemStack[] itemArray = new ItemStack[9];
        VoidRelicFileName voidRelicFileName = VoidRelicFileName.valueOf(item);
        for(int i=0;i<voidRelicFileName.getGiftFile().length;i++){
            stackList.add(MythicMobs.inst().getItemManager().getItemStack(voidRelicFileName.getGiftFile()[i]));
        }
        return  stackList.toArray(itemArray);
    }
}
