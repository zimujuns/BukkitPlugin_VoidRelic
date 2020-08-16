package com.mzimu.zmvoidrelic.VoidRelic.data;

import com.mzimu.zmvoidrelic.Main;
import org.bukkit.inventory.ItemStack;

public enum VoidRelicFileName {
    虚空遗物A1("A","1", new String[]{"bate1", "bate2", "bate3","bate4","bate5","bate6"},"虚空遗物A1"),
    虚空遗物A2("A","2", new String[]{"bate1", "bate2", "bate3","bate4","bate5","bate6"},"虚空遗物A2"),
    虚空遗物A3("A","3", new String[]{"bate1", "bate2", "bate3","bate4","bate5","bate6"},"虚空遗物A3"),
    虚空遗物A4("A","4", new String[]{"bate1", "bate2", "bate3","bate4","bate5","bate6"},"虚空遗物A4");
    private String fileName,grade,index,itemName;
    private String[] giftFile;
    private ItemStack itemStack;
    VoidRelicFileName(String grade,String index,String giftFile[],String itemName) {
        this.fileName = "xkyw"+grade+index;
        this.grade = grade;
        this.index = index;
        for(int i=0;i<giftFile.length;i++){
            giftFile[i] = grade+index+"_"+giftFile[i];
        }
        this.giftFile = giftFile;
        this.itemName = itemName;
    }

    public String getFileName() {
        return fileName;
    }

    public String getItemName() {
        return itemName;
    }

    public String getGrade() {
        return grade;
    }

    public String getIndex() {
        return index;
    }

    public String[] getGiftFile() {
        return giftFile;
    }


}
