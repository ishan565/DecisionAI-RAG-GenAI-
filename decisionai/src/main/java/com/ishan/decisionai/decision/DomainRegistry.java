package com.ishan.decisionai.decision;

import com.ishan.decisionai.model.*;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DomainRegistry {

    private final Map<DecisionType, DomainPack> registry = new HashMap<>();


    public DomainRegistry() {

        registry.put(DecisionType.COLLEGE,
                new DomainPack(
                        DecisionType.COLLEGE,
                        List.of(
                                "tuitionCost",
                                "placementRate",
                                "campusLife",
                                "location",
                                "facultyQuality",
                                "researchOpportunities"
                        )
                ));

        registry.put(DecisionType.JOB,
                new DomainPack(
                        DecisionType.JOB,
                        List.of(
                                "salary",
                                "learningOpportunity",
                                "workLifeBalance",
                                "brandValue",
                                "growthPotential",
                                "stability"
                        )
                ));

        registry.put(DecisionType.RENT_BUY,
                new DomainPack(
                        DecisionType.RENT_BUY,
                        List.of(
                                "monthlyCost",
                                "longTermInvestment",
                                "flexibility",
                                "maintenanceCost",
                                "lifestyleFit",
                                "taxBenefits"
                        )
                ));

        registry.put(DecisionType.LAPTOP,
                new DomainPack(
                        DecisionType.LAPTOP,
                        List.of(
                                "performance",
                                "battery",
                                "portability",
                                "budget"
                        )
                ));
    }

    public DomainPack getDomainPack(DecisionType type) {
        return registry.get(type);
    }
}
