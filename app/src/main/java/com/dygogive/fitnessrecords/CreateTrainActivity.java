package com.dygogive.fitnessrecords;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.dygogive.fitnessrecords.databinding.ActivityCreateTrainBinding;
import com.dygogive.fitnessrecords.databinding.ActivityExercisesBinding;
import com.dygogive.fitnessrecords.fitness.Training;
import com.dygogive.fitnessrecords.fitness.TrainingPlan;

public class CreateTrainActivity extends AppCompatActivity {

    private ActivityCreateTrainBinding binding;

    private Dialog dialog;
    private static final int DIALOG_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateTrainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


    }


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

        int id = item.getItemId();

        switch (item.getItemId()) {
            case R.id.menItem_newTrainPlan:
                showDialog(DIALOG_ID);
                break;
        }


        return true;
    }













    @Override
    protected Dialog onCreateDialog(int id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Create Training");

        View view = getLayoutInflater().inflate(R.layout.dialog_add_new_training,null);

        builder.setView(view);
        Log.d("myDiaLog", "onCreateDialog");

        return builder.create();
    }

    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {
        super.onPrepareDialog(id, dialog);
        Log.d("myDiaLog", "onPrepareDialog");



        Button btnOk = dialog.getWindow().findViewById(R.id.btnOk);
        btnOk.setOnClickListener(v -> {

        });
    }












    private TrainingPlan createTraining(){
        TrainingPlan trainingPlan = new TrainingPlan();

        return trainingPlan;
    }

    public void onClick(View view) {
        if (view.getId() == R.id.btCreate) {
            showDialog(DIALOG_ID);
        }
    }
}