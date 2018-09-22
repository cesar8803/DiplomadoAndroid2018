package com.example.Fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mobilestudiolaptop004.placefinder.R;
import com.example.mobilestudiolaptop004.placefinder.model.Venue;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MapsResultFragment extends Fragment {

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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_maps_result, container, false);
    }

}
