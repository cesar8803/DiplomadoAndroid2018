package com.example.Adapter;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.mobilestudiolaptop004.placefinder.R;
import com.example.mobilestudiolaptop004.placefinder.model.Venue;

import java.util.List;

public class ListResultsAdapter extends RecyclerView.Adapter {

    public List<Venue> venues;


    public ListResultsAdapter(List<Venue> venues) {
        this.venues = venues;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Decimos que template (xml) utilizaremos para la lista en este caso fue foursquare_result_item
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.foursquare_result_item,null);
        MyViewHolder vh = new MyViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((MyViewHolder)holder).name.setText(venues.get(position).getName());
        ((MyViewHolder)holder).distance.setText((venues.get(position).getLocation().getDistance().toString()));
        ((MyViewHolder)holder).city.setText(venues.get(position).getLocation().getCity());
        ((MyViewHolder)holder).numerito.setText(String.valueOf(position + 1));
    }

    @Override
    public int getItemCount() {
        return venues.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView distance;
        public TextView city;
        public TextView numerito;


        public MyViewHolder(View itemView){
            super (itemView);
            name=itemView.findViewById(R.id.name);
            distance=itemView.findViewById(R.id.distancia);
            city=itemView.findViewById(R.id.ciudad);
            numerito=itemView.findViewById(R.id.numerito);
        }

    }
}
