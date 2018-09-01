package com.example.mobilestudiolaptop003.storelocator.Adaptero;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.mobilestudiolaptop003.storelocator.modelo.Store;
import com.example.mobilestudiolaptop004.placefinder.R;

import java.util.List;

public class ResultList extends RecyclerView.Adapter {
    public List<Store> stores;

    public ResultList(List<Store> stores) {
        this.stores = stores;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.horynom,null);
        MyViewHolder vh = new MyViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position){
        ((MyViewHolder)holder).nombre.setText(stores.get(position).getTitulo());
        ((MyViewHolder)holder).horario.setText(stores.get(position).getHorario());

    }

    @Override
    public int getItemCount() {return stores.size();}

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView nombre;
        public TextView horario;

        public MyViewHolder(View itemView){
            super(itemView);
            nombre=itemView.findViewById(R.id.nombre);
            horario=itemView.findViewById(R.id.horario);
        }
    }
}