package com.example.adrian.conagua_almonaci.Parseo;

import java.util.List;

public class Result {
    private String name;
    private String skydescriptionlong;
    private Double longitude;
    private Double latitude;
    private String state;


    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSkydescriptionlong() {
        return skydescriptionlong;
    }

    public void setSkydescriptionlong(String skydescriptionlong) {
        this.skydescriptionlong = skydescriptionlong;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

}
