package com.example.mobilestudiolaptop008.firebchat.Actitities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mobilestudiolaptop008.firebchat.Enums.FragmentTypes;
import com.example.mobilestudiolaptop008.firebchat.Fragments.LoginFragment;
import com.example.mobilestudiolaptop008.firebchat.Fragments.RegisterFragment;
import com.example.mobilestudiolaptop008.firebchat.R;

public class MainActivity extends AppCompatActivity implements LoginFragment.OnLoginClickListener,RegisterFragment.OnRegisterListener,LoginFragment.OnCompleteLoginListener {


    private FragmentTypes currentFragment;
    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        this.setTitle("");

        changeFragment(FragmentTypes.LOGIN,false);



    }


    private void changeFragment(FragmentTypes fragmentTypes, boolean addTobackStack)
    {

if (validateCurrentFragment(fragmentTypes))
    switch (fragmentTypes)
    {
        case LOGIN:
            LoginFragment loginFragment = LoginFragment.newInstance();
            loginFragment.setOnLoginClickListener(this);
            loginFragment.setOnCompleteLoginListener(this);
            fragment = loginFragment;
            break;
        case REGISTER:
            RegisterFragment registerFragment = RegisterFragment.newInstance();
            registerFragment.setOnRegisterListener(this);
            fragment = registerFragment;
            break;
    }


    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

fragmentTransaction.setCustomAnimations(R.anim.enter_from_right,R.anim.exit_to_left,R.anim.enter_from_left,R.anim.enter_from_right);


if (addTobackStack) fragmentTransaction.addToBackStack(fragmentTypes.name());
currentFragment = fragmentTypes;
fragmentTransaction.replace(R.id.fragmentcontainer,fragment).commit();




    }


    private boolean validateCurrentFragment(FragmentTypes selectedfragmentTypes)
    {

            return selectedfragmentTypes != currentFragment ? true : false;

    }

    @Override
    public void onRegisterButtonPress() {

        changeFragment(FragmentTypes.REGISTER,true);


    }

    @Override
    public void OnRegisterComplete() {


        openLogin();


    }

    @Override
    public void OnLoginComplete() {
        openLogin();
    }

    private void openLogin()
    {

        Intent intent = new Intent(this,DashBoardActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);

    }


}
