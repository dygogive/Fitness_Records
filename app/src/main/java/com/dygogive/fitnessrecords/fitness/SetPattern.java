package com.dygogive.fitnessrecords.fitness;

public class SetPattern {

    // one set has exercise, type of weight, weight, repeats
    private Exercise exercise;
    private Reps reps;

    public void setExercise(Exercise exercise) {
        this.exercise = exercise;
    }

    public void setReps(Reps reps) {
        this.reps = reps;
    }

    public Exercise getExercise() {
        return exercise;
    }

    public Reps getReps() {
        return reps;
    }

    public SetPattern(Exercise exercise, Reps reps) {
        this.exercise = exercise;
        this.reps = reps;
    }
}
