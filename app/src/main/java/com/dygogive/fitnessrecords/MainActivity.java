package com.dygogive.fitnessrecords;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.dygogive.fitnessrecords.databinding.ActivityMainBinding;
import com.dygogive.fitnessrecords.fitness.Exercise;
import com.dygogive.fitnessrecords.fitness.Training;
import com.dygogive.fitnessrecords.lists.ExercisesActivity;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ActivityResultLauncher resultLauncher = null;

    private Exercise selExercise = new Exercise();

    private Training training = new Training();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //bind layout to activity
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        //launcher for open other activities
        resultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if(result.getResultCode() == RESULT_OK) {
                        String s = result.getData().getStringExtra("exercise");
                        Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
                        selExercise.setName(s);
                    }
                }
        );

    }


    //обробник натискань екранних елементів
    public void onClick(View view) {
        //запустити актівіті з списком вправ
        resultLauncher.launch(new Intent(this, ExercisesActivity.class));
    }
}