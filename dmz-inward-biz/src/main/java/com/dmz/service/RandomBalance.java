package com.dmz.service;

import java.util.*;

/**
 * @author dmz
 * @date 2016/12/29
 */
public class RandomBalance extends LoadBalance<String> {

    private Random random = new Random();

    private Integer total;

    private List<HitSection> candidatesWithWeight = new ArrayList<>();

    public RandomBalance() {
    }

    public RandomBalance(Map<Integer, String> factors) {
        initWeightCandidates(factors);
    }

    public RandomBalance(List<String> factors) {
        candidates.addAll(factors);
    }

    private void initWeightCandidates(Map<Integer, String> factors) {

        int total = 0;

        Iterator<Map.Entry<Integer, String>> ite = factors.entrySet().iterator();
        while (ite.hasNext()) {
            Map.Entry<Integer, String> factor = ite.next();
            if (factor.getKey() <= 0) {
                ite.remove();
            } else {
                total += factor.getKey().intValue();
            }
        }

        if (factors != null && !factors.isEmpty()) {
            candidates.addAll(factors.values());
            this.total = new Integer(total);
            loadWeightCandidates(factors);
        }
    }

    @Override
    public synchronized String elect() {
        return candidates == null || candidates.isEmpty() ?
                null : candidates.get(random.nextInt(candidates.size()));
    }

    @Override
    public synchronized String electWithWeight() {

        if (total != null && candidatesWithWeight != null) {
            for (HitSection hitSection : candidatesWithWeight) {
                if (hitSection.isHit(Math.abs(random()))) {
                    return hitSection.hitKey;
                }
            }
        }
        return null;
    }

    private void loadWeightCandidates(Map<Integer, String> factors) {

        List<Integer> weight = new ArrayList<>(factors.keySet());
        Collections.sort(weight);
        int begin = 0;
        for (Integer integer : weight) {
            int start = begin;
            int end = integer;
            HitSection hitSection = new HitSection(start, end, factors.get(integer));
            candidatesWithWeight.add(hitSection);
            begin = end;
        }
    }

    private synchronized int random() {
        return random.nextInt() % total;
    }

    private class HitSection {

        int start;
        int end;
        String hitKey;

        public HitSection(int start, int end, String hitKey) {
            this.start = start;
            this.end = end;
            this.hitKey = hitKey;
        }

        public boolean isHit(int random) {

            if (random >= start && random < end) {
                return true;
            }
            return false;
        }

    }
}
