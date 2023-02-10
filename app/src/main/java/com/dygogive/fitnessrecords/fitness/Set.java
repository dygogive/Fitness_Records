package com.dygogive.fitnessrecords.fitness;

public class Set {

    // one set has exercise, type of weight, weight, repeats
    private Exercise exercise;
    private WeightType weightType;
    private Weight weight;
    private Reps reps;

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public void setWeightType(WeightType weightType) {
        this.weightType = weightType;
    }

    public void setWeight(Weight weight) {
        this.weight = weight;
    }

    public void setReps(Reps reps) {
        this.reps = reps;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public WeightType getWeightType() {
        return weightType;
    }

    public Weight getWeight() {
        return weight;
    }

    public Reps getReps() {
        return reps;
    }

    public Set(Exercise exercise, WeightType weightType, Weight weight, Reps reps) {
        this.exercise = exercise;
        this.weightType = weightType;
        this.weight = weight;
        this.reps = reps;
    }
}