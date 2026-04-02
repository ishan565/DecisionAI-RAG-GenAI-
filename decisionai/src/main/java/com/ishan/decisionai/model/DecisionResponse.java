package com.ishan.decisionai.model;

import java.util.List;
import java.util.Map;

public class DecisionResponse {

    private String recommendation;
    private double optionAScore;
    private double optionBScore;
    private String explanation;
    private List<String> evidence;
    private double confidence;

    private Map<String, Double> contributionA;
    private Map<String, Double> contributionB;
    private String dominantFactor;
    private String weakestFactor;
    private String reasoning;

    public DecisionResponse(
            String recommendation,
            double optionAScore,
            double optionBScore,
            String explanation,
            List<String> evidence,
            double confidence,
            Map<String, Double> contributionA,
            Map<String, Double> contributionB,
            String dominantFactor,
            String weakestFactor,
            String reasoning
    ) {
        this.recommendation = recommendation;
        this.optionAScore = optionAScore;
        this.optionBScore = optionBScore;
        this.explanation = explanation;
        this.evidence = evidence;
        this.confidence = confidence;
        this.contributionA = contributionA;
        this.contributionB = contributionB;
        this.dominantFactor = dominantFactor;
        this.weakestFactor = weakestFactor;
        this.reasoning = reasoning;
    }

    // Getters
    public String getRecommendation() { return recommendation; }
    public double getOptionAScore() { return optionAScore; }
    public double getOptionBScore() { return optionBScore; }
    public String getExplanation() { return explanation; }
    public List<String> getEvidence() { return evidence; }
    public double getConfidence() { return confidence; }
    public Map<String, Double> getContributionA() { return contributionA; }
    public Map<String, Double> getContributionB() { return contributionB; }
    public String getDominantFactor() { return dominantFactor; }
    public String getWeakestFactor() { return weakestFactor; }
    public String getReasoning() { return reasoning; }
}
