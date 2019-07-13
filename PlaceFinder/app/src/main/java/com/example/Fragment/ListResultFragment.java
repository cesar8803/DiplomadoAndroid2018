package com.example.Fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.Adapter.ListResultsAdapter;
import com.example.mobilestudiolaptop004.placefinder.R;
import com.example.mobilestudiolaptop004.placefinder.model.Venue;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListResultFragment extends Fragment {

    public RecyclerView recyclerView;
    public List<Venue> venues;
    public RecyclerView.LayoutManager layoutManager;

    public void setVenues(List<Venue>venues){
        this.venues=venues;
    }

    public ListResultFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viewroot= inflater.inflate(R.layout.fragment_list_result, container, false);
        recyclerView=viewroot.findViewById(R.id.recyviewfragment);
        layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        ListResultsAdapter listResultsAdapter = new ListResultsAdapter(venues);

        recyclerView.setAdapter(listResultsAdapter);
        return viewroot;
    }

}
