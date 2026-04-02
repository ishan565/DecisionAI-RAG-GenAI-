package com.ishan.decisionai.controller;

import com.ishan.decisionai.decision.DecisionEngine;
import com.ishan.decisionai.model.*;
import com.ishan.decisionai.service.OpenAIService;
import org.springframework.web.bind.annotation.*;
import com.ishan.decisionai.rag.LocalDocumentRetriever;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/decision")
@CrossOrigin
public class DecisionController {

    private final DecisionEngine decisionEngine;
    private final OpenAIService openAIService;
    private final LocalDocumentRetriever retriever;

    public DecisionController(DecisionEngine decisionEngine,
                              OpenAIService openAIService,
                              LocalDocumentRetriever retriever) {
        this.decisionEngine = decisionEngine;
        this.openAIService = openAIService;
        this.retriever = retriever;
    }



    // ---------- MAIN DECISION ----------
    @PostMapping("/generic")
    public DecisionResponse decide(@RequestBody GenericDecisionRequest request) {

        DecisionResponse base =
                decisionEngine.decide(
                        request.getOptions(),
                        request.getPreferences()
                );

        // 🔥 RAG STARTS HERE
        List<String> facts =
                retriever.retrieve(
                        request.getDecisionType().name(),
                        base.getDominantFactor()
                );

        String aiReport =
                openAIService.generateExplanation(
                        request.getDecisionType().name(),
                        base.getRecommendation(),
                        base.getOptionAScore(),
                        base.getOptionBScore(),
                        base.getConfidence(),
                        base.getDominantFactor(),
                        base.getWeakestFactor(),
                        base.getReasoning(),
                        facts
                );

        return new DecisionResponse(
                base.getRecommendation(),
                base.getOptionAScore(),
                base.getOptionBScore(),
                aiReport,
                facts,
                base.getConfidence(),
                base.getContributionA(),
                base.getContributionB(),
                base.getDominantFactor(),
                base.getWeakestFactor(),
                base.getReasoning()
        );
    }



    // ---------- SCENARIO ----------
    @PostMapping("/scenario")
    public List<ScenarioResult> simulateScenario(@RequestBody ScenarioRequest request) {

        List<ScenarioResult> results = new ArrayList<>();

        for (Scenario scenario : request.getScenarios()) {

            UserPreferences prefs = new UserPreferences();
            prefs.setWeights(scenario.getWeights());

            DecisionResponse decision =
                    decisionEngine.decide(request.getOptions(), prefs);

            results.add(
                    new ScenarioResult(
                            scenario.getScenarioName(),
                            decision.getRecommendation(),
                            decision.getOptionAScore(),
                            decision.getConfidence()
                    )
            );
        }

        return results;
    }
}
