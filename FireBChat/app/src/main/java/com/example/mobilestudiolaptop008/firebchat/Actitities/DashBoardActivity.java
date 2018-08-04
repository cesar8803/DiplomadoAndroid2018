package com.example.mobilestudiolaptop008.firebchat.Actitities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mobilestudiolaptop008.firebchat.Enums.FragmentTypes;
import com.example.mobilestudiolaptop008.firebchat.Fragments.DashboardFragment;
import com.example.mobilestudiolaptop008.firebchat.Fragments.LoginFragment;
import com.example.mobilestudiolaptop008.firebchat.Fragments.RegisterFragment;
import com.example.mobilestudiolaptop008.firebchat.Fragments.chatRoomFragment;
import com.example.mobilestudiolaptop008.firebchat.Fragments.mapsFragment;
import com.example.mobilestudiolaptop008.firebchat.R;


public class DashBoardActivity extends AppCompatActivity {

    private FragmentTypes currentFragment;
    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);


        changeFragment(FragmentTypes.DASHBOARD,false);



    }

    private void changeFragment(FragmentTypes fragmentTypes, boolean addTobackStack)
    {

        if (validateCurrentFragment(fragmentTypes))
            switch (fragmentTypes)
            {
                case DASHBOARD:
                    DashboardFragment dashboardFragment = DashboardFragment.newInstance();
                   // dashboardFragment.setOnLoginClickListener(this);
                    //dashboardFragment.setOnCompleteLoginListener(this);
                    fragment = dashboardFragment;
                    break;
                case CHATROOM:
                    chatRoomFragment chatroomfragment = chatRoomFragment.newInstance();
                    //chatRoomFragment.setOnRegisterListener(this);
                    fragment = chatroomfragment;
                    break;
                case CHAT:
                    mapsFragment mapsfragment  = mapsFragment.newInstance();
                    //mapsfragment.setOnRegisterListener(this);
                    fragment = mapsfragment;
                    break;
            }


        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left,R.anim.enter_from_left,R.anim.enter_from_right);


        if (addTobackStack) fragmentTransaction.addToBackStack(fragmentTypes.name());
        currentFragment = fragmentTypes;
        fragmentTransaction.replace(R.id.fragdash,fragment).commit();




    }


    private boolean validateCurrentFragment(FragmentTypes selectedfragmentTypes)
    {

        return selectedfragmentTypes != currentFragment ? true : false;

    }


}
