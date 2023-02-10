package com.dygogive.fitnessrecords.lists;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.dygogive.fitnessrecords.R;

import com.dygogive.fitnessrecords.databinding.ActivityExercisesBinding;
import com.dygogive.fitnessrecords.sql.DBexercises;

import java.util.ArrayList;
import java.util.List;

public class ExercisesActivity extends AppCompatActivity {

    private ActivityExercisesBinding binding;

    ListView lv;
    DBexercises dBexercises;

    List<String> list;
    ArrayAdapter arrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityExercisesBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_exercises);

        initializing();
    }

    private void initializing() {
        //хелпер для роботи з базою
        dBexercises = new DBexercises(this);

        //наявний список вправ
        list = new ArrayList<>();

        //получити дані з бази в контейнер
        renewListExercises();

        //ініціювати список ListView
        lv = (ListView) findViewById(R.id.list_view_exercises);
        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        //показати на екран список вправ
        initListView();
    }


    private void renewListExercises(){
        Cursor cursor = dBexercises.getFullCursor();
        list.clear();

        if(cursor.moveToFirst()) {
            int indexID   = cursor.getColumnIndex(DBexercises.COLUMN_ID);
            int indexName = cursor.getColumnIndex(DBexercises.COLUMN_NAME);
            do{
                int id     = cursor.getInt(indexID);
                String exe = cursor.getString(indexName);
                //Log.d("logTag", "id = " + id + "; Exercise = " + exe + "; ");
                //Заповнити контейнер списком вправ
                list.add(exe);
            }while (cursor.moveToNext());
        } else {
            //отримати масив назв врправ з ресурсу
            String[] exes = this.getResources().getStringArray(R.array.set_exercises);
            dBexercises.putInBaseFromArray(exes);
        }
    }


    private void initListView(){
        arrayAdapter  = new ArrayAdapter(this,
                android.R.layout.simple_list_item_single_choice, list);
        lv.setAdapter(arrayAdapter);
    }


    public void onClick(View view) {
        switch (view.getId()){
            //натискання кнопки видалити
            case R.id.btDel: deletingChosenExercise();
                break;
            //натискання кнопки вибрати
            case R.id.btSelect: {
                Intent intent = new Intent();
                String exercise = getChoosingExercise();
                intent.putExtra("exercise","Chosen exercise " + exercise);
                setResult(RESULT_OK,intent);
                finish();
            } break;
        }
    }

    private String getChoosingExercise() {

        Cursor cursor = dBexercises.getFullCursor();

        if (    cursor.moveToFirst() & (list.size() != 0)   ) {
            int pos = lv.getCheckedItemPosition();
            if(pos == -1) return "null";
            if(pos == list.size()) return "null";

            return list.get(pos);

        } else return null;
    }

    private void deletingChosenExercise() {
        Cursor cursor = dBexercises.getFullCursor();

        if (    cursor.moveToFirst() & (list.size() != 0)   ) {
            int pos = lv.getCheckedItemPosition();
            if(pos == -1) return;
            if(pos == list.size()) return;

            String valueToDelete = list.get(pos);

            //видалення з бази вибраної позиції по назві
            dBexercises.getDatabase().delete(DBexercises.TABLE1, DBexercises.COLUMN_NAME + " = ?", new String[]{valueToDelete});

            renewListExercises();
            arrayAdapter.notifyDataSetChanged();

        } else {
            renewListExercises();
            arrayAdapter.notifyDataSetChanged();
        }
    }


    @Override
    protected void onDestroy() {

        dBexercises.closeAll();

        super.onDestroy();
    }
}