package com.example.adrian.conagua_almonaci.Fragmentos;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.adrian.conagua_almonaci.R;

public class PronosticoClima extends Fragment {


    public PronosticoClima (){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_pronostico_clima, container, false);

        return view;
    }

}
