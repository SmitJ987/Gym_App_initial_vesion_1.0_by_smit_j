package com.example.gymapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

import static com.example.gymapplication.TrainingActivity.TRAINING_KEY;


//todo: step 3: extending our ViewHolder class, to the parent class
public class PlanRecViewAdapter extends RecyclerView.Adapter<PlanRecViewAdapter.ViewHolder>{


    //todo: step 13: to solve the bug: the RecView in PlansActivity was not updating
    //todo: step 13.1: creating a callBack interface
    public interface RemovePlan
    {
        //todo: step 13.2: creating one method: removePlanResult
        void onRemovePlanResult(Plan plan);
    }

    //todo: step 13.3: creating instance of the RemovePlan interface
    private RemovePlan removePlan;


    //todo: step 11: steps to let the planRecViewAdapter behave differently
    //  in EditActivity and PlanActivity

    //todo: step 11.1: an empty String
    private String type="";



    //todo: step 5: defining the AL of plans, and context
    private ArrayList<Plan> plans = new ArrayList<>();
    private Context mContext;

    //todo: step 6: getting the context via the constructor
    public PlanRecViewAdapter(Context mContext)
    {
        this.mContext = mContext;
    }

    //todo: step 4: implementing methods of the ViewHolder class

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //todo: step 9: creating View object, making ViewHolder from it, and returning the ViewHolder
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.plan_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {


        //todo: step 10: work of the onBindViewHolder method:
        //let set the values of different Views
        holder.trainingName.setText(plans.get(position).getTraining().getName());
        holder.txtDescription.setText(plans.get(position).getTraining().getShortDescription());

        //holder.txtMinutes.setText(plans.get(position).getMinutes());
        holder.txtMinutes.setText(String.valueOf(plans.get(position).getMinutes()));

        Glide.with(mContext).asBitmap().load(plans.get(position).getTraining().getImageUrl()).into(holder.trainingImage);

        //now let's determine visibility of our circles
        if(plans.get(position).isAccomplished())
        {
            //as the training is accomplished: empty circle: invisible, checkedCircle: visible
            holder.checkedCircle.setVisibility(View.VISIBLE);
            holder.emptyCircle.setVisibility(View.GONE);
        }
        else
        {
            holder.checkedCircle.setVisibility(View.GONE);
        }
        //setting onClickListener for the parent CardView
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //navigate to the to TrainingActivity
                Intent intent = new Intent(mContext,TrainingActivity.class);
                //clearing the backstack, if required
                //intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                //we also have to pass our Training
                intent.putExtra(TRAINING_KEY,plans.get(position).getTraining());
                mContext.startActivity(intent);

            }
        });

        //todo: step 11.3: check the type: if it is "edit", then do the modifications
        if(type.equals("edit"))
        {
            //but first, create setter for this String: type

            //if the planRecViewAdapter is initialised inside the EditActivity
            //so this is the "edit" type, so here we will make two onClickListeners
            holder.emptyCircle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //before setting the state of Accomplished, first ask user for the confirmation
                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext)
                            .setTitle("Finished")
                            .setMessage("have you finished "+plans.get(position).getTraining().getName()+" ?")
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    //so here we change the state of Accomplished
                                    //below line is wrong
                                    //-->plans.get(position).setAccomplished(true);
                                    //below is a right code:
                                    for(Plan p: Utils.getPlans())
                                    {
                                        if(p.equals(plans.get(position)))
                                        {
                                            p.setAccomplished(true);
                                        }
                                    }
                                    //after foreach loop,notify that the data set has been changed
                                    notifyDataSetChanged();
                                }
                            });

                            builder.show();
                }
            });
            //todo: step 12: to define longPressListener for our cardView: to remove the plan
            // from the week
            holder.parent.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    //first get the user's confirmation to remove the plan
                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                    builder.setTitle("Remove Plan")
                            .setMessage("Do you really want to remove "+plans.get(position).getTraining().getName()+ " from your week?")
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    /*
                                    here i will write a logic and we will change it later on
                                    we will see why:
                                    basically iam going to remove this from the User's week plans
                                    but it's not going to refresh our planRecyclerViewAdapter
                                    but we have solution for that*/

                                    //todo: step 13.4: initialising the RemovePlan interface: remember to initialise it inside the onBindViewHolder only
                                    //and doing it inside the try and catch blocks
                                    try
                                    {
                                        //below is right
                                        //todo: step 13.4.1: passing the mContext to removePlan
                                        removePlan = (RemovePlan) mContext;
                                        //todo: step 13.4.2: calling onRemovePlanResult method, (which is the method of the RemovePlan interface
                                        //and as removePlan is an instance of RemovePlan interface, we can call that method
                                        removePlan.onRemovePlanResult(plans.get(position));
                                        //todo: next step: go to EditActivity to implement the RemovePlan interface


                                    }
                                    catch (ClassCastException e)
                                    {
                                        e.printStackTrace();
                                    }

                                    //todo: step 12.1:  now first, go to Utils class to create the RemovePlan method

                                    //now we are removing plan with the help of RemovePlan interface, se we do not need below
                                    //logic, just the above try catch  block is needed, so commenting below code:

                                    /*// todo:12.2 as we have made that removePlan() method in Utils class let's use it
                                    if (Utils.removePlan(plans.get(position)))
                                    {
                                        Toast.makeText(mContext, "Plan Removed Successfully", Toast.LENGTH_SHORT).show();
                                        notifyDataSetChanged();
                                    }*/
                                }
                            });
                    builder.create().show();
                    //in order to make work the onLongOnClickListener work, we have to return true instead of false
                    //return false;
                    return true;
                }
            });
        }






    }

    @Override
    public int getItemCount() {
        //todo:step 8: returning size of the plans AL
        return plans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        //todo: step 2: instantiating and initialising the UI elements of plan_item.xml
        private TextView txtMinutes, txtDescription, trainingName;
        private ImageView checkedCircle, emptyCircle, trainingImage;
        private MaterialCardView parent;

        //todo: step 1: the constructor:
        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);

            //todo: step 2.1: initialising the UI elements
            txtMinutes = itemView.findViewById(R.id.txtMinutes);
            txtDescription = itemView.findViewById(R.id.txtDescription);
            trainingName = itemView.findViewById(R.id.txtName);
            trainingImage = itemView.findViewById(R.id.trainingImage);

            parent = itemView.findViewById(R.id.parent);

            checkedCircle = itemView.findViewById(R.id.checkedCircle);
            emptyCircle = itemView.findViewById(R.id.emptyCircle);
        }

        //as we have initialised our ViewHolder class we can now extend it to our parent class
        //i.e PlanRecViewAdapter

    }

    //todo: step 7: setter method for the plans
    public void setPlans(ArrayList<Plan> plans)
    {
        this.plans = plans;
        notifyDataSetChanged();
    }

    //todo: step 11.4: setter for the String: type
    public void setType(String type)
    {
        this.type = type;
        //we don't need the "notifyDataSetChanged", as we are placing the value of this string
        //at the making(beginning) of this adapter
    }
}
