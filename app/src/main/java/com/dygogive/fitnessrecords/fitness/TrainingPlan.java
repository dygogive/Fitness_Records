package com.dygogive.fitnessrecords.fitness;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TrainingPlan {
    private List<Exercise> exercises = new LinkedList<>();

    public void addExercise(Exercise exercise) {
        exercises.add(exercise);
    }

    public List<Exercise> getExercises() {
        return exercises;
    }
}
