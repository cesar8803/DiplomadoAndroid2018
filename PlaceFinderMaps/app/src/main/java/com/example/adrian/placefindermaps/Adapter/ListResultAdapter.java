package com.example.adrian.placefindermaps.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.adrian.placefindermaps.R;
import com.example.adrian.placefindermaps.model.Venue;

import java.util.List;


public class ListResultAdapter extends RecyclerView.Adapter {
    public List<Venue>venues;
    public ListResultAdapter(List<Venue> venues){this.venues=venues;}

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.foursquare_result_item,null);
        MyViewHolder vh = new MyViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((MyViewHolder)holder).name.setText(venues.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return venues.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
       public TextView name;

       public MyViewHolder(View itemView){
           super (itemView);
           name=itemView.findViewById(R.id.name);
       }
    }
}
