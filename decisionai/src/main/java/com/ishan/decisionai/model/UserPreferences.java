package com.ishan.decisionai.model;

import java.util.Map;

public class UserPreferences {

    // key = criteria name, value = importance weight (1–5)
    private Map<String, Integer> weights;

    public Map<String, Integer> getWeights() {
        return weights;
    }

    public void setWeights(Map<String, Integer> weights) {
        this.weights = weights;
    }
}
