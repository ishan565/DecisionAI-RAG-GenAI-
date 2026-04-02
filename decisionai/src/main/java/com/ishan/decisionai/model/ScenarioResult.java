package com.ishan.decisionai.model;

public class ScenarioResult {

    private String scenarioName;
    private String recommendation;
    private double score;
    private double confidence;

    public ScenarioResult(String scenarioName,
                          String recommendation,
                          double score,
                          double confidence) {
        this.scenarioName = scenarioName;
        this.recommendation = recommendation;
        this.score = score;
        this.confidence = confidence;
    }

    public String getScenarioName() {
        return scenarioName;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public double getScore() {
        return score;
    }

    public double getConfidence() {
        return confidence;
    }
}
