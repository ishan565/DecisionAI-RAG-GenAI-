package com.ishan.decisionai.model;

import java.util.List;
import java.util.Map;

public class DomainPack {

    private DecisionType decisionType;
    private List<String> criteria;

    public DomainPack(DecisionType decisionType, List<String> criteria) {
        this.decisionType = decisionType;
        this.criteria = criteria;
    }

    public DecisionType getDecisionType() {
        return decisionType;
    }

    public List<String> getCriteria() {
        return criteria;
    }

    public Map<String, Integer> getDefaultWeights() {
        return Map.of();
    }
}
