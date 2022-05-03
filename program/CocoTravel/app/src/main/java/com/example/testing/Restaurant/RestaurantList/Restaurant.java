package com.example.testing.Restaurant.RestaurantList;

import android.graphics.Bitmap;

public class Restaurant {
    private String mID;
    private String mType;
    private String mCountry;
    private String mAddress;
    private String mName;
    private String mType2;
    private Bitmap mImage;

    public Restaurant()
    {}



    public String getID() {
        return mID;
    }

    public void setID(String ID) {
        mID = ID;
    }

    public String getType() {
        return mType;
    }

    public void setType(String type) {
        mType = type;
    }

    public String getCountry() {
        return mCountry;
    }

    public void setCountry(String country) {
        mCountry = country;
    }

    public String getAddress() {
        return mAddress;
    }

    public void setAddress(String address) {
        mAddress = address;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getType2() {
        return mType2;
    }

    public void setType2(String type2) {
        mType2 = type2;
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
