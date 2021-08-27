package com.example.gymapplication;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

//todo: step 5: i believe we are going to pass this plan via an Intent, so make this one Parcelable
//  so implementing Parcelable here

public class Plan implements Parcelable {

    //todo: step 7: adding parcelable implementation
    protected Plan(Parcel in) {
        training = in.readParcelable(Training.class.getClassLoader());
        minutes = in.readInt();
        day = in.readString();
        isAccomplished = in.readByte() != 0;
    }

    public static final Creator<Plan> CREATOR = new Creator<Plan>() {
        @Override
        public Plan createFromParcel(Parcel in) {
            return new Plan(in);
        }

        @Override
        public Plan[] newArray(int size) {
            return new Plan[size];
        }
    };

    //todo: step 6: implementing methods of the parcelable (two methods)
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(training, flags);
        dest.writeInt(minutes);
        dest.writeString(day);
        dest.writeByte((byte) (isAccomplished ? 1 : 0));
    }

    //todo step 1: instantiate Training, no. of minutes, the day, and the boolean: isAccomplished
    private Training training;
    private int minutes;
    private String day;
    private boolean isAccomplished;

    //todo: step 2: constructor for the above fields:
    public Plan(Training training, int minutes, String day, boolean isAccomplished)
    {
        this.training = training;
        this.minutes = minutes;
        this.day = day;
        this.isAccomplished = isAccomplished;
    }

    //todo: step 3: getters and setters for the fields:
    public void setTraining(Training training)
    {
        this.training = training;
    }
    public Training getTraining()
    {
        return training;
    }

    public void setMinutes(int minutes)
    {
        this.minutes = minutes;
    }
    public int getMinutes()
    {
        return minutes;
    }

    public void setDay(String day)
    {
        this.day = day;
    }
    public String getDay()
    {
        return day;
    }

    public void setAccomplished(boolean isAccomplished)
    {
        this.isAccomplished = isAccomplished;
    }
    public boolean isAccomplished()
    {
        return isAccomplished;
    }

    //todo: step 4: creating the toString method
    @Override
    public String toString() {
        return "Plan{" +
                "training=" + training +
                ", minutes=" + minutes +
                ", day='" + day + '\'' +
                ", isAccomplished=" + isAccomplished +
                '}';


    }
}

/*
this is plan model
we are saving the training itself,
no of mins the user s going to spend on this training,
the day on which the user will practice this training

and the boolean: isAccomplished is an indicator, that if the user has
completed this plan or not




 */