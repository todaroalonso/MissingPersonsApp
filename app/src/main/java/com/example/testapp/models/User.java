package com.example.testapp.models;

import com.google.firebase.database.Exclude;

import java.io.Serializable;

public class User implements Serializable {
    private String mFName;
    private String mLName;
    private String mEmail;
    private String mLocation;
    private String mMobile;
    private String mUserType;
    private String mKey;
    int misUser;


    public User() {
        //empty constructor needed
    }

    public User (String fname,String lname,String email,String location, String mobile,int isUser) {
        if (fname.trim().equals("")) {
            fname = "No Name";
        }
        if (mobile.trim().equals("")) {
            mobile = "No Mobile ";
        }


        mFName = fname;
        mLName=lname;
        mEmail=email;
        mLocation=location;
        mMobile=mobile;
        misUser=isUser;



    }

    public String getFName() {
        return mFName;
    }
    public String getLName() {
        return mLName;
    }
    public String getEmail() {
        return mEmail;
    }

    public String getLocation() {
        return mLocation;
    }
    public String getMobile() {
        return mMobile;
    }
    public int getisUser() {
        return misUser;
    }





    public void setFName(String fname) {
        mFName = fname;
    }
    public void setLName(String lname) {
        mLName = lname;
    }
    public void setEmail(String email) {
        mEmail=email;
    }
    public void setLocation(String location) {
        mLocation=location;
    }
    public void setMobile(String mobile) {
        mMobile=mobile;
    }
    public void setisUser(int isUser) {
        misUser=isUser;
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