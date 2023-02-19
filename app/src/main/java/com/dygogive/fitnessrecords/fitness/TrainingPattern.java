package com.dygogive.fitnessrecords.fitness;

import java.util.LinkedList;
import java.util.List;

public class TrainingPattern {
    private List<SetPattern> sets = new LinkedList<>();

    private String name;

    public TrainingPattern(String patternName) {
        name = patternName;
    }

    public void addSet(SetPattern set) {
        sets.add(set);
    }

    public List<SetPattern> getSets() {
        return sets;
    }

    public String getName() {
        return name;
    }
}
