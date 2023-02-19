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
import android.widget.EditText;

import com.dygogive.fitnessrecords.databinding.ActivityCreateTrainBinding;
import com.dygogive.fitnessrecords.fitness.TrainingPattern;
import com.dygogive.fitnessrecords.sql.PatternsSQL;

import java.util.LinkedList;
import java.util.List;

public class CreateTrainPatternActivity extends AppCompatActivity {

    private static final String DATABASE_NAME = "trainingPatterns";

    private ActivityCreateTrainBinding binding;
    private TrainingPattern trainingPattern;
    private List<TrainingPattern> patterns = new LinkedList<>();
    private PatternsSQL patternsSQL;





    /*
    *  On create method for initialising this activity
    *
    *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateTrainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //create helper for database
        patternsSQL = new PatternsSQL(this,"trainingPatterns", 1);
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

        switch (item.getItemId()) {
            case R.id.menItem_newTrainPlan:
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


    private Dialog dialog;
    private static final int DIALOG_ID = 1;
    @Override
    protected Dialog onCreateDialog(int id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Create Training Pattern");

        View view = getLayoutInflater().inflate(R.layout.dialog_add_new_training,null);

        builder.setView(view);
        Log.d("myDiaLog", "onCreateDialog");

        return builder.create();
    }

    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {
        super.onPrepareDialog(id, dialog);
        Log.d("myDiaLog", "onPrepareDialog");

        //edit text in dialog
        EditText editTextext = dialog.findViewById(R.id.etPatternTrainName);
        //button in dialog
        Button btnOk = dialog.getWindow().findViewById(R.id.btnOk);

        //listener for button
        btnOk.setOnClickListener(v -> {
            //text from edit text
            String txtName = editTextext.getText().toString();
            //new object of training pattern
            trainingPattern = new TrainingPattern(txtName);
            //add this new object to List of patterns
            patterns.add(trainingPattern);
        });
    }





    /*on click method for button listening
    *
    **
    **/
    public void onClick(View view) {
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
        patternsSQL.getDatabase().close();
    }
}