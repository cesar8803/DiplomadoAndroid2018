package com.example.adrian.tortilla.Adaptador;

import android.support.annotation.NonNull;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adrian.tortilla.Parseo.Result;
import com.example.adrian.tortilla.R;

import java.util.List;

import static com.example.adrian.tortilla.MainActivity.FragmentoMapa;
import static com.example.adrian.tortilla.MainActivity.TodasTortillas;
import static com.example.adrian.tortilla.MainActivity.latitudTortilla;
import static com.example.adrian.tortilla.MainActivity.longitudTortilla;
import static com.example.adrian.tortilla.MainActivity.nombresote;

public class TortillaAdaptador extends RecyclerView.Adapter {
    public List<Result> results;
    public TortillaAdaptador(List<Result>results){
        this.results=results;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tortillacardview,null);
        MyViewHolder vh = new MyViewHolder(view);
        return vh;
    }



    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ((MyViewHolder)holder).nombretortilla.setText(results.get(position).getNombreComercial());
        ((MyViewHolder)holder).edotortilla.setText(results.get(position).getEstado());
        ((MyViewHolder)holder).munitortilla.setText(results.get(position).getMunicipio());
        ((MyViewHolder)holder).precitortilla.setText("$" + results.get(position).getPrecio().toString() + " Kg");


        //holder.itemView.setOnClickListener(new View.OnClickListener() {
            //@Override
            //public void onClick(View view) {
                //if (view.getId()!=R.id.Blista && view.getId()!=R.id.Bmapa){
                    //latitudTortilla=results.get(position).getLatitud();
                    //longitudTortilla=results.get(position).getLongitud();
                    //FragmentoMapa ();
                //}
            //}
        //});
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView nombretortilla;
        public TextView edotortilla;
        public TextView munitortilla;
        public TextView precitortilla;

        public MyViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            nombretortilla=itemView.findViewById(R.id.nombreCo);
            edotortilla=itemView.findViewById(R.id.edo);
            munitortilla=itemView.findViewById(R.id.muni);
            precitortilla=itemView.findViewById(R.id.preci);
        }

        @Override
        public void onClick(View itemview) {
            //if (itemview.getId()!=R.id.Blista && TodasTortillas==false){
                latitudTortilla=results.get(getAdapterPosition()).getLatitud();
                longitudTortilla=results.get(getAdapterPosition()).getLongitud();
                nombresote=results.get(getAdapterPosition()).getNombreComercial();
                FragmentoMapa ();
               // }else if (TodasTortillas==true){
               // FragmentoMapa ();


                //}
        }
    }
}
