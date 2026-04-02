package com.ishan.decisionai.rag;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.util.*;

@Service
public class LocalDocumentRetriever {

    public List<String> retrieve(String decisionType, String dominantFactor) {

        try {

            String file = "knowledge/" + decisionType.toLowerCase() + ".txt";

            List<String> lines = Files.readAllLines(
                    new ClassPathResource(file).getFile().toPath()
            );

            String query = decisionType + " " + dominantFactor + " decision";

            Map<String,Integer> queryVec = TextVectorizer.toVector(query);

            List<Map.Entry<String,Double>> scored = new ArrayList<>();

            for(String line : lines){

                Map<String,Integer> vec = TextVectorizer.toVector(line);
                double sim = CosineSimilarity.similarity(queryVec, vec);

                scored.add(Map.entry(line, sim));
            }

            scored.sort((a,b)->Double.compare(b.getValue(), a.getValue()));

            List<String> top = new ArrayList<>();
            for(int i=0;i<Math.min(3,scored.size());i++)
                top.add(scored.get(i).getKey());

            return top;

        } catch (Exception e){
            return List.of();
        }
    }
}
