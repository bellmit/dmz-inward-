package com.dmz.service;

import java.util.*;

/**
 * @author dmz
 * @date 2016/12/29
 */
public class RoundBalance extends LoadBalance<String> {

    private int candidatesIndex;

    private int candidatesWeightIndex;

    private List<String> candidatesWithWeight = new ArrayList<>();

    public RoundBalance() {
    }

    public RoundBalance(Map<Integer, String> factors) {

        Iterator<Map.Entry<Integer, String>> ite = factors.entrySet().iterator();
        while (ite.hasNext()) {
            Map.Entry<Integer, String> factor = ite.next();
            if (factor.getKey() <= 0) {
                ite.remove();
            }
        }
        Integer min = Collections.min(factors.keySet());
        loadCandidatesWeight(min, factors);

    }

    public RoundBalance(List<String> factors) {
        candidates.addAll(factors);
    }

    private void loadCandidatesWeight(Integer min, Map<Integer, String> factors) {

//        List<String> candidatesWeight = new ArrayList<>();
        if (allCanMod(min, factors.keySet())) {
            for (Integer integer : factors.keySet()) {
                int count = integer / min;
                for (int start = 0; start < count; start++) {
                    candidatesWithWeight.add(factors.get(integer));
                }
            }
        } else {
            Set<Map.Entry<Integer, String>> entrys = factors.entrySet();
            for (Map.Entry<Integer, String> entry : entrys) {
                for (int i = 0; i < entry.getKey(); i++) {
                    candidatesWithWeight.add(entry.getValue());
                }
            }
        }

//        candidatesWithWeight.addAll(candidatesWeight);
    }

    private boolean allCanMod(Integer min, Set<Integer> set) {

        for (Integer integer : set) {
            if (integer % min != 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public synchronized String elect() {

        if (candidates == null || candidates.isEmpty()) {
            return null;
        }

        if (candidatesIndex >= candidates.size()) {
            candidatesIndex = 0;
        }
        return candidates.get(candidatesIndex++);
    }

    @Override
    public synchronized String electWithWeight() {

        if (candidatesWithWeight == null || candidatesWithWeight.isEmpty()) {
            return null;
        }
        if (candidatesWeightIndex >= candidatesWithWeight.size()) {
            candidatesWeightIndex = 0;
        }
        return candidatesWithWeight.get(candidatesWeightIndex);
    }

}
