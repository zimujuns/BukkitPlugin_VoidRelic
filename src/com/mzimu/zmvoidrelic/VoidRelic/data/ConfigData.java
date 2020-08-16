package com.mzimu.zmvoidrelic.VoidRelic.data;

import com.mzimu.zmvoidrelic.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.*;

import java.util.Map;
import java.util.Set;

public class ConfigData {
    private FileConfiguration fileConfiguration;
    private Plugin plugin;

    public ConfigData(Plugin plugin) {
        this.fileConfiguration = plugin.getConfig();
        this.plugin = plugin;
    }

    public void setVoidNumber(String name,int num){
        fileConfiguration.set(name,num);
    }

    public void save(){
        Set<Map.Entry<String,Integer>> setEntry =  Main.voidNumber.entrySet();
        for(Map.Entry<String,Integer> entry : setEntry){
            fileConfiguration.set(entry.getKey(),entry.getValue());
        }
        plugin.saveConfig();
    }

    public Integer getVoidNumber(String name){
        if(fileConfiguration.isInt(name)){
            return fileConfiguration.getInt(name);
        }else{
            fileConfiguration.set(name,0);
            return 0;
        }

    }
}
