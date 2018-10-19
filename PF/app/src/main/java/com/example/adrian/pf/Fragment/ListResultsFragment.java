package com.example.adrian.pf.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.adrian.pf.Adaptador.Adapta;
import com.example.adrian.pf.Parseo.Venue;
import com.example.adrian.pf.R;

import java.util.List;

public class ListResultsFragment extends Fragment {

    public RecyclerView recyclerView;
    public List<Venue> venues;
    public RecyclerView.LayoutManager layoutManager;

    public void setVenues(List<Venue>venues){
        this.venues=venues;
    }

    public ListResultsFragment(){
        //Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View viewroot=inflater.inflate(R.layout.fragment_list_result, container, false);
        recyclerView=viewroot.findViewById(R.id.mirecyclerview);
        layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        Adapta adapta = new Adapta(venues);
        recyclerView.setAdapter(adapta);
        return viewroot;
    }
}
