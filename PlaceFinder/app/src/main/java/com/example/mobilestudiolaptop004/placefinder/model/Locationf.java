package com.example.mobilestudiolaptop004.placefinder.model;

import java.util.ArrayList;

public class Locationf {

    private double lat;
    private double lng;
    private int distance;

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public ArrayList<LabeledLatLngs> getLabeledLatLngs() {
        return labeledLatLngs;
    }

    public void setLabeledLatLngs(ArrayList<LabeledLatLngs> labeledLatLngs) {
        this.labeledLatLngs = labeledLatLngs;
    }

    private ArrayList<LabeledLatLngs> labeledLatLngs; //en venue lo puedo llamar como quiera


    


}
