package com.dygogive.fitnessrecords.lists;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.dygogive.fitnessrecords.databinding.ActivitySetsBinding;

public class SetsActivity extends AppCompatActivity {


    private ActivitySetsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySetsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


    }
}