package com.mzimu.zmvoidrelic.VoidRelic.event;

import com.mzimu.zmvoidrelic.VoidRelic.data.VoidRelicFileName;
import com.mzimu.zmvoidrelic.VoidRelic.event.updata.VoidRelicUp;
import io.lumine.xikage.mythicmobs.MythicMobs;
import org.bukkit.inventory.ItemStack;

import java.util.Random;


public class RollGiftEvent {

    private RollGiftEvent(){
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{
        private VoidRelicFileName voidRelicFileName;

        public Builder setVoidRelicFileName(VoidRelicFileName voidRelicFileName) {
            this.voidRelicFileName = voidRelicFileName;
            return this;
        }


        /**
         * 通过 在VoidRelicUp的枚举
         * 来判断几个参数
         *              对应的虚空遗物
         *                      掉落物
         *                      概率
         *
         * @param a
         * @return
         */
        public ItemStack start(String a){
            Random random = new Random();
            int i = random.nextInt(60)+1;
            VoidRelicUp.Quality v = VoidRelicUp.Quality.valueOf(a) == null? VoidRelicUp.Quality.BASE : VoidRelicUp.Quality.valueOf(a) ;
            if(i<=v.getChange()[0]){
                i = random.nextInt(3);
            }else if(i<=v.getChange()[1]){
                i= random.nextInt(2)+3;
            }else{
                i=5;
            }
            //获取MM的物品的代码
            ItemStack itemStack = MythicMobs.inst()
                    .getItemManager()
                    .getItemStack(voidRelicFileName.getGiftFile()[i]);
            return itemStack;
        }
    }

}
