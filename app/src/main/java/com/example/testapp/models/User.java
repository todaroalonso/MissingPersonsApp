package com.example.testapp.models;

import com.google.firebase.database.Exclude;

import java.io.Serializable;

public class User implements Serializable {
    private String mFName;
    private String mLName;
    private String mLocation;
    private String mMobile;
    private String mUserType;
    private String mKey;


    public User() {
        //empty constructor needed
    }

    public User (String fname,String lname,String location, String mobile ) {
        if (fname.trim().equals("")) {
            fname = "No Name";
        }
        if (mobile.trim().equals("")) {
            mobile = "No Mobile ";
        }


        mFName = fname;
        mLName=lname;
        mLocation=location;
        mMobile=mobile;

    }

    public String getFName() {
        return mFName;
    }
    public String getLName() {
        return mLName;
    }

    public String getLocation() {
        return mLocation;
    }
    public String getMobile() {
        return mMobile;
    }




    public void setFName(String fname) {
        mFName = fname;
    }
    public void setLName(String lname) {
        mLName = lname;
    }
    public void setLocation(String location) {
        mLocation=location;
    }
    public void setMobile(String mobile) {
        mMobile=mobile;
    }




    @Exclude
    public String getKey() {
        return mKey;
    }

    @Exclude
    public void setKey(String key) {
        mKey = key;
    }

}