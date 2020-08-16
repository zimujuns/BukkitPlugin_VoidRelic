package com.mzimu.zmvoidrelic.trigger;

import com.mzimu.zmvoidrelic.Main;
import com.mzimu.zmvoidrelic.VoidRelic.data.VoidRelicPlayData;
import com.mzimu.zmvoidrelic.VoidRelic.data.VoidRelicFileName;
import com.mzimu.zmvoidrelic.VoidRelic.event.DungeonCacheInitialization;
import com.mzimu.zmvoidrelic.VoidRelic.event.PlayCarryJudgeEvent;
import com.mzimu.zmvoidrelic.VoidRelic.event.RollGiftEvent;
import com.mzimu.zmvoidrelic.VoidRelic.event.gui.ClickInventoryMainEvent;
import com.mzimu.zmvoidrelic.VoidRelic.event.gui.MainGui;
import com.mzimu.zmvoidrelic.VoidRelic.event.updata.VoidRelicUp;
import com.mzimu.zmvoidrelic.util.IsVoidRelic;
import com.mzimu.zmvoidrelic.util.UtilItem;
import de.erethon.dungeonsxl.event.dplayer.instance.game.DGamePlayerEscapeEvent;
import de.erethon.dungeonsxl.event.dplayer.instance.game.DGamePlayerFinishEvent;
import de.erethon.dungeonsxl.world.DGameWorld;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.world.WorldUnloadEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.*;

import java.util.*;

public class OnDungeonEvent {
    //存入的是玩家的虚空遗物缓存 并非奖励
    private Map<String, List<VoidRelicPlayData>> dungeonsVpodRelic = new HashMap<>();
    //用来判断Roll过的世界
    private List<String> isRollWorld = new ArrayList<>();
    public OnDungeonEvent(Plugin plugin) {
        new OnGamePlayLeaveEvent(plugin);
        new OnPlayerTeleportEvent(plugin);
        new OnWorldUnloadEvent(plugin);
        new OnGamePlayFinishEvent(plugin);

    }
    //传送触发
    public class OnPlayerTeleportEvent implements Listener {
        public OnPlayerTeleportEvent(Plugin plugin) {
            plugin.getServer().getPluginManager().registerEvents(this,plugin);
        }

        @EventHandler
        public void main(PlayerTeleportEvent playerTeleportEvent){
            /**
             * 这个If判断
             * 是加入游戏的判断
             *
             * 目的是将缓存中的虚空遗物获取出来
             * 然后进行随机
             * 随机后向玩家打开
             *
             * 有问题 请修改
             * BUG 进入游戏 后退出 会刷新两个 而且不会判断是否完成副本
             */
            Player p = playerTeleportEvent.getPlayer();
            String world = playerTeleportEvent.getTo().getWorld().getName();
            DGameWorld dGameWorld = DGameWorld.getByWorld(playerTeleportEvent.getTo().getWorld());
            //搜索是否有这个对局
            // 是加入的对局
            if(dGameWorld!=null && p.getInventory().getItemInOffHand().getType()!=Material.AIR){
                ItemStack handItem = p.getInventory().getItemInOffHand();
                //判断虚空遗物是否正确 且 是否是再同一世界传送
                if(world != playerTeleportEvent.getFrom().getWorld().getName() && IsVoidRelic.is(handItem)){
                    List<VoidRelicPlayData> voidRelicPlayDataList;
                    //判断缓存中是否有这个世界的玩家缓存 若为获得则是第一次 进行初始化 若获得 就直接获取
                    if(dungeonsVpodRelic.containsKey(world))
                        voidRelicPlayDataList = dungeonsVpodRelic.get(world);
                    else
                        voidRelicPlayDataList = new ArrayList<VoidRelicPlayData>();
                    //将数据存入
                    voidRelicPlayDataList.add(new VoidRelicPlayData(p,VoidRelicFileName.valueOf(handItem.getItemMeta().getDisplayName()),UtilItem.getQuality(handItem.getItemMeta().getLore())));
                    dungeonsVpodRelic.put(world, voidRelicPlayDataList);
                    //扣除物品
                    handItem.setAmount(handItem.getAmount()-1);
                    p.getInventory().setItemInOffHand(handItem);
                }

            }


        }

    }
    //删除在RewardDisplay的缓存 避免上一局Roll的物品保存到当前对局
    public class OnWorldUnloadEvent implements Listener{
        public OnWorldUnloadEvent(Plugin plugin) {
            plugin.getServer().getPluginManager().registerEvents(this,plugin);
        }
        @EventHandler
        public void main(WorldUnloadEvent worldUnloadEvent){
            if(DGameWorld.getByWorld(worldUnloadEvent.getWorld())!= null){
                //简单的初始化操作
                new DungeonCacheInitialization(worldUnloadEvent.getWorld().getName(),isRollWorld,dungeonsVpodRelic);
            }

        }

    }


    /**
     * 直接离开事件
     */
    public class OnGamePlayLeaveEvent implements Listener{
        public OnGamePlayLeaveEvent(Plugin plugin){
            plugin.getServer().getPluginManager().registerEvents(this,plugin);
        }
        @EventHandler
        public void main(DGamePlayerEscapeEvent dGamePlayerEscapeEvent){
            //简单的消息
            Player player = dGamePlayerEscapeEvent.getDPlayer().getPlayer();
            List<VoidRelicPlayData> list = dungeonsVpodRelic.get(dGamePlayerEscapeEvent.getDPlayer().getWorld().getName());
            //判断是否没有导入 没有导入就不提示消息
            if(list!=null){
                for(VoidRelicPlayData v : list){
                    if(v.getPlayer().getName().equals(player.getName())){
                        player.sendMessage("§a[虚空遗物]§7提醒: 您虽然装备了虚空遗物，但是因为直接退出的原因 没有解开遗物 无法获得物品!虚空遗物已被销毁无法恢复");
                    }
                }
            }
        }
    }
    /**
     * 完成事件
     */
    public class OnGamePlayFinishEvent implements Listener{
        public OnGamePlayFinishEvent(Plugin plugin){
            plugin.getServer().getPluginManager().registerEvents(this,plugin);
        }
        @EventHandler
        public void main(DGamePlayerFinishEvent dGamePlayerFinishEvent){

            //在这里判断是否是规定副本


            //判断这个玩家是否为带核桃的玩家 若不是 则不允许进入奖励选择界面
            new PlayCarryJudgeEvent(dungeonsVpodRelic,dGamePlayerFinishEvent,isRollWorld);




        }

    }
}
