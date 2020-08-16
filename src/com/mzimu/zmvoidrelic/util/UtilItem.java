package com.mzimu.zmvoidrelic.util;

import java.util.List;

public class UtilItem {
    public static String getQuality(List<String> lore){
        switch (lore.get(lore.size()-1)){
            case "[瑕疵]":
                return "A";
            case "[完整]":
                return "B";
            case "[无暇]":
                return "C";
            case "[璀璨]":
                return "D";
            default:
                return "BASE";

        }
    }
}
