package mx.mobilestudio.placefinder.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import mx.mobilestudio.placefinder.R;
import mx.mobilestudio.placefinder.adapter.ListResultsAdapter;
import mx.mobilestudio.placefinder.model.Venue;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListResultsFragment extends Fragment {

    public RecyclerView recyclerView;

    public List<Venue> venues;

    private RecyclerView.LayoutManager layoutManager;



    public void setVenues(List<Venue> venues) {
        this.venues = venues;
    }

    public ListResultsFragment() {
        // Required empty public constructor
    }

  /*
  El parametro View (retorno) corresponde al UI de  el fragmeto
   */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View viewroot = inflater.inflate(R.layout.fragment_list_results, container, false);
        recyclerView  = viewroot.findViewById(R.id.recyviewfragment);

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        ListResultsAdapter listResultsAdapter = new ListResultsAdapter(venues);

        recyclerView.setAdapter(listResultsAdapter);

        return viewroot;
    }



}
