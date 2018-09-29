package com.example.Fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mobilestudiolaptop004.placefinder.R;
import com.example.mobilestudiolaptop004.placefinder.model.Venue;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public  class MapsResultFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap googleMap;
    private List<Venue> venues;
    public void setVenues(List<Venue> venues) {
        this.venues = venues;
    }


    public MapsResultFragment() {
        // Required empty public constructor


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment, la linea de abajo genera la ropita
        View view= inflater.inflate(R.layout.fragment_maps_result, container, false);
        MapFragment mapFragment= (MapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        //el mapa se inicializo correctamentr y es visible para el usuario
        this.googleMap=googleMap;
        //CameraUpadte es una actualizacion al enfoque de la parte visible del mapa
        LatLng latLng= new LatLng(19.395209,  -99.1544203);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng,14f);

        this.googleMap.moveCamera(cameraUpdate);

        //Agregamos marcador al mapa

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        markerOptions.title("Mobile Studio");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
        this.googleMap.addMarker(markerOptions);
        paintFourSquareMarkersinMap();
    }

    public void paintFourSquareMarkersinMap(){
        for(Venue currentVenue : venues){

            Double lat=currentVenue.getLocation().getLat();
            Double longg=currentVenue.getLocation().getLng();
            String name = currentVenue.getName();
            LatLng latLng= new LatLng(lat,  longg);
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(latLng);
            markerOptions.title(name);
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE));
            this.googleMap.addMarker(markerOptions);
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng,14f);
            this.googleMap.moveCamera(cameraUpdate);
        }

    }

    //ariba agregue la palabra "this" para hacer referencia a mi clase ya que esta es mi clase


}
