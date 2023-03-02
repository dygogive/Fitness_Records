package com.dygogive.fitnessrecords.sql;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.dygogive.fitnessrecords.fitness.Exercise;
import com.dygogive.fitnessrecords.fitness.Reps;
import com.dygogive.fitnessrecords.fitness.SetPattern;
import com.dygogive.fitnessrecords.fitness.TrainingPattern;

import java.util.ArrayList;
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

        ContentValues cv = new ContentValues();

        String patternName = trainingPattern.getName();


        cv.put("pattern_name", patternName);
        cv.put("exercise_name", "--");
        cv.put("repeats", 1);
        database.insert("patterns", null, cv);
        cv.clear();


    }

    //Create list of objects of TrainingPatterns from SQL table
    public List<TrainingPattern> getTrainPatterns(){

        TrainingPattern trainingPattern = null;
        List<TrainingPattern> trainingPatterns = new ArrayList<>();

        Cursor cursor  =  database.query("patterns",
                null,null,null,null,null,null);


        if (cursor.moveToFirst()) {
            int i_pattern_name = cursor.getColumnIndex("pattern_name");
            int i_exercise = cursor.getColumnIndex("exercise_name");
            int i_repeats = cursor.getColumnIndex("repeats");
            do{
                String pattern_name  = cursor.getString(i_pattern_name);
                String exercise_name  = cursor.getString(i_exercise);
                int repeats = cursor.getInt(i_repeats);

                Exercise exercise = new Exercise(exercise_name);
                Reps reps = new Reps(repeats);
                SetPattern setpattern = new SetPattern(exercise, reps);


                trainingPattern = new TrainingPattern(pattern_name);
                trainingPattern.addSet(setpattern);

                trainingPatterns.add(trainingPattern);

            }while (cursor.moveToNext());
        } else {
            Toast.makeText(context, "No patterns", Toast.LENGTH_SHORT).show();
        }

        return trainingPatterns;
    }


    public void dbToLog(String logTag, String tableName){
        Cursor cursor  =  database.query("patterns",
                null,null,null,null,null,null);

        if(cursor.moveToFirst()) {
            do {
                @SuppressLint("Range")
                String name = cursor.getString(cursor.getColumnIndex("pattern_name"));
                Log.d(logTag, name);
            } while (cursor.moveToNext());
        } else {
            Log.d(logTag, "db is Empty");
        }

    }


    public void closeAll(){
        database.close();
        dBhelper.close();
    }


    private class DBhelper extends SQLiteOpenHelper {

        public DBhelper(@Nullable Context context, @Nullable String name, int version) {
            super(context, name, null, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("Create table patterns(_id integer primary key autoincrement, pattern_name text, exercise_name text, repeats integer)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
