package com.mzimu.zmvoidrelic.VoidRelic.event;

import com.mzimu.zmvoidrelic.VoidRelic.data.VoidRelicFileName;
import io.lumine.xikage.mythicmobs.MythicMobs;
import org.bukkit.inventory.ItemStack;

public class GetVoidRelicEvent {
    // ， 代表分段
    public GetVoidRelicEvent() {
    }

    public static ItemStack createItemStack(String name){
        VoidRelicFileName voidRelicFileName = VoidRelicFileName.valueOf(name);
        ItemStack item = MythicMobs.inst().getItemManager().getItemStack(voidRelicFileName.getFileName());
        return item;
    }

    //通过MM来获取对应的物品
    public static ItemStack getGiftItem(int grade,int num){
        if(65<=grade || grade<=90){
            return createItemStack("虚空遗物"+(char)grade+num);
        }else{
            throw new NullPointerException("您输入的值超出范围，请多找ASCII表中的A-Z范围进行赋值");
        }

    }




}
