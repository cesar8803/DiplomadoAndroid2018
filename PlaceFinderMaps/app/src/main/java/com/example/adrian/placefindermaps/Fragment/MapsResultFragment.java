package com.example.adrian.placefindermaps.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.adrian.placefindermaps.R;
import com.example.adrian.placefindermaps.model.Venue;

import java.util.List;

public class MapsResultFragment extends Fragment{
    private List<Venue> venues;

    public void setVenues(List<Venue> venues) {
        this.venues = venues;
    }

    public MapsResultFragment(){
        //Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_maps_result, container, false);
    }

}
