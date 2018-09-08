package com.example.adrian.practicafinaldatafinder.Adptr;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adrian.practicafinaldatafinder.Mo.Venue;
import com.example.adrian.practicafinaldatafinder.R;

import java.util.List;

public class Adap extends RecyclerView.Adapter {
    public List<Venue> venues;

    public Adap(List<Venue> venues){
        this.venues=venues;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_v,null);
        MyViewHolder vh = new MyViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            ((MyViewHolder) holder).t1.setText(venues.get(position).getLocation().getCrossStreet());
    }

    @Override
    public int getItemCount() {
        return venues.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView t1;
        public MyViewHolder(View itemView){
            super(itemView);
            t1=itemView.findViewById(R.id.t1);
        }
    }
}
