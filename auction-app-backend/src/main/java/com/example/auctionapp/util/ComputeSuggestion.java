package com.example.auctionapp.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComputeSuggestion {
    public static int computeLevenshteinDistance(CharSequence lhs, CharSequence rhs) {
        int len0 = lhs.length() + 1;
        int len1 = rhs.length() + 1;

        // the array of distances
        int[] cost = new int[len0];
        int[] newcost = new int[len0];

        // initial cost of skipping prefix in String s0
        for (int i = 0; i < len0; i++) cost[i] = i;

        // dynamically computing the array of distances
        // transformation cost for each letter in s1
        for (int j = 1; j < len1; j++) {
            // initial cost of skipping prefix in String s1
            newcost[0] = j;

            // transformation cost for each letter in s0
            for(int i = 1; i < len0; i++) {
                // matching current letters in both strings
                int match = (lhs.charAt(i - 1) == rhs.charAt(j - 1)) ? 0 : 1;

                // computing cost for each transformation
                int costReplace = cost[i - 1] + match;
                int costInsert  = cost[i] + 1;
                int costDelete  = newcost[i - 1] + 1;

                // keep minimum cost
                newcost[i] = Math.min(Math.min(costInsert, costDelete), costReplace);
            }

            // swap cost/newcost arrays
            int[] swap = cost;
            cost = newcost;
            newcost = swap;
        }

        // the distance is the cost for transforming all letters in both strings
        return cost[len0 - 1];
    }

    public static String suggestCorrection(List<String> productNames, String query) {
        final int DISTANCE_THRESHOLD = 2;
        Map<String, Integer> combinationFrequency = new HashMap<>();

        // user query into words
        String[] queryWords = query.toLowerCase().split("\\W+");

        for (String productName : productNames) {
            String[] productWords = productName.toLowerCase().split("\\W+");

            // to generate all possible combinations of product words that match length of query words
            for (int i = 0; i <= productWords.length - queryWords.length; i++) {
                StringBuilder sb = new StringBuilder();
                boolean isCloseMatch = true;

                for (int j = 0; j < queryWords.length; j++) {
                    int distance = computeLevenshteinDistance(queryWords[j], productWords[i + j]);
                    if (distance > DISTANCE_THRESHOLD) {
                        isCloseMatch = false;
                        break;
                    }
                    sb.append(productWords[i + j]).append(" ");
                }

                if (isCloseMatch) {
                    String matchedPhrase = sb.toString().trim();
                    combinationFrequency.put(matchedPhrase, combinationFrequency.getOrDefault(matchedPhrase, 0) + 1);
                }
            }
        }

        if (combinationFrequency.isEmpty()) {
            return null;
        }

        // return combination with highest frequency
        return Collections.max(combinationFrequency.entrySet(), Map.Entry.comparingByValue()).getKey();
    }
}
