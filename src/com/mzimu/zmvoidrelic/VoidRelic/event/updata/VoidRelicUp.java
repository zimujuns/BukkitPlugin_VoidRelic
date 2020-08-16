package com.mzimu.zmvoidrelic.VoidRelic.event.updata;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class VoidRelicUp {
    public VoidRelicUp(){
        return;
    }
    public static Builder builder(){
        return new Builder();
    }
    public enum Quality{
        A("[瑕疵]",25,new int[]{35,20,5}),
        B("[完整]",50,new int[]{33,22,5}),
        C("[无暇]",75,new int[]{29,26,5}),
        D("[璀璨]",100,new int[]{25,28,7}),
        BASE(null,0,new int[]{40,15,5});
        private String a;
        private int num;
        private int[] change;

        Quality(String a,int num,int[] change) {
            this.a = a;
            this.num = num;
            this.change = change;
        }

        public int[] getChange() {
            return change;
        }

        //扣除的虚空元素 的数量
        public int getNum() {
            return num;
        }

        //获得内容
        public String getAttr() {
            return a;
        }

        //数组化
        public static String[] toArray(){
            String[] array = {A.getAttr(),B.getAttr(),C.getAttr(),D.getAttr()};
            return array;
        }


    }


    public static class Builder{
        public static String[] pArray = VoidRelicUp.Quality.toArray();
        private Player player;
        private ItemStack itemStack;
        private int i;

        //获取玩家的
        public Builder setPlayer(Player player) {
            this.player = player;
            return this;
        }

        //获取目标的
        public Builder setItemStack(ItemStack itemStack) {
            this.itemStack = itemStack;
            return this;
        }

        //用来获取对应品级的
        public Builder setI(int i) {
            this.i = i;
            return this;
        }

        public void stack(){
            ItemMeta itemMeta = itemStack.getItemMeta();
            List<String> lore = itemMeta.getLore();
            String a=pArray[i];
            lore.add(a);
            itemMeta.setLore(lore);
            player.getInventory().getItemInMainHand().setItemMeta(itemMeta);
            player.closeInventory();
        }
    }
}
