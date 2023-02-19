package com.dygogive.fitnessrecords.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Patterns;

import androidx.annotation.Nullable;

import com.dygogive.fitnessrecords.CreateTrainPatternActivity;
import com.dygogive.fitnessrecords.fitness.Exercise;
import com.dygogive.fitnessrecords.fitness.Reps;
import com.dygogive.fitnessrecords.fitness.Set;
import com.dygogive.fitnessrecords.fitness.SetPattern;
import com.dygogive.fitnessrecords.fitness.Training;
import com.dygogive.fitnessrecords.fitness.TrainingPattern;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PatternsSQL {

    private Context context;
    private DBhelper dBhelper;
    private SQLiteDatabase database;

    //Constructor
    public PatternsSQL(Context con, String dbName, int ver) {
        context = con;
        dBhelper = new DBhelper(context, dbName, ver);
        database = dBhelper.getWritableDatabase();
    }

    //return database
    public SQLiteDatabase getDatabase() {
        return database;
    }

    //put TrainingPattern to sql database
    public void putInBase(TrainingPattern trainingPattern){

        //закинути масив базових вправ у таблицю з вправами
        ContentValues cv = new ContentValues();
        String patternName = trainingPattern.getName();
        List<SetPattern> sets = trainingPattern.getSets();

        for ( SetPattern set : sets ) {
            cv.put("pattern_name", patternName);
            cv.put("exercise", set.getExercise().getName());
            cv.put("repeats", set.getReps().getRepeats());
            database.insert("patterns", null, cv);
            cv.clear();
        }
    }

    //Create list of objects of TrainingPatterns from SQL table
    public List<TrainingPattern> getTrainPatterns(){

        List<TrainingPattern> trainingPatterns = new ArrayList<>();

        Cursor cursor  =  database.query("patterns",
                null,null,null,null,null,null);


        if (cursor.moveToFirst()) {
            int i_id = cursor.getColumnIndex("_id");
            int i_pattern_name = cursor.getColumnIndex("pattern_name");
            int i_exercise = cursor.getColumnIndex("exercise");
            int i_repeats = cursor.getColumnIndex("repeats");
            do{

                int id  = cursor.getInt(i_id);
                String pattern_name  = cursor.getString(i_pattern_name);
                String exercise_name  = cursor.getString(i_exercise);
                int repeats = cursor.getInt(i_repeats);

                Exercise exercise = new Exercise(exercise_name);
                Reps reps = new Reps(repeats);
                SetPattern setpattern = new SetPattern(exercise, reps);


                if(trainingPatterns.size() >= 1) {
                    for (TrainingPattern tp : trainingPatterns) {
                        if (tp.getName() == pattern_name) {
                            tp.addSet(setpattern);
                        }
                    }
                }


                TrainingPattern trainingPattern = new TrainingPattern(pattern_name);
                trainingPattern.addSet(setpattern);
                trainingPatterns.add(trainingPattern);



            }while (cursor.moveToNext());
        }


        return trainingPatterns;
    }






    private class DBhelper extends SQLiteOpenHelper {

        public DBhelper(@Nullable Context context, @Nullable String name, int version) {
            super(context, name, null, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table patterns(_id integer primary key, pattern_name text, exercise_name text, repeats int);"    );
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
