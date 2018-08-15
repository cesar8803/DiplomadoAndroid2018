package com.example.mobilestudiolaptop004.placefinder.model;

import java.util.ArrayList;


public class ResponseF {

    private ArrayList<Venue> venues; //en venue lo puedo llamar como quiera

    public ArrayList<Venue> getVenues() {
        return venues;
    }

    public void setVenues(ArrayList<Venue> venues) {
        this.venues = venues;
    }


    private ArrayList<Categories> categories;

    public ArrayList<Categories> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Categories> categories) {
        this.categories = categories;
    }

}

//public ArrayList<Venue> getVenues(){
//return venues;
//}
//public void setVenues(ArrayList<Venue> venues) {
//this.venues=venues;
//}