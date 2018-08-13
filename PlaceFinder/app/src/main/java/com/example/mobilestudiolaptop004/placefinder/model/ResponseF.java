package com.example.mobilestudiolaptop004.placefinder.model;

import java.util.ArrayList;

public class ResponseF {
    public ArrayList<Venue> getVenues() {
        return venues;
    }

    public void setVenues(ArrayList<Venue> venues) {
        this.venues = venues;
    }

    private ArrayList<Venue> venues; //en venue lo puedo llamar como quiera

}

//public ArrayList<Venue> getVenues(){
//return venues;
//}
//public void setVenues(ArrayList<Venue> venues) {
//this.venues=venues;
//}