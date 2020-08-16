package com.mzimu.zmvoidrelic;

import com.mzimu.zmvoidrelic.VoidRelic.data.ConfigData;
import com.mzimu.zmvoidrelic.VoidRelic.event.gui.RewardDisplay;
import com.mzimu.zmvoidrelic.command.CommandMain;
import com.mzimu.zmvoidrelic.trigger.OnDungeonEvent;
import com.mzimu.zmvoidrelic.trigger.OnPlayLeaveEvent;
import com.mzimu.zmvoidrelic.trigger.OnPluginTrigger;
import com.mzimu.zmvoidrelic.trigger.triggerMain;
import io.lumine.xikage.mythicmobs.MythicMobs;
import io.lumine.xikage.mythicmobs.items.ItemManager;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 修改时间
 * @Time 2020/2/23 02点07分
 * 修改了几个空指针的BUG
 *
 * @Time 2020/2/22 01点36分
 * 在点击物品栏中的代码添加传送事件
 * 修复了不显示退出提示的BUG
 * 修复了存储虚空点数的BUG
 * 修复了中键别的物品的空指针报错问题
 *
 * @Time 2020/2/21 14点09分
 * 修复了组队玩家手持虚空遗物进入副本 达成条件未获取物品的BUG
 * 整理了代码 将部分在触发器的事件 隔开放置到event
 * 添加了ConditionDungeonData 目的用于存放条件满足的副本名称 并且用于判断
 *              计划对VV做进一步的支持
 *
 *
 *
 *
 * @Time 2020/2/20 19点09分
 * 增加ConfigData 增加变量voidNumber 增加OnPlayerLeaveEvent触发器 修改了OnLeaveDungeon代码
 *          通过触发器来添加用于存储虚空点数的 voidNumber 变量
 *          但有组队玩家手持虚空遗物进入副本 达成条件未获取物品的BUG
 *
 *
 *
 * @Time 2020/2/20 15点27分
 * 增加品质提升
 *
 * @Time 2020/2/20 13点21分
 * 增加详情显示
 */
public class Main extends JavaPlugin {

    public static List<ItemStack> voidRelicItem = new ArrayList<>();

    public static Map<String,Integer> voidNumber = new HashMap<>();
    public static RewardDisplay rewardDisplay;
    public static ConfigData configData;

    @Override
    public void onDisable() {
        configData.save();
    }

    @Override
    public void onEnable() {
        configData = new ConfigData(this);
        rewardDisplay = new RewardDisplay();



        for(Player player : this.getServer().getOnlinePlayers()){
            String name = player.getName();
            voidNumber.put(name,configData.getVoidNumber(name));
        }

        new OnPluginTrigger(this);
        new triggerMain(this);

        getCommand("zmvr").setExecutor(new CommandMain());
    }

}
