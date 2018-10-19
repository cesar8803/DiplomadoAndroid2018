package com.example.adrian.pf.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.adrian.pf.Parseo.Venue;
import com.example.adrian.pf.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class MapsResultsFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap googleMap;
    private List<Venue> venues;
    public void setVenues(List<Venue> venues){
        this.venues=venues;
    }

    public MapsResultsFragment () {
        //Required empty public constructor
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        View view = inflater.inflate(R.layout.fragment_result_maps, container,false);
        MapFragment mapFragment = (MapFragment)getChildFragmentManager().findFragmentById(R.id.mimapa);
        mapFragment.getMapAsync(this);
        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap=googleMap;
        LatLng latLng = new LatLng(19.433997,-99.146006);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng,14f);
        this.googleMap.moveCamera(cameraUpdate);
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Adrian Almonaci");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
        this.googleMap.addMarker(markerOptions);
        macadores();
    }


    public void macadores(){
        for (Venue currrentVenue:venues) {
            Double lat = currrentVenue.getLocation().getLat();
            Double lng = currrentVenue.getLocation().getLng();
            LatLng latLng = new LatLng(lat, lng);
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(latLng);
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
            this.googleMap.addMarker(markerOptions);
            //CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 14f);
            //this.googleMap.moveCamera(cameraUpdate);
            }
        }

    }




