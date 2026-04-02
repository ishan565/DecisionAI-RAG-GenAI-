package com.ishan.decisionai.model;

import java.util.List;

public class GenericDecisionRequest {

    private List<DecisionOption> options;
    private UserPreferences preferences;
    private DecisionType decisionType;
    public List<DecisionOption> getOptions() {
        return options;
    }

    public UserPreferences getPreferences() {
        return preferences;
    }
    public DecisionType getDecisionType() {
        return decisionType;
    }
}
