package com.dygogive.fitnessrecords;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import com.dygogive.fitnessrecords.databinding.ActivityCreateTrainBinding;
import com.dygogive.fitnessrecords.databinding.ActivityExercisesBinding;

public class CreateTrainActivity extends AppCompatActivity {

    private ActivityCreateTrainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateTrainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


    }


    @Override
    protected Dialog onCreateDialog(int id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Create Training");




        return super.onCreateDialog(id);
    }
}