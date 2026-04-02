package com.ishan.decisionai.service;

import java.util.HashMap;
import java.util.Map;

public class CriteriaMapper {

    private static final Map<String, String> semanticMap = new HashMap<>();

    static {

        semanticMap.put("performance", "performance");
        semanticMap.put("speed", "performance");
        semanticMap.put("cpu", "performance");
        semanticMap.put("power", "performance");

        semanticMap.put("battery", "battery");
        semanticMap.put("batterylife", "battery");
        semanticMap.put("backup", "battery");

        semanticMap.put("weight", "portability");
        semanticMap.put("size", "portability");
        semanticMap.put("thinness", "portability");
        semanticMap.put("portability", "portability");

        semanticMap.put("price", "budget");
        semanticMap.put("cost", "budget");
        semanticMap.put("budget", "budget");
        semanticMap.put("value", "budget");
    }

    public static String map(String rawKey) {
        String normalized = rawKey.toLowerCase()
                .replace(" ", "")
                .replace("_", "");
        return semanticMap.getOrDefault(normalized, null);
    }
}
