package mx.mobilestudio.placefinder.model;

/**
 * Created by cesar on 8/11/18.
 */

public class Location {
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
}
