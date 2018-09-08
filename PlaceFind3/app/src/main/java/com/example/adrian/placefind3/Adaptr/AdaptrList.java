package com.example.adrian.placefind3.Adaptr;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.adrian.placefind3.Mode.Liveapi;
import com.example.adrian.placefind3.Mode.Store;
import com.example.adrian.placefind3.R;

import java.util.List;

public class AdaptrList extends RecyclerView.Adapter {
    public List<Store> stores;

    public AdaptrList(List<Store>Stores){
        this.stores=stores;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adaptrlist,null);
        MyViewHolder vh = new MyViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((MyViewHolder)holder).tit.setText(stores.get(position).getTitulo());
        //((MyViewHolder)holder).dir.setText(stores.get(position).getDireccion());
        //((MyViewHolder)holder).ubi.setText(stores.get(position).getCcubicacion());
    }

    @Override
    public int getItemCount() {
        return stores.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView tit;
        //public TextView dir;
        //public TextView ubi;

        public MyViewHolder(View itemView) {
            super(itemView);
            tit=itemView.findViewById(R.id.tit);
            //dir=itemView.findViewById(R.id.dir);
            //ubi=itemView.findViewById(R.id.ubi);
        }
    }

}
