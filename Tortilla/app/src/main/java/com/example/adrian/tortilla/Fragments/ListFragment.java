package com.example.adrian.tortilla.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.adrian.tortilla.Adaptador.TortillaAdaptador;
import com.example.adrian.tortilla.Parseo.Result;
import com.example.adrian.tortilla.R;

import java.util.List;


public class ListFragment extends Fragment {

    public RecyclerView recyclerView;
    public List<Result> results;
    public RecyclerView.LayoutManager layoutManager;

    public void setResults(List<Result>results){
        this.results=results;
    }

    public ListFragment(){
        //Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View viewroot= inflater.inflate(R.layout.fragment_list,container,false);
        recyclerView=viewroot.findViewById(R.id.recyclerview);
        layoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        TortillaAdaptador tortillaAdaptador = new TortillaAdaptador(results);
        recyclerView.setAdapter(tortillaAdaptador);
        return viewroot;
    }

}
