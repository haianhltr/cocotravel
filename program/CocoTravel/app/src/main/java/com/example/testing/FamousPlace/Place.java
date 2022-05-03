package com.example.testing.FamousPlace;

import android.graphics.Bitmap;

import java.util.Date;

public class Place {

    private String mID;
    private String name;
    private int ranking;
    private String type;
    private String address;
    private String district;
    private Bitmap image;
    private boolean bus;
    private String shortdesc;
    public Place() {

    }

    public String getID() {
        return mID;
    }

    public void setID(String ID) {
        mID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getShortdesc() {
        return shortdesc;
    }

    public void setShortdesc(String desc) {
        this.shortdesc = desc;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public boolean isBus() {
        return bus;
    }

    public void setBus(boolean bus) {
        this.bus = bus;
    }
}
