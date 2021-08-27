package com.example.gymapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class AllTrainingsActivity extends AppCompatActivity {
    //todo: step 1: the tag
    private static final String TAG = "AllTrainingsActivity";

    //todo step 2: instantiating and initialising our recyclerView
    private RecyclerView recyclerView;

    //todo: step 3: instantiating our training adapter
    TrainingRecViewAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: started");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_trainings);

        recyclerView = findViewById(R.id.recyclerView);

        //todo: step 4: initialising the adapter
        adapter = new TrainingRecViewAdapter(this);

        //todo: step 5: setting above created adapter for our recyclerView
        recyclerView.setAdapter(adapter);

        //todo: step 6: setting layout manager for our recycler view
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));


        //todo: second part: step 6: getting the trainings AL from Utils
        ArrayList<Training> allTrainings = Utils.getTrainings();
        //it is impossible for the getTrainings method to be null, but still i will put a check
        if(allTrainings!=null)
        {
            
            //todo: step 7: setting trainings to the adapter
            adapter.setTrainings(allTrainings);
        }

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
