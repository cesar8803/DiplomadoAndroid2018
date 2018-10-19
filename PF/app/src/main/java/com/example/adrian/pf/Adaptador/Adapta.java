package com.example.adrian.pf.Adaptador;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.adrian.pf.Parseo.Venue;
import com.example.adrian.pf.R;

import java.util.List;

public class Adapta extends RecyclerView.Adapter {

    public List<Venue> venues;

    public Adapta (List<Venue> venues){
        this.venues = venues;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.foursquare_result_item,null);
        MyViewHolder vh = new MyViewHolder(view);
        return vh;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView name;
        public TextView id;

        public MyViewHolder(View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.nombre);
            id=itemView.findViewById(R.id.iden);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((MyViewHolder)holder).name.setText(venues.get(position).getName());
        ((MyViewHolder)holder).id.setText(venues.get(position).getId());

    }


    @Override
    public int getItemCount() {
        return venues.size();
    }
}
