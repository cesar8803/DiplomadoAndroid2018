package mx.mobilestudio.placefinder.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

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
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
