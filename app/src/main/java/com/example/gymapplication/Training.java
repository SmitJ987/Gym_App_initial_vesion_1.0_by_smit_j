package com.example.gymapplication;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;


//todo: in order to pass this java class with the intent, we need to implement a parcelable interface
//old line below
//-->public class Training {

//new line
public class Training implements Parcelable
{

    //now we have error, so implementing methods of parcelable



    //todo: step 1, instantiating the fields
    private int id;
    private String name;
    private String shortDescription;
    private String longDescription;
    private String imageUrl;

    //todo: step 2: creating the constructor:
    public Training(int id,String name,String shortDescription,String longDescription,String imageUrl)
    {
        this.id = id;
        this.name = name;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.imageUrl = imageUrl;
    }

    //below three things i.e "protected(Training)...", "Creator<Training> CREATOR", "public Training[] newArray..."
    // are made because we have implemented Parcelable interface to our Training class
    protected Training(Parcel in) {
        id = in.readInt();
        name = in.readString();
        shortDescription = in.readString();
        longDescription = in.readString();
        imageUrl = in.readString();
    }

    public static final Creator<Training> CREATOR = new Creator<Training>() {
        @Override
        public Training createFromParcel(Parcel in) {
            return new Training(in);
        }

        @Override
        public Training[] newArray(int size) {
            return new Training[size];
        }
    };

    //todo: step 3: getters and setters for all fields
    public void setId(int id)
    {
        this.id = id;
    }
    public int getId()
    {
        return id;
    }

    public void setName(String name)
    {
        this.name = name;
    }
    public String getName()
    {
        return name;
    }

    public void setImageUrl(String imageUrl)
    {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl()
    {
        return imageUrl;
    }


    public void setShortDescription(String shortDescription)
    {
        this.shortDescription = shortDescription;
    }
    public String getShortDescription()
    {
        return shortDescription;
    }

    public void setLongDescription(String longDescription)
    {
        this.longDescription = longDescription;
    }
    public String getLongDescription()
    {
        return longDescription;
    }

    //todo: adding  the toString() method: alt+insert, ok for all
    @Override
    public String toString() {
        return "Training{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", longDescription='" + longDescription + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';

        //that's all we need inside the training model
        //now to show this training model inside the recyclerViewAdapter,
        // we make a new layout file, "training_item.xml"
    }


    //below two method are implemented because we have implemented Parcelable Interface
    //to our Training class
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(shortDescription);
        dest.writeString(longDescription);
        dest.writeString(imageUrl);
    }
}



