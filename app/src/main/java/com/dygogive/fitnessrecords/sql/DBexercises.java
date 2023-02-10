package com.dygogive.fitnessrecords.sql;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.dygogive.fitnessrecords.R;

public class DBexercises {

    //Параметри бази даних вправ
    public static final int
            DB_VERSION = 2;
    public static final String
            DB_NAME = "exercises",
            TABLE1 = "simple",
            COLUMN_ID = "_id",
            COLUMN_NAME = "name",
            CREATE_TABLE1 = "create table "+ TABLE1 +"(" + COLUMN_ID + " integer primary key, " + COLUMN_NAME + " text)";

    private DbHelper dbHelper;
    Context context;

    private SQLiteDatabase database;

    public DBexercises(Context con) {
        context = con;
        dbHelper = new DbHelper();
        database = dbHelper.getWritableDatabase();
    }

    public SQLiteDatabase getDatabase() {
        return database;
    }

    public void putInBaseFromArray(String[] exes){

        //закинути масив базових вправ у таблицю з вправами
        ContentValues cv = new ContentValues();
        for (String exe : exes) {
            cv.put(COLUMN_NAME, exe);
            database.insert(TABLE1, null, cv);
            cv.clear();
        }
    }

    public Cursor getFullCursor() {
        //курсор потрібної таблиці
        Cursor cursor = database.query(DBexercises.TABLE1,null,null,null,null,null,null);
        return cursor;
    }

    public void closeAll() {
        dbHelper.close();
    }


    private class DbHelper extends SQLiteOpenHelper {



        public DbHelper() {
            super(context, DB_NAME, null, DB_VERSION);
        }



        @Override
        public void onCreate(SQLiteDatabase db) {
            //створити таблицю
            db.execSQL(CREATE_TABLE1);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }


}

