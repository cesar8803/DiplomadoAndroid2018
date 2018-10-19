package com.example.adrian.tortilla.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.adrian.tortilla.Parseo.Result;
import com.example.adrian.tortilla.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


import java.util.List;

import static com.example.adrian.tortilla.MainActivity.TodasTortillas;
import static com.example.adrian.tortilla.MainActivity.latitudTortilla;
import static com.example.adrian.tortilla.MainActivity.longitudTortilla;
import static com.example.adrian.tortilla.MainActivity.nombresote;

public class MapFragmentTortilla extends Fragment implements OnMapReadyCallback {
    private GoogleMap googleMap;
    private List<Result>results;
    public void setResults(List<Result>results){
        this.results=results;
    }


    public MapFragmentTortilla(){
        //Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_map,container,false);
        MapFragment mapFragment = (MapFragment)getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
                return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;

        if (TodasTortillas == false) {

            LatLng latLng = new LatLng(latitudTortilla, longitudTortilla);
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 14f);
            this.googleMap.moveCamera(cameraUpdate);
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(latLng);
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker());
            markerOptions.title(nombresote);
            this.googleMap.addMarker(markerOptions);
        } else if (TodasTortillas==true){
            for(Result currentResult : results){
                latitudTortilla=currentResult.getLatitud();
                longitudTortilla=currentResult.getLongitud();
                nombresote=currentResult.getNombreComercial();
                LatLng latLng = new LatLng(latitudTortilla,longitudTortilla);
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLng);
                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET));
                markerOptions.title(nombresote);
                this.googleMap.addMarker(markerOptions);
                CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng,14f);
                this.googleMap.moveCamera(cameraUpdate);
            }
        }
    }
}
