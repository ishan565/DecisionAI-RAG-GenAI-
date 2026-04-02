package com.ishan.decisionai.model;

import java.util.Map;

public class Scenario {

    private String scenarioName;
    private Map<String, Integer> weights;

    public String getScenarioName() {
        return scenarioName;
    }

    public Map<String, Integer> getWeights() {
        return weights;
    }
}
