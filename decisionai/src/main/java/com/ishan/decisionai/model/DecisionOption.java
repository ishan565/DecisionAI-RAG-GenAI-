package com.ishan.decisionai.model;

import java.util.Map;

public class DecisionOption {

    private String name;
    private Map<String, Integer> criteriaScores;

    public DecisionOption() {}

    public DecisionOption(String name, Map<String, Integer> criteriaScores) {
        this.name = name;
        this.criteriaScores = criteriaScores;
    }

    public String getName() {
        return name;
    }

    public Map<String, Integer> getCriteriaScores() {
        return criteriaScores;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCriteriaScores(Map<String, Integer> criteriaScores) {
        this.criteriaScores = criteriaScores;
    }
}
