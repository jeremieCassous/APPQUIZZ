package com.example.supermegagigaquizz.model.model;

public class User {
    private String mFirstName;

    public String toString() {
        return "User{" +
                "mFirstname='" + mFirstName + '\'' +
                '}';
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }
}
