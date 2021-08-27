package com.example.gymapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;



public class PlanActivity extends AppCompatActivity {

    private static final String TAG = "PlanActivity";

    //todo: step 1: instantiating UI elements
    private RelativeLayout noPlanLayout;
    private NestedScrollView nestedScrollView;

    private RelativeLayout planRelativeLayout;

    private RecyclerView mondayRecView, tuesdayRecView, wednesdayRecView, thursdayRecView,
    fridayRecView, saturdayRecView, sundayRecView;


    private RelativeLayout mondayRelLayout, tuesdayRelLayout, wednesdayRelLayout, thursdayRelLayout,
    fridayRelLayout, saturdayRelLayout, sundayRelLayout;

    private TextView mondayEdit, tuesdayEdit, wednesdayEdit, thursdayEdit, fridayEdit, saturdayEdit,
    sundayEdit;

    private Button btnAddPlan;

    //todo: step 5.2: defining 7 adapters
    private PlanRecViewAdapter mondayAdapter, tuesdayAdapter, wednesdayAdapter, thursdayAdapter,
            fridayAdapter, saturdayAdapter, sundayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);

        //todo: step 1.1: calling initViews() method for initialising the views
        initViews();


        //todo: step 2: if the plans AL in the Utils is empty, then make onClickListener for the btnAddPlan button
        ArrayList<Plan> plans = Utils.getPlans();
        if(plans!=null)
        {

            //again it's a possibility that even if the AL is not null, but also
            //it may have no plans in it: i.e AL with no Plans:
            //for that again an if else:
            if(plans.size()>0)
            {
                //so now we have plans in our AL
                //so change the visibility of the noPlanLayout to Gone
                //and set the nestedScrollView's visibility to Visible
                noPlanLayout.setVisibility(View.GONE);
                nestedScrollView.setVisibility(View.VISIBLE);

                //todo: step 4: now we have created plan_item.xml, and PlanRecViewAdapter
                //now we can initialise our RecViews
                //so calling initRecViews() method
                initRecViews();

                //todo: step 10: to set onClickListeners for the "Edit" textviews, call the method
                //  setEditOnClick()
                setEditOnClickListeners();
            }
            else
            {
                //no plans in AL, so same logic as the else case defined below
                //as the size of AL is zero
                //so we have to change the visibility
                //of the noPlanLayout from gone to visible
                noPlanLayout.setVisibility(View.VISIBLE);

                //and also we have to make this nestedScrollView to GONE
                nestedScrollView.setVisibility(View.GONE);

                //now setting onClickListener for the button: btnAddPlan
                btnAddPlan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //here when user clicks, i want to navigate him to AllTrainingsActivity
                        Intent intent = new Intent(PlanActivity.this,AllTrainingsActivity.class);
                        //clearing stack trace
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);

                    }
                });
            }
        }
        else
        {
            //so this else means: that the plans AL is null,
            //so we have to change the visibility
            //of the noPlanLayout from gone to visible
            noPlanLayout.setVisibility(View.VISIBLE);

            //and also we have to make this nestedScrollView to GONE
            nestedScrollView.setVisibility(View.GONE);

            //now setting onClickListener for the button: btnAddPlan
            btnAddPlan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //here when user clicks, i want to navigate him to AllTrainingsActivity
                    Intent intent = new Intent(PlanActivity.this,AllTrainingsActivity.class);
                    //clearing stack trace

                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);

                }
            });
        }

        //todo: step 3: we have 7 recycler Views and for these recycler views we need a recyclerView
        //  adapter: so create a PlanRecViewAdapter.java
    }

    //todo: step 1.2: making of initViews()
    private void initViews()
    {

        Log.d(TAG, "initViews: started");

        noPlanLayout = findViewById(R.id.noPlanLayout);
        planRelativeLayout = findViewById(R.id.planRelLayout);
        nestedScrollView = findViewById(R.id.nestedScrollView);

        mondayRelLayout = findViewById(R.id.mondayRelLayout);
        tuesdayRelLayout = findViewById(R.id.tuesdayRelLayout);
        wednesdayRelLayout = findViewById(R.id.wednesdayRelLayout);
        thursdayRelLayout = findViewById(R.id.thursdayRelLayout);
        fridayRelLayout = findViewById(R.id.fridayRelLayout);
        saturdayRelLayout = findViewById(R.id.saturdayRelLayout);
        sundayRelLayout = findViewById(R.id.sundayRelLayout);

        mondayEdit = findViewById(R.id.mondayEdit);
        tuesdayEdit = findViewById(R.id.tuesdayEdit);
        wednesdayEdit = findViewById(R.id.wednesdayEdit);
        thursdayEdit = findViewById(R.id.thursdayEdit);
        fridayEdit = findViewById(R.id.fridayEdit);
        saturdayEdit = findViewById(R.id.saturdayEdit);
        sundayEdit = findViewById(R.id.sundayEdit);

        btnAddPlan = findViewById(R.id.btnAddPlan);

        mondayRecView = findViewById(R.id.mondayRecView);
        tuesdayRecView = findViewById(R.id.tuesdayRecView);
        wednesdayRecView = findViewById(R.id.wednesdayRecView);
        thursdayRecView = findViewById(R.id.thursdayRecView);
        fridayRecView = findViewById(R.id.fridayRecView);
        saturdayRecView = findViewById(R.id.saturdayRecView);
        sundayRecView = findViewById(R.id.sundayRecView);
    }

    //todo: step 5: making initRecViews() method
    private void initRecViews()
    {

        //todo: step 5.1: let's define 7 adapters: 1 for each day

        //todo: step 5.3: logd
        Log.d(TAG, "initRecViews: started");
        //todo: step 5.4: instantiating the 7 adapters: for each day:
        //  first instantiate the Adapter and then initialise the recView by:
        //  mondayRecView.setAdapter(mondayAdapter) like this
        //  and set the Linear layout (horizontal)

        //step 5.4: instantiate the mondayAdapter
        mondayAdapter = new PlanRecViewAdapter(this);

        //step 5.5: initialising mondayRecView:
        mondayRecView.setAdapter(mondayAdapter);

        //step 5.6: setting linearLayout(horizontal) for mondayRecView
        mondayRecView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));
        //if we pass true in reverseLayout: then our items will be shown in reverse order
        //now i need to set items for my Adapter
        //so fir that i  make one new method: getPlansByDay: so that is step 6.

        //todo: step 7: using the getPlansByDay() method, to get all the plans of monday
        ArrayList<Plan> mondayPlans = getPlansByDay("monday");
        //todo: step 8: now setting this AL: mondayPlans, to the adapter
        mondayAdapter.setPlans(mondayPlans);

        //do same for all days
        tuesdayAdapter = new PlanRecViewAdapter(this);
        tuesdayRecView.setAdapter(tuesdayAdapter);
        tuesdayRecView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));

        ArrayList<Plan> tuesdayPlans = getPlansByDay("tuesday");
        tuesdayAdapter.setPlans(tuesdayPlans);


        wednesdayAdapter = new PlanRecViewAdapter(this);
        wednesdayRecView.setAdapter(wednesdayAdapter);
        wednesdayRecView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));

        ArrayList<Plan> wednesdayPlans = getPlansByDay("wednesday");
        wednesdayAdapter.setPlans(wednesdayPlans);


        thursdayAdapter = new PlanRecViewAdapter(this);
        thursdayRecView.setAdapter(thursdayAdapter);
        thursdayRecView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));

        ArrayList<Plan> thursdayPlans = getPlansByDay("thursday");
        thursdayAdapter.setPlans(thursdayPlans);

        fridayAdapter = new PlanRecViewAdapter(this);
        fridayRecView.setAdapter(fridayAdapter);
        fridayRecView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        ArrayList<Plan> fridayPlans = getPlansByDay("friday");
        fridayAdapter.setPlans(fridayPlans);


        saturdayAdapter = new PlanRecViewAdapter(this);
        saturdayRecView.setAdapter(saturdayAdapter);
        saturdayRecView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));

        saturdayAdapter.setPlans(getPlansByDay("saturday"));

        sundayAdapter = new PlanRecViewAdapter(this);
        sundayRecView.setAdapter(sundayAdapter);
        sundayRecView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));
        sundayAdapter.setPlans(getPlansByDay("sunday"));


        //re-remember: instead of doing this way: we can put all these days in a single RecyclerView
    }

    //todo: step 6: making the getPlansByDay() method
    private ArrayList<Plan> getPlansByDay(String day)
    {
        //todo: step 6.1: get ArrayList of all of plans
        ArrayList<Plan> allPlans = Utils.getPlans();

        ArrayList<Plan> presentDayPlans = new ArrayList<>();

        //now i will check, that in allPlans and if some plan's day is equal to
        // the day which is coming as argument, then we add this plan to the presentDayPlans AL
        for(Plan p: allPlans)
        {
            //instead od p.getDay().equals, it is better to say: p.getDay().equalsIgnoreCase
            if(p.getDay().equalsIgnoreCase(day))
            {
                presentDayPlans.add(p);
            }
        }
        //after this for each loop, i will return my presentDayPlans AL
        return presentDayPlans;
        //now go and use this method inside the initRecView() method
    }

    //todo: step 11: making of setEditOnClickListeners()
    private void setEditOnClickListeners()
    {
        //todo: step 11.1:
        //intent to ek hi banana hai, khali har ek day ke edit pe, uss day ka name put karna hai
        final Intent intent = new Intent(this,EditActivity.class);


        //todo: step 11.2 : below all
        mondayEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("dayName","monday");
                startActivity(intent);
            }
        });

        tuesdayEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("dayName","TuesDay");
                startActivity(intent);
            }
        });

        wednesdayEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("dayName","wednesday");
                startActivity(intent);
            }
        });

        thursdayEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("dayName","ThursDay");
                startActivity(intent);
            }
        });

        fridayEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("dayName","friDay");
                startActivity(intent);
            }
        });

        saturdayEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("dayName","saturDay");
                startActivity(intent);
            }
        });

        sundayEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("dayName","sunDay");
                startActivity(intent);
            }
        });
    }


    //todo: step 9: overriding the onBackPressMethod
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(PlanActivity.this, MainActivity.class);
        //clearing the back stack
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}

/*
so here we have 7 textViews, 7 RecyclerViews

we have noPlanRelativeLayout: in which, we change it's visibility according to the plan's ArrayList
    (i.e if we have no plans, so Plans AL will be empty, so it's visibility will be VISIBLE
    but if we have plans inside the Plans AL, then the visibility of this "noPlanRelLayout" will
    be "Gone")

we have NestedScrollView

and then we have this Button: btnAddPlan
    this button is inside our noPlanRelLayout
    and we are going to create an onClickListener for this btnAddPlan button,
    if the user does not have any plan

    so first of all, we have to check if the Plans AL is empty or not, inside the Utils class

    so go to Utils class, and make getter for this Plan's AL


 */
