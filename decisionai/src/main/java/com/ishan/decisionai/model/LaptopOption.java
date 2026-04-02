package com.ishan.decisionai.model;

public class LaptopOption {

    private String name;
    private int performanceScore;
    private int batteryScore;
    private int portabilityScore;
    private int budgetScore;

    public LaptopOption(String name, int performanceScore, int batteryScore, int portabilityScore, int budgetScore) {
        this.name = name;
        this.performanceScore = performanceScore;
        this.batteryScore = batteryScore;
        this.portabilityScore = portabilityScore;
        this.budgetScore = budgetScore;
    }

    public String getName() { return name; }
    public int getPerformanceScore() { return performanceScore; }
    public int getBatteryScore() { return batteryScore; }
    public int getPortabilityScore() { return portabilityScore; }
    public int getBudgetScore() { return budgetScore; }
}
