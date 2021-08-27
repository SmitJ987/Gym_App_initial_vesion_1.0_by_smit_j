package com.example.gymapplication;

import android.app.Dialog;
import android.os.Bundle;
import android.os.Parcel;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import static com.example.gymapplication.TrainingActivity.TRAINING_KEY;

//todo: step 1: extend the DialogFragment to this PlanDetailDialog class
public class PlanDetailDialog extends DialogFragment {


    //todo: step 6.5.3: making a callback interface:
    public interface PassPlanInterface
    {
        void getPlan(Plan plan);
    }
    //after that we need to instantiate this interface
    //todo: step 6.5.4: instantiating the PassPlanInterface
    private PassPlanInterface passPlanInterface;
    //step 6.5.5: would be to initialise this passPlanInterface inside the onClick method, of the btnAdd button


    //step 3.1: instantiating our UI elements:
    private EditText edtTxtMinutes;
    private Spinner spinner;
    private Button btnDismiss, btnAdd;
    private TextView txtName;


    //todo: step 2: override the onCreateDialog method

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        //this onCreateDialog method is responsible for creating our dialog
        //todo: step 2.1: create our View object
        //but for this we need the LayoutInflater here, in order to get that, use getActivity
        //by this "getActivity()" method we will have access to the parentActivity
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_plan_details,null);
        //after above step, i can initialise my view items

        //todo: step 2.2: calling initViews() method
        initViews(view);

        //now that we have our view object, we can create our dialog
        //here we have multiple options again, iam going to create alertDialog (coming from androidX package)

        //todo: step 4: creating alert dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setView(view)
                .setTitle("Enter Details");
        //here above we needed to pass the context, so we passed getActivity()
        //and i used, .setVIew() and passed our view, which we created above
        //so this way we can customise our alert Dialog


        //todo: step 6: in order to get the incoming training, iam going to receive that via the arguments of this fragment

        //todo: step 6.1: get the bundle from the getArguments() method
        Bundle bundle = getArguments();

        //now check if the bundle is not null
        if(bundle!=null)
        {
            //todo: step 6.2: get the Training object from the bundle
            final Training training =  bundle.getParcelable(TRAINING_KEY);
            //for the key i will use the same key which i had defined in my Training Activity, i.e the "TRAINING_KEY"

            //check if training object is not null
            if(null!=training)
            {
                //todo: step 6.3: setting name of the training
                txtName.setText(training.getName());

                //todo: step 6.4: setting onClickListener for the btnDismiss
                btnDismiss.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //so we can dismiss the dialog here, by calling the dismiss() method
                        dismiss();
                    }
                });

                //todo: step 6.5: setting onClickListener for btnAdd
                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //todo: 6.5.1: here first of all, i need to get the day and minutes
                        String day =  spinner.getSelectedItem().toString();
                        //int minutes = Integer.valueOf(edtTxtMinutes.getText().toString()) ;
                        int minutes = Integer.parseInt(edtTxtMinutes.getText().toString());

                        //todo: step 6.5.2: creating a "Plan" object
                        Plan plan = new Plan(training,minutes,day,false);

                        // now i have to save this plan inside the static array list
                        //and also after that i need to navigate the user to Plan Activity in which
                        //we have not created yet

                        //in order to navigate the user to another activity, i need to do it in the parentActivity
                        //i.e the Training activity
                        //for that i need to pass this "Plan" we just create to the parent activity

                        /*
                        from previous sections,we seen that, when we want to pass some data from fragment to the
                        parent activity, we need to create a callback interface

                        so that we will be step 6.5.3: at top of this file somewhere
                         */

                        //here i need to use try Catch block in case of classCastException, so
                        try
                        {
                            //todo: step 6.5.5: initialising the passPlanInterface:
                            passPlanInterface  = (PassPlanInterface) getActivity();
                            //so basically iam going to implement this passPlanInterface inside my
                            //Training Activity which my parentActivity of this dialog

                            //for that iam initialising this passPlanInterface with this getActivity method

                            //todo: step 6.5.6
                            passPlanInterface.getPlan(plan);
                            dismiss();  //to dismiss the dialog, as we got the plan
                        }
                        catch(ClassCastException e)
                        {
                            e.printStackTrace();
                            dismiss(); //as we have shown the error, ow work finished
                        }

                    }
                });
            }
        }

        //todo: step 5: instead of returning super statement, we will return builder.create()
        //-->return super.onCreateDialog(savedInstanceState);
        return builder.create();

        /*this DialogFragment at the end is a fragment
        inside this dialog we are going to get the Training and after receiving the day and the minutes
        we are going to create a "Plan" object, and we are going to pass that to the parentActivity()

        in order to get the incoming training, iam going to receive that via the arguments of this fragment
         */


        //now as the result of this dialog, i have returned builder.create()
        //now that we have this dialog, we can show it from inside our Training activity
        //so go to Training activity
    }

    //todo: step 3: making of initViews() method
    private void initViews(View view)
    {
        //step 3.2: initialising the UI elements
        edtTxtMinutes = view.findViewById(R.id.edtTxtMinutes);
        spinner = view.findViewById(R.id.spinnerDays);

        btnDismiss = view.findViewById(R.id.btnDismiss);
        btnAdd = view.findViewById(R.id.btnAdd);

        txtName = view.findViewById(R.id.txtName);
    }

}

