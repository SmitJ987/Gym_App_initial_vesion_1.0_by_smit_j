package com.example.gymapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



import com.bumptech.glide.Glide;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;

import static com.example.gymapplication.TrainingActivity.TRAINING_KEY;

//todo: step 4: extension to the ViewHolder class we created inside this class
public class TrainingRecViewAdapter extends RecyclerView.Adapter<TrainingRecViewAdapter.ViewHolder> {

    private static final String TAG = "TrainingRecViewAdapter";

    //todo: step 6: defining the ArrayList of all trainings
    private ArrayList<Training> trainings = new ArrayList<>();

    //todo: step 7: defining the context, as we will need it in the Glide work
    private Context mContext;

    //todo: step 8: as i will receive this mContext from the constructor,
    public TrainingRecViewAdapter(Context mContext) {
        this.mContext = mContext;
    }

    //todo: step 5: implementing methods of the above extension i.e methods of adapter
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //todo: step10: creating an instance of ViewHolder and returning it
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.training_item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        //todo: step 11: here the magic happens

        //todo: step 11.1 Log.d(TAG, "onBindViewHolder: called");

        //todo: step 11.2 setting name,description and image for our training item
        holder.txtName.setText(trainings.get(position).getName());
        holder.txtDescription.setText(trainings.get(position).getShortDescription());

        Glide.with(mContext).asBitmap().load(trainings.get(position).getImageUrl())
                .into(holder.image);

        //todo: step 11.3: setting onClickListener for our parent: cardView
        //by clicking on it, i am going to navigate the user to another activity
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Toast.makeText(mContext, "yet to be completed", Toast.LENGTH_SHORT).show();

                //making the intent and putExtra the Training:
                Intent intent = new Intent(mContext,TrainingActivity.class);
                intent.putExtra(TRAINING_KEY,trainings.get(position));
                mContext.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        //todo: step 9: returning the size of trainings AL
        return trainings.size();
    }

    //todo: step 1: create our view holder
    public class ViewHolder extends RecyclerView.ViewHolder
    {
        //as we are using materialCardView, so instantiate MaterialCardView
        private MaterialCardView parent;
        private ImageView image;
        private TextView txtName;
        private TextView txtDescription;

        //todo step 2: creating our constructor
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //todo: step 3: we have to initialise our UI element in this constructor,
            //so for that, first instantiate them above

            parent = itemView.findViewById(R.id.parent);
            image = itemView.findViewById(R.id.image);
            txtName = itemView.findViewById(R.id.txtName);
            txtDescription = itemView.findViewById(R.id.txtDescription);

            //that was all for our viewHolder class

        }
    }

    //todo: step 12: setter method for our ArrayList
    public void setTrainings(ArrayList<Training> trainings)
    {
        this.trainings = trainings;
        //todo: step 12.1: notify that the data set has been changed
        notifyDataSetChanged();
    }
}
