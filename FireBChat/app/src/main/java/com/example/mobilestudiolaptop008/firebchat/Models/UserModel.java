package com.example.mobilestudiolaptop008.firebchat.Models;

import com.google.firebase.database.PropertyName;

public class UserModel {

    private String id;
    private String name;
    @PropertyName("url")
    private String imageurl;

    public UserModel() {
    }

    public UserModel(String id) {
        this.id = id;
    }

    public UserModel(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}
