package com.dygogive.fitnessrecords;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.dygogive.fitnessrecords.databinding.ActivityMainBinding;
import com.dygogive.fitnessrecords.lists.ExercisesActivity;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ActivityResultLauncher resultLauncher = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //bind layout to activity
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        //launcher for open other activities
        resultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {}
        );

    }


    //обробник натискань екранних елементів
    public void onClick(View view) {
        //запустити актівіті з списком вправ
        resultLauncher.launch(new Intent(this, ExercisesActivity.class));
    }
}