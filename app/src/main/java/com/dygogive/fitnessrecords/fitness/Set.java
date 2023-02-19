package com.dygogive.fitnessrecords.fitness;

public class Set extends SetPattern {

    private WeightType weightType;
    private Weight weight;


    public void setWeightType(WeightType weightType) {
        this.weightType = weightType;
    }

    public void setWeight(Weight weight) {
        this.weight = weight;
    }

    public WeightType getWeightType() {
        return weightType;
    }

    public Weight getWeight() {
        return weight;
    }


    public Set(Exercise exercise, WeightType weightType, Weight weight, Reps reps) {
        super(exercise,reps);
        this.weightType = weightType;
        this.weight = weight;
    }
}