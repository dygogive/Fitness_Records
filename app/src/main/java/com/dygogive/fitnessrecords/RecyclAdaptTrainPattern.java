package com.dygogive.fitnessrecords;


import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dygogive.fitnessrecords.databinding.FragmentPatternsTrainingItemBinding;
import com.dygogive.fitnessrecords.fitness.TrainingPattern;

import java.util.LinkedList;
import java.util.List;

//адаптер для рециклер в'ю
public class RecyclAdaptTrainPattern extends RecyclerView.Adapter<RecyclAdaptTrainPattern.HolderTrainingPattern> {

    private List<TrainingPattern> patternsTraining = new LinkedList<>();

    @NonNull
    @Override
    public HolderTrainingPattern onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //Створити інфлейтер із контейнеру (RecyclerView / ViewGroup)
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        Log.d("logFrag", "test21");
        //створити в'ю елемента
        View item = layoutInflater.inflate(R.layout.fragment_patterns_training_item,parent,false);
        Log.d("logFrag", "test22");

        //Поміщуємо в'ю в оболочку Holder
        HolderTrainingPattern holder = new HolderTrainingPattern(item);
        Log.d("logFrag", "test23");
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderTrainingPattern holder, int position) {
        //привязати дані одного з ітемів по позиції до холдера
        holder.constructItemForRecyclerView(position, patternsTraining.get(position));
    }

    @Override
    public int getItemCount() {
        return patternsTraining.size();
    }

    public void addPatternListToAdapter(List<TrainingPattern> patterns) {
        patternsTraining = patterns;
        notifyDataSetChanged();
    }

    public void addPatternToAdapter(TrainingPattern pattern) {
        patternsTraining.add(pattern);
        notifyDataSetChanged();
    }

    static class HolderTrainingPattern extends RecyclerView.ViewHolder{

        //біндер
        private final FragmentPatternsTrainingItemBinding binding = FragmentPatternsTrainingItemBinding.bind(itemView);

        //конструктор
        public HolderTrainingPattern(@NonNull View itemView) {
            super(itemView);
        }

        //Заповнити даними View itemView
        public void constructItemForRecyclerView(int itemNumber, TrainingPattern pattern) {
            binding.itemNumber.setText("" + itemNumber);
            binding.content.setText(pattern.getName());
        }


    }
}
