package com.example.gymapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.nio.channels.ClosedByInterruptException;
import java.util.ArrayList;

import static com.example.gymapplication.TrainingActivity.TRAINING_KEY;

//todo: step 7: implementing the RemovePlan interface
public class EditActivity extends AppCompatActivity implements PlanRecViewAdapter.RemovePlan {

    private static final String TAG = "EditActivity";

    //todo: step 7.1: implementing the method of RemovePlanInterface
    @Override
    public void onRemovePlanResult(Plan plan) {
        //todo:step 7.1.1: defining what we want to do this method
        if(Utils.removePlan(plan))
        {
            Toast.makeText(this, "Training removed from your plan", Toast.LENGTH_SHORT).show();

            //now iam going to set the new values for my adapter
            plansAdapter.setPlans(getPlansByDay(plan.getDay()));
            //editPlanRecView.setAdapter(plansAdapter);
            //now let check, if theRecyclerView is updating or not

        }
    }

    //todo: step 1: defining the UI elements
    private RecyclerView editPlanRecView;
    private TextView txtDayName;
    private Button btnAddMorePlans;

    //todo: step 4.2: define the Adapter
    private PlanRecViewAdapter plansAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.d(TAG, "onCreate: started");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        //todo: step 4.3: initialising the adapter
        plansAdapter = new PlanRecViewAdapter(this);

        //todo: step 6: calling the setType() method, like adapter.setType("edit");
        plansAdapter.setType("edit");

        //todo: step 1.1: calling the initViews() method.
        initViews();

        //todo: step 2: to set the Value of the txtDayName,we need to get the Intent
        Intent intent = getIntent();
        if(intent!=null)
        {
            String dayName = intent.getStringExtra("dayName");
            if(null!=dayName)
            {
                txtDayName.setText(dayName);

                //todo: step 4.1: getting the plans of the present day, with help of getPlansByDay method
                ArrayList<Plan> presentDayPlans = getPlansByDay(dayName);

                //todo: step: we have to set the Adapter of the RecView, so first make the Adapter

                //todo: step 4.4: setting the adapter to the RecView
                editPlanRecView.setAdapter(plansAdapter);
                //todo: step 4.5: setting the layout of rec view
                editPlanRecView.setLayoutManager(new LinearLayoutManager(this));
                //todo: step 4.6: setting the Plans in the Adapter (the plans we got from getPlansByDay() method)
                plansAdapter.setPlans(presentDayPlans);



            }
        }

        //todo: step 5: onClickListener for btnAddMorePlans: navigate the user to AllTrainingsActivity
        btnAddMorePlans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addPlanIntent  = new Intent(EditActivity.this,AllTrainingsActivity.class);
                //addPlanIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(addPlanIntent);

            }
        });
    }

    //todo: step 3: receiving plans for this specific day
    //copying the same method which was in the PlanActivity: the getPlansByDay() method

    //re-remember, we can put this method in the Utils class, so we do not need to copy paste it in
    //different classes, but here we need it only in two classes, PlanActivity and EditActivity, so it's bearable
    private ArrayList<Plan> getPlansByDay(String day)
    {
        ArrayList<Plan> allPlans = Utils.getPlans();
        ArrayList<Plan> presentDayPlans = new ArrayList<>();

        for(Plan p: allPlans)
        {
            if(p.getDay().equalsIgnoreCase(day))
            {
                presentDayPlans.add(p);
            }
        }
        return presentDayPlans;
    }


    //todo: step 1.2: making of initViews() method
    private void initViews()
    {
        editPlanRecView = findViewById(R.id.editPlanRecView);
        txtDayName = findViewById(R.id.txtDayName);
        btnAddMorePlans = findViewById(R.id.btnAddMorePlans);
    }

    //todo: step 8: overriding the onBackPressed method, to start the PlanActivity again

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,PlanActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}


