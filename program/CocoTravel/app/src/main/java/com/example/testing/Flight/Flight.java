package com.example.testing.Flight;

import java.util.Date;

public class Flight {

    //private UUID mId;
    private String mId2;
    private String mFrom;
    private String mTo;
    private String mDuration;
    private String mTransit;
    private double mPrice;
    private Date mDate;
    private boolean mLuggage;
    public Flight() {

    }




    public Flight(String id) {
        mId2 = id;


    }

    /*public UUID getId() {
    return mId;
    }*/
    public String getId() {
        return mId2;
    }

    public String getFrom()
    {
        return mFrom;
    }
    public void setFrom(String from)
    {
        mFrom = from;
    }

    public String getTo()
    {
        return mTo;
    }
    public void setTo(String to)
    {
        mTo = to;
    }


    public String getDuration()
    {
        return mDuration;
    }
    public void setDuration(String duration)
    {
        mDuration = duration;
    }

    public String getTransit()
    {
        return mTransit;
    }
    public void setTransit(String transit)
    {
        mTransit = transit;
    }


    public Double getPrice()
    {
        return mPrice;
    }
    public void setPrice(double price)
    {
        mPrice = price;
    }





    public Date getDate()
    {
        return mDate;
    }
    public void setDate(Date date)
    {
        mDate = date;
    }
    public boolean isSolved()
    {
        return mLuggage;
    }
    public void setSolved(boolean included) {
        mLuggage = included; }

}
