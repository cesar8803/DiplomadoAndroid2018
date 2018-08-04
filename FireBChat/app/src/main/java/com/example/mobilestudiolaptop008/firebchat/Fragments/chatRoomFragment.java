package com.example.mobilestudiolaptop008.firebchat.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mobilestudiolaptop008.firebchat.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class chatRoomFragment extends Fragment {


    public chatRoomFragment()
    {}

    public static chatRoomFragment newInstance()
    {
        chatRoomFragment fragment = new chatRoomFragment();
        return fragment;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat_room, container, false);
    }

}
