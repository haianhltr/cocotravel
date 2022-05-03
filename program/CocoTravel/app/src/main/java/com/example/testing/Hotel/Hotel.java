package com.example.testing.Hotel;

import java.util.Date;
import java.util.UUID;

public class Hotel {

    private String mId;
    private String mCity;
    private String mName;
    private String mDistrict;
    private double mRate;
    private String mAddress;
    private double mFromcenter;
    private boolean mBreakfast;


    private double mPrice;
    private boolean mTransportation;
    private byte[] mImage;


    public Hotel() {


    }



    public Hotel(String id) {
        mId = id;

    }

    public String getId() {
        return mId; }

    public String getCity()
    {
        return mCity;
    }
    public void setCity(String city)
    {
        mCity = city;
    }


    public String getDistrict()
    {
        return mDistrict;
    }
    public void setDistrict(String district)
    {
        mDistrict = district;
    }

    public Double getRate()
    {
        return mRate;
    }
    public void setRate(double rate)
    {
        mRate = rate;
    }


    public String getAddress(){ return mAddress;}
    public void setAddress(String address){mAddress = address; }

    public double getFromcenter(){return mFromcenter;}
    public void setFromcenter(double fromcenter){mFromcenter = fromcenter;}

    public boolean getBreakfast(){ return mBreakfast;}
    public void setBreakfast(boolean breakfast){mBreakfast=breakfast;}


    public Double getPrice() {return mPrice;}
    public void setPrice(double price){mPrice=price;}

    public boolean getTransportation() {return mTransportation;}
    public void setTransportation(boolean transportation){ mTransportation= transportation;}


    public String getName(){return  mName;}
    public void setName(String name){
        mName = name;
    }

    public byte [] getImage(){return  mImage;}
    public void setImage(byte [] image){
        mImage = image;
    }





}


