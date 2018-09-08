package com.example.Fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mobilestudiolaptop004.placefinder.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListResultFragment extends Fragment {


    public ListResultFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_result, container, false);
    }

}
