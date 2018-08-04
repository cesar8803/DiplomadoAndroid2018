package com.example.mobilestudiolaptop008.firebchat.Models;

public class LocationModel {

    private String date;
    private String latitude;
    private String longitude;
    private String userId;


    public LocationModel() {
    }

    public LocationModel(String date, String latitude, String longitude, String userId) {
        this.date = date;
        this.latitude = latitude;
        this.longitude = longitude;
        this.userId = userId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
