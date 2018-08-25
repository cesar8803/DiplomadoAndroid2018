package mx.mobilestudio.placefinder.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import mx.mobilestudio.placefinder.R;
import mx.mobilestudio.placefinder.model.Venue;

/**
 * Created by cesar on 8/25/18.
 */

public class ListResultsAdapter extends RecyclerView.Adapter{

    public List<Venue> venues;

    public ListResultsAdapter() {

    }

    public ListResultsAdapter(List<Venue> venues) {
        this.venues = venues;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        //DEcidimos que template (xml) utilizaremos para la lista
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.foursquare_result_item,null);
        MyViewHolder vh = new MyViewHolder(view);

        return  vh;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ((MyViewHolder) holder).name.setText(venues.get(position).getName());
        ((MyViewHolder) holder).distance.setText(venues.get(position).getLocation().getDistance().toString());

    }

    @Override
    public int getItemCount() {
        return venues.size();
    }




    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public  TextView distance;


        public MyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            distance = itemView.findViewById(R.id.distancia);
        }


    }


}
