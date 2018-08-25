package com.example.mobilestudiolaptop003.placefinder.model;

public class Venue {
    private String id;
    private String name;

    private LocationF location;

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

    public LocationF getLocation() {
        return location;
    }

    public void setLocation(LocationF location) {
        this.location = location;
    }
}
