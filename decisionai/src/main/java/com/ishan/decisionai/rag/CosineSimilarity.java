package com.ishan.decisionai.rag;

import java.util.Map;

public class CosineSimilarity {

    public static double similarity(Map<String,Integer> a, Map<String,Integer> b){

        double dot = 0;
        double magA = 0;
        double magB = 0;

        for(String key : a.keySet()){
            int x = a.get(key);
            magA += x*x;

            if(b.containsKey(key))
                dot += x * b.get(key);
        }

        for(int y : b.values())
            magB += y*y;

        if(magA == 0 || magB == 0) return 0;

        return dot / (Math.sqrt(magA) * Math.sqrt(magB));
    }
}
