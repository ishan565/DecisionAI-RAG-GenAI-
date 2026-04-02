package com.ishan.decisionai.rag;

import java.util.*;

public class TextVectorizer {

    public static Map<String, Integer> toVector(String text) {

        Map<String, Integer> map = new HashMap<>();

        text = text.toLowerCase().replaceAll("[^a-z0-9 ]", " ");

        for (String word : text.split("\\s+")) {
            if (word.length() < 3) continue;
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        return map;
    }
}
