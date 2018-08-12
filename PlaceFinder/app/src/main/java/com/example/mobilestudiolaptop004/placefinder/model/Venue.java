package com.example.mobilestudiolaptop004.placefinder.model;

public class Venue {
    private String id;
    private String name;

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

    public Locationf getLocation() {
        return location;
    }

    public void setLocation(Locationf location) {
        this.location = location;
    }

    private Locationf location;
}
