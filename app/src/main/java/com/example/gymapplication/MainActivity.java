package com.example.gymapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //todo: step 1: adding log here
    private static final String TAG="MainActivity";

    //todo: step 2: instantiating button
    private Button btnAbout, btnPlans, btnAllTrainings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //todo: step 3: calling intiView() method
        initViews();



        //todo: step 5: coming back from Utils class and calling the initTrainings() method
        //todo: solving the bug: that whenever we add a new training in our plan
        //and them see all the trainings, then all of them are seen as double double
        if(Utils.getTrainings()==null)
        {
            Utils.initTrainings();
        }

        //because it is static method, we do not need to initialise our Utils class
        //we are going to show all of our trainings inside the "AllTrainingsActivity"
        //for that i need to create a getter method inside the Utils class
        //so go to Utils class now


        //todo: step 6: creating onClickListener for all three buttons, but we have not yet made their
        //activities so
        // TODO: 13-08-2021 make onClickListeners of all three buttons above.

        //todo: step 7: onClickListener for btnAllTrainings
        btnAllTrainings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AllTrainingsActivity.class);
                startActivity(intent);
            }
        });

        //todo: step 8: onClickListener for btnPlans
        btnPlans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PlanActivity.class);
                startActivity(intent);
                //let's now run the app
            }
        });

        //todo: step 9: AlertDialog for the btnAbout
        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                builder.setTitle("About")
                        .setMessage(getString(R.string.webActivity_message))
                        .setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setPositiveButton("Visit", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MainActivity.this, "visiting his Linkedin...", Toast.LENGTH_SHORT).show();
                                //now here i want to navigate the User to WebActivity
                                //so making WebActivity
                                Intent intent = new Intent(MainActivity.this,WebActivity.class);
                                startActivity(intent);
                            }
                        });
                builder.create();
                builder.show();
            }
        });
    }



    //todo: step 4: making initView() method
    private void initViews()
    {
        Log.d(TAG,"initViews: started");
        btnAbout = findViewById(R.id.btnAbout);
        btnAllTrainings = findViewById(R.id.btnAllActivities);
        btnPlans = findViewById(R.id.btnPlans);
    }

}
