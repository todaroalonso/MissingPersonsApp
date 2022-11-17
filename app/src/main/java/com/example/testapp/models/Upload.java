package com.example.testapp.models;

import com.google.firebase.database.Exclude;

import java.io.Serializable;

public class Upload implements Serializable {
    private String mName;
    private String mDOB;
    private String mWeight;
    private String mClothes;
    private String mContacts;
    private String mEyeColour;
    private String mHeight;
    private String mMissingDate;
    private String mComplexion;
    private String mImageUrl;
    private String mKey;

    public Upload() {
        //empty constructor needed
    }

    public Upload (String name, String imageUrl , String age, String weight
            , String clothes ,String contacts , String missingdate,String complexion) {
        if (name.trim().equals("")) {
            name = "No Name";
        }
        if (age.trim().equals("")) {
            name = "No Age";
        }
        if (weight.trim().equals("")) {
            name = "No Weight";
        }
        if (clothes.trim().equals("")) {
            name = "No Clothes Details";
        }
        if (contacts.trim().equals("")) {
            name = "No Contacts";
        }
        if (missingdate.trim().equals("")) {
            name = "No missingDate";
        }
        if (complexion.trim().equals("")) {
            name = "No Complexion";
        }

        mName = name;
        mImageUrl = imageUrl;
        mDOB=age;
        mWeight=weight;
        mClothes=clothes;
        mContacts=contacts;
        //mEyeColour=eyecolour;
        //mHeight=height;
        mMissingDate=missingdate;
        mComplexion=complexion;
    }

    public String getName() {
        return mName;
    }
    public String getDOB() {
        return mDOB;
    }
    public String getWeight() {
        return mWeight;
    }
    public String getClothes() {
        return mClothes;
    }
    public String getContacts() {
        return mContacts;
    }
    /*
    public String getEyeColour() {
        return mEyeColour;
    }
    public String getHeight() {
        return mHeight;
    }*/
    public String getMissingDate() {
        return mMissingDate;
    }
    public String getComplexion() {
        return mComplexion;
    }



    public void setName(String name) {
        mName = name;
    }
   public void setDOB(String DOB) {
        mDOB = DOB;
    }
    public void setWeight(String weight) {
        mWeight = weight;
    }
    public void setClothes(String clothes) {
        mClothes = clothes;
    }
    public void setContacts(String contacts) {
        mContacts = contacts;
    }
/*
    public void setEyecolour(String eyecolour) {
        mEyeColour = eyecolour;
    }
    public void setHeight(String height) {
        mHeight = height;
    }*/
    public void setMissingDate(String missingDate) {
        mMissingDate = missingDate;
    }
    public void setComplexion(String complexion) {
        mComplexion = complexion;
    }

    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
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