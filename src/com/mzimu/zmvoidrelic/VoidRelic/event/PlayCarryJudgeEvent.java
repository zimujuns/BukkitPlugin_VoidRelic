package com.mzimu.zmvoidrelic.VoidRelic.event;

import com.mzimu.zmvoidrelic.Main;
import com.mzimu.zmvoidrelic.VoidRelic.data.VoidRelicPlayData;
import de.erethon.dungeonsxl.event.dplayer.instance.game.DGamePlayerFinishEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Map;

public class PlayCarryJudgeEvent {

    public PlayCarryJudgeEvent(Map<String, List<VoidRelicPlayData>> dungeonsVpodRelic, DGamePlayerFinishEvent dGamePlayerFinishEvent, List<String> isRollWorld) {
        String world = dGamePlayerFinishEvent.getDPlayer().getDGroup().getGameWorld().getWorld().getName();
        List<VoidRelicPlayData> voidRelicPlayDataList = dungeonsVpodRelic.get(world);
        if (voidRelicPlayDataList!=null) {
            for (int i = 0; i < voidRelicPlayDataList.size(); i++) {
                //首先判断这个玩家是否为带核桃的玩家 若不是 则不允许进入奖励选择界面
                //通过判断是否为空来判断是否为第一次初始化 避免造成重复Roll物品的现象
                if (voidRelicPlayDataList.get(i).getPlayer().getName() == dGamePlayerFinishEvent.getDPlayer().getPlayer().getName()) {
                    if (!isRollWorld.contains(world)) {
                        for (int j = 0; j < voidRelicPlayDataList.size(); j++) {
                            ItemStack item = RollGiftEvent.builder()
                                    .setVoidRelicFileName(voidRelicPlayDataList.get(i).getVoidRelicFileName())
                                    .start(voidRelicPlayDataList.get(i).getQuality());
                            Main.rewardDisplay.putItemStack(world, item);
                        }
                        isRollWorld.add(world);
                    }
                    //必须停止
                    dGamePlayerFinishEvent.setCancelled(true);
                    //将roll的物品显示在玩家面前
                    Main.rewardDisplay.main(world
                            , dGamePlayerFinishEvent.getDPlayer().getPlayer());

                }
            }
        }
    }
}
