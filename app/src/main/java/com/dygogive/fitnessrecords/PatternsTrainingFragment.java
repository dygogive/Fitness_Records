package com.dygogive.fitnessrecords;

import android.content.Context;
import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dygogive.fitnessrecords.fitness.TrainingPattern;

import java.util.ArrayList;
import java.util.List;


/* фрагмент для представлення списку моїх шаблонів тренувань.
*
 */
public class PatternsTrainingFragment extends Fragment {

    Context context;
    CreateTrainPatternActivity activity;
    RecyclerView recyclerView;
    RecyclAdaptTrainPattern adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //RecyclerView в якості лайаута для фрагменту
        View view = inflater.inflate(R.layout.recyclerview_patterns, container, false);
        // тут виконується задавання адаптеру для (RecyclerView) view
        if (view instanceof RecyclerView) {
            recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
            adapter = new RecyclAdaptTrainPattern();
            activity = (CreateTrainPatternActivity) context;
            recyclerViewAdapterInit();
            recyclerView.setAdapter(adapter);
            activity.sendAdapter(adapter);
        }

        //тепер можна повернути готове в'ю  RecyclerView
        return view;
    }

    public void recyclerViewAdapterInit() {
        //менеджер потрібний щоб організувати розміщення елементів в Рециклері в'ю
        adapter.addPatternListToAdapter(activity.getPatternsSQL().getTrainPatterns());
    }


    public static PatternsTrainingFragment newInstance() {
        return new PatternsTrainingFragment();
    }


}