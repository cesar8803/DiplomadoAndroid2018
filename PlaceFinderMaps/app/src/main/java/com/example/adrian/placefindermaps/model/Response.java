package com.example.adrian.placefindermaps.model;

import java.util.List;

public class Response {
    private List<Venue> venues=null;

    public List<Venue> getVenues() {
        return venues;
    }

    public void setVenues(List<Venue> venues) {
        this.venues = venues;
    }
}
