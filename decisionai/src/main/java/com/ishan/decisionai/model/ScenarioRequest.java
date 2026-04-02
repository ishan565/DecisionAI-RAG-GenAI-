package com.ishan.decisionai.model;

import java.util.List;

public class ScenarioRequest {

    private DecisionType decisionType;
    private List<DecisionOption> options;
    private List<Scenario> scenarios;

    public DecisionType getDecisionType() {
        return decisionType;
    }

    public List<DecisionOption> getOptions() {
        return options;
    }

    public List<Scenario> getScenarios() {
        return scenarios;
    }
}
