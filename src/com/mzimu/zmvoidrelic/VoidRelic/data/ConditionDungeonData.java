package com.mzimu.zmvoidrelic.VoidRelic.data;

public class ConditionDungeonData {
    //这里是 对应的虚空遗物副本
    public static String[] dungeon = {"bate","bate1"};

    public Boolean isDungeon(String name){
        for(int i=0;i<dungeon.length;i++){
            if(dungeon[i].equalsIgnoreCase(name)){
                return true;
            }
        }
        return false;
    }
}
