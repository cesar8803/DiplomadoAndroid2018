package mx.mobilestudio.placefinder.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import mx.mobilestudio.placefinder.R;
import mx.mobilestudio.placefinder.model.Venue;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapsResultsFragment extends Fragment implements OnMapReadyCallback {

    private List<Venue> venues;
    private GoogleMap googleMap;


    public void setVenues(List<Venue> venues) {
        this.venues = venues;
    }

    public MapsResultsFragment() {
        // Required empty public constructor

        //Todo implementar mapas
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_maps_results, container, false);

        MapFragment mapFragment = (MapFragment) getChildFragmentManager().findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);

        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        // El mapa se inicializo correctamente y es visible para el usuario
        this.googleMap = googleMap;

        //CameraUpdate es una actualización al enfoque de la parte visible del mapa

        LatLng latLng = new LatLng(19.395209,-99.1544203);


        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 14f);

        this.googleMap.moveCamera(cameraUpdate);

        // Agregamos un marcador al mapa

        MarkerOptions markerOptions = new MarkerOptions();

        markerOptions.position(latLng);

        markerOptions.title("MobileStudio");

        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));

        this.googleMap.addMarker(markerOptions);


        paintFourSquareMarketsinMap();
    }



    public void paintFourSquareMarketsinMap(){

        for(Venue currentVenue : venues){

            Double lat = currentVenue.getLocation().getLat();
            Double lon = currentVenue.getLocation().getLng();
            String name = currentVenue.getName();

            LatLng latLng = new LatLng(lat,lon);


            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(latLng);
            markerOptions.title(name);
            markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
            this.googleMap.addMarker(markerOptions);


        }

    }



}
