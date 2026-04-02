package com.ishan.decisionai.decision;

import com.ishan.decisionai.model.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DecisionEngine {

    public DecisionResponse decide(List<DecisionOption> options, UserPreferences prefs) {

        if(options == null || options.size() < 2)
            throw new RuntimeException("AI failed to generate 2 options");

        Map<String,Integer> weights = prefs.getWeights();

        DecisionOption best = null;
        DecisionOption second = null;

        double bestScore = -1;
        double secondScore = -1;

        Map<String,Double> bestContrib = new HashMap<>();
        Map<String,Double> secondContrib = new HashMap<>();

        for(DecisionOption option : options){

            if(option.getCriteriaScores()==null || option.getCriteriaScores().isEmpty())
                continue;

            double total = 0;
            Map<String,Double> contribution = new HashMap<>();

            for(String c : weights.keySet()){
                int score = option.getCriteriaScores().getOrDefault(c,0);
                int weight = weights.get(c);
                double contrib = score * weight;
                total += contrib;
                contribution.put(c,contrib);
            }

            if(total > bestScore){
                secondScore = bestScore;
                second = best;
                secondContrib = bestContrib;

                bestScore = total;
                best = option;
                bestContrib = contribution;

            }else if(total > secondScore){
                secondScore = total;
                second = option;
                secondContrib = contribution;
            }
        }

        if(best == null)
            throw new RuntimeException("No valid option scored. AI output invalid.");

        double confidence = (bestScore==0)?0:(bestScore-secondScore)/bestScore;

        String dominantFactor="N/A";
        double maxDiff=0;

        for(String c:bestContrib.keySet()){
            double diff=Math.abs(bestContrib.get(c)-secondContrib.getOrDefault(c,0.0));
            if(diff>maxDiff){maxDiff=diff;dominantFactor=c;}
        }

        String weakestFactor="N/A";
        double minVal=Double.MAX_VALUE;

        for(String c:bestContrib.keySet()){
            if(bestContrib.get(c)<minVal){minVal=bestContrib.get(c);weakestFactor=c;}
        }

        String reasoning="Decision driven mainly by '"+dominantFactor+
                "' while weakest area '"+weakestFactor+"'";

        return new DecisionResponse(
                best.getName(),
                bestScore,
                secondScore,
                "",
                List.of(),
                confidence,
                bestContrib,
                secondContrib,
                dominantFactor,
                weakestFactor,
                reasoning
        );
    }
}
