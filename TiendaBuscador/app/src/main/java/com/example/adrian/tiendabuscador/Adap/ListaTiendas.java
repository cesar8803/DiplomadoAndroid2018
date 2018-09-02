package com.example.adrian.tiendabuscador.Adap;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.adrian.tiendabuscador.Mdl.Store;
import com.example.adrian.tiendabuscador.R;

import java.util.List;

public class ListaTiendas extends RecyclerView.Adapter{
    public List<Store> stores;

    public ListaTiendas(List<Store>stores){
        this.stores=stores;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.displaylista,null);
        MyViewHolder vh = new MyViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((MyViewHolder)holder).nombr.setText(stores.get(position).getTitulo());
        ((MyViewHolder)holder).dir.setText(stores.get(position).getDireccion());
        ((MyViewHolder)holder).dir.setText(stores.get(position).getCcubicacion());
    }

    @Override
    public int getItemCount() {
        return stores.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView nombr;
        public TextView dir;
        public TextView ubi;

        public MyViewHolder(View itemView) {
            super(itemView);
            nombr = itemView.findViewById(R.id.nombr);
            dir = itemView.findViewById(R.id.dir);
            ubi=itemView.findViewById(R.id.ubi);
        }
    }
}
