package com.example.testing.Restaurant.Top10;

import android.graphics.Bitmap;

public class Food {
    private String mID;
    private String mDescription;
    private Bitmap mImage;
    private String mName;
    private String mAltname;

    public Food()
    {}



    public String getID() {
        return mID;
    }

    public void setID(String ID) {
        mID = ID;
    }



    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }


    public String getAltname() {
        return mAltname;
    }

    public void setAltname(String altname) {
     mAltname  = altname;
    }

    public void setImage(Bitmap image)
    {
        mImage = image;
    }

    public Bitmap getImage()
    {
        return mImage;

    }
}
