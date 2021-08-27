package com.example.gymapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;


//todo: step 9: implementing the interface we created
//old line:
//public class TrainingActivity extends AppCompatActivity {
//new line:
public class TrainingActivity extends AppCompatActivity implements PlanDetailDialog.PassPlanInterface
{

    //todo: step 10: after implementing the interface above, we need to override/implement the method
    //  of our PassPlanInterface: i.e the getPlan() method
    @Override
    public void getPlan(Plan plan)
    {
        //todo: step 10.1: adding log here
        Log.d(TAG, "getPlan: Plan: "+plan.toString());
        //todo: step 10.2:
        //now we have to save this plan, i will save it inside Utils class's static arrayList
        //go there: it is step 4 of Utils class

        //todo: step 10.3: using the addPlan() method of Utils class, to add the above plan
        if(Utils.addPlan(plan))
        {
            //todo: step 10.4
            Toast.makeText(this, plan.getTraining().getName()+" is added to your plan", Toast.LENGTH_SHORT).show();
            //todo: step 10.5: navigate the user to Plan Activity
            //navigate the user to plan activity
            //Intent intent = new Intent(TrainingActivity.this,Plan.class);
            //as now i have made the dummy PlanActivity, navigate the user to it now
            Intent intent = new Intent(TrainingActivity.this,PlanActivity.class);
            //clearing the back stack

            //todo: bug: whenever we add a plan, and then see allTrainings
            //  then the same old trainings are shown twice, add another training in plan, and again
            //  see all trainings, and it will show all training 3 times each, so on and so forth

            //todo: above bug is solved now, see in the MainActivity, we put the if block
            //  if(Utils.getTraining==null): only then Utils.initTrainings

            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        }


    }

    //todo: step 0: logt
    private static final String TAG = "TrainingActivity";

    //todo: creating constant for key
    public static final String TRAINING_KEY = "training";

    //todo: step 1: instantiating and initialising the UI elements
    private ImageView trainingImage;
    private TextView txtName,txtDescription;
    private Button btnAddToPlan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);

        //todo step 2: calling initViews()
        initViews();

        //todo: step 4: get the intent
        //iam going to pass my training via the intent from the TrainingRecViewAdapter to this activity
        //i.e the TrainingActivity
        //so when coming from there to here, in the intent putExtra the Training
        Intent intent = getIntent();
        //making sure that the intent is not null
        if(intent!=null)
        {
          //as intent is not null, get Training from it, also we need constant for it so creating it at top

            //todo: step 5: get the training
          final Training training= intent.getParcelableExtra(TRAINING_KEY);

          //make sure that the training is not null
            if(null!=training)
            {
                //now that we have the training, we can set the UI elements to the details of
                //the training we got
                //todo: step 6: setting the UI elements of the TrainingActivity with the data of training we got
                txtName.setText(training.getName());
                txtDescription.setText(training.getLongDescription());
                Glide.with(this).asBitmap().load(training.getImageUrl()).into(trainingImage);

                //todo: step 7: setting onClickListener for the btnAddToPlan
                btnAddToPlan.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //here we will open a dialog to ask to the user, that for how much time
                        //he will do this training, beside that iam going to ask for the day
                        // TODO: 13-08-2021 show the dialog

                        //todo: step 8:
                        //so coming here after i have making the: dialog_plan_details.xml, and PlanDetailDialog.java
                        PlanDetailDialog dialog = new PlanDetailDialog();
                        //now we have to set the arguments for this dialog
                        Bundle bundle = new Bundle();
                        //inside this bundle, i have to pass my training:
                        bundle.putParcelable(TRAINING_KEY,training);
                        //now i can say:
                        dialog.setArguments(bundle);
                        //and finally
                        dialog.show(getSupportFragmentManager(),"plan detail dialog");
                        //above in, inside the dialog.show(),
                        //in first argument we need FragmentManager, so i passed getSupportFragmentManager() method
                        //and in second argument we require tag: so : "plan detail dialog"
                        //that tag is just for debugging purposes

                        //also we have to implement the interface we had created, so it is step 9

                    }
                });
            }
        }


    }

    //todo step 3: making initViews()
    private void initViews()
    {
        Log.d(TAG, "initViews: started");
        txtName = findViewById(R.id.txtName);
        txtDescription = findViewById(R.id.txtDescription);
        trainingImage = findViewById(R.id.trainingImage);
        btnAddToPlan = findViewById(R.id.btnAddToPlan);
    }


}
