package com.dygogive.fitnessrecords;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dygogive.fitnessrecords.databinding.ActivityCreateTrainBinding;
import com.dygogive.fitnessrecords.databinding.DialogAddNewTrainingBinding;
import com.dygogive.fitnessrecords.fitness.TrainingPattern;
import com.dygogive.fitnessrecords.sql.PatternsSQL;

import java.util.LinkedList;
import java.util.List;

public class CreateTrainPatternActivity extends AppCompatActivity {






    private PatternsSQL patternsSQL;
    private RecyclAdaptTrainPattern adapter;




    /*
    *  On create method for initialising this activity
    *
    *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCreateTrainBinding binding = ActivityCreateTrainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        //назва бази даних
        final String DATABASE_NAME = "trainingPatterns";
        //ссилка на помічника бази даних
        patternsSQL = new PatternsSQL(this,DATABASE_NAME, 8);


        /*Добавити фрагмент на екран
        *
        * */
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //назвати frameLayout і наказати фрагменту створити себе
        fragmentTransaction.replace(R.id.frameLayout,PatternsTrainingFragment.newInstance(),null);
        //підтвердити
        fragmentTransaction.commit();

    }

    //дати помічника по базі даних кому потрібно
    public PatternsSQL getPatternsSQL() {
        return patternsSQL;
    }

    /*
    * Creating MENU in upper right corner of activity
    *
    *
    *
    * */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_create_training_plans, menu);
        return true;
    }
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //при натисканні пунктів меню виконати наступні дії
        switch (item.getItemId()) {
            case R.id.menItem_newTrainPlan:
                //показати діалог
                showDialog(DIALOG_ID);
                break;
        }
        return true;
    }









    /*
     * Creating DIALOG for creation new Training Pattern
     *
     *
     *
     * */
    //Діалог і його номер
    private Dialog dialog;
    DialogAddNewTrainingBinding dialBind;
    private static final int DIALOG_ID = 1;
    @Override
    protected Dialog onCreateDialog(int id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //назва діалогу
        builder.setTitle("Create Training Pattern");
        //в'ю діалогу
        View view = getLayoutInflater().inflate(R.layout.dialog_add_new_training,null);
        //дати йому в'ю
        builder.setView(view);
        //біндер
        dialBind = DialogAddNewTrainingBinding.bind(view);
        //створити
        return builder.create();
    }

    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {
        super.onPrepareDialog(id, dialog);
        //слухач на кнопку
        dialBind.btnOk.setOnClickListener(v -> {
            TrainingPattern pattern = new TrainingPattern(dialBind.etPatternTrainName.getText().toString());
            patternsSQL.putInBase(pattern);
            adapter.addPatternToAdapter(pattern);
            patternsSQL.dbToLog("patterns","patterns");
        });
    }



    /*on click method for button listening
    *
    **
    **/
    public void onClick(View view) {
        //показати діалог
        if (view.getId() == R.id.btCreate) {
            showDialog(DIALOG_ID);
        }
    }




    /* closing open SQL connections in this method
    *
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        patternsSQL.closeAll();
    }



    public void sendAdapter(RecyclAdaptTrainPattern adapt) {
        adapter = adapt;
    }
}



