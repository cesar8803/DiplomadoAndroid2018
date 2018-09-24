package com.example.adrian.placefindermaps.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.adrian.placefindermaps.Adapter.ListResultAdapter;
import com.example.adrian.placefindermaps.R;
import com.example.adrian.placefindermaps.model.Venue;

import java.util.List;



public class ListResultFragment extends Fragment {

    public RecyclerView recyclerView;
    public List<Venue>venues;
    public RecyclerView.LayoutManager layoutManager;

    public void setVenues(List<Venue> venues) {
        this.venues = venues;
    }

    public ListResultFragment(){
        //Required empty public constructor
    }

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        View viewroot = inflater.inflate(R.layout.fragment_list_result,container,false);
        recyclerView=viewroot.findViewById(R.id.recyviewfragment);

        layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        ListResultAdapter listResultAdapter=new ListResultAdapter(venues);
        recyclerView.setAdapter(listResultAdapter);
        return viewroot;
    }
}
